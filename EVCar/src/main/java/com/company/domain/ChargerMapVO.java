package com.company.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChargerMapVO {
	private String area;
	private String place;
	private String addr;
	private double lon;
	private double lat;
	private String f_char;
	private String s_char;
	private String sup_veh;
	
	
	
}
