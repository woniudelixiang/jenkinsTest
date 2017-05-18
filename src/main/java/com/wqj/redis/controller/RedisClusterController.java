package com.wqj.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wqj.common.controller.CommonController;

import redis.clients.jedis.JedisCluster;

@Controller
@RequestMapping("/redisCluster")
public class RedisClusterController extends CommonController {
	
	@Autowired
	private JedisCluster jedisCluster;
	
	@RequestMapping("/invoke")
	public @ResponseBody String test() {
		try {
		for(int i = 0 ; i < 100 ; i++) {
			jedisCluster.set("key" + i, "value" + i);
		}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "OK";
	}
	
	
	@RequestMapping("/get")
	public @ResponseBody String get() {
		try {
			for(int i = 0 ; i < 100 ; i++) {
				String val = jedisCluster.get("key" + i);
				System.out.println(val);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "OK";
	}
	
	
	
	
}
