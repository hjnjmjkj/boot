package com.hk.ssm4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiOperation;

@Controller
public class RestController {
	@ApiOperation(value="hello", notes="")
	@PostMapping("hello")
	@ResponseBody
	public String hello(String s,Long i){
		return "hello";
	}
}
