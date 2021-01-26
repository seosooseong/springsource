package com.company.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;



/*
 *  @Scheduled => 스프링 프레임워크에 TaskSheduler 인터페이스 구현되어 있음.
 *  		   => 메소드 void, 파라메터를 갖지 않아야 한다.
 *  		   => 사용방식 
 *  ① cron : 표현식 사용하기 (초, 분, 시, 일, 월, 요일, 년도)
 *  ② fixedDelay : 이전에 실행된 Task의 종료 시간으로부터 정의된 시간만큼 경과한 후 실행 (밀리세컨드)
 *  ③ fixedRate : 이전에 실행된 Task의 시작 시간으로부터 정의된 시간만큼 경과한 후 실행 (밀리세컨드)
 *  
 *  
*/
@Component
public class TaskTest {
	
	@Scheduled(cron=" 0 * * * * *")
	public void shedulerTest() {
		System.out.println("매 분 1초마다 스케쥴링 테스트....");
		
	}
	@Scheduled(fixedDelay = 10000)
	public void shedulerTest2() {
		System.out.println("10초마다 스케쥴링 테스트....");
		
	}
	
	
}
