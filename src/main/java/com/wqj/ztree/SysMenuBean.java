package com.wqj.ztree;

public class SysMenuBean {
	
	// 菜单id
	private long id;
	// 菜单名称
	private String name;
	// 父菜单的id
	private long pId;
	// 是否展开  0不展开    1展开
	private Integer open = 0;
	// 菜单打开时的图标
	private String iconOpen;
	// 菜单关闭时的图标
	private String iconClose;
	// 叶子节点的图标
	private String icon;
	// 点击跳转地址
	private String url;
	
	private String target="_blank";
	
	public SysMenuBean() {
		super();
	}


	public SysMenuBean(long id, String name, long pId, Integer open, String iconOpen, String iconClose, String icon,
			String url) {
		this.id = id;
		this.name = name;
		this.pId = pId;
		this.open = open;
		this.iconOpen = iconOpen;
		this.iconClose = iconClose;
		this.icon = icon;
		this.url = url;
	}


	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getpId() {
		return pId;
	}
	public void setpId(long pId) {
		this.pId = pId;
	}

	public Integer getOpen() {
		return open;
	}

	public void setOpen(Integer open) {
		this.open = open;
	}

	public String getIconOpen() {
		return iconOpen;
	}

	public void setIconOpen(String iconOpen) {
		this.iconOpen = iconOpen;
	}

	public String getIconClose() {
		return iconClose;
	}

	public void setIconClose(String iconClose) {
		this.iconClose = iconClose;
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

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
}
