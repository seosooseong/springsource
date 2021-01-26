package com.company.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.company.domain.AuthVO;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// http://localhost:8081/member/changePwd 를 요청 시
		HttpSession session = request.getSession();
		
		if(session!=null) {
			AuthVO auth = (AuthVO) session.getAttribute("auth");
			if(auth!=null) {
				return true; //세션값이 있다면 사용자가 요청한 컨트롤러 진입
			}
		}
		// 세션 값이 없으면 "로그인 폼 페이지"로 돌려보내기
		response.sendRedirect("/member/signin");
		
		return false;
	}
}
