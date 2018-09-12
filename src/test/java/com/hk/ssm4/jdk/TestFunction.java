package com.hk.ssm4.jdk;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class TestFunction {
	public static void main(String[] args) {
        Predicate<Integer> predOdd = integer -> integer % 2 == 1;
        System.out.println(predOdd.test(5));
        //控制台输出 5
        
        List<String> names = new ArrayList<String>();
        
        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");
          
        names.forEach(System.out::println);
    }
	
	public static void eval(List<Integer> list, Predicate<Integer> predicate) {
	      for(Integer n: list) {
	        
	         if(predicate.test(n)) {
	            System.out.println(n + " ");
	         }
	      }
	   }
}
