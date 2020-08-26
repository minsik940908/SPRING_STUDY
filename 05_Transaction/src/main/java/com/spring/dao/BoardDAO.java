package com.spring.dao;

import java.util.ArrayList;

import com.spring.dto.BoardDTO;

public interface BoardDAO {

	ArrayList<BoardDTO> list();

	BoardDTO detail(int idx);

	int uphit(int idx);

	int login(String id, String pw);

	int write(String user_name, String subject, String content);

	int write(BoardDTO dto);

	int delete(String idx);

	int update(BoardDTO dto);
	
}
