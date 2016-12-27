package com.wqj.common.enums;

/**
 * @author Jiafa Lv
 * @email simon-jiafa@126.com
 * @date 2014-6-16 下午4:07:53
 */
public enum ReturnType {
	ZREO(0), 
	ONE(1), 
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	TEN(10);
	int type;
	
	ReturnType(int type){
		this.type = type;
	}
	
	/**
	 * @return the status
	 */
	public int getType() {
		return type;
	}
}
