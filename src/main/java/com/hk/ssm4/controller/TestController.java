package com.hk.ssm4.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hk.ssm4.service.TestService;


@Controller
public class TestController {
	@Autowired
	private TestService testService;
	@RequestMapping("/index")
	public String index(Model model){
		model.addAttribute("name", testService.test());
		return "index";
	}
	@RequestMapping("/lock")
	public String lock(Model model){
		model.addAttribute("name", "lock");
		try {
			testService.lock();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "index";
	}
	@RequestMapping("/lock2")
	public String unlock(Model model){
		model.addAttribute("name", "lock2");
		testService.lock2();
		return "index";
	}
	@RequestMapping("/getKey")
	public String getKey(Model model){
		model.addAttribute("name", testService.getKey());
		return "index";
	}
	@RequestMapping("/blockQueue")
	public String blockQueue(Model model) throws InterruptedException{
		testService.testBlockQueue();
		model.addAttribute("name", "blockQueue");
		return "index";
	}
	@RequestMapping("/redissonClientTest")
	public String redissonClientTest(Model model) throws InterruptedException{
		testService.redissonClientTest();
		model.addAttribute("name", "redissonClientTest");
		return "index";
	}
}
