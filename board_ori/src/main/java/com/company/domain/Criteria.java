package com.company.domain;

import org.apache.ibatis.javassist.compiler.ast.StringL;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
	private int pageNum; //사용자가 선택한 페이지 번호
	private int amount;  //한 페이지에 보여줄 게시물 수
	
	private String type;    //검색조건
	private String keyword; //검색어
	
	public Criteria() {
		this(1,10);
	}


	public Criteria(int pageNum, int amount) {
		super();
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public String[] getTypeArr() {
		return type == null?new String[] {}:type.split("");
	}
}
