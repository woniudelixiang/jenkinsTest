package com.wqj.common.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 接口返回数据模型
 * @author Qijun wang
 * @email wqjjob@126.com
 * @date 2016年7月9日 下午3:21:49
 */
public class CommonJsonBean {

	public static final String MSG_SUCCES = "OK";
	public static final int CODE_SUCCESS = 0; // 正常
	public static final int CODE_FAIL = 1; // 参数有误
	
	protected List<?> datas;
	protected int statusCode;
	protected String message;
	
	public CommonJsonBean(){
		datas = new ArrayList<Object>();
		message = MSG_SUCCES;
		statusCode = CODE_SUCCESS;		
	}
	
	public CommonJsonBean(int statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}
	
	public CommonJsonBean(List<?> datas, int statusCode, String message) {
		this.datas = datas;
		this.statusCode = statusCode;
		this.message = message;
	}

	public List<?> getDatas() {
		return datas;
	}

	public void setDatas(List<?> datas) {
		this.datas = datas;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static String getMsgSucces() {
		return MSG_SUCCES;
	}

	public static int getCodeSuccess() {
		return CODE_SUCCESS;
	}

	public static int getCodeFail() {
		return CODE_FAIL;
	}
}
