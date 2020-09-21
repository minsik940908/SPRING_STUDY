package kr.co.goodee.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AopService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void before() {
		logger.info("before 메서드 실행");
	}

	public void after() {
		logger.info("after 메서드 실행");
	}

	public String afterreturning() {
		logger.info("afterreturning 메서드 실행");
		return "afterreturning return value";
	}

	public void afterthrowing() throws Exception {
		logger.info("afterthrowing 메서드 실행");
		throw new Exception("Test Exception");//강제 예외 발생
		
	}

	public void around(String string) {
		logger.info("around 메서드 실행, 인자 값 : " + string);
		
	}
	
}
