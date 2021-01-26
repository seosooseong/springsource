package com.company.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class ProductVO {
	private String code;
	private String pname;
	private String category;
	private int amount;
	private int price;
	private String etc;
	
	//의미가 없다. 파일인데 string으로 받아봤자
	//private String file;
}
