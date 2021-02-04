package com.company.bank;

public class DuplicateAcountException extends RuntimeException {
	public DuplicateAcountException() {
		System.out.println("계좌번호를 확인해 주세요");
	}
}
