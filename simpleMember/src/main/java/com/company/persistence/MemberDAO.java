package com.company.persistence;

import static com.company.persistence.JDBCUtil.close;
import static com.company.persistence.JDBCUtil.commit;
import static com.company.persistence.JDBCUtil.getConnection;
import static com.company.persistence.JDBCUtil.rollback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.company.domain.MemberVO;

@Repository // 객체생성
public class MemberDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public int insert(MemberVO member) {
		int result = 0;
		try {
			String sql = "insert into member1 values(?,?,?,?,?)";
			con = getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, member.getUserid());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getGender());
			pstmt.setString(5, member.getEmail());
			result = pstmt.executeUpdate();
			if (result > 0) {
				commit(con);
			}
		} catch (Exception e) {
			rollback(con);
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(con);
		}
		return result;
	}

	public int update(MemberVO member) {
		int result = 0;
		try {
			String sql = "update member1 set password =? where userid=?";
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getUserid());

			result = pstmt.executeUpdate();
			if (result > 0) {
				commit(con);
			}

		} catch (Exception e) {
			rollback(con);
			e.printStackTrace();
		} finally {
			close(con);
			close(pstmt);

		}
		return result;
	}

	public int delete(MemberVO member) { // 아이디 비번 일치시 삭제
		int result = 0;
		try {
			String sql = "delete from member1 where userid=? and password=?";
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getUserid());
			pstmt.setString(2, member.getPassword());

			result = pstmt.executeUpdate();
			if (result > 0) {
				commit(con);
			}

		} catch (Exception e) {
			rollback(con);
			e.printStackTrace();
		} finally {
			close(con);
			close(pstmt);

		}
		return result;
	}

	public MemberVO getRow(MemberVO member) { // 아이디 비번 일치시 가져오기
		MemberVO vo = null;
		
		try {
			String sql = "select * from member1 where userid=? and password=?";
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getUserid());
			pstmt.setString(2, member.getPassword());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo=new MemberVO(rs.getString(1),
						rs.getNString(2),rs.getNString(3),
						rs.getNString(4),rs.getNString(5));
			}

		} catch (Exception e) {
			rollback(con);
			e.printStackTrace();
		} finally {
			close(rs);
			close(con);
			close(pstmt);

	}
		return vo;
	}

	public List<MemberVO> getList() {
		List<MemberVO> list = new ArrayList();

		try {
			String sql = "select * from member1";
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setUserid(rs.getString("userid"));
				vo.setPassword(rs.getString("password"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				vo.setGender(rs.getString("gender"));
				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return list;
	}
}
