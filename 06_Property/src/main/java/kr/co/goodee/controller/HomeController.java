package kr.co.goodee.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.goodee.service.LoginService;

@Controller
public class HomeController {
	
	@Autowired LoginService service;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		
		return "login";
	}
	
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public ModelAndView login(HttpSession session, @RequestParam HashMap<String, String> params) {
//		logger.info("params : " + params);
//		String page="redirect:/";
//		String msg = "로그인에 실패 했습니다";
//		
//		if(service.login(params) >0) {
//			page = "result";
//			msg = "로그인에 성공 했습니다";
//		}
//		
//		ModelAndView mav = new ModelAndView();
//		//redirect시 보낸 데이터는 get 방식의 파라메터로 전달이 된다.
//		mav.addObject("msg", msg);
//		mav.setViewName(page);
//		return mav;
//	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpSession session, RedirectAttributes rAttr, @RequestParam HashMap<String, String> params) {
		logger.info("params : " + params);
		String page="redirect:/";
		String msg = "로그인에 실패 했습니다";
		
		if(service.login(params) >0) {
			page = "redirect:/result";
			msg = "로그인에 성공 했습니다";
		}
		
		//RedirectAttributes에 넣으면 redirect 기존 model에 값을 넣은 것 처럼 사용 가능
		rAttr.addFlashAttribute("msg", msg);
		return page;
	}
	
	@RequestMapping(value = "/result", method = RequestMethod.GET)
	public String result() {
		return "result";
	}
	
}
