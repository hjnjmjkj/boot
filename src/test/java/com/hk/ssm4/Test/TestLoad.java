package com.hk.ssm4.Test;

public class TestLoad {  
    /** 
     * Description 
     * @param args 
     */  
    public static void main(String[] args) {  
        Son s = new Son("0");  
    }  
}  
  
class Parent{  
    {  
        System.out.println("1");  
    }  
    static{  
        System.out.println("2");  
    }  
    public Parent(){  
        System.out.println("3");  
    }  
    public Parent(String s){  
    	System.out.println("4");  
    }  
}  
  
class Son extends Parent{  
    {  
        System.out.println("5");  
    }  
    static{  
        System.out.println("6");  
    }  
    public Son(){  
        System.out.println("7");  
    }  
    public Son(String s){  
    	System.out.println("8");  
    }  
}  