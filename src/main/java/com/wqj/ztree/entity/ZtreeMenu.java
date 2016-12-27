package com.wqj.ztree.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wqj.common.orm.entity.BaseEntity;

@Entity
@Table(name = "ztree_menu")
@SuppressWarnings("serial")
public class ZtreeMenu  extends BaseEntity{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	
	// 菜单名称
	@Column(name = "name")
	private String name;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_id")
	private ZtreeMenu pId;
	
	// 点击跳转地址
	@Column(name = "url")
	private String url;
	
	//是否展开     0不展开    1已展开
	@Column(name = "open", columnDefinition = "int(11) default 0")
	private Integer open = 0;
	
	// 等级
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "rank_id")
	private ZtreeRank rank;

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

	public ZtreeMenu getpId() {
		return pId;
	}

	public void setpId(ZtreeMenu pId) {
		this.pId = pId;
	}

	public Integer getOpen() {
		return open;
	}

	public void setOpen(Integer open) {
		this.open = open;
	}

	public ZtreeRank getRank() {
		return rank;
	}

	public void setRank(ZtreeRank rank) {
		this.rank = rank;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
