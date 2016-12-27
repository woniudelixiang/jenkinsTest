package com.wqj.menuStudy.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.wqj.common.Const;
import com.wqj.common.orm.entity.BaseEntity;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-29
 */
@SuppressWarnings("serial")
@MappedSuperclass
public class NodeModel extends BaseEntity {

	public static final String CHECKED = "checked";
	//是否展开        0不展开        1 展开
	private Boolean expanded = true;  
	//是否是叶子节点    0否    1是
	private Boolean leaf = false; 

	@Transient
	private boolean last;
	@Transient
	private boolean first;
	@Transient
	private String[] rows;
	@Transient
	private int level;
	@Transient
	private List<? extends NodeModel> child = new ArrayList<NodeModel>();


	public Boolean getExpanded() {
		return expanded;
	}

	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}

	public Boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public String[] getRows() {
		return rows;
	}

	public void setRows(String[] rows) {
		this.rows = rows;
	}

	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<? extends NodeModel> getChild() {
		return child;
	}

	public void setChild(List<? extends NodeModel> child) {
		this.child = child;
	}

}
