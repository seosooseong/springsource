package com.company.poly;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TVUser {
	//다형성
	public static void main(String[] args) {
		// 컨테이너 구동
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		TV lg = (TV) ctx.getBean("lg");
		//TV lg = new LgTv();
		//TV lg = new LgTV(new AppleSpeaker());
		lg.turnOn();
		lg.soundUp();
		lg.soundDown();
		lg.turnOff();
		
		TV tv = (TV) ctx.getBean("samsung");
		System.out.println(lg == tv? "같은 객체":"다른 객체");
	}

}
