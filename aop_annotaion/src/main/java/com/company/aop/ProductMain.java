package com.company.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ProductMain {

	public static void main(String[] args) /*throws Exception*/ {
		log.info("productMain 실행");
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("aop_config2.xml");
		Product product = (Product) ctx.getBean("product"); //aop_config.xml - id
		
		product.setCompany("Lego");
		product.setName("Lego 블럭");
		product.setPrice("270000");
		product.getInfo();
		
	}

}
