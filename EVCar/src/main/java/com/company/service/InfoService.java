package com.company.service;

import java.util.List;

import com.company.domain.CarInfoVO;

public interface InfoService {
	
	//차량등록
	public boolean regist(CarInfoVO info);
	
	//삭제
	public boolean remove(int bno);
	
	//수정
	public boolean modify(CarInfoVO info);
	
	//목록
	public List<CarInfoVO> getList(); 

	//검색
	
	
}
