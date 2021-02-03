package com.company.domain;

import java.util.Date;

import lombok.Data;

@Data
public class NoticeVO {
	private int bno;
	private String title;
	private String writer;
	private String content;
	private Date regDate;
	private Date updateDate;
	private int readCnt;
}

