package com.hk.ssm4;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableTransactionManagement
@ServletComponentScan
@MapperScan("com.hk.ssm4.mapper")
public class Ssm4Application {

	public static void main(String[] args) {
		SpringApplication.run(Ssm4Application.class, args);
	}
}
