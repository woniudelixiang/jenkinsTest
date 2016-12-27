package com.wqj.common.enums;

public enum AuthorName {
	missing("没有标明作者"),wangqijun("王启军"), geshen("葛什");
	
	
	private String value;

	private AuthorName(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
