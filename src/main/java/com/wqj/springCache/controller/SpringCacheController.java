package com.wqj.springCache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wqj.common.controller.CommonController;
import com.wqj.springCache.service.SpringCacheService;

import net.sf.ehcache.CacheManager;

@Controller
@RequestMapping("/springCache")
public class SpringCacheController extends CommonController {

	@Autowired
	SpringCacheService springCacheService;
	
	@Autowired
	private CacheManager cacheManager;
	
	@RequestMapping("/main")
	public String main(){
		return "springCache/main";
	}
	
	@ResponseBody
	@RequestMapping("/list/{userId}")
	public String list(@PathVariable Long userId){
		springCacheService.getUser(userId);
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
		return "ok";
	}
	
	
	
}
