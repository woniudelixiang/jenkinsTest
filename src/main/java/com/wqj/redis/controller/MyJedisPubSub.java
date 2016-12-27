package com.wqj.redis.controller;

import redis.clients.jedis.JedisPubSub;

public class MyJedisPubSub extends JedisPubSub {
	
	// 订阅时的处理  
	@Override
	public void onSubscribe(String channel, int subscribedChannels) {  
		System.out.println("onSubscribe:  " + channel + "=" + subscribedChannels);  
	}  
	
	// 取得订阅的消息后的处理  
	@Override
    public void onMessage(String channel, String message) {  
        System.out.println("onMessage:  " + channel + "=" + message);  
    }  
  
    // 取消订阅时的处理  
	@Override
    public void onUnsubscribe(String channel, int subscribedChannels) {  
         System.out.println("onUnsubscribe:  " + channel + "=" + subscribedChannels);  
    }  
	
	
    // 模式匹配方式订阅时的处理  
	@Override
    public void onPSubscribe(String pattern, int subscribedChannels) {  
         System.out.println("onPSubscribe:  " + pattern + "=" + subscribedChannels);  
    }  
  
    // 取得模式匹配方式订阅的消息后的处理  
	@Override
    public void onPMessage(String pattern, String channel, String message) {  
        System.out.println("onPMessage:  " + pattern + "=" + channel + "=" + message);  
    }  
	
	// 取消模式匹配方式订阅时的处理  
	@Override
	public void onPUnsubscribe(String pattern, int subscribedChannels) {  
		System.out.println("onPUnsubscribe:  " + pattern + "=" + subscribedChannels);  
	}
	
}
