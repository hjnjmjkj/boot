package com.hk.ssm4.jdk;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TestList {
	public static void main(String[] args) {
		System.out.println(Optional.ofNullable("123").equals(Optional.ofNullable("123")));
		System.out.println(Objects.equals(null,"123"));
		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
		List<String> String = Arrays.asList("3", "2", "2", "3", "7", "3", "5");
		
		
		
	}
}
