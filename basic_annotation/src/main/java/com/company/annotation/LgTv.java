package com.company.annotation;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
@Component("tv") //객체 자동생성 = <bean id="tv" class="com.company.annotation.LgTv" /> 동일
public class LgTv implements TV {
	
	
	//적절한 시점에 생성된 객체 "주입" - @Autowired, @Inject
	//특정 객체 지정 - @Qualifier
	

	//@Autowired // <-적절한 시점에 생성된 객체 "주입"
	@Inject // = @Autowired
	@Qualifier("apple") // <-단독사용 불가 ( 주입대상이 여러개인 경우 특정 객체 지정 ex:sony, apple 등...)
	private Speaker speaker; //멤버 변수

	@Override
	public void turnOn() {
		System.out.println("LG티비 - 전원 on");
	}
	@Override
	public void turnOff() {
		System.out.println("LG티비 - 전원 OFF");
	}
	@Override
	public void soundUp() {
		//System.out.println("LG티비 - 볼륨 UP");
		//SonySpeaker speaker = new SonySpeaker(); //중복코드
		speaker.volumUp();
	}
	@Override
	public void soundDown() {
		//System.out.println("LG티비 - 볼륨 Down");
		//SonySpeaker speaker = new SonySpeaker();
		speaker.volumDown();
	}
}
