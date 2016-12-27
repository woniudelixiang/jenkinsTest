package com.wqj.common.util.pojo;

public class User {

	// 用户名
	private String username;
	// 密码
	private String password;
	
	private Person person ;

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", person=" + person + "]";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
