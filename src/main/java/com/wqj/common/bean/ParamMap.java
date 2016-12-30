package com.wqj.common.bean;

import java.util.Map;

public class ParamMap<k,v> {
	Map<k,v> param;

	public Map<k, v> getParam() {
		return param;
	}

	public void setParam(Map<k, v> param) {
		this.param = param;
	}

	@Override
	public String toString() {
		return "ParamMap [param=" + param + "]";
	}
	
}
