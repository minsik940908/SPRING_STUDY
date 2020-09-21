package kr.co.goodee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.goodee.dao.InterDAO;

@Service
public class InterService {

	@Autowired InterDAO dao;
	//로그인 요청
	public String login(String id, String pw) {
		return dao.login(id,pw);
	}

}
