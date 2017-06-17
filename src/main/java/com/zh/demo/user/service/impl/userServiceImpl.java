package com.zh.demo.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zh.demo.user.service.userService;
import com.zh.demo.user.dao.userDao;
import com.zh.demo.user.entity.user;

@Service
public class userServiceImpl implements userService {
    
	@Autowired
	private userDao userDao;
	
	@Override
	public List<user> getAllUser() {
		// TODO Auto-generated method stub
		return userDao.getAllUser();
	}
	
	@Override
	public user getByMobile(String mobile) {
		// TODO Auto-generated method stub
		return userDao.getByMobile(mobile);
	}
	
	@Override
	public void addUser(user user) {
		// TODO Auto-generated method stub
		 userDao.addUser(user);
	}
}
