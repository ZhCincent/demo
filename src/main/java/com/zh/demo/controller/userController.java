package com.zh.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.zh.demo.entity.user;
import com.zh.demo.service.userService;

@Controller
public class userController {

	@Autowired
	private userService userService;
	
	
	@RequestMapping("/intoLogin")
	public String getAllUser(Map<String, Object> map){
		List<user> list=userService.getAllUser();
		map.put("hello", list.get(0).getName());
		return "/menu/login";
	}
	
	@RequestMapping("/login")
	public String login(String username,String password){
		Map<String, Object> map=new HashMap<String, Object>();
		System.out.println(username);
		System.out.println(password);
		map.put("succcess", true);
		String json=JSON.toJSONString(map);
		System.out.println(json);
		return json;
	}
	
}
