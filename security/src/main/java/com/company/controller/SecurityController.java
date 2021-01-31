package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/security/*")
public class SecurityController {
	
	@GetMapping("/all")
	public void allAccess() {
		log.info("모든 사람 가능");
	}
	
	@GetMapping("/member")
	public void memberAccess() {
		log.info("멤버만 가능");
	}
	
	@GetMapping("/admin")
	public void adminAccess() {
		log.info("관리자 가능");
	}
	
	@GetMapping("/login")
	public void loninForm(String error,Model model,String logout) {
		log.info("로그인 폼 요청");
		
		if(error!=null) {
			model.addAttribute("error", "로그인 정보를 확인해 주세요");
		}
	}
	
}