package com.wqj.excel.bean;

import java.util.List;

public class Resource {
	
	//标签的id
	private String id;
	//标签的 名称
	private String name;
	
	private List<ExcelContextBean> context;

	public Resource(String id, List<ExcelContextBean> contexts) {
		this.id = id;
		this.context = contexts;
	}

	public Resource() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ExcelContextBean> getContext() {
		return context;
	}

	public void setContext(List<ExcelContextBean> context) {
		this.context = context;
	}
}
