package kr.co.goodee.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.goodee.dao.TeamDAO;

@Service
public class TeamService {

	@Autowired TeamDAO dao;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public HashMap<String, Object> listCall() {
		logger.info("리스트 요청 서비스");
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("list", dao.listCall());
		return result;
	}

	public HashMap<String, Object> update(HashMap<String, String> params) {
		logger.info("업데이트 요청 서비스");
		int success = dao.update(params);
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("sucess", success);
		return result;
	}
}
