package com.wqj.redis.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable{

	private long id;
	private String name;
	
	public User() {
		
	}
	
	public User(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
}
