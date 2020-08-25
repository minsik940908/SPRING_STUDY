package com.spring.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.spring.dto.MemberDTO;

public interface MemberDAO {

	//매개 변수에 DTO를 사용 할 수 있다.
	int join(MemberDTO dto);//회원 가입
	
	//매개 변수에 HashMap을 사용 할 수 있다.
	ArrayList<MemberDTO> list(HashMap<String, String> params);

	int update(HashMap<String, String> params);

	ArrayList<MemberDTO> multi(ArrayList<String> params);
	
}
