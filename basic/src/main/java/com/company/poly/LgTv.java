package com.company.poly;

public class LgTv implements TV {
	
	private Speaker speaker; //멤버 변수
	private int price; 
	
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
//	①
//	public LgTv(){
//	speaker = new SonySpeaker();
//	}
	
//	②	
//	public LgTv(Speaker speaker){
//		this.speaker = speaker;
//	}
	
	
	@Override
	public void turnOn() {
		System.out.println("LG티비 - 전원 on, 가격 :"+price);
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
