package com.hk.ssm4.service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.hk.ssm4.entity.UserEntity;
import com.hk.ssm4.enums.UserSexEnum;

@Service
public class UserService {
	@Autowired
	private com.hk.ssm4.mapper.master.UserMapper masterUser;
	@Autowired
	private com.hk.ssm4.mapper.salve.UserMapperSalve salveUser;
	@Autowired
	private JmsTemplate jmsTemplate;
	@Resource(name="activeMQQueue")
	private Destination destination;
	@Transactional
	public void userInsert(){
		masterUser.insert(new UserEntity("hk","123",UserSexEnum.MAN));
		//salveUser.insert(new UserEntity("hk","123",UserSexEnum.MAN));
	}
	@Transactional
	public void userInsertAntSendMsg(){
		masterUser.insert(new UserEntity("hk","123456",UserSexEnum.MAN));
		sendMessage(destination,"hk");
	}
	
	/**
     * 向指定队列发送消息
     */
    public void sendMessage(Destination destination, final String msg) {
        System.out.println("向队列" + destination.toString() + "发送了消息------------" + msg);
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });
    }
}
