package com.spring.dao;

import java.util.ArrayList;

import com.spring.dto.BbsDTO;

public interface BbsDAO {

	ArrayList<BbsDTO> list();//게시판 리스트 만들기

	int delete(String idx);//삭제 하기

	BbsDTO detail(String idx);//상세 보기

	int write(String userName, String subject, String content);
	
}
