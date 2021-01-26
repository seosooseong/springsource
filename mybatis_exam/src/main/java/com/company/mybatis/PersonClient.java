package com.company.mybatis;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.domain.PersonVO;
import com.company.service.PersonService;

public class PersonClient {

	public static void main(String[] args) {

	ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
	PersonService service = (PersonService)ctx.getBean("person");
	
	//service.insertPerson("Kang", "강민섭");
			
	//update
	//service.updatePerson("hong123", "홍금보");
	
	//delete
	//service.deletePerson("");
	
	
	//select
	System.out.println(service.get("hong123"));
	
	//selectall
	List<PersonVO> list = service.list();
		for(PersonVO vo:list) {
			System.out.println(vo);
		}
	}

}
