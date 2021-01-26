package com.company.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TVUser {
	//다형성
	public static void main(String[] args) {
		// 컨테이너 구동
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config2.xml");
		TV lg = (TV) ctx.getBean("tv");

		lg.turnOn();
		lg.soundUp();
		lg.soundDown();
		lg.turnOff();
		
		
	}

}
