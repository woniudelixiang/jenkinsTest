package com.wqj.ztree.entity;

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

@Entity
@Table(name = "ztree_icon")
@SuppressWarnings("serial")
public class RandIcon  extends BaseEntity{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	
	// 叶子节点的图标
	@Column(name = "icon")
	private String icon;
	
	// 菜单打开时的图标
	@Column(name = "icon_open")
	private String iconOpen;
	
	// 菜单关闭时的图标
	@Column(name = "icon_close")
	private String iconClose;
	
	// 等级
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "rank_id")
	private ZtreeRank rank;

	public RandIcon() {
		super();
	}

	public RandIcon(long id, String iconOpen, String iconClose, ZtreeRank rank) {
		this.id = id;
		this.iconOpen = iconOpen;
		this.iconClose = iconClose;
		this.rank = rank;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIconOpen() {
		return "http://wqjpassport.6655.la:24801/"+iconOpen;
	}

	public void setIconOpen(String iconOpen) {
		this.iconOpen = iconOpen;
	}

	public String getIconClose() {
		return "http://wqjpassport.6655.la:24801/"+iconClose;
	}

	public void setIconClose(String iconClose) {
		this.iconClose = iconClose;
	}

	public ZtreeRank getRank() {
		return rank;
	}

	public void setRank(ZtreeRank rank) {
		this.rank = rank;
	}

	public String getIcon() {
		return "http://wqjpassport.6655.la:24801/"+icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
