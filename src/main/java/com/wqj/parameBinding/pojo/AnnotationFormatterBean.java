package com.wqj.parameBinding.pojo;

import com.wqj.parameBinding.util.formatter.anotation.DateToLong;

public class AnnotationFormatterBean {

	@DateToLong(affter=" 00:00:00")
	public Long startTime;
	
	@DateToLong(affter=" 23:59:59")
	public Long endTime;

	public Long version;
	
	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
}
