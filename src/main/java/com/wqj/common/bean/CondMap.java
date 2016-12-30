package com.wqj.common.bean;

import java.util.Map;

public class CondMap<k,v> {

	Map<k,v> cond;

	public Map<k, v> getCond() {
		return cond;
	}

	public void setCond(Map<k, v> cond) {
		this.cond = cond;
	}

	@Override
	public String toString() {
		return "CondMap [cond=" + cond + "]";
	}
}
