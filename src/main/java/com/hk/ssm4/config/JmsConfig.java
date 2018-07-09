package com.hk.ssm4.config;

import javax.jms.ConnectionFactory;
import javax.transaction.TransactionManager;

import org.apache.activemq.ActiveMQXAConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosConnectionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.jta.JtaTransactionManager;


//@Configuration
public class JmsConfig {
	@Bean(name="amqXaConnectionFactory")
	public ActiveMQXAConnectionFactory getActiveMQXAConnectionFactory(){
		ActiveMQXAConnectionFactory factory=new ActiveMQXAConnectionFactory();
		factory.setBrokerURL("tcp://localhost:61616");
		return factory;
	}
	
	@Bean(name="connectionFactory",initMethod="init",destroyMethod="close")
	public AtomikosConnectionFactoryBean getAtomikosConnectionFactoryBean(@Qualifier("amqXaConnectionFactory") ActiveMQXAConnectionFactory amqXaConnectionFactory){
		AtomikosConnectionFactoryBean atomikos=new AtomikosConnectionFactoryBean();
		atomikos.setUniqueResourceName("XAactiveMQ");
		atomikos.setXaConnectionFactory(amqXaConnectionFactory);
		atomikos.setPoolSize(5);
		return atomikos;
	}
	@Bean(name="jmsTemplate")
	public JmsTemplate getJmsTemplate(@Qualifier("connectionFactory") ConnectionFactory connectionFactory){
		JmsTemplate jmsTemplate=new JmsTemplate();
		jmsTemplate.setConnectionFactory(connectionFactory);
		jmsTemplate.setSessionTransacted(true);
		return jmsTemplate;
	}
	@Bean(name="jmsListenerContainerFactory")
	public DefaultJmsListenerContainerFactory getDefaultJmsListenerContainerFactory(@Qualifier("connectionFactory") ConnectionFactory connectionFactory,
			@Qualifier("transactionManager") JtaTransactionManager transactionManager){
		DefaultJmsListenerContainerFactory factory=new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setTransactionManager(transactionManager);
		return factory;
	}
	@Bean(name="activeMQQueue")
	public ActiveMQQueue getActiveMQQueue(){
		ActiveMQQueue queue=new ActiveMQQueue("test");
		return queue;
	}
}
