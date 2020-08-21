package com.spring.service;

import java.util.HashMap;

import org.springframework.ui.Model;

//인터페이스 사용이유? -> 필수로 사용할 메소드를 강제화 시킬 때(규격)
public interface CrudInterface {

	public void listView(Model model);//리스트 보기
	
	public void write(HashMap<String, String> params);//글쓰기
	
	public void contentView(Model model);//상세보기
	
	public void modify(Model model);//수정하기
	
	public void delete(Model model);//삭제하기
	
}
