package com.zdc.sharding.java.controller;

import com.zdc.sharding.java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShardingJavaController {

	@Autowired
	public UserService userService;

	@GetMapping("/insert")
	public String insert(){
		userService.insert();
		return "success";
	}
}
