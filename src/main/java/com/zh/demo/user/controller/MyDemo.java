package com.zh.demo.user.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/aa")
public class MyDemo {
	
	@RequestMapping("/test")
	public String ts(Map<String, Object> map){
		map.put("hello", "i just test");
		return "helloJsp";
	}

}
