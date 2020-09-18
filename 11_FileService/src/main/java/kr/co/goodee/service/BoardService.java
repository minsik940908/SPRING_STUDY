package kr.co.goodee.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.co.goodee.dao.BoardDAO;
import kr.co.goodee.dto.BoardDTO;
import kr.co.goodee.dto.FileDTO;

@Service
public class BoardService {

	@Autowired BoardDAO dao;
	
	@Value("#{config['Globals.root']}") String root;
	private String fullpath = null;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void list(Model model) {
		logger.info("리스트 서비스 요청");
		ArrayList<BoardDTO> list = dao.list();
		logger.info("리스트 갯수 : "+ list.size());
		model.addAttribute("list",list);
	}

	public ModelAndView upload(MultipartFile file, HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		fullpath = root+"upload/";
		//파일명을 추출
		String fileName = file.getOriginalFilename();
		logger.info("원래 파일 이름 : " + fileName);
		//새로운 파일명 만들기
		String newFileName = System.currentTimeMillis()+fileName.substring(fileName.lastIndexOf("."));
		//파일명 바꾸기+저장(java.nio <- java 7부터 적용)
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(fullpath+newFileName);
			Files.write(path, bytes);
			//업로드 성공한 파일을 session.에 저장
			HashMap<String, String> fileList = (HashMap<String, String>) session.getAttribute("fileList");
			fileList.put(newFileName, fileName);
			logger.info("업로드 한 파일 갯수 : " + fileList.size());
			session.setAttribute("fileList", fileList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//DB에 저장하기(X) <- 파일 업로드 과정에서 수시로 올리고 지우고 할거기 때문에
		mav.addObject("path", "photo/"+newFileName);//업로드 성공한 파일 경로 
		mav.setViewName("uploadForm");
		
		return mav;
	}

	public HashMap<String, Object> fileDelete(String fileName, HttpSession session) {
		logger.info("파일 삭제 서비스 요청");
		HashMap<String, Object> result = new HashMap<String, Object>();
		int success = 0;
		
		//1. session에서 fileList 가져오기
		HashMap<String, String> fileList = (HashMap<String, String>) session.getAttribute("fileList");
		//2. 실제 파일 삭제 하기
		String delFileName = root+"/upload/"+fileName;
		logger.info("지울 파일 경로 : " + delFileName);
		File file = new File(delFileName);
		if(file.exists()) {
			if(file.delete()) {
				success = 1;
			}
		}else {
			logger.info("이미 삭제된 상황");
			success = 1;
		}
		//3. fileList에서 삭제한 파일명 지우기
		if(fileList.get(fileName) != null) {
			fileList.remove(fileName);
			logger.info("삭제 후 남은 파일 갯수 : " + fileList.size());
		}
		//4. session에 fileList 넣기
		session.setAttribute("fileList", fileList);
		
		result.put("success", success);
		return result;
	}
	
	@Transactional
	public ModelAndView write(HashMap<String, String> params, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String page = "redirect:/writeForm";
		BoardDTO bean = new BoardDTO();//빈(dto)을 사용해야만 방금 넣은 값의 키를 알 수 있다.
		
		//1.bbs 테이블에 작성자, 제목, 내용 넣기
		bean.setSubject(params.get("subject"));
		bean.setContent(params.get("content"));
		bean.setUser_name(params.get("user_name"));
		
		HashMap<String, Object> fileList = (HashMap<String, Object>) session.getAttribute("fileList");
		
		if(dao.write(bean)==1) {//generateKey를 사용하였기 때문에 쿼리 실행 완료 후 bean에 idx값이 생긴다.
			//2.photo 테이블에 파일이 소속된 idx, 원본 파일명, 새 파일명 넣기
			int size = fileList.size();
			logger.info("저장할 파일 수 : " + size);
			int idx = bean.getIdx();
			if(size >0) {//업로드한 파일이 있다면
				//idx, oriFileName, newFileName
				logger.info(idx + " 번 게시물에 소속된 파일 등록");
				for(String key : fileList.keySet()) {
					//idx oriFileName, newFileName
					dao.wrtieFile(idx, (String)fileList.get(key), key);
				}
			}	
			page="redirect:/detail?idx="+idx;//등록된 상ㅅ 페이지로 이동(detail이 만들어 지기 전 까지는 404)
			
		}else {
			//session의 fileList에 저장된 모든 파일을 삭제
			for(String fileName : fileList.keySet()) {
				File file = new File(root+"upload/"+fileName);
				boolean success = file.delete();
				logger.info(fileName+"삭제 결과 : " + success);
			}
		}
		//이후 fileList 내용도 삭제
		session.removeAttribute("fileList");
		mav.setViewName(page);
		return mav;
	}

	//상세보기
	public ModelAndView detail(String idx) {
		ModelAndView mav = new ModelAndView();
		logger.info("상세보기 서비스 요청");
		BoardDTO dto = dao.detail(idx);
		ArrayList<FileDTO> fileList = dao.fileList(idx);
		logger.info("첨부된 파일 : " + fileList.size());
		mav.addObject("fileList", fileList);
		mav.addObject("info",dto);
		mav.setViewName("detail");
		return mav;
	}

	public void download(String orifilename, String newfilename, HttpServletResponse resp) {
		logger.info("다운로드 서비스 요청");
		//1. 파일 객체 생성
		try {
			Path path = Paths.get(root+"upload/"+newfilename);
			byte[] file = Files.readAllBytes(path);//2. 파일을 읽어오기
			//3. response에 보낼 파일에 대한 예고
			orifilename = URLEncoder.encode(orifilename, "UTF-8");//파일명 한글 깨짐 방지
			resp.setContentType("application/octet-stream");
			resp.setHeader("content-Disposition", "attachment;filename=\""+orifilename+"\"");
			//4. 보내기
			OutputStream os = resp.getOutputStream();
			BufferedOutputStream buf = new BufferedOutputStream(os);
			buf.write(file);
			
			//5. 자원 반납
			buf.close();
			os.flush();
			os.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
