package com.company.book;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.domain.BookVO;
import com.company.service.BookService;

public class BookClient {

	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("book_config.xml");
		BookService service = (BookService)ctx.getBean("service");
		
		
		//도서정보입력
//		BookVO insertVO = new BookVO(999, "자바스크립트210104", "서수성", 20100);
//		if(service.insertBook(insertVO)) {
//			System.out.println("도서 정보 입력");
//		}
		//service.getlList()
		List<BookVO> list = service.getList();
		for (BookVO vo : list) {
			System.out.println(vo);		
			}
		
	}

}
