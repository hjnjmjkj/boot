package com.hk.ssm4.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

public class TestCollectors {
	private List<String> list = new ArrayList<>();

    List<Users> users;

    @Before
    public void load() {
        list.add("张三");
        list.add("李四");
        list.add("王五");
        list.add("赵六");

        users = Arrays.asList(new Users(1, "张三", 18),
                new Users(2, "李四", 30),
                new Users(3, "王五", 20),
                new Users(4, "赵六", 18));

    }
    @Test
    public void groupingBy() {
        users.stream().collect(Collectors.groupingBy(Users::getAge)).forEach((key, value) -> {
            System.out.println(key + "  ---" + value.stream().mapToInt(p->p.getAge())
                    .sum());
        });

    }
    /**
     * 分组,然后取最大的值
     */
    @Test
    public void groupingByMax() {

        //分组后去最大值
        Map<Integer, Optional<Users>> collect = users.stream().collect(Collectors.groupingBy(Users::getAge, Collectors.maxBy(Comparator.comparing(Users::getId))));

        //分组后去最小值
        Map<Integer, Optional<Users>> collect2 = users.stream().collect(Collectors.groupingBy(Users::getAge, Collectors.minBy(Comparator.comparing(Users::getId))));

        collect.forEach((key, value) -> {
            System.out.println("key " + key + "          " + "value : " + value);
        });


        System.out.println("---------------------");

        collect2.forEach((key, value) -> {
            System.out.println("key " + key + "          " + "value : " + value);
        });

    }
    
    @Test
    public void mapping() {

        Map<Integer, List<String>> collect = users.stream().collect(Collectors.groupingBy(Users::getAge, Collectors.mapping( item ->{
            
            //当然你这里也可以构建一个新的对象，进行返回
            return item.getName();
        }, Collectors.toList())));

        //   Map<Integer, List<Object>> collect = users.stream().collect(Collectors.groupingBy(Users::getAge, Collectors.mapping(item ->{ return Arrays.asList(item); }, Collectors.toList())));

        collect.forEach((key, value) -> {
            System.out.println("key : " + key + "value :" + value);
        });
    }
    
    /**
     * 单字段进行排序
     */
    @Test
    public void sort() {

        //倒序
        System.out.println("倒序");
        users.stream().sorted(Comparator.comparing(Users::getAge).reversed()).collect(Collectors.toList()).forEach(item -> {
            System.out.println(item.getAge());
        });

        System.out.println("升序");

        //升序
        users.stream().sorted(Comparator.comparing(Users::getAge)).collect(Collectors.toList()).forEach(item -> {
            System.out.println(item.getAge());
        });

    }


    
    public static class Users{
    	private int id;
    	private String name;
    	private int age;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public Users(int id, String name, int age) {
			super();
			this.id = id;
			this.name = name;
			this.age = age;
		}
    	
		
		
    	
    }

}
