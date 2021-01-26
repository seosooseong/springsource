package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.BookVO;
import com.company.persistence.BookDAO;

@Service("service")  //<--이걸 해야 클라이언트에서 찾아온다.
public class BookServiceImpl implements BookService {
	
	@Autowired //@Autowired
	private BookDAO dao; //그냥쓰면 null이기 때문에 객체생성 한 것을 넣어야 하기떄문에 @Repository 하고
	
	@Override
	public boolean insertBook(BookVO vo) {
		
		return dao.insert(vo)>0?true:false;
	}

	@Override
	public boolean updateBook(BookVO vo) {
		
		return dao.update(vo)>0?true:false;
	}

	@Override
	public boolean deleteBook(int code) {
		
		return dao.delete(code)>0?true:false;
	}

	@Override
	public BookVO getRow(int code) {
		
		return dao.getRow(code);
	}

	@Override
	public List<BookVO> getList() {
		
		return dao.getList();
	}

}
