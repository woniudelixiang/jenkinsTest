package com.wqj.lottery.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import com.wqj.common.orm.entity.BaseEntity;

/**
 * 奖品实体
 * @author Qijun wang
 * @email wqjjob@126.com
 * @date 2016年7月9日 下午4:08:18
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "prize")
public class Prize extends BaseEntity {

	@Id
	@GeneratedValue(generator = "identity")
	@GenericGenerator(name = "identity", strategy = "identity")
	//菜单的id
	private Long id;
	
	// 奖品名称
	@Column(name = "name")
	private String name;
	
	// 概率
	@Column(name = "probability", columnDefinition = "int(11) default 0")
	private Integer probability;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getProbability() {
		return probability;
	}

	public void setProbability(Integer probability) {
		this.probability = probability;
	}
}
