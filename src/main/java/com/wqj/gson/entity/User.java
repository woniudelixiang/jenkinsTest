package com.wqj.gson.entity;

import com.google.gson.annotations.SerializedName;

public class User {

	@SerializedName("userId")
	private Long id;
	
	private String username;
	
	private Integer sex;
	
	public User() {}

	public User(Long id, String username, Integer sex) {
		super();
		this.id = id;
		this.username = username;
		this.sex = sex;
	}



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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", sex=" + sex + "]";
	}
}
