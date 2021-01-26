package com.company.aop;

import org.springframework.stereotype.Component;

import lombok.Setter;

@Setter
@Component //객체생성! 이름을 지정하지 않을 경우  class명으로 객체를 생성(default) - ex. product(소문자)
public class Product {
	private String company;
	private String name;
	private String price;
	
	public void getInfo() /*throws Exception*/ {
		System.out.println("회사명: "+company);
		System.out.println("제품명: "+name);
		System.out.println("가  격: "+price);
		//throw new Exception("강제예외발생");
	}
}
