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
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wqj.common.orm.entity.BaseEntity;

/**
 * 用户角色信息表
 * @author Qijun wang
 * @email wqjjob@126.com
 * @date 2017年3月31日 下午1:33:38
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "sys_user_role")
public class SystemUserRoleInfo extends BaseEntity {

	@Id
	@GeneratedValue(generator = "identity")
	@GenericGenerator(name = "identity", strategy = "identity")
	@Column(name = "user_role_id", columnDefinition = "bigint")
	private Long userRoleId;
	
	// 用户外键
	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "user_id", insertable=false, updatable=false)
	@JoinColumn(name = "user_id")
	private SystemUserInfo systemUser;
	
	// 角色外键
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private SystemRoleInfo systemRole;

	public Long getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}

	public SystemUserInfo getSystemUser() {
		return systemUser;
	}

	public void setSystemUser(SystemUserInfo systemUser) {
		this.systemUser = systemUser;
	}

	public SystemRoleInfo getSystemRole() {
		return systemRole;
	}

	public void setSystemRole(SystemRoleInfo systemRole) {
		this.systemRole = systemRole;
	}

	public static List<SystemUserRoleInfo> getUserRoleList(List<Long> roleIds, SystemUserInfo systemUser) {
		List<SystemUserRoleInfo> userRoleList = new ArrayList<SystemUserRoleInfo>();
		if(roleIds!=null){
			for (Long roleId : roleIds) {
				SystemUserRoleInfo userRole = new SystemUserRoleInfo();
				userRole.setSystemRole(new SystemRoleInfo(roleId));
				userRole.setSystemUser(systemUser);
				userRoleList.add(userRole);
			}
		}
		return userRoleList;
	}
}
