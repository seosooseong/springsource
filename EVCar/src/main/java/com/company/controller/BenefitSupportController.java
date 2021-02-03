package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

//전기자동차 해택 컨트롤러
@Controller
@Slf4j
@RequestMapping("/benefitSupport/*")
public class BenefitSupportController {
	@GetMapping("/test")
	public void getest() {
		log.info("test시작");
	}
}
