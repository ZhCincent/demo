package com.zh.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zh.demo.dao.userDao;
import com.zh.demo.entity.user;
import com.zh.demo.service.userService;
@Service
public class userServiceImpl implements userService {
    
	@Autowired
	private userDao userDao;
	
	@Override
	public List<user> getAllUser() {
		// TODO Auto-generated method stub
		return userDao.getAllUser();
	}
	
}
