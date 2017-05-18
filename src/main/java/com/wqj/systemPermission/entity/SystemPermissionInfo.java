package com.wqj.systemPermission.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.wqj.common.orm.entity.BaseEntity;

/**
 * 权限信息表
 * @author Qijun wang
 * @email wqjjob@126.com
 * @date 2017年3月31日 下午1:36:34
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "sys_permission")
public class SystemPermissionInfo extends BaseEntity {

	@Id
	@GeneratedValue(generator = "identity")
	@GenericGenerator(name = "identity", strategy = "identity")
	@Column(name = "permission_id", columnDefinition = "bigint")
	private Long permissionId;
	
	// 权限名称
	@Column(name = "permission_name", columnDefinition = "varchar(20)")
	private String permissionName;
	
	// 访问地址
	@Column(name = "url", columnDefinition = "longtext")
	private String url;
	
	// 权限说明
	@Column(name = "permission_description", columnDefinition = "longtext")
	private String permissionDescription;

	//父权限
//	@ManyToOne(fetch = FetchType.LAZY)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "permission_parent_id")
	private SystemPermissionInfo parentPermission;

	// 子权限集合
	@OneToMany(mappedBy = "parentPermission", fetch = FetchType.LAZY)
	@Cascade({CascadeType.DELETE})
	private List<SystemPermissionInfo> childs = new ArrayList<SystemPermissionInfo>();
	
	// 角色集合
	@OneToMany(mappedBy = "systemPermission", fetch = FetchType.LAZY)
	private List<SystemRolePermissionInfo> roles = new ArrayList<SystemRolePermissionInfo>();
	
	public SystemPermissionInfo() {
		super();
	}
	
	public SystemPermissionInfo(Long permissionId) {
		super();
		this.permissionId = permissionId;
	}
	
	public SystemPermissionInfo(String permissionName, String url, String permissionDescription,
			SystemPermissionInfo parentPermission) {
		super();
		this.permissionName = permissionName;
		this.url = url;
		this.permissionDescription = permissionDescription;
		this.parentPermission = parentPermission;
	}



	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPermissionDescription() {
		return permissionDescription;
	}

	public void setPermissionDescription(String permissionDescription) {
		this.permissionDescription = permissionDescription;
	}

	public SystemPermissionInfo getParentPermission() {
		return parentPermission;
	}

	public void setParentPermission(SystemPermissionInfo parentPermission) {
		this.parentPermission = parentPermission;
	}

	public List<SystemPermissionInfo> getChilds() {
		return childs;
	}

	public void setChilds(List<SystemPermissionInfo> childs) {
		this.childs = childs;
	}

	public List<SystemRolePermissionInfo> getRoles() {
		return roles;
	}

	public void setRoles(List<SystemRolePermissionInfo> roles) {
		this.roles = roles;
	}
}
