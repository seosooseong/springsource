package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.domain.LoginVO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class LoginController {
	
	// get => login.jsp,  post => 사용자가 입력한 데이터를 가져와서 db작업
	//@RequestMapping(value="/login", method=RequestMethod.GET) // http://localhost:8081/login
	@GetMapping("/login")
	public void login() {
		log.info("login...."); //login => view 리졸버
	}
	
	//@RequestMapping(value="/login", method=RequestMethod.POST) // http://localhost:8081/login
  
	/* 방법1: 기존방법 사용자 입력값 가져오기 - 잘 안씀 */
	//	@PostMapping("/login")
//	public void loginPost(HttpServletRequest request) {
//		log.info("login post...."); //login => view 리졸버
//		log.info("userid: "+request.getParameter("userid"));
//		log.info("password: "+request.getParameter("password"));
//	}
	
	/* 방법2: Parameter 처리(단, 이름은 동일해야 가능) */
//     단점 :  많아지면 처리가 귀찮아짐.
	
	@PostMapping("/login") //@RequestParam("기존 명")으로 쓰면 다른거라도 가능하다.
	public String loginPost(@RequestParam("userid")String userid, String password, Model model) {
		log.info("login post...."); //login => view 리졸버
		log.info("userid: "+userid);
		log.info("password: "+password);
		
		model.addAttribute("login", new LoginVO(userid, password));
		return "logout";
	}
	
	/* 방법3: 권장, VO  */
//	@PostMapping("/login")
//	public void loginPost(LoginVO vo) {
//		log.info("login post...."); //login => view 리졸버
//		log.info("userid(vo): "+vo.getUserid());
//		log.info("password(vo): "+vo.getPassword());
//	}
//	@PostMapping("/login")
//	public String loginPost(LoginVO vo) {
//		log.info("login post...."); //login => view 리졸버
//		log.info("userid(vo): "+vo.getUserid());
//		log.info("password(vo): "+vo.getPassword());
//		return "logout"; //String일시 return 필요, logout => view 리졸버
//		// 장점: 바인딩, 다음페이지까지 값 유지
//	}
}
