package kr.co.goodee.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.co.goodee.service.BoardService;


@Controller
public class HomeController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired BoardService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {

		logger.info("리스트 요청 컨트롤러");
		service.list(model);
		return "list";
	}
	
	@RequestMapping(value = "/writeForm", method = RequestMethod.GET)
	public String writeForm(Model model, HttpSession session) {
		logger.info("글쓰기 페이지 이동");
		//fileList를 session에 담아준다.
		HashMap<String, String> fileList = new HashMap<String, String>();
		session.setAttribute("fileList", fileList);
		
		return "writeForm";
	}
	
	@RequestMapping(value = "/uploadForm", method = RequestMethod.GET)
	public String uploadForm(Model model) {
		logger.info("uploadForm 페이지 이동");
		return "uploadForm";
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ModelAndView upload(MultipartFile file, HttpSession session) {
		logger.info("upload 요청");
		return service.upload(file, session);
	}
	
	@RequestMapping(value = "/fileDelete", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Object> fileDelete(@RequestParam String fileName, HttpSession session) {
		logger.info("파일 삭제 요청");
		logger.info("파일 이름 : " + fileName);
		return service.fileDelete(fileName, session);
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public ModelAndView write(@RequestParam HashMap<String, String> params, HttpSession session) {
		logger.info("글쓰기 요청");
		logger.info("params : " + params);
		return service.write(params, session);
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(@RequestParam String idx) {
		logger.info("상세보기 요청 : " + idx);
		return service.detail(idx);
	}
	
	@RequestMapping(value = "/download/{orifilename}/{newfilename}", method = RequestMethod.GET)
	public void download(HttpServletResponse resp, @PathVariable String orifilename, @PathVariable String newfilename) {
		logger.info("다운로드 요청 : " + orifilename + "/" + newfilename);
		
		service.download(orifilename, newfilename, resp);
	}
}
