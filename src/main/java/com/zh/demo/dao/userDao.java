package com.zh.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zh.demo.common.datasource.mysql.MybatisMapper;
import com.zh.demo.entity.user;

@Mapper
public interface userDao extends MybatisMapper {
	//获取全部用户
	List<user> getAllUser();
}
