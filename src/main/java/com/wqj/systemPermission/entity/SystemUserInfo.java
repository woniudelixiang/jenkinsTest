package com.wqj.systemPermission.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.wqj.common.orm.entity.BaseEntity;

/**
 * 系统用户信息表
 * @author Qijun wang
 * @email wqjjob@126.com
 * @date 2017年3月31日 下午1:36:57
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "sys_user")
public class SystemUserInfo extends BaseEntity implements HttpSessionBindingListener{

	@Id
	@GeneratedValue(generator = "identity")
	@GenericGenerator(name = "identity", strategy = "identity")
	@Column(name = "user_id", columnDefinition = "bigint")
	private Long userId;
	
	//用户名
	@Column(name = "user_name", columnDefinition = "varchar(20)")
	private String userName;

	// 密码
	@Column(name = "password", columnDefinition = "varchar(20)")
	private String password;

	//用户类型     0:普通用户    1：管理员     2：超级管理员  
	@Column(name = "user_type",columnDefinition = "int(11) default 0")
	private Integer userType;
	
	//父用户
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_parent_id")
	private SystemUserInfo parentUser;

	// 部门
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id")
	private DepartmentInfo department;
	
	// 子用户集合
	@OneToMany(mappedBy = "parentUser", fetch = FetchType.LAZY)
	@Cascade({CascadeType.DELETE})
	private List<SystemUserInfo> childs = new ArrayList<SystemUserInfo>();
	
	// 角色集合
//	@ManyToMany
//	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)//使用hibernate注解级联保存和更新
//	@JoinTable(name = "t_user_role",
//	joinColumns = {@JoinColumn(name = "user_id")},//JoinColumns定义本方在中间表的主键映射
//	inverseJoinColumns = {@JoinColumn(name = "role_id")})//inverseJoinColumns定义另一在中间表的主键映射
//	private List<SystemRoleInfo> roles = new ArrayList<SystemRoleInfo>();
	
//	http://www.cnblogs.com/lj95801/p/5008519.html
//	http://blog.csdn.net/xrt95050/article/details/7322222
	
	
//	@OneToMany(fetch = FetchType.EAGER) /*mappedBy = "systemUser"*/ 
//	@OneToMany(mappedBy = "systemUser")
//	private Set<SystemUserRoleInfo> roles = new HashSet<SystemUserRoleInfo>();
	/**
	 * （1）ManyToOne（多对一）单向：不产生中间表，但可以用@Joincolumn（name="  "）来指定生成外键的名字，外键在多的一方表中产生！
	 * （2）OneToMany（一对多）单向：会产生中间表，此时可以用@onetoMany @Joincolumn（name=" "）避免产生中间表，并且指定了外键的名字（别看@joincolumn在一中写着，但它存在在多的那个表中）
	 * （3）OneToMany ,ManyToOne 双向（两个注解一起用的）：如果不在@OneToMany中加mappedy属性就会产生中间表，此时通常在@ManyToOne的注解下再添上注解@Joincolumn(name=" ")来指定外键的名字(说明：多的一方为关系维护端，关系维护端负责外键记录的更新，关系被维护端没有权利更新外键记录)！（@OneToMany(mappedBy="一对多中，多中一的属性")出现mapby为被维护端|||默认为延迟加载)
	 */
	/**
	 * 第一种配置：关联关系由一的一方来维护，在多的一方配置是：	@ManyToOne(fetch = FetchType.LAZY)
                                                @JoinColumn(name = "user_id", insertable=false, updatable=false)
	 * @OneToMany()
	 * @Cascade({CascadeType.ALL})
	 * @JoinColumn(name = "user_id") // 必须写，否则会生成一个连接表
	 */
	/**
	 * 第二种配置：关联关系有多的一方维护
	 * 
	 */
	@OneToMany(mappedBy = "systemUser",fetch = FetchType.EAGER)
	@Cascade({CascadeType.ALL})
	private List<SystemUserRoleInfo> roles = new ArrayList<SystemUserRoleInfo>();
	
	public boolean isHaveMenu(Long id){
		if(userType==2){ // 超级管理员拥有所有的权限
			return true;
		}
		
		for (SystemUserRoleInfo userRole : roles) {
			List<SystemRolePermissionInfo> permissions = userRole.getSystemRole().getPermissions();
			for (SystemRolePermissionInfo rolePermission : permissions) {
				 if(rolePermission.getSystemPermission().getPermissionId().equals(id)){
					 return true;
				 }
			}
		}
		return false;
	}
	
	public boolean isHavePermissionMenu(String url){
		if(userType==2){ // 超级管理员拥有所有的权限
			return true;
		}
		
		for (SystemUserRoleInfo userRole : roles) {
			List<SystemRolePermissionInfo> permissions = userRole.getSystemRole().getPermissions();
			for (SystemRolePermissionInfo rolePermission : permissions) {
				if(StringUtils.isNotBlank(rolePermission.getSystemPermission().getUrl())){
					System.out.println("url : " + url.replace("UI", ""));
					System.out.println("getUrl() : " + rolePermission.getSystemPermission().getUrl());
					System.out.println(url.replace("UI", "").contains(rolePermission.getSystemPermission().getUrl()));
					if(url.replace("UI", "").contains(rolePermission.getSystemPermission().getUrl())){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public SystemUserInfo getParentUser() {
		return parentUser;
	}

	public void setParentUser(SystemUserInfo parentUser) {
		this.parentUser = parentUser;
	}

	public List<SystemUserInfo> getChilds() {
		return childs;
	}

	public void setChilds(List<SystemUserInfo> childs) {
		this.childs = childs;
	}

//	public Set<SystemUserRoleInfo> getRoles() {
//		return roles;
//	}
//
//
//	public void setRoles(Set<SystemUserRoleInfo> roles) {
//		this.roles = roles;
//	}


	public Integer getUserType() {
		return userType;
	}

	public List<SystemUserRoleInfo> getRoles() {
		return roles;
	}


	public void setRoles(List<SystemUserRoleInfo> roles) {
		this.roles = roles;
	}


	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public DepartmentInfo getDepartment() {
		return department;
	}
	public void setDepartment(DepartmentInfo department) {
		this.department = department;
	}
	
	
//	public static void main(String[] args) {
//		
//		String getUrl = "sys/department/editDepartment";
//		String url = "/dao-study/sys/department/editDepartment/1";
//		System.out.println(url.contains(getUrl));
//	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SystemUserInfo other = (SystemUserInfo) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	// 当该用户放进HttpSession时，该方法会执行
	@Override
	@SuppressWarnings("unchecked")
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println(this.userName + " 用户放进session了..........");
		HttpSession session = event.getSession();
		ServletContext sc = session.getServletContext();
		Map<SystemUserInfo,HttpSession> onlineUserMap = (Map<SystemUserInfo, HttpSession>) sc.getAttribute("onlineUserMap");
		onlineUserMap.put(this, session);
	}

	// 当该用户与HttpSession 解绑时，该方法会执行
	@Override
	@SuppressWarnings("unchecked")
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println(this.userName + " 用户与session解绑了..........");
		HttpSession session = event.getSession();
		ServletContext sc = session.getServletContext();
		Map<SystemUserInfo,HttpSession> onlineUserMap = (Map<SystemUserInfo, HttpSession>) sc.getAttribute("onlineUserMap");
		onlineUserMap.remove(this);
	}
}
