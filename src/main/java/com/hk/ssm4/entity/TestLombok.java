package com.hk.ssm4.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Builder
@Slf4j
@ToString
@EqualsAndHashCode
public class TestLombok {
	private String name;
	private String sex;
	private String addr;
	public static void main(String[] args) {
		TestLombok test=new TestLombok("1", "2", "3");
		test.setName("123");
		System.out.println(test.getName());
		log.error("111111111111111111111111");
		System.out.println(TestLombok.builder().name("112333").build().getName());
		System.out.println(test.toString());
		try {
			TestLombok test2=null;
			test2.getAddr();
		} catch (Exception e) {
			log.info(e.getMessage(),e);
		}
	}
	
	
}
