package kr.co.goodee.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.goodee.service.InterService;

@Controller
public class HomeController {
	
	@Autowired InterService service;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		logger.info("로그인 페이지 이동 요청");
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam String id, @RequestParam String pw, HttpSession session, Model model) {
		logger.info("로그인 페이지 요청");
		logger.info("id와 pw : " + id +" / "+ pw);
		String userId = service.login(id,pw);
		logger.info("로그인 한 ID : " + userId);
		
		if(userId == null) {
			return "redirect:/";
		}else {
			session.setAttribute("loginId", userId);
			
		}
		return "redirect:/list";
	}
	
	//list 요청을 하면 list.jsp로 이동
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		logger.info("리스트 요청");
		return "list";
	}
	
	//detail 요청을 하면 detail.jsp로 이동
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail() {
		logger.info("상세보기 요청");
		return "detail";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		logger.info("로그아웃 요청");
		session.removeAttribute("loginId");
		return "redirect:/";
	}
	
}
