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
@Table(name = "student")
public class Student extends BaseEntity{

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "student_id")
	private long studentId;
	
	@Column(name = "nickname")
	private String nickname;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToOne(mappedBy = "student")
	private BigStudent bigStudent;
	
	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", nickname=" + nickname
				+ ", user=" + user.getUserId() + "]";
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
