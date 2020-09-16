package kr.co.goodee.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.goodee.dto.UserInfo;

@RestController//@ResponseBody를 안 써도 된다.(Spring 4.2 이하에서는 못쓴다.)
@RequestMapping(value="/rest")//rest로 시작되는 놈은 이 컨트롤러로 와라
public class AjaxController {
	
	//ResponseEntity는 사용하기 불편할 수 있지만 서버 상태를 보여 줄 수 있다.
	@RequestMapping(value="/list")
	public ResponseEntity<ArrayList<String>> list(){
		ArrayList<String > list = new ArrayList<String>();
		list.add("하나");
		list.add("둘");
		list.add("셋");
		
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/map")
	public HashMap<String, String> map(){
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("일", "one");
		map.put("이", "two");
		map.put("삼", "three");
		
		return map;
	}
	
	@RequestMapping(value="/object")
	public UserInfo object(){
		UserInfo info = new UserInfo();
		info.setAge(11);
		info.setGender("남");
		info.setId("min");
		info.setName("minsik");
		info.setSuccess(false);
		
		return info;
	}
}
