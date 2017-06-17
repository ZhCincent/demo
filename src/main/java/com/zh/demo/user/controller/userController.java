package com.zh.demo.user.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.zh.demo.user.entity.user;
import com.zh.demo.user.service.userService;
import com.zh.demo.utils.Md5Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
public class userController {
	private final static Logger logger=LoggerFactory.getLogger(userController.class);
	@Autowired
	private userService userService;
	
	
	@RequestMapping("/intoLogin")
	public String getAllUser(Map<String, Object> map){
//		List<user> list=userService.getAllUser();
//		map.put("hello", list.get(0).getName());
		return "/menu/login";
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public String login(String username,String password){
		Map<String, Object> map=new HashMap<String, Object>();
		user user=userService.getByMobile(username);
		if (user==null) {
			map.put("success", false);
            map.put("msg", "未发现此用户相关信息");			
			return JSONObject.toJSONString(map);
		}
		if (user.getPassword().equals(Md5Utils.getMD5(password))) {
			map.put("success", true);
            map.put("msg", "登陆成功");			
			
		}else {
			map.put("success", false);
            map.put("msg", "账户名密码错误");			
		}
		return JSONObject.toJSONString(map);
	}
	
	@RequestMapping("/addUser")
	@ResponseBody
	public void addUser(){
//		for (int i = 0; i < 1000; i++) {
//			user user=new user();
//			user.setId(UUID.randomUUID().toString());
//			user.setMobile(i+"zzz");
//			user.setName("zzz");
//			user.setPassword("123456");
//			user.setSex(0);
//			userService.addUser(user);
//		}
	}
}
