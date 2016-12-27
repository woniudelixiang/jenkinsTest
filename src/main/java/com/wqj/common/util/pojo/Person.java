package com.wqj.common.util.pojo;

public class Person {
	
	// 国籍
	private String nationality;
	// 性别
	private Integer sex;

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Override
	public String toString() {
		return "Person [nationality=" + nationality + ", sex=" + sex + "]";
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
}
