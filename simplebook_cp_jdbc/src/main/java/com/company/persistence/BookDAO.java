package com.company.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.company.domain.BookVO;

@Repository // 객체생성
public class BookDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int insert(BookVO vo) {
		String sql = "insert into booktbl values(?,?,?,?)";
		int result = jdbcTemplate.update(sql, vo.getCode(), vo.getTitle(), vo.getWriter(), vo.getPrice());

		return result;
	}

	public int update(BookVO vo) {
		String sql = "update booktbl set price=? where code=? ";
		int result = jdbcTemplate.update(sql, vo.getPrice(), vo.getCode());
		return result;
	}

	public int delete(int code) {
		String sql = "delete from booktbl where code=?";
		int result = jdbcTemplate.update(sql, code);
		return result;
	}

	public BookVO getRow(int code) {
		String sql = "select * from booktbl where code=?";
		Object[] args = { code }; // 필요한 code를 배열로
		BookVO vo = jdbcTemplate.queryForObject(sql, args, new BookRowMapper());
		return vo;
	}

	public List<BookVO> getList() {
		String sql = "select * from booktbl";
		return jdbcTemplate.query(sql, new BookRowMapper());
	}
}
