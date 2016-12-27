package com.wqj.common.utils.pojo;

public class PersonBean {

	private Integer sex;
	private String name;
	private String hobby;

	public PersonBean() {
		super();
	}

	public PersonBean(String name) {
		super();
		this.name = name;
	}
	
	public PersonBean(Integer sex, String name) {
		super();
		this.sex = sex;
		this.name = name;
	}

	public PersonBean(Integer sex, String name, String hobby) {
		super();
		this.sex = sex;
		this.name = name;
		this.hobby = hobby;
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

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	@Override
	public String toString() {
		return "PersonBean [sex=" + sex + ", name=" + name + ", hobby=" + hobby + "]";
	}

	
}
