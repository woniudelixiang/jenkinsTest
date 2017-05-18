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

@SuppressWarnings("serial")
@Entity
@Table(name = "department")
public class DepartmentInfo extends BaseEntity {

	@Id
	@GeneratedValue(generator = "identity")
	@GenericGenerator(name = "identity", strategy = "identity")
	@Column(name = "department_id", columnDefinition = "bigint")
	private Long departmentId;

	// 部门名称
	@Column(name = "department_name", columnDefinition = "varchar(20)")
	private String departmentName;
	
	// 角色说明
	@Column(name = "department_description", columnDefinition = "longtext")
	private String departmentDescription;
	
	// 上级部门
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_parent_id")
	private DepartmentInfo parentDepartment;

	// 子部门集合
	@OneToMany(mappedBy = "parentDepartment", fetch = FetchType.LAZY)
	@Cascade({CascadeType.DELETE})
	private List<DepartmentInfo> childs = new ArrayList<DepartmentInfo>();

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentDescription() {
		return departmentDescription;
	}

	public void setDepartmentDescription(String departmentDescription) {
		this.departmentDescription = departmentDescription;
	}

	public DepartmentInfo getParentDepartment() {
		return parentDepartment;
	}

	public void setParentDepartment(DepartmentInfo parentDepartment) {
		this.parentDepartment = parentDepartment;
	}

	public List<DepartmentInfo> getChilds() {
		return childs;
	}

	public void setChilds(List<DepartmentInfo> childs) {
		this.childs = childs;
	}
	
}
