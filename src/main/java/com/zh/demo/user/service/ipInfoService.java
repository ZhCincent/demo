package com.zh.demo.user.service;

import org.apache.ibatis.annotations.Param;

import com.zh.demo.user.entity.ipinfo;

public interface ipInfoService {
	Integer addIpInfo(@Param("info")ipinfo info);

}
