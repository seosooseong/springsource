package com.company.mapper;

import java.util.List;

import com.company.domain.BoardVO;

public interface BoardMapper {
	//BoardMapper의 id와 메소드 일치!!!
	public int insert(BoardVO vo);
	public int update(BoardVO vo);
	public int delete(BoardVO vo);
	public BoardVO select(int bno);
	public List<BoardVO> selectAll();

	
}
