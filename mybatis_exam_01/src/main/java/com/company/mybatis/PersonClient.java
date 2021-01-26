package com.company.mybatis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.service.PersonService;

public class PersonClient {

	public static void main(String[] args) {

	ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
	PersonService service = (PersonService)ctx.getBean("person");
	
	//service.insertPerson("Kang", "강민섭");
			
	//update
	service.updatePerson("hong123", "홍금보");
	
	//delete
	//service.deletePerson("");
	}

}
