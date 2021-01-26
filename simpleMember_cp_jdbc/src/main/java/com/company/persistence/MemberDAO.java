package com.company.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.company.domain.MemberVO;

@Repository // 객체생성
public class MemberDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int insert(MemberVO member) {

		String sql = "insert into member1 values(?,?,?,?,?)";
		int result = jdbcTemplate.update(sql, member.getUserid(), member.getPassword(), member.getName(),
				member.getGender(), member.getEmail());

		return result;
	}

	public int update(MemberVO member) {
		String sql = "update member1 set password =? where userid=?";
		int result = jdbcTemplate.update(sql, member.getPassword(), member.getUserid());

		return result;
	}

	public int delete(MemberVO member) { // 아이디 비번 일치시 삭제
		String sql = "delete from member1 where userid=? and password=?";
		int result = jdbcTemplate.update(sql, member.getUserid(), member.getPassword());
		return result;
	}

	public MemberVO getRow(MemberVO member) { // 아이디 비번 일치시 가져오기

		String sql = "select * from member1 where userid=? and password=?";
		Object[] args = { member.getUserid(), member.getPassword() }; // 필요한 code를 배열로
		MemberVO vo = jdbcTemplate.queryForObject(sql, args, new MemberRowMapper());
		return vo;
	}
	
	public List<MemberVO> getList() {
		String sql = "select * from member1";
		return jdbcTemplate.query(sql, new MemberRowMapper());
	}
}
