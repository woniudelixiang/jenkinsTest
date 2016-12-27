package com.wqj.ztree.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.wqj.common.orm.entity.BaseEntity;

@Entity
@Table(name = "ztree_rank")
@SuppressWarnings("serial")
public class ZtreeRank  extends BaseEntity{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	
	// 等级名称
	@Column(name = "rank_name")
	private String rankName;
	
	// 等级值
	@Column(name = "rank_value")
	private Integer rankValue = 1;
	
	@OneToMany(mappedBy = "rank")
	private List<ZtreeMenu> ztreeMenus;
	
	@OneToOne(mappedBy = "rank")
	private RandIcon randIcon;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRankName() {
		return rankName;
	}

	public void setRankName(String rankName) {
		this.rankName = rankName;
	}

	public Integer getRankValue() {
		return rankValue;
	}

	public void setRankValue(Integer rankValue) {
		this.rankValue = rankValue;
	}
	
	
	public List<ZtreeMenu> getZtreeMenus() {
		return ztreeMenus;
	}

	public void setZtreeMenus(List<ZtreeMenu> ztreeMenus) {
		this.ztreeMenus = ztreeMenus;
	}

	public RandIcon getRandIcon() {
		return randIcon;
	}

	public void setRandIcon(RandIcon randIcon) {
		this.randIcon = randIcon;
	}
	
}
