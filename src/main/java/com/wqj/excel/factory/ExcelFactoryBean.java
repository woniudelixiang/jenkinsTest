package com.wqj.excel.factory;

import java.util.Map;

import com.wqj.excel.base.AbstractExportExcelUtils;
import com.wqj.excel.bean.Resource;

public class ExcelFactoryBean extends AbstractExportExcelUtils{
	
	private static ExcelFactoryBean factory =null;
	
	private ExcelFactoryBean(){
		
	}

	@SuppressWarnings("static-access")
	@Override
	public void setMap(Map<String, Resource> map) {
		super.map = map;
		logger.info("-----------------map.size : "+super.getMap().size());
	}
	
	public static ExcelFactoryBean createInstance(){
		if(factory ==null){
			factory = new ExcelFactoryBean();
		}
		return factory;
	}
}
