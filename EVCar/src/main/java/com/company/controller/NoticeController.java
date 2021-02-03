package com.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.Criteria;
import com.company.domain.NoticeVO;
import com.company.domain.PageDTO;
import com.company.service.NoticeService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/comunity/*")
public class NoticeController {
	
	@Autowired
	private NoticeService service;
	
	
	
	
	@GetMapping("/noticeList")
	public void list(Criteria cri, Model model) {
		
		log.info("list: "+cri);
		
		model.addAttribute("notice", service.getList(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, service.getTotalCount(cri)));
	}
	
	@GetMapping("/noticeRegister")
	public void register() {
		
	}
	
	@PostMapping("/noticeRegister")
	public String register(NoticeVO notice, RedirectAttributes rttr) {
		
		log.info("notice register" + notice);
		
		service.register(notice);
		
		rttr.addFlashAttribute("result", notice.getBno());
		
		return "redirect:/comunity/noticeList";
	}
	
	@GetMapping({"/noticeGet", "/noticeModify"})
	public void get(@RequestParam("bno") int bno, Model model) {
		
		log.info("noticeGet or noticeModify");
		model.addAttribute("notice", service.get(bno));
		
		//조회수+1
		service.plusCnt(bno);
	}
	
	@PostMapping("/noticeModify")
	public String modify(NoticeVO notice, RedirectAttributes rttr) {
		log.info("notice modify:" + notice);
		
		if (service.modify(notice)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/comunity/noticeList";
	}
	
	@PostMapping("/noticeRemove")
		 public String remove(@RequestParam("bno") int bno, RedirectAttributes rttr)
		 {
		
		 log.info("remove..." + bno);
		 if (service.remove(bno)) {
		 rttr.addFlashAttribute("result", "success");
		 }
		 return "redirect:/comunity/noticeList";
		 }
} 
