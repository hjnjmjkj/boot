package com.hk.ssm4;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableTransactionManagement
@ServletComponentScan
@EnableSwagger2
@ComponentScan
@MapperScan("com.hk.ssm4.mapper")
public class Ssm4Application {

	public static void main(String[] args) {
		SpringApplication.run(Ssm4Application.class, args);
	}
}
