package com.wqj.jqueryFileUpload.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wqj.common.orm.entity.BaseEntity;

@SuppressWarnings("serial")
@Entity
@Table(name = "img_user")
public class ImgUser extends BaseEntity{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private long userId;
	
	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;
	
	/*@OneToMany(mappedBy = "imgUser")
	private Set<Image> images;*/
	
	@Override
	public String toString() {
		return "ImgUser [userId=" + userId + ", username=" + username
				+ ", password=" + password + "]";
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
