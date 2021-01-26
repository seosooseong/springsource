package com.company.service;

import java.util.List;

import com.company.domain.BoardVO;

public interface BoardService {
	public int insertBoard(BoardVO vo);
	public int updateBoard(BoardVO vo);
	public int deleteBoard(BoardVO vo);
	public BoardVO getRow(int bno);
	public List<BoardVO> getList();
}
