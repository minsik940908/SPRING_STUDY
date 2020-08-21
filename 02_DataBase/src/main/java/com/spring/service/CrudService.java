package com.spring.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.spring.dao.CrudDao;
import com.spring.dto.CrudDto;


@Service
public class CrudService implements CrudInterface {

	private static final Logger logger = LoggerFactory.getLogger(CrudService.class);
	
	@Override
	public void listView(Model model) {
		logger.info("서비스 호출");
		CrudDao dao = new CrudDao();
		
		try {
			ArrayList<CrudDto> list = dao.listView();
			dao.resClose();
			model.addAttribute("list",list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void write(HashMap<String, String> params) {
		logger.info("글쓰기");
		int success = 0;
		try {
			CrudDao dao = new CrudDao();
			success = dao.write(params);
			dao.resClose();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("글쓰기 성공 여부 : " + success);
	}

	@Override
	public void contentView(Model model) {
		logger.info("상세보기 서비스");
		//model에 Controller에서 넣은 파라메터 값을 뽑아옴 -> 비효율적
		Map<String,Object> map = model.asMap();
		String idx = (String) map.get("idx");
		logger.info("idx : " + idx);
		try {
			CrudDao dao = new CrudDao();
			CrudDto dto = dao.detail(idx);
			model.addAttribute("info", dto);
			dao.resClose();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void modify(Model model) {
		
	}

	@Override
	public void delete(Model model) {

	}

}
