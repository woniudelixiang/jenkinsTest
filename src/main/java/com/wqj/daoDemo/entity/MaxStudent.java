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
@Table(name = "maxStudent")
public class MaxStudent extends BaseEntity{

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ms_id")
	private long msId;
	
	@Column(name = "m_name")
	private String mName;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bsId")
	private BigStudent bigStudent;

	public long getMsId() {
		return msId;
	}

	public void setMsId(long msId) {
		this.msId = msId;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public BigStudent getBigStudent() {
		return bigStudent;
	}

	public void setBigStudent(BigStudent bigStudent) {
		this.bigStudent = bigStudent;
	}
}
