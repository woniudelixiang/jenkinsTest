package com.wqj.common.message;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Message implements Serializable {
	private int id;
	private String content;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
