package kr.co.goodee.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.goodee.dto.UserInfo;

@Controller
public class HomeController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody ArrayList<String> list() {
		
		//@ResponseBody를 사용하면 response에 하던 기존 작업이 사라진다.
		//@ResponseBody를 사용하면 자바의 자료구조를 클라이언트로 내보낼 수 있다.
		logger.info("리스트 요청");
		ArrayList<String> list = new ArrayList<String>();
		list.add("하나");
		list.add("둘");
		list.add("셋");
		return list;
	}
	
	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, String> map() {
		
		logger.info("맵 요청");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("일", "one");
		map.put("이", "two");
		map.put("삼", "three");
		return map;
	}

	@RequestMapping(value = "/object", method = RequestMethod.GET)
	public @ResponseBody UserInfo object() {
		logger.info("object 요청");
		UserInfo info = new UserInfo();
		info.setAge(30);
		info.setGender("남자");
		info.setId("admin");
		info.setName("minsik");
		info.setSuccess(true);
		
		return info;
	}
	
}
