package kr.co.goodee.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
}
