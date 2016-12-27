package com.wqj.common.util.pojo;

import com.wqj.common.orm.entity.BaseEntity;

public class Student extends BaseEntity{

	// 昵称
	private String name;
	
	
	private User user;
	
	
	
	@Override
	public String toString() {
		return "Student [name=" + name + ", user=" + user + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
