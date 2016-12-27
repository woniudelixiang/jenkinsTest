/**
 * 
 */
package com.wqj.gson.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wqj.common.annotation.JsonCycleFilter;
import com.wqj.common.orm.entity.BaseEntity;

@Entity
@Table(name = "fornow_user")
@SuppressWarnings("serial")
public class Users extends BaseEntity{
	
	@Id
	@GeneratedValue(generator = "identity")
	@GenericGenerator(name = "identity", strategy = "identity")
	@Column(name = "user_id", columnDefinition = "bigint")
	private Long userId;
	
	private String username;
	
	// 学生
	@JsonCycleFilter
	@OneToOne(mappedBy = "user")
	private Students students;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Students getStudent() {
		return students;
	}

	public void setStudent(Students student) {
		this.students = student;
	}
}
