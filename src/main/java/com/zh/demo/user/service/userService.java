package com.zh.demo.user.service;

import java.util.List;
import java.util.Map;

import com.zh.demo.user.entity.user;

public interface userService {
	//获取全部用户
	List<user> getAllUser();
	
	user getByMobile(String mobile);
	
	void addUser(user user);
	
	Map<String, Object> getHighIpLoc(String ip);
}
