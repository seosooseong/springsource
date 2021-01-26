package com.company.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisterVO {

	private String userid;
	private String name;
	private String password;
	private String confirm_password;
	private String gender;
	private String email;
	
	
	public boolean isPasswordEqualToConfirmPassword() {
		return password.equals(confirm_password);
	}
}
