package com.company.annotation;

public class SamsungTv implements TV {

	@Override
	public void turnOn() {
		System.out.println("삼성티비 - 전원 on");

	}

	@Override
	public void turnOff() {
		System.out.println("삼성티비 - 전원 OFF");

	}

	@Override
	public void soundUp() {
		System.out.println("삼성티비 - 볼륨 UP");

	}

	@Override
	public void soundDown() {
		System.out.println("삼성티비 - 볼륨 Down");

	}

}
