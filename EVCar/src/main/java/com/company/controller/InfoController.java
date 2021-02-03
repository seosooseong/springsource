package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.company.domain.CarInfoVO;
import com.company.service.InfoService;

import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.Post;

//자동차 소개 컨트롤러
@Controller
@Slf4j
@RequestMapping("/info/*")
public class InfoController {
	
	@Autowired
	private InfoService service;
	
	//등록 폼
	@GetMapping("/infoRegist")
	public void regist() {
		log.info("등록 폼 OPEN...");
	}
	
	//작성
	@PostMapping("/infoRegist")
	public String registPost(CarInfoVO info){
		log.info("차량 등록..."+info);
		service.regist(info);
		return "redirect:list";
	}
	
	//목록
	@GetMapping("/list")
	public void list(Model model) {
		log.info("전기차 목록...");
		List<CarInfoVO> list = service.getList();
		model.addAttribute("list",list);
	}
	
	
	//검색 ..모르겠다;;
	
	
	//수정
}
