package com.hk.ssm4.config;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;

@Configuration
@EnableTransactionManagement
public class JtaTransactionConfig {
	/**
     * atomikos
     * 配置jta事务管理器
     */
    @Bean(name = "transactionManager")
    public JtaTransactionManager jtaTransactionManager () {
        return new JtaTransactionManager(userTransaction(), userTransactionManager());
    }
    
    
    
    /**
     * atomikos
     * 创建userTransaction
     */
    @Bean
    public UserTransaction userTransaction(){
    	UserTransaction userTransaction = new UserTransactionImp();
        return userTransaction;
    }
    
    /**
     * atomikos
     * 配置UserTransactionManager
     */
    @Bean(initMethod = "init", destroyMethod = "close")
    public TransactionManager userTransactionManager(){
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(true);//事务强制关闭
        return userTransactionManager;
    }
}
