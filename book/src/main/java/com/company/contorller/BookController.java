package com.company.contorller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.BookVO;
import com.company.service.BookService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BookController {
	@Autowired
	private BookService service;

	@PostMapping("/insert")
	public String insert(BookVO book, RedirectAttributes rttr) {
		log.info("도서정보입력..." + book);

		try {
			if (service.insertBook(book)) {
				return "redirect:/select"; // 성공-> 리스트로
			} else {
				return "redirect:/"; // 실패-> 돌아가기
			}
		} catch (Exception e) {
			rttr.addFlashAttribute("tab", "insert"); // 키값은 임의로 지정 "tab"
			return "redirect:/"; // 실패-> 돌아가기
		}
	}

	// 전체리스트
	@GetMapping("/select")
	public String selectAll(Model model) {
		log.info("전체리스트 가져오기..");

		// 서비스호출
		List<BookVO> list = service.getList();
		model.addAttribute("list", list);

		return "book_selectAll";
	}
	//삭제
	@PostMapping("/delete")
	public String deletePost(int code, RedirectAttributes rttr) {
		log.info("도서정보삭제..." + code);
		
		if (service.deleteBook(code)) {
			return "redirect:/select"; // 성공-> 리스트로
		} else {
			rttr.addFlashAttribute("tab", "delete"); // 키값은 임의로 지정 "tab"
			return "redirect:/"; // 실패-> 돌아가기
		}
	}
	//수정
	@PostMapping("/modify")
	public String updatePost(int code,int price, RedirectAttributes rttr) {
		log.info("도서가격수정..." + code);
		if (service.updateBook(code, price)) {
			return "redirect:/select"; // 성공-> 리스트로
		} else {
			rttr.addFlashAttribute("tab", "modify"); // 키값은 임의로 지정 "tab"
			return "redirect:/"; // 실패-> 돌아가기
		}
		
	}
	//도서정보 검색
	@PostMapping("/search")
	public String search(String criteria,String keyword, Model model, RedirectAttributes rttr) {
		log.info("도서정보검색..."+criteria+","+keyword);
		
		List<BookVO> list = service.searchList(criteria, keyword);
		//if ( list.size()>0) {
		if ( !list.isEmpty()) {
			model.addAttribute("list",list);
			return "book_selectAll"; // 성공-> book_searchAll
		} else {
			rttr.addFlashAttribute("tab", "search"); // 키값은 임의로 지정 "tab"
			return "redirect:/"; // 실패-> 돌아가기
		}
	}
	@GetMapping("/search")
	public String serchGet(RedirectAttributes rttr) {
		log.info("searchForm요청");
		rttr.addFlashAttribute("tab", "search"); // 키값은 임의로 지정 "tab"
		return "redirect:/";
		
	}
}
















