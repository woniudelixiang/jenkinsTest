package com.wqj.returnValue.entity;

public class User {

	private String username;
	private Integer sex;
	
	public User() {}

	public User(String username, Integer sex) {
		this.username = username;
		this.sex = sex;
	}

	public String getUsername() {
		return "姓名： "+username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
}
