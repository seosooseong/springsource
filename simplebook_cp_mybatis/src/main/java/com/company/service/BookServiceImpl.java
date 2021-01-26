package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.BookVO;
import com.company.mapper.BookMapper;

@Service("service")  //<--이걸 해야 클라이언트에서 찾아온다.
public class BookServiceImpl implements BookService {
	
	@Autowired //@Autowired
	private BookMapper mapper; //그냥쓰면 null이기 때문에 객체생성 한 것을 넣어야 하기떄문에 @Repository 하고
	
	@Override
	public boolean insertBook(BookVO vo) {
		
		return mapper.insert(vo)>0?true:false;
	}

	@Override
	public boolean updateBook(BookVO vo) {
		
		return mapper.update(vo)>0?true:false;
	}

	@Override
	public boolean deleteBook(int code) {
		
		return mapper.delete(code)>0?true:false;
	}

	@Override
	public BookVO getRow(int code) {
		
		return mapper.select(code);
	}

	@Override
	public List<BookVO> getList() {
		
		return mapper.selectAll();
	}

}
