package com.wqj.excel.base;

import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import com.wqj.excel.bean.Resource;

public abstract class AbstractExportExcelUtils {

	protected static  Logger logger = Logger.getLogger(AbstractExportExcelUtils.class);
	
	protected static  Map<String,Resource> map = new LinkedHashMap<String,Resource>();
	

	public AbstractExportExcelUtils(){
		
	}

	@SuppressWarnings("static-access")
	public AbstractExportExcelUtils(Map<String,Resource> map ){
		this.map = map;
	}
	
	/*
	 * 获取单个resource
	 */
	public synchronized static Resource getRes(String idName){
		return map.get(idName);
	}
	
	/**
	 * @return the map
	 */
	public Map<String, Resource> getMap() {
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public abstract void setMap(Map<String, Resource> map);

	/**
	 * @return the logger
	 */
	public Logger getLogger() {
		return logger;
	}

	/**
	 * @param logger the logger to set
	 */
	@SuppressWarnings("static-access")
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	
	
	
}
