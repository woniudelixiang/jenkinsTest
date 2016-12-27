package com.wqj.excel.entity;

public class Excel {

	private String sex;
	private String name;
	
	
	public Excel() {
		super();
	}
	
	public Excel(String sex, String name) {
		this.sex = sex;
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
