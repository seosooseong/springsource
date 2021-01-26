package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.BookVO;
import com.company.mapper.BookMapper;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookMapper mapper;

	@Override
	public boolean insertBook(BookVO book) {
		return mapper.insert(book) > 0 ? false : true;
	}

	@Override
	public boolean deleteBook(int code) {
		return mapper.delete(code) > 0 ? false : true;
	}

	@Override
	public boolean updateBook(int code, int price) {
		return mapper.update(code, price) > 0 ? false : true;
	}

	@Override
	public List<BookVO> getList() {
		return mapper.selectAll();
	}

	@Override
	public List<BookVO> searchList(String criteria, String keyword) {
		return mapper.search(criteria, keyword);
	}

}
