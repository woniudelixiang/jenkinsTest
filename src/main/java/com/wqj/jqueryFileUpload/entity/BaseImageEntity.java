package com.wqj.jqueryFileUpload.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import com.wqj.common.orm.entity.BaseEntity;

@SuppressWarnings("serial")
@MappedSuperclass
public class BaseImageEntity extends BaseEntity{

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	
	//原文件名称
	@Column(name = "name")
	private String name;
	
	//上传后的文件名
	@Column(name = "newFilename")
	private String newFilename;
	
	//文件大小
	@Column(name = "size", columnDefinition = "bigint(20) default 0")
	private long size = 0;

	@Transient
	private String url;
	@Transient
	private String thumbnailUrl;
	@Transient
	private String deleteUrl;
	@Transient
	private String deleteType;
	
	@Override
	public String toString() {
		return "BaseImageEntity [id=" + id + ", name=" + name + ", newFilename=" + newFilename + ", size=" + size
				+ ", url=" + url + ", thumbnailUrl=" + thumbnailUrl + ", deleteUrl=" + deleteUrl + ", deleteType="
				+ deleteType + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNewFilename() {
		return newFilename;
	}

	public void setNewFilename(String newFilename) {
		this.newFilename = newFilename;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getDeleteUrl() {
		return deleteUrl;
	}

	public void setDeleteUrl(String deleteUrl) {
		this.deleteUrl = deleteUrl;
	}

	public String getDeleteType() {
		return deleteType;
	}

	public void setDeleteType(String deleteType) {
		this.deleteType = deleteType;
	}
}
