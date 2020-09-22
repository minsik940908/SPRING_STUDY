package kr.co.goodee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import kr.co.goodee.dao.ScheduleDAO;

@Service
public class ScheduleService {
	
	@Autowired ScheduleDAO dao;
	
	//스케쥴러는 한번 시작하면 서비스를 내릴	때 까지 멈출수가 없다.
	//0에서 시작해서 5씩 증가
	//@Scheduled(cron="0/5 * * * * MON-FRI")//초 분 시 일 월 요일 연도 : 크론방식
	//XML방식과 annotation 방식은 혼용 할 수 없다.
	public void loop5() {
		System.out.println("5초마다 찍는다.");
	}
	
	//@Scheduled(fixedDelay = 5000)//5초 후
	@Scheduled(fixedRate = 3000)//5초 단위로 실행
	public void loop() {
		int cnt = dao.cnt();
		System.out.println("현재 게시물의 갯수 : " + cnt);
	}

}
