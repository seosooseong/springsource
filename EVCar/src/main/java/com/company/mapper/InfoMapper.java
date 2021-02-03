package com.company.mapper;

import java.util.List;

import com.company.domain.CarInfoVO;

public interface InfoMapper {
	
	public int insert(CarInfoVO info); //삽입
	public int delete(int bno); //삭제
	public int update(CarInfoVO info); //제목 내용
	public List<CarInfoVO> list(); //리스트
	
	//검색
}
