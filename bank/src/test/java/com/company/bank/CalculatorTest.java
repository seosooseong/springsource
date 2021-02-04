package com.company.bank;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

//테스트 클래스는 무조건 public이어야 한다!

public class CalculatorTest {
	
	@Test //해당 어노테이션을 통해서 이 메소드가 단위 테스트 메소드라는것 명시
	public void testAdd() {
		Calculator cal = new Calculator();
		
//			  1 :기대값 / 2: 리턴값 / 3: 허용 오차값
		assertEquals(60, cal.add(10,40), 0);
	}
}
