package com.wqj.daoDemo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wqj.common.orm.dao.HibernateDaoSupport;
import com.wqj.daoDemo.entity.User;
import com.wqj.daoDemo.service.UserService;

@Service("userService")
public class UserServiceImpl extends HibernateDaoSupport<User> implements UserService{

	public UserServiceImpl(){
		System.out.println("******************   userServiceImpl init   ******************************");
	}

	@Override
	public List<User> getUserByUserName(String userName) throws Exception{
//		int s = Integer.parseInt(userName);
		return null;
	}
}
