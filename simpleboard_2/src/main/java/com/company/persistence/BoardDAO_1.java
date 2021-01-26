package com.company.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.company.domain.BoardVO;
import static com.company.persistence.JDBCUtil.*;

//@Repository = 빈 생성(new BoardDAO같은 효과)
@Repository
public class BoardDAO_1 {
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public int insert(BoardVO vo) {
		int result = 0;

		try {
			con = getConnection();
			String sql = "insert into spring_board(bno,title,content,writer) "
					+ "values(seq_board.nextval,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriter());
			result = pstmt.executeUpdate();
			if(result>0) commit(con);
			
		} catch (Exception e) {
			e.printStackTrace();
			rollback(con);
		}finally {
			close(pstmt);
			close(con);
		}
		return result;
	}
	public int update(BoardVO vo) {
		int result = 0;
		
		return result;
	}
	public int delete(BoardVO vo) {
		int result = 0;
		
		return result;
	}
	public BoardVO getRow(int bno) {
		BoardVO vo = null;
		
		return vo;
	}
	public List<BoardVO> getList() {
		List<BoardVO> list = new ArrayList<>();
		
		return list;
	}
	
}	
