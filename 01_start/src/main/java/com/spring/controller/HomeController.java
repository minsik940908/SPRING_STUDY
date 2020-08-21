package com.spring.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller//이 클래스는 컨트롤러다
public class HomeController {
	
	//로그찍는 설정
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	//"/"로 들어왔을 경우 home이라는 메서드 실행
	//GET 방식만을 받는다.
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {//model은 request의 attribute 역할
		//logger.info("Welcome home! The client locale is {}.");
		//로그설정에서 특정 등급 이하의 로그는 찍지 않도록 설정 가능
		logger.info("메인 페이지 접속 시도");//TRACE < DEBUG < INFO < WARN < ERROR < FATAL 으로 로그 등급을 둘 수 있다.
		
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate ); //request.setAttribute("serverTime",formattedDate);
		
		return "home";//requestDispatcher가 필요 없다.
	}
	
	@RequestMapping(value = "/member")//모든 형태의 요청을 다 받는다.(GET,POST,PUT,DELETE,...) -> 보안상 좋지않아 권장X
	public ModelAndView member() {
		//modelAndView = model + view
		ModelAndView mav = new ModelAndView();
		logger.info("Welcome home! The client locale is {}.");

		mav.addObject("serverTime","member");
		mav.setViewName("home");
		//model and view를 사용하면 service에서도 값을 보내거나 갈 곳을 지정 할 수 있다.
		return mav;
	}
	
}


