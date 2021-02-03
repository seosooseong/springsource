package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

//자동차 모델 컨트롤러
@Controller
@Slf4j
@RequestMapping("/models/*")
public class ModelController {
	
	@GetMapping("/model")
	public void getest() {
		log.info("test시작");
	}
}
