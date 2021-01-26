package com.company.service;

import com.company.domain.AuthVO;
import com.company.domain.ChangeVO;
import com.company.domain.LoginVO;
import com.company.domain.RegisterVO;

public interface RegisterService {
	//아이디 중복 			    //resultType="com.company.domain.RegisterVO" 맞춰주기
	public RegisterVO selectById(String userid);
	
	
	//회원가입
	public boolean register(RegisterVO regist);
	
	//로그인 (성공시) =>userid, name만 가지고 다님
	//     AuthVO는 DB
	public AuthVO islogin(LoginVO login); //LoginVO login사용자가 입력한 정보를 담고 있음.
	
	//회원탈퇴
	public boolean leave(LoginVO login);
	
	public boolean update(ChangeVO change);
	
}
