package com.wqj.springCache.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.wqj.daoDemo.entity.User;
import com.wqj.springCache.service.SpringCacheService;

@Service("springCacheService")
public class SpringCacheServiceImpl implements SpringCacheService{

	
	@Override
	@Cacheable(value="myCache",key="'SpringCacheServiceImpl_getUser_'+#userId")
	public User getUser(Long userId) {
		
		System.out.println("进入getA方法   userId=" + userId);
		
		return null;
	}

	
	
}
