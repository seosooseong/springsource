package com.company.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Component("log") // 기본값은 logAdvice
public class LogAdvice {
	public void beforLog() {
		System.out.println("[공통로그] 비즈니스 로직 전 수행");
	}

	public void afterLog() {
		System.out.println("[공통로그] 비즈니스 로직 후 수행 - 예외와 상관없이 호출");

	}
	public void afterThrowLog() {
		System.out.println("[공통로그] 비즈니스 로직 후 수행 - 예외발생시 호출");
		
	}
	public void afterReturnLog() {
		System.out.println("[공통로그] 비즈니스 로직 후 수행 - 정상 수행시 호출");
		
	}
	public void aroundLog(ProceedingJoinPoint pjp) { //ProceedingJoinPoint pjp 객체가 꼭 필요
		System.out.println("[공통로그] 비즈니스 로직 전 수행");
		
		try {
			pjp.proceed(); //원래 수행하려고 하는 메소드 호출
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		System.out.println("[공통로그] 비즈니스 로직 후 수행");
	}

}
