package com.company.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.BoardVO;
import com.company.domain.Criteria;
import com.company.domain.FileAttach;
import com.company.domain.PageVO;
import com.company.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/board/*")
public class BoardController {

	@Autowired
	private BoardService service;

	// 게시글 작성 폼 보여주기
	@GetMapping("/register")
	public void register() {
		log.info("작성페이지 이동...");
	}

	// 게시글 작성
	@PostMapping("/register")
	public String registerPost(BoardVO board, RedirectAttributes rttr) {
		log.info("게시글 작성..." + board);

		// (추가) 파일첨부 확인
		/*
		 * if(board.getAttachList()!=null) { board.getAttachList().forEach(attach ->
		 * log.info(""+attach)); }
		 */

		log.info("게시글 번호 : " + board.getBno() + "번 작성");
		if (service.regist(board)) {
			// 등록성공메시지를 모달로 띄우기 위해서 글번호 보내기~
			rttr.addFlashAttribute("result", board.getBno());
			return "redirect:list"; // 성공-> 리스트, redirect: /board/list 로 명확히 지정도 가능.
			// redirect는 http://localhost기준 + "
		}
		return "/board/register";

	}

	// 게시글 목록 보기
	@GetMapping("/list")
	public void list(Model model, Criteria cri) {
		log.info("게시글 목록...");
		// 전체목록
		List<BoardVO> list = service.getList(cri);

		// 전체게시물수
		int total = service.getTotalCnt(cri);

		model.addAttribute("list", list);
		model.addAttribute("pageVO", new PageVO(cri, total));
	}

	// ★ 특정게시글 보기 - 제목 누르면 게시글 뿌리기
	// /board/read?bno=43 => read.jsp
	// /board/modify?bno=43=> modify.jsp
	@GetMapping({ "/read", "/modify" }) // 2개 처리
	public void get(int bno, @ModelAttribute("cri") Criteria cri, Model model) {
		log.info("게시글open..." + bno);
		log.info("Criteria 정보 : " + cri);

		
		BoardVO baord = service.getRow(bno);
		model.addAttribute("board", baord); // board/read, /board/modify

	}

//	특정게시물 삭제
	@PostMapping("/remove")
	public String remove(int bno, Criteria cri, RedirectAttributes rttr) {
		log.info("게시글 삭제..." + bno);
		
//		게시물 번호에 해당하는 첨부파일 삭제 (서버 폴더 파일 삭제 + 데이터베이스 삭제)
		
//		서버 폴더 안 파일 삭제하기
//		① bno에 해당하는 첨부물 목록 알아내기
		List<FileAttach> attachList = service.getAttachList(bno);
//		성공하면 리스트 보여주기
		if(service.remove(bno)) {// ② 데이터베이스 삭제 (게시물, 첨부물)
//		③ 파일 삭제
			deleteFiles(attachList);
			rttr.addFlashAttribute("result", "success");
		}
		
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());

		return "redirect:list"; // 성공-> 리스트, redirect: /board/list 로 가능.
		// redirect는 http://localhost기준 + "
	}
	
	public void deleteFiles(List<FileAttach> attachList) {
		log.info("첨부물 삭제 :"+attachList);
		
		if(attachList == null || attachList.size() <= 0) {
			return;
		}
		for(FileAttach attach:attachList) {
			Path path = Paths.get("E:\\upload\\",attach.getUploadPath()+"\\"+attach.getUuid()+"_"+attach.getFileName());
			
			
			//일반 파일, 이미지 원본 파일 삭제
			try {
				Files.deleteIfExists(path);
				
				if(Files.probeContentType(path).startsWith("image")) { // 썸네일도 삭제
					Path thumb = Paths.get("E:\\upload\\",attach.getUploadPath()+"\\s_"+attach.getUuid()+"_"+attach.getFileName());
					Files.delete(thumb);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	
	
	
	
//	특정 게시물 수정
	@PostMapping("/modify")
	public String modify(BoardVO board, Criteria cri, RedirectAttributes rttr) {
		log.info("게시글 수정..." + board);
		log.info("Criteria 정보 : " + cri);
		
//		(추가) 파일첨부 확인
		 if(board.getAttachList()!=null) { board.getAttachList().forEach(attach ->
		 log.info(""+attach)); }
		 
		service.modify(board);

		rttr.addFlashAttribute("result", "success");
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());

		// 성공하면 list
		return "redirect:list";
	}
	
	
	//첨부물 가져오기
	@GetMapping(value = "/getAttachList", produces =MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE )
	public ResponseEntity<List<FileAttach>> getAttachList(int bno){
		log.info("첨부물 가져오기 : "+bno);
		return new ResponseEntity<List<FileAttach>>(service.getAttachList(bno),HttpStatus.OK);
	}
}















