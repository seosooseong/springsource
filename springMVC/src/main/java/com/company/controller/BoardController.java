package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.domain.BoardVO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@GetMapping("/insert")
	public void insert() {
		log.info("insert 요청...");
	}
	
	
	/*[실습] - 사용자가 입력한 게시물 가져오기
	         - 정보확인
	         - 페이지이동 /view                 */
//	@PostMapping("/insert")
//	public String insert(@ModelAttribute("vo") BoardVO vo) {
//		log.info("insert 요청..."+vo);
//		return "board/view";
//		
//	}
	
	
	
	//  http://localhost:8081/board/view   +post
	@PostMapping("/view")
	public void view(@ModelAttribute("vo") BoardVO vo) {
		log.info("view...."+vo); //  board/view  => view 리졸버
	}

	@GetMapping("/select")
	public void select() {
		log.info("select 요청...");
		
	}
}
