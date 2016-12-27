package com.wqj.jqueryFileUpload.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Qijun wang
 * @email wqjjob@126.com
 * @date 2016-4-13 上午9:09:38
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "images")
public class Image extends BaseImageEntity{
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private ImgUser user;

	// 是否是背景图片 0不是    1是的
	@Column(name = "cover", columnDefinition = "int(20) default 0")
	private Integer cover = 0;
	
	
	public ImgUser getUser() {
		return user;
	}

	public void setUser(ImgUser user) {
		this.user = user;
	}

	public Integer getCover() {
		return cover;
	}

	public void setCover(Integer cover) {
		this.cover = cover;
	}
}
