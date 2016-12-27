package com.wqj.excel.bean;

import java.util.Comparator;

public class BeanComparator implements Comparator<ExcelContextBean> {

	@Override
	public int compare(ExcelContextBean o1, ExcelContextBean o2) {
		// TODO Auto-generated method stub
		return o1.getRowIndex()  - o2.getRowIndex();
	}

}
