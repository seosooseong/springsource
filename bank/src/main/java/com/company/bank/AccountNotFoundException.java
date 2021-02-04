package com.company.bank;

public class AccountNotFoundException extends RuntimeException {
	public AccountNotFoundException() {
		System.out.println("계좌번호가 중복되었습니다.");
	}
}
