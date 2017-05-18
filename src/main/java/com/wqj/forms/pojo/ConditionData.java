package com.wqj.forms.pojo;

public class ConditionData {

	private String name;
	private String password;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ConditionData [name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}
}
