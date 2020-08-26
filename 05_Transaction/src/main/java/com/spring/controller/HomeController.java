package com.spring.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.dto.BoardDTO;
import com.spring.service.BoardService;


@Controller
public class HomeController {
	
	@Autowired BoardService service;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam String id, @RequestParam String pw, HttpSession session) {
		logger.info(id + " : " + pw);
		int cnt = service.login(id,pw);
		ModelAndView mav = new ModelAndView();
		String page = "redirect:/";
		if(cnt > 0) {
			page = "redirect:/list";
			session.setAttribute("id", id);
		}
		mav.setViewName(page);
		return mav;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(HttpSession session) {
		logger.info("게시판 리스트 호출");
		ModelAndView mav = null;
		String id = (String) session.getAttribute("id");
		if(id != null) {
			mav = service.list();
		}else {
			mav = new ModelAndView();
			mav.addObject("msg", "로그인 후 사용하세요");
			mav.setViewName("login");
		}
		return mav;
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(@RequestParam int idx) {
		logger.info("상세보기 리스트 호출");
		logger.info("상세보기를 위한 idx : " + idx);
		return service.detail(idx);
	}
	
	@RequestMapping(value = "/writeForm", method = RequestMethod.GET)
	public String writeForm() {
		
		return "writeForm";
	}
	
	@RequestMapping(value = "/back", method = RequestMethod.GET)
	public String back() {
		logger.info("목록 리스트 호출");
		return "redirect:/list";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, Model model) {
		logger.info("로그 아웃");
		session.removeAttribute("id");
		model.addAttribute("msg", "로그아웃 되었습니다.");
		return "login";
	}
	
	//dto로 파라메터를 받기 위한 조건
	//1. @ModelAttribute 를 사용 할 것
	//2. view에서 보내는 name과 dto내의 필드명이 일치 할 것
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public ModelAndView write(@ModelAttribute BoardDTO dto) {
		
		logger.info(dto.getSubject() + " : " + dto.getContent());
		return service.write(dto);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam String idx) {
		logger.info("게시물 삭제");
		logger.info("삭제할 idx : " + idx);
		
		return service.delete(idx);
	}
	
	@RequestMapping(value = "/updateForm", method = RequestMethod.GET)
	public ModelAndView updateForm(@RequestParam int idx) {
		ModelAndView mav = null;
		logger.info("게시물 수정");
		mav = service.detail(idx);
		mav.setViewName("updateForm");
		return mav;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute BoardDTO dto) {		
		logger.info(dto.getIdx() + " : " + dto.getSubject() + " : " + dto.getContent());
		
		return service.update(dto);
	}
	
}
