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
package com.wqj.common.bean;

/**
 * @author Jiafa Lv
 * @email simon-jiafa@126.com
 * @date 2014-6-16 下午4:05:56
 */
public class ReturnTypeInfo {
	private int rtype = 0;
	
	
	public ReturnTypeInfo() {
		super();
	}
	
	public ReturnTypeInfo(int rtype){
		this.rtype = rtype;
	}

	/**
	 * @return the rtype
	 */
	public int getRtype() {
		return rtype;
	}

	/**
	 * @param rtype the rtype to set
	 */
	public void setRtype(int rtype) {
		this.rtype = rtype;
	}
	
	
}
