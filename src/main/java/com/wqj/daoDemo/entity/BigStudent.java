package com.wqj.daoDemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.wqj.common.orm.entity.BaseEntity;

@SuppressWarnings("serial")
@Entity
@Table(name = "bigStudent")
public class BigStudent extends BaseEntity{

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bs_id")
	private long bsId;
	
	@Column(name = "b_name")
	private String bName;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "student_id")
	private Student student;

	@OneToOne(mappedBy = "bigStudent")
	private MaxStudent maxStudent;
	
	public long getBsId() {
		return bsId;
	}

	public void setBsId(long bsId) {
		this.bsId = bsId;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
