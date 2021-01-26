package com.company.app;

public class TVUser {

	public static void main(String[] args) {
		LgTv lg = new LgTv();
		lg.turnOn();
		lg.soundUp();
		lg.soundDown();
		lg.turnOff();
		
		samsungTv samsung = new samsungTv();
		samsung.powerOn();
		samsung.volumeUp();
		samsung.volumeDown();
		samsung.powerOff();
	}

}
