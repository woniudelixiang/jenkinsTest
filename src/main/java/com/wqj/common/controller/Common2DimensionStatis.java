package com.wqj.common.controller;

import java.util.ArrayList;

/** 
 * @author George E-mail:lendun@163.com  
 * @version 创建时间：2013-7-10 下午04:41:22  
 *  
 */
public class Common2DimensionStatis {

	// 列标题
	private ArrayList<String> colTitles = new ArrayList<String>();
	// 单元值一
	ArrayList<String[]> cellOneValues = new ArrayList<String[]>() ;
	// 单元值二
	ArrayList<String[]> cellTwoValues = new ArrayList<String[]>() ;
	// 一维结果值
	ArrayList<SimpleStatisObject> baseArrayValues = new ArrayList<SimpleStatisObject>();
	
	public ArrayList<String> getColTitles() {
		return colTitles;
	}
	public void setColTitles(ArrayList<String> colTitles) {
		this.colTitles = colTitles;
	}
	public ArrayList<String[]> getCellOneValues() {
		return cellOneValues;
	}
	public void setCellOneValues(ArrayList<String[]> cellOneValues) {
		this.cellOneValues = cellOneValues;
	}
	public ArrayList<String[]> getCellTwoValues() {
		return cellTwoValues;
	}
	public void setCellTwoValues(ArrayList<String[]> cellTwoValues) {
		this.cellTwoValues = cellTwoValues;
	}
	public ArrayList<SimpleStatisObject> getBaseArrayValues() {
		return baseArrayValues;
	}
	public void setBaseArrayValues(ArrayList<SimpleStatisObject> baseArrayValues) {
		this.baseArrayValues = baseArrayValues;
	}
	
	
}
