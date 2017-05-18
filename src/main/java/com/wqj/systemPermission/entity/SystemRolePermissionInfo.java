package com.wqj.systemPermission.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wqj.common.orm.entity.BaseEntity;

/**
 * 角色权限信息表
 * @author Qijun wang
 * @email wqjjob@126.com
 * @date 2017年3月31日 下午1:33:38
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "sys_role_permission")
public class SystemRolePermissionInfo extends BaseEntity {

	@Id
	@GeneratedValue(generator = "identity")
	@GenericGenerator(name = "identity", strategy = "identity")
	@Column(name = "role__permission_id", columnDefinition = "bigint")
	private Long rolePermissionId;
	
	// 角色外键
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	private SystemRoleInfo systemRole;

	// 权限外键
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "permission_id")
	private SystemPermissionInfo systemPermission;

	public Long getRolePermissionId() {
		return rolePermissionId;
	}

	public void setRolePermissionId(Long rolePermissionId) {
		this.rolePermissionId = rolePermissionId;
	}

	public SystemRoleInfo getSystemRole() {
		return systemRole;
	}

	public void setSystemRole(SystemRoleInfo systemRole) {
		this.systemRole = systemRole;
	}

	public SystemPermissionInfo getSystemPermission() {
		return systemPermission;
	}

	public void setSystemPermission(SystemPermissionInfo systemPermission) {
		this.systemPermission = systemPermission;
	}
}
