package com.spring.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.spring.dao.BoardDAO;
import com.spring.dto.BoardDTO;

@Service
public class BoardService {

	
	
	@Autowired BoardDAO dao;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	//게시판 리스트 호출
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		
		ArrayList<BoardDTO> list = dao.list();//게시판 불러오기

		logger.info("list size : "  + list.size());
		mav.addObject("list", list);
		mav.setViewName("list");
		
		return mav;
	}
	
	/* 옵션은 줘도 되고 안줘도 된다.
	 * Isolation.READ_COMMITTED : 다른 트랜젝션에서 COMMIT 된 데이터만 읽어올 수 있게 하는 Level(기본 옵션)
	 * Isolation.READ_UNCOMMITTED : 다른 트랜젝션에서 COMMIT 되지 않은 데이터도 읽을 수 있는 Level
	 * Isolation.REPEATABLE_READ : 조회중인 데이터를 다른 트랜젝션에서 UPDATE 못하도록 막는 Level
	 * Isolation.SERIALIZABLE : 한 트랜젝션에서 조회(접근)할 경우 그 트랜젝션의 작업내용이 COMMIT 될 때 까지 다른 트랜젝션은 CRUD(접근)불가
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public ModelAndView detail(int idx) {
		ModelAndView mav = new ModelAndView();
		int success = uphit(idx);
		logger.info("조회수 증가 성공->1 실패->0 : " + success);
		BoardDTO dto = dao.detail(idx);
		mav.addObject("dto", dto);
		mav.setViewName("detail");
		return mav;
	}
	
	public int uphit(int idx) {
		return dao.uphit(idx);
	}

	public int login(String id, String pw) {
		
		int cnt = dao.login(id, pw);
		logger.info("회원 여부 : " + cnt);
		return cnt;
	}

	public ModelAndView write(String user_name, String subject, String content) {
		
		ModelAndView mav = new ModelAndView();
		int success = dao.write(user_name, subject, content);
		String msg = "글쓰기 실패";
		String page = "writeForm";
		if(success > 0) {
		    msg = "글쓰기 성공";
		    page = "redirect:/list";
		}
		mav.addObject("msg",msg);
		mav.setViewName(page);
		return mav;
	}

	public ModelAndView write(BoardDTO dto) {
		
		ModelAndView mav = new ModelAndView();
		int success = dao.write(dto);
		logger.info("success : " + success);
		String page = "writeForm";
		if(success > 0) {
			page = "redirect:/list";
		}
		mav.setViewName(page);
		
		return mav;
	}

	public ModelAndView delete(String idx) {
		
		ModelAndView mav = new ModelAndView();
		String msg = "삭제 실패";
		int success = dao.delete(idx);
		if(success > 0) {
			msg = "삭제 성공";
		}
		mav.setViewName("redirect:/list");
		return mav;
		
	}

	public ModelAndView update(BoardDTO dto) {
		int success = dao.update(dto);
		int idx = dto.getIdx();
		logger.info("수정 성공 여부 : " +  success);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/detail?idx="+idx);
		return mav;
	}
	
}
