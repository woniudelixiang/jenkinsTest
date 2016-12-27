package com.wqj.common.enums;

public enum ViewName {

	list("-list"), insert("-insert"), show("-show"), 
	edit("-edit"), redirect("redirect:"), forward("forward:");

	private String value;

	private ViewName(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
