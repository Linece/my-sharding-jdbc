package com.zdc.sharding.java.controller;

import com.zdc.sharding.java.entity.UserInfo;
import com.zdc.sharding.java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShardingJavaController {

	@Autowired
	public UserService userService;

	@GetMapping("/insert")
	public String insert(){
		userService.insert();
		return "success";
	}


	@GetMapping("/{id")
	public UserInfo users(@PathVariable Long id){
		return this.userService.selectByid(id);
	}
}
