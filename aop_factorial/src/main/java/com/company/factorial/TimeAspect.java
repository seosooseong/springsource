package com.company.factorial;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect // <aop:aspectj-autoproxy/>로 활성화 필요
public class TimeAspect {
	@Around(value="execution(* com.company.factorial..*(..))") //포인트값 설정
	public Object measure(ProceedingJoinPoint pjp) throws Throwable {
		
		long start = System.nanoTime();
		try {
			//pjp.proceed()는 factorial 결과를 리턴하기 위해 필요.
			 Object obj =pjp.proceed();
			 return obj;
		} finally {
			long end = System.nanoTime();
			System.out.println("걸린시간 : "+(end-start));
		}
		
	}
}
