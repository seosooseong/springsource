package com.company.book;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.domain.BookVO;
import com.company.service.BookService;

public class BookClient {

	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("book_config.xml");
		BookService service = (BookService) ctx.getBean("service");

		// 도서정보입력
//		BookVO insertVO = new BookVO(999, "자바스크립트210104", "서수성", 20100);
//		if(service.insertBook(insertVO)) {
//			System.out.println("도서 정보 입력");
//		}
		// service.getlList()
		List<BookVO> list = service.getList();
		for (BookVO vo : list) {
			System.out.println(vo);
		}

		// 게시글 하나 가져오기
//		BookVO row = service.getRow(500);
//		System.out.println("가져온 게시글");
//		System.out.println(row.toString());

		// 게시글 하나 삭제하기
//		BookVO deleteRow = new BookVO();
//		deleteRow.setCode(1011);
//		int delete = service.deleteBook(deleteRow);
//		if (delete > 0) {
//			System.out.println("삭제 성공!");
//		}
	}

}
