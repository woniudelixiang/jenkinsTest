package com.wqj.common.helper.pojo;

import java.util.ArrayList;


import org.joda.time.DateTime;

public class Test1 {
	private Long id;
	
	private String name;
	
	private Integer type;
	
	private Long endTime;

	private Subject subject;
	
	private Long[] arg ;
	
	private ArrayList<Subject> subjects ;
	
	private DateTime dateTime;

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Long[] getArg() {
		return arg;
	}

	public void setArg(Long[] arg) {
		this.arg = arg;
	}

	public ArrayList<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(ArrayList<Subject> subjects) {
		this.subjects = subjects;
	}

	public DateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(DateTime dateTime) {
		this.dateTime = dateTime;
	}
}
