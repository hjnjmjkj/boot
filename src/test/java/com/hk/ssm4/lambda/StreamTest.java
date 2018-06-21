package com.hk.ssm4.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

public class StreamTest {
	public static void main(String[] args) {
		List<People> sortLists = Lists.newArrayList();
		sortLists.add(new People(1));
		sortLists.add(new People(4));
		sortLists.add(new People(6));
		sortLists.add(new People(3));
		sortLists.add(new People(2));
		sortLists.add(new People(2));
		List<People> sortLists2=sortLists.stream().map(s->{s.age=s.age+1; return s;}).collect(Collectors.toList());
		sortLists2.stream().forEach(p1 ->System.out.println(p1.age));
		System.out.println("------------飘逸的分割线-----------");
		sortLists.forEach(p1 ->System.out.println(p1.age));
		
	}
	
	public static class People{
		public int age;
		
		public People() {
			super();
		}

		public People(int age) {
			super();
			this.age = age;
		}
		
	}
}
