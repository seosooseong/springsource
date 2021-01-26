package com.company.poly;

public class LgTv implements TV {
	
	private Speaker speaker; //멤버 변수로 선언
//	①
	public LgTv(){
		System.out.println("LgTV 객체 생성");
	}
	
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}
	
//	②	
//	public LgTv(Speaker speaker){
//		this.speaker = speaker;
//	}
	
	
	@Override
	public void turnOn() {
		System.out.println("LG티비 - 전원 on");
	}
	@Override
	public void turnOff() {
		System.out.println("LG티비 - 전원 OFF");
	}
	@Override
	public void soundUp() {
		//System.out.println("LG티비 - 볼륨 UP");
		//SonySpeaker speaker = new SonySpeaker(); //중복코드
		speaker.volumUp();
	}
	@Override
	public void soundDown() {
		//System.out.println("LG티비 - 볼륨 Down");
		//SonySpeaker speaker = new SonySpeaker();
		speaker.volumDown();
	}
}
