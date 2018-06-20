package com.hk.ssm4;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.hk.ssm4.service.TestService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Test {
	@Autowired
	private TestService testService;
	@Autowired
	private CacheManager cacheManager;
	@org.junit.Test
	public void test() throws Exception {  
		System.out.println(cacheManager.getCache("testMap").get("1"));
	    
	}  
	
}
