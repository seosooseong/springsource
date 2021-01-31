package com.company.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageVO {       // 페이지 나누기와 관련된 모든 정보를 가지고 있는 객체
	private int startPage;  
	private int endPage;    
	private boolean prev;   
	private boolean next;   
	private int total;      
	private Criteria cri;   
	
	public PageVO(Criteria cri, int total) {
		this.total = total; //전체게시물 수
		this.cri = cri;     //현재페이지번호, 페이지당 게시물 갯수
		
		//계산
		//ex 3페이지 -> 하단 1 2 3 4 5 6 7 8 9 10
		//ex 12페이지 -> 하단 11 12 13 14 15 16 17 18 19 20
		
		this.endPage =(int)(Math.ceil(cri.getPageNum()/10.0))*10;
		this.startPage = endPage -9;
		
		//
		int realEnd=(int)(Math.ceil((total/1.0)/cri.getAmount()));
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		//
		this.prev=this.startPage>1;
		this.next=this.endPage<realEnd;
	}
	
}
