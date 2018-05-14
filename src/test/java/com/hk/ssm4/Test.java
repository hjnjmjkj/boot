package com.hk.ssm4;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.hk.ssm4.service.TestService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Test {
	@Autowired
	private TestService testService;
	@org.junit.Test
	public void test() throws Exception {  
		//会自动注册 System.getProperties() 和 System.getenv()  
	    Environment environment = new StandardEnvironment();  
	    System.out.println(environment.getProperty("server.port"));  
	    System.out.println(testService.test());
	}  
}
