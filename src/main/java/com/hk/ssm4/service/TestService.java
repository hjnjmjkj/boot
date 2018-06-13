package com.hk.ssm4.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TestService {
	@Cacheable(value="testMap")
	public String test(){
		return "String";
	}
}
