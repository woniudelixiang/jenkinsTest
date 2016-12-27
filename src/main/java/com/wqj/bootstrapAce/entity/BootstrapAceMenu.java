package com.wqj.bootstrapAce.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;
import com.wqj.common.orm.entity.BaseEntity;
import com.wqj.menuStudy.entity.NodeModel;

@Entity
@SuppressWarnings("serial")
@Table(name = "bootstrap_ace_menu")
public class BootstrapAceMenu extends BaseEntity{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@Expose  
	private long id;
	
	@Column(name = "text")
	@Expose
	private String text;
	
	@Column(name = "icon")
	@Expose
	private String icon;
	
	@Column(name = "url")
	@Expose
	private String url;

	//父菜单
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_id")
	private BootstrapAceMenu parent;
	
	@Transient
	@Expose
	private List<? extends BootstrapAceMenu> childs = new ArrayList<BootstrapAceMenu>();
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public BootstrapAceMenu getParent() {
		return parent;
	}

	public void setParent(BootstrapAceMenu parent) {
		this.parent = parent;
	}

	public List<? extends BootstrapAceMenu> getChilds() {
		return childs;
	}

	public void setChilds(List<? extends BootstrapAceMenu> childs) {
		this.childs = childs;
	}
}
