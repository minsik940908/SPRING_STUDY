package kr.co.goodee.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.co.goodee.dto.BoardDTO;
import kr.co.goodee.service.BoardService;

/**
 * Handles requests for the application home page.
 */
@RestController //jackson이 없으면 정상동작이 안된다
public class HomeController {
	
	@Autowired BoardService service;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public HashMap<String, Object> home() {
	
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("msg", "잘못된 사용 요청 입니다. 사용자 API를 참조 하세요");
		
		return result;
	}
	
	/*
	  domain?key=value <- 일반적인 요청
	  domain/{항목1}/{항목2} <- restful 요청 방식
	  restful : url을 보고 요청에 대한 내용을 짐작할 수 있어야 하는 것
	  url의 인자 값이 어떤 의미를 내포하는지 사전에 약속이 되어 있어야 한다.
	  해당 약속을 명시한 문서를 우리는 api문서라고 부른다.
	  API : 사용 설명서(예) JAVA API
	  
	  restful 요청 방식 예시
	  [Domain]/(인증키)/(xml)/(CardSubwayStatsNew)/(1)/(5)/(20151101)
	  [Domain]/KEY/TYPE/SERVICE/START_INDEX/END_INDEX/USE_DT
	  
	  API문서
	  항목명				타입(필수여부)				설명 
	  KEY					String(필수)				인증키 / OpenAPI 에서 발급된 인증키
	  TYPE				String(필수)				요청파일타입 / xml : xml, xml파일 : xmlf, 엑셀파일 : xls, json파일 : json
	  SERVICE			String(필수)				서비스명 / CardSubwayStatsNew
	  START_INDEX 	INTEGER(필수)			요청시작위치 / 정수 입력 (페이징 시작번호 입니다 : 데이터 행 시작번호)
	  END_INDEX 		INTEGER(필수)			요청종료위치 / 정수 입력 (페이징 끝번호 입니다 : 데이터 행 끝번호)
	  USE_DT 			STRING(필수)			사용일자 / YYYYMMDD 형식의 문자열
	  
	  [Domain]/(인증키)/json/CardSubwayStatsNew/1/50/20200812
	  => json 타입으로 CardSubwayStatsNew 서비스에 요청, 2020년08월12일 데이터를 1번부터 50번까지 가져와라
	  */
	
	//list / 페이지당 보여줄 리스트 수 / 몇 페이지 볼 건지 
	@RequestMapping(value = "/list/{pagePercnt}/{page}", method = RequestMethod.GET)
	public HashMap<String, Object> list(@PathVariable int pagePercnt, @PathVariable int page) {
		
		logger.info("pagePercnt : " + pagePercnt);
		logger.info("page : "+page);
		
		return service.list(page, pagePercnt);
	}
	
	//restController은 무조건 response로 응답하므로 특정 view로 보내기가 어렵다.
	//이 경우 modelandview를 사용하면 원하는 뷰로 이동이 가능하다.
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("list");
		return mav;
		
	}
}
