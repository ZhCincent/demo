package com.zh.demo.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zh.demo.common.datasource.mysql.MybatisMapper;
import com.zh.demo.user.entity.user;

@Mapper
public interface userDao extends MybatisMapper {
	//获取全部用户
	List<user> getAllUser();
	
	//通过手机获取用户
	user getByMobile(String mobile);
	
	void addUser(@Param("user")user user);
}
