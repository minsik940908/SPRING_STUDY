package com.spring.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.BbsDAO;
import com.spring.dto.BbsDTO;

@Service
public class HomeService {

	@Autowired BbsDAO dao;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//list 요청
	public ArrayList<BbsDTO> list() {
		ArrayList<BbsDTO> list = dao.list();
		logger.info("list : " + list);
		return list;
	}
	
	//삭제 요청
	public void delete(String idx) {
		int success = dao.delete(idx);
		System.out.println("delete : " + success);
	}

	public BbsDTO detail(String idx) {
		
		return dao.detail(idx);
	}

	public void write(HashMap<String, String> params) {
		String userName = params.get("userName");
		String subject = params.get("subject");
		String content = params.get("content");
		
		int success = dao.write(userName, subject, content);
		logger.info("wrtie 성공 : 1, 실패 : 0,  -> " + success);
	}
	
}
