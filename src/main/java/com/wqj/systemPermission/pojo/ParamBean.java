package com.wqj.systemPermission.pojo;

import java.util.List;

public class ParamBean {
	private List<Long> rolePermissionList;
	private List<Long> roleIds;
	private Long roleId;
	
	public List<Long> getRolePermissionList() {
		return rolePermissionList;
	}
	public void setRolePermissionList(List<Long> rolePermissionList) {
		this.rolePermissionList = rolePermissionList;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public List<Long> getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(List<Long> roleIds) {
		this.roleIds = roleIds;
	}
}
