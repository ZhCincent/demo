package com.zh.demo.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zh.demo.user.dao.ipInfoDao;
import com.zh.demo.user.entity.ipinfo;
import com.zh.demo.user.service.ipInfoService;
@Service
public class ipInfoServiceImol implements ipInfoService{
  
	@Autowired
	private ipInfoDao infoDao;
	
	@Override
	public Integer addIpInfo(ipinfo info) {
		// TODO Auto-generated method stub
		return infoDao.addIpInfo(info);
	}

}
