/*****************************************************************************
 *
 *                      FORNOW PROPRIETARY INFORMATION
 *
 *          The information contained herein is proprietary to ForNow
 *           and shall not be reproduced or disclosed in whole or in part
 *                    or used for any design or manufacture
 *              without direct written authorization from ForNow.
 *
 *            Copyright (c) 2014 by ForNow.  All rights reserved.
 *
 *****************************************************************************/
package com.wqj.common.enums;

/**
 * @author Jiafa Lv
 * @email simon-jiafa@126.com
 * @date 2014-6-24 下午2:03:21
 */
public enum AuthodType {
	simon("吕加法"), 
	wangqijun("王启军"),
	gesheng("葛升"),
	liujun("刘军"),
	chenle("陈乐");
	String name;
	
	AuthodType(String name){
		this.name = name;
	}
	
	/**
	 * @return the status
	 */
	public String getName() {
		return name;
	}
}
