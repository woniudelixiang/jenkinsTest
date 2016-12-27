package com.wqj.daoDemo.service;

import java.util.List;

import com.wqj.common.orm.dao.BaseDao;
import com.wqj.daoDemo.entity.User;

public interface UserService extends BaseDao<User> {
	
	List<User>  getUserByUserName(String userName) throws Exception;
	
}
