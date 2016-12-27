package com.wqj.gson.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import com.wqj.common.orm.entity.BaseEntity;

/**
 * 班型基础表
 * @author yangliwei
 * @date 2016-10-11 下午2:15:17
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "fornow_class_type")
public class ClassTypeInfo extends BaseEntity {
	@Id
	@GeneratedValue(generator = "identity")
	@GenericGenerator(name = "identity", strategy = "identity")
	@Column(name = "class_type_id", columnDefinition = "bigint")
	private Long classTypeId;
	
	// 班型
	@Column(name = "class_name")
	private String className;

	public Long getClassTypeId() {
		return classTypeId;
	}

	public void setClassTypeId(Long classTypeId) {
		this.classTypeId = classTypeId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
}
