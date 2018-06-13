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
}
