package com.hk.ssm4.Test;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.hk.ssm4.jms.JmsListenerService;
import com.hk.ssm4.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Rollback(false)
@Transactional
public class TestJms {
	@Autowired
	private UserService userService;
	@Test
	public void TestUserInsertAntSendMsg(){
		userService.userInsertAntSendMsg();
		System.out.println("等消息");
	}
	

    
}
