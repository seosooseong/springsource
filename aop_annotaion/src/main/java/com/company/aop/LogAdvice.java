package com.company.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component("log") // 기본값은 logAdvice
@Aspect //
public class LogAdvice {

	@Before(value = "execution(* com.company.aop.Product.getInfo())")
	public void beforLog() {
		System.out.println("[Before] 비즈니스 로직 전 수행");
	}

	@After(value = "execution(* com.company.aop.Product.getInfo())")
	public void afterLog() {
		System.out.println("[After] 비즈니스 로직 후 수행 - 예외와 상관없이 호출");
	}

	@AfterThrowing(value = "execution(* com.company.aop.Product.getInfo())")
	public void afterThrowLog() {
		System.out.println("[AfterThrowing] 비즈니스 로직 후 수행 - 예외발생시 호출");
	}

	@AfterReturning(value = "execution(* com.company.aop.Product.getInfo())")
	public void afterReturnLog() {
		System.out.println("[AfterReturning] 비즈니스 로직 후 수행 - 정상 수행시 호출");
	}

	@Around(value = "execution(* com.company.aop.Product.getInfo())")
	public void aroundLog(ProceedingJoinPoint pjp) { // ProceedingJoinPoint pjp 객체가 꼭 필요
		System.out.println("[Around] 비즈니스 로직 전 수행");

		try {
			pjp.proceed(); // 원래 수행하려고 하는 메소드 호출
		} catch (Throwable e) {
			e.printStackTrace();
		}

		System.out.println("[Around] 비즈니스 로직 후 수행");
	}

}
