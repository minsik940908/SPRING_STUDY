package com.spring.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.service.CrudService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired CrudService service;//객체 하나만 생성해서 하나로 공유

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home( Model model) {
		logger.info("리스트 보기 요청");
		service.listView(model);
		return "list";
	}
	
	@RequestMapping(value="/detail", method = RequestMethod.GET)
	public String detail(Model model, HttpServletRequest req) {
		logger.info("상세보기 요청");
		model.addAttribute("idx", req.getParameter("idx"));
		service.contentView(model);
		return "detail";
	}
	
	@RequestMapping(value="/writeForm", method = RequestMethod.GET)
	public String writeForm() {
		return "writeForm";
	}
	
	@RequestMapping(value="/write", method = RequestMethod.POST)
	public String write(@RequestParam HashMap<String, String> params) {
		System.out.println(params);
		service.write(params);
		return "redirect:/";
	}
	
	
}
