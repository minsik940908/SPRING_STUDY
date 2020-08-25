package com.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.dto.BbsDTO;
import com.spring.service.HomeService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired HomeService service;//이렇게 사용하면 객체화가 필요 없다.
	//이렇게 사용하면 사용 클래스 명을 따로 적어줄 필요가 없다.
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		logger.info("리스트 요청");
		ArrayList<BbsDTO> list = service.list();
		model.addAttribute("list",list);
		return "list";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(Model model, @RequestParam String idx) {
		logger.info("delete 요청 : " + idx);
		service.delete(idx);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(Model model, @RequestParam String idx) {
		logger.info("detail 요청 : " + idx);
		BbsDTO dto = service.detail(idx);
		model.addAttribute("info", dto);
		return "detail";
	}
	
	@RequestMapping(value = "/writeForm", method = RequestMethod.GET)
	public String wirteForm(Model model) {
		return "writeForm";
	}
	
	//보내는 method와 받는 method가 맞지 않으면 405에러
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(Model model, @RequestParam HashMap<String, String> params) {
		logger.info("params : {}" + params);
		service.write(params);
		return "redirect:/";
	}
	
}
