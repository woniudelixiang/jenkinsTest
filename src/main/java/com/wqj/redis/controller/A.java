package com.wqj.redis.controller;

public abstract class A {
	private String a;
	
	 public void isNull() throws Exception {
		 if (a == null) {
			 throw new Exception("JedisPubSub was not subscribed to a Jedis instance.");
		 }
		 System.out.println(a);
	 }
}
