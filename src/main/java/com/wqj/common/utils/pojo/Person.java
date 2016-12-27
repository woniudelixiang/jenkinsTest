package com.wqj.common.utils.pojo;

public class Person {

	private Integer sex;
	private String name;

	public Person() {
		super();
	}

	public Person(String name) {
		super();
		this.name = name;
	}
	
	public Person(Integer sex, String name) {
		super();
		this.sex = sex;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "Person [sex=" + sex + ", name=" + name + "]";
	}
}
