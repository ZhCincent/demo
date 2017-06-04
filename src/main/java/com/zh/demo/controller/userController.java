package com.zh.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zh.demo.entity.user;
import com.zh.demo.service.userService;

@Controller
public class userController {

	@Autowired
	private userService userService;
	
	
	@RequestMapping("/login")
	public String getAllUser(Map<String, Object> map){
		List<user> list=userService.getAllUser();
		map.put("hello", list.get(0).getName());
		return "/menu/login";
	}
}
