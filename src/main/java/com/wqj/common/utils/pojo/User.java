package com.wqj.common.utils.pojo;

public class User extends Person{

	private String name;

	
	public User() {
		super();
	}

	public User(String name) {
		super("clx");
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [name=" + name + "]";
	}
}
