package com.company.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.AuthVO;
import com.company.domain.ChangeVO;
import com.company.domain.LoginVO;
import com.company.domain.RegisterVO;
import com.company.service.RegisterService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j // @Log4j2와 동일
@RequestMapping("/member/*") // 기본: http://localhost:8081/member
public class RegisterController {

	@Autowired
	private RegisterService service;

	@GetMapping("/step1") // http://localhost:8081/member +/step1
	public void step1Get() {
		log.info("step1 페이지 요청");
	}

	@PostMapping("/step2")
	public String step2Get(boolean agree, RedirectAttributes rttr) { // http://localhost:8081/member +/step2
		log.info("step2 회원가입 요청" + agree);

		// 사용자가 체크한 값 있다면 유지 , 없다면 step1으로 되돌리기
		if (!agree) {
			// return "step1";
			rttr.addFlashAttribute("check", "false");
			return "redirect:/member/step1";
		}
		return "/member/step2";
	}

	@PostMapping("/step3")
	public String step3Post(@ModelAttribute("rg") RegisterVO regist) {
		// step.jsp에서 넘긴 값 가져오기
		// 로그 출력
		// step3로 이동
		log.info("step3 페이지 요청..." + regist);

		if (regist.isPasswordEqualToConfirmPassword()) {// 일치하면 step3로 이동
			service.register(regist);
			return "/member/step3";
		} else {
			// 두개의 비밀번호가 일치하지 않는다면 step2로
			return "/member/step2";
		}
	}

	// 페이지 제어 : 주소 오류 방지 -> /step2,/step3 라도 step1으로 이동
	@GetMapping(value = { "/step2", "/step3" })
	public String Step2_3() {
		log.info("/step2, /step3 직접 요청");
		return "redirect:step1";
	}

	// 원래주소 : /WEB-INF/views/false 지만
	//
	@ResponseBody // 보내는 리턴값을 jsp로 인식 x, 실제 값으로 처리
	@PostMapping("/checkId")
	// 중복아이디 체크
	public String checkId(String userid) {
		log.info("중복아이디 검사 요청" + userid);
		RegisterVO dupId = service.selectById(userid);
		if (dupId != null) {
			return "false";
		}
		return "true";
	}

	// 로그인 - signin 보여주기

	@GetMapping("/signin")
	public void signIn() {
		log.info("로그인 요청...");

	}

	// 로그인 정보(아이디, 비밀번호)를 가져오는 컨트롤러 작성
	@PostMapping("/signin")
	public String loginPost(LoginVO login, HttpSession session) {
		log.info("로그인 요청... " + login);
		AuthVO auth = service.islogin(login);

		if (auth != null) {
			session.setAttribute("auth", auth);
			return "redirect:/"; // 처음페이지로

		} else { // userid, password 틀려서 로그인을 못 한 경우
			return "redirect:signin";
		}

	}

	// 로그아웃 -> 세션을 해제 후 index 이동
	@GetMapping("/logout")
	public String loguout(HttpSession session) {
		log.info("로그아웃 요청...");
		session.invalidate(); // 세션 전체 삭제
//		session.removeAttribute("auth"); //특정세션만 삭제
		return "redirect:/";
	}

	// 회원탈퇴 폼 보여주기
	@GetMapping("/leave")
	public void leaveGet() {
		log.info("탈퇴페이지 이동...");
	}

	// 회원탈퇴 - 회원삭제 -> 세션해제 -> index이동
	@PostMapping("/leave")
	public String leavePost(LoginVO login, HttpSession session) {
		log.info("회원탈퇴 요청..." + login);

		if (service.leave(login)) {
			session.invalidate();
			return "redirect:/";
		} else { // 비밀번호가 틀린 겨웅
			return "redirect:leave";
		}
	}
	//회원정보수정 폼 보여주기
	@GetMapping("/changePwd")
	public void changeInfo() {
		log.info("회원정보수정 폼...");
	}
	
	@PostMapping("/changePwd") // http://localhost:8081/member/changePwd +post
	public String changePost(ChangeVO change, @SessionAttribute AuthVO auth, HttpSession session,RedirectAttributes rttr) {
		//ChangeVO-password new_password confirm_password;
		log.info("비밀번호수정..."+change);
		
		//userid세션에서 가져와 change에 담기
		//AuthVO auth = (AuthVO) session.getAttribute("auth");  
		//다른방법:@SessionAttribute AuthVO auth로 대체가 가능.
		change.setUserid(auth.getUserid());
		//service 비밀번호 요청
		
		//성공 - > 세션해제 후 로그인 페이지로 이동
		if(service.update(change)) {
			session.invalidate();
			return "redirect:signin";
		}
		//실패 -> 비밀번호 변경폼 보여주기
		rttr.addFlashAttribute("error", "비밀번호를 확인하세요");
		return "redirect:changePwd";
	}
}












