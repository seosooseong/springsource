package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

//자동차 소개 컨트롤러
@Controller
@Slf4j
@RequestMapping("/info/*")
public class InfoController {
	
	@GetMapping("/test")
	public void getest() {
		log.info("test시작");
	}
	
	@GetMapping("/infoAll")
	public void infoAll() {
		log.info("전기차 목록...");
	}
}
