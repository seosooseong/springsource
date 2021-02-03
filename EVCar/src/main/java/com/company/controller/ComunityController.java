package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

//자동차 커뮤니티 컨트롤러
@Controller
@Slf4j
@RequestMapping("/comunity/*")
public class ComunityController {
	
	@GetMapping("/qna")
	public void getQna() {
		log.info("qna start");
	}
}
