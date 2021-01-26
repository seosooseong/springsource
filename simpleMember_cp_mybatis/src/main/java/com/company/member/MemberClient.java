package com.company.member;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.domain.MemberVO;
import com.company.service.MemberService;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MemberClient {
	
	public static void main(String[] args) {
		
		log.info("Client 실행");
		
		ApplicationContext ctx =new ClassPathXmlApplicationContext("member_config2.xml");
		MemberService service = (MemberService)ctx.getBean("service");
		

//		MemberVO insertVO = new MemberVO("sumin", "s880205", "김수민", "여","sumin@kakao.com");
//		if(service.memberInsert(insertVO)) {
//			System.out.println("멤버 등록 성공!");
//		}
		
		
	
		List<MemberVO> list = service.getMemberList();
		for(MemberVO vo:list) {
			System.out.println(vo);
		}
	}

}
