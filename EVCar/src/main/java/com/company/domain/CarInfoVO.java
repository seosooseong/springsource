package com.company.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class CarInfoVO {
		private int bno;  	
		private String brand;
		private String carname;
		private String carname_en; 		//차명 영어
		private int rowprice;  			//최저가
		private int highprice; 			//최대가
		private int Fuel; 				//연비
		private int releaseDate;		//출시연도
		private String title;  			//제목
		private String content; 		//설명
		
}
