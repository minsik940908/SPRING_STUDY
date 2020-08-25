package com.spring.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.spring.dao.MemberDAO;
import com.spring.dto.MemberDTO;

@Service
public class MemberService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired MemberDAO dao;
	
	public ModelAndView join(HashMap<String, String> params) {
		
		
		ModelAndView mav = new ModelAndView(); //model + view
		
	    MemberDTO dto = new MemberDTO();
	    
	    dto.setId(params.get("user_id"));
	    dto.setPw(params.get("user_pw"));
	    dto.setAge(Integer.parseInt(params.get("user_age")));
	    dto.setName(params.get("user_name"));
	    dto.setEmail(params.get("user_email"));
	    dto.setGender(params.get("user_gender"));
		
	    String msg = "가입에 실패 했습니다.";
	    String page = "writeForm";
		if(dao.join(dto)>0) {
			msg = "가입에 성공 했습니다.";
			page="result";
		}
		
		mav.addObject("msg",msg);
		mav.setViewName(page); //성공 실패에 따라 페이지가 달라진다.
		return mav;
	}

	public ModelAndView list(HashMap<String, String> params) {
		
		ArrayList<MemberDTO> list = dao.list(params);
		int total = list.size();
		logger.info("list size : " + total);
		System.out.println(list);
		String msg = params.get("keyword") + "에 대한 검색 결과가 없습니다.";
		if(total > 0) {
			msg = params.get("keyword") + "에 대한 검색 결과가 "+total+"건 있습니다";
		}
	    ModelAndView mav = new ModelAndView();
	    mav.addObject("msg", msg);
	    mav.addObject("list", list);
	    mav.setViewName("list");
		
		return mav;
	}

	public ModelAndView update(HashMap<String, String> params) {
		int success = dao.update(params);
		System.out.println(params);
		String msg = params.get("id") + "의 정보 수정에 실패 했습니다";
		
		if(success > 0) {
			msg = params.get("id") + "의 정보 수정에 성공 했습니다";
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", msg);
		mav.setViewName("result");
		return mav;
	}

	public ModelAndView multi(String[] userName) {
		
		ArrayList<String> params = new ArrayList<String>();
		for(String name : userName) {
			params.add(name);
		}
		ArrayList<MemberDTO> list = dao.multi(params);
		logger.info("list : " + list);
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("list");
		return mav;
		
	}

	
}
