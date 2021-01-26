package com.company.poly;

public class TVUser {
	//다형성

	public static void main(String[] args) {
		//①TV lg = new LgTv();
		//②	
		LgTv lg = new LgTv();
		lg.setSpeaker(new AppleSpeaker());
		lg.turnOn();
		lg.soundUp();
		lg.soundDown();
		lg.turnOff();
		

	}

}
