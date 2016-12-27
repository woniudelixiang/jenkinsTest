package com.wqj.bootstrapAce.pojo;

import java.util.List;

public class BootstrapAceMenuBean {

	private long id;
	private String text;
	private String icon;
	private String url;
	private List<BootstrapAceMenuBean> childs;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<BootstrapAceMenuBean> getChilds() {
		return childs;
	}
	public void setChilds(List<BootstrapAceMenuBean> childs) {
		this.childs = childs;
	}
}
