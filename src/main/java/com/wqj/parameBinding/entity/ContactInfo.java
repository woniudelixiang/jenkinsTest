package com.wqj.parameBinding.entity;

public class ContactInfo {

	private String phone;
	private String address;
	
	@Override
	public String toString() {
		return "ContactInfo [phone=" + phone + ", address=" + address + "]";
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
