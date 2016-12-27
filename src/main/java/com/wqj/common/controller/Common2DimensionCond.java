package com.wqj.common.controller;

import java.util.ArrayList;
import java.util.Date;

import com.wqj.common.Const;
import com.wqj.common.util.DateUtils;

/** 
 * @author George E-mail:lendun@163.com  
 * @version 创建时间：2013-7-10 下午04:38:30  
 *  
 */
public class Common2DimensionCond {

//	private List<StatisticsLevelSetting> statisLevelSettings;
	
	private String startDateStr;    						// 映射表单起始时间控件
	private String endDateStr;      						// 映射表单结束时间控件
	private Date startDate;  								// 起始字段
	private Date endDate;    								// 结束字段
	
	private String 		seleClause;									// select主句
	private String  	whereClause = Const.NULL_VALUE_STRING;		// where子句
	private String      colField = Const.NULL_VALUE_STRING;			// 列条件字段名
	private String      rowField = Const.NULL_VALUE_STRING; 		// 行字段名
	
	private ArrayList<String> rowObjectiveValues;					// 行目标值
	private ArrayList<String> rowObjectiveTitles;					//
	private ArrayList<String> colObjectiveValues;					// 列目标值
	private ArrayList<String> colObjectiveTitles;					//
	
	public String getStartDateStr() {
		return startDateStr;
	}
	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
		if(startDateStr != null){
			startDate = DateUtils.string2Date(startDateStr);
		}
	}
	public String getEndDateStr() {
		return endDateStr;
	}
	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
		if(endDateStr != null){
			endDate = DateUtils.string2Date(endDateStr);
		}
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
//	public List<StatisticsLevelSetting> getStatisLevelSettings() {
//		return statisLevelSettings;
//	}
//	public void setStatisLevelSettings(
//			List<StatisticsLevelSetting> statisLevelSettings) {
//		this.statisLevelSettings = statisLevelSettings;
//	}
	public String getSeleClause() {
		return seleClause;
	}
	public void setSeleClause(String seleClause) {
		this.seleClause = seleClause;
	}
	public String getWhereClause() {
		return whereClause;
	}
	public void setWhereClause(String whereClause) {
		this.whereClause = whereClause;
	}
	public String getColField() {
		return colField;
	}
	public void setColField(String colField) {
		this.colField = colField;
	}
	public String getRowField() {
		return rowField;
	}
	public void setRowField(String rowField) {
		this.rowField = rowField;
	}
	public ArrayList<String> getRowObjectiveValues() {
		return rowObjectiveValues;
	}
	public void setRowObjectiveValues(ArrayList<String> rowObjectiveValues) {
		this.rowObjectiveValues = rowObjectiveValues;
	}
	public ArrayList<String> getRowObjectiveTitles() {
		return rowObjectiveTitles;
	}
	public void setRowObjectiveTitles(ArrayList<String> rowObjectiveTitles) {
		this.rowObjectiveTitles = rowObjectiveTitles;
	}
	public ArrayList<String> getColObjectiveValues() {
		return colObjectiveValues;
	}
	public void setColObjectiveValues(ArrayList<String> colObjectiveValues) {
		this.colObjectiveValues = colObjectiveValues;
	}
	public ArrayList<String> getColObjectiveTitles() {
		return colObjectiveTitles;
	}
	public void setColObjectiveTitles(ArrayList<String> colObjectiveTitles) {
		this.colObjectiveTitles = colObjectiveTitles;
	}
}
