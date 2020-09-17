package kr.co.goodee.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.co.goodee.dao.BoardDAO;
import kr.co.goodee.dto.BoardDTO;

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

}
