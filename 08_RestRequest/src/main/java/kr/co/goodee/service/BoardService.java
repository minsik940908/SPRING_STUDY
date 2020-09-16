package kr.co.goodee.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.goodee.dao.BoardDAO;
import kr.co.goodee.dto.BoardDTO;

@Service
public class BoardService {
	
	@Autowired BoardDAO dao;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public HashMap<String, Object> list(int page, int pagePercnt) {
		
		//몇번부터 몇번까지 가져올 것인가?
		//page : 1페이지 1 ~ ?
		//pagePercnt : 5
		// page=1, pagePercnt = 5 ====> 1~5까지 보여달라
		int end = page * pagePercnt;
		int start = (end-pagePercnt)+1;
		
		//현재 만들수 있는 페이지 수(페이지당 5개 보여줌)
		//20/5 -> 4page
		//15/5 -> 3page
		//21/5 -> 5page
		//전체 게시물 수
		int totalcnt = dao.allcnt();
		
		logger.info("총 게시물 수 : " + totalcnt);
		int range = totalcnt % pagePercnt>0 ? Math.round(totalcnt / pagePercnt)+1 : Math.round(totalcnt / pagePercnt);
		logger.info("총 페이지 수 : " + range);//만들수 있는 페이지
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("range", range);
		map.put("total", totalcnt);
		map.put("currpage", page);
		map.put("list", dao.list(start, end));
		
		logger.info("map : " + map);
		
		return map;
	}}
