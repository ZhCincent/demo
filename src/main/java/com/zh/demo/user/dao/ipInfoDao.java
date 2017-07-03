package com.zh.demo.user.dao;

import org.apache.ibatis.annotations.Param;

import com.zh.demo.common.datasource.mysql.MybatisMapper;
import com.zh.demo.user.entity.ipinfo;

public interface ipInfoDao extends MybatisMapper{
	Integer addIpInfo(@Param("info")ipinfo info);
}
