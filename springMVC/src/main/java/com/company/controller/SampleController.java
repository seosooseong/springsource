package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller // @component, @service, @repository
@RequestMapping("/sample/*") // http://localhost:8081/sample/*** ..아래 SampleController 응답
public class SampleController {
	
	//- 메세지 파일 [/WEB-INF/views/sample/basic.jsp]을(를) 찾을 수 없습니다. -jsp경로 확인
	//- 메세지가 없다면 controller를 잘 못 만듬.
	
	/* void 와 string 차이 구분 */
	
	// cmd.equals("list.do") - 같은 개념, 경로를 지정
	@RequestMapping("/basic") // http://localhost:8081/sample/basic
	public void basic() {
		log.info("basic...."); // /sample/basic => view 리졸버에 넘어간다. (void)
	}
	@RequestMapping("/test") // http://localhost:8081/sample/test 주소랑 상관없의 임의값으로...
	public String test() {
		log.info("test....");
		return "default";   // default => view 리졸버
	}
}
