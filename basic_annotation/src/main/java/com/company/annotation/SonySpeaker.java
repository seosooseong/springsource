package com.company.annotation;

import org.springframework.stereotype.Component;

@Component("sony")
public class SonySpeaker implements Speaker {
	public SonySpeaker() {
		System.out.println("==== Sonyspeaker 객체생성");
		
	}
	@Override
	public void volumUp() {
		System.out.println("==== Sonyspeaker volumUp");
	}
	
	@Override
	public void volumDown() {
		System.out.println("==== Sonyspeaker volumDown");
	}
}
