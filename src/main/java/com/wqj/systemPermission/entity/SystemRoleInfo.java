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
 * 角色信息表
 * @author Qijun wang
 * @email wqjjob@126.com
 * @date 2017年3月31日 下午1:36:46
 */
@SuppressWarnings("serial")
@Entity
//@org.hibernate.annotations.Entity(dynamicUpdate=true,dynamicInsert=true)
@Table(name = "sys_role")
//@DynamicUpdate(true)
//@DynamicInsert(true)
//@SelectBeforeUpdate(true)
public class SystemRoleInfo extends BaseEntity {

	@Id
	@GeneratedValue(generator = "identity")
	@GenericGenerator(name = "identity", strategy = "identity")
	@Column(name = "role_id", columnDefinition = "bigint")
	private Long roleId;
	
	//角色名称
	@Column(name = "role_name", columnDefinition = "varchar(20)")
	private String roleName;
	
	// 角色说明
	@Column(name = "role_description", columnDefinition = "longtext")
	private String roleDescription;
	
	//父角色
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_parent_id")
	private SystemRoleInfo parentRole;

	// 子角色集合
	@OneToMany(mappedBy = "parentRole", fetch = FetchType.LAZY)
	@Cascade({CascadeType.DELETE})
	private List<SystemRoleInfo> childs = new ArrayList<SystemRoleInfo>();
	
	// 权限集合
	@OneToMany(mappedBy = "systemRole", fetch = FetchType.EAGER)
	@Cascade({CascadeType.DELETE})
	private List<SystemRolePermissionInfo> permissions = new ArrayList<SystemRolePermissionInfo>();
	
//	@ManyToMany
//	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)//使用hibernate注解级联保存和更新
//	@JoinTable(name = "t_user_role",
//	joinColumns = {@JoinColumn(name = "role_id")},//JoinColumns定义本方在中间表的主键映射
//	inverseJoinColumns = {@JoinColumn(name = "user_id")})//inverseJoinColumns定义另一在中间表的主键映射
//	private List<SystemUserInfo> users = new ArrayList<SystemUserInfo>();
	
	public SystemRoleInfo(Long roleId) {
		super();
		this.roleId = roleId;
	}

	public SystemRoleInfo() {
		super();
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public SystemRoleInfo getParentRole() {
		return parentRole;
	}

	public void setParentRole(SystemRoleInfo parentRole) {
		this.parentRole = parentRole;
	}

	public List<SystemRoleInfo> getChilds() {
		return childs;
	}

	public void setChilds(List<SystemRoleInfo> childs) {
		this.childs = childs;
	}

	public List<SystemRolePermissionInfo> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<SystemRolePermissionInfo> permissions) {
		this.permissions = permissions;
	}
}
