package com.wqj.parameBinding.entity;

public class User {
	private String name;
	private Integer age;
	private ContactInfo contactInfo;
	
	
//	@Override
//	public String toString() {
//		return "User [name=" + name + ", age=" + age + "]";
//	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", contactInfo=" + contactInfo + "]";
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public ContactInfo getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}
}
