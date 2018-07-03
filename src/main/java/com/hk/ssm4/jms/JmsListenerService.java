package com.hk.ssm4.jms;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.hk.ssm4.service.UserService;

@Service
public class JmsListenerService {
	@Autowired
	private UserService userService;
	@JmsListener(destination = "test")
	@Transactional
    public void receive(String msg){
        System.out.println("监听到的消息内容为: " + msg);
        userService.userInsert();
    }
}
