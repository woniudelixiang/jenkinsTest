/*****************************************************************************
 *
 *                      FORNOW PROPRIETARY INFORMATION
 *
 *          The information contained herein is proprietary to ForNow
 *           and shall not be reproduced or disclosed in whole or in part
 *                    or used for any design or manufacture
 *              without direct written authorization from ForNow.
 *
 *            Copyright (c) 2015 by ForNow.  All rights reserved.
 *
 *****************************************************************************/
package com.wqj.common.orm.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import com.wqj.common.Const;
import com.wqj.common.util.DateUtils;

/**
 * @author Jiafa Lv
 * @since Oct 31, 2013
 */
@MappedSuperclass
@SuppressWarnings("serial")
public abstract class BaseEntity implements Serializable {

	@Column(updatable = false)
	@Type(type = "com.wqj.common.orm.entity.type.PersistentDateTime")
	private DateTime createDateTime;
	@Type(type = "com.wqj.common.orm.entity.type.PersistentDateTime")
	private DateTime lastModifyDateTime;

	@Column(updatable = false, name = "createDate", columnDefinition = "bigint(20) default -99")
	private long createDate = System.currentTimeMillis();
	@Column(name = "lastModifyDate", columnDefinition = "bigint(20) default -99")
	private long lastModifyDate = System.currentTimeMillis();

	public long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

	public long getLastModifyDate() {
		return lastModifyDate;
	}

	public void setLastModifyDate(long lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

	public String getCreateDateStr() {
		if (createDate != Const.NULL_VALUE_LONG) {
			return DateUtils.long2ShortString(createDate);
		} else {
			return Const.NULL_VALUE_DISPLAY;
		}
	}

	public String getLastModifyDateStr() {
		if (lastModifyDate != Const.NULL_VALUE_LONG) {
			return DateUtils.long2ShortString(lastModifyDate);
		} else {
			return Const.NULL_VALUE_DISPLAY;
		}
	}

	public DateTime getCreateDateTimeStr() {
//		if (createDate != Const.NULL_VALUE_LONG) {
//			return DateUtils.long2LongString(createDate);
//		} else {
//			return Const.NULL_VALUE_DISPLAY;
//		}
		
		
		return createDateTime;
	}

	/**
	 * @return the createDateTime
	 */
	public DateTime getCreateDateTime() {
		return createDateTime;
	}

	/**
	 * @param createDateTime
	 *            the createDateTime to set
	 */
	public void setCreateDateTime(DateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

	/**
	 * @return the lastModifyDateTime
	 */
	public DateTime getLastModifyDateTime() {
		return lastModifyDateTime;
	}

	/**
	 * @param lastModifyDateTime
	 *            the lastModifyDateTime to set
	 */
	public void setLastModifyDateTime(DateTime lastModifyDateTime) {
		this.lastModifyDateTime = lastModifyDateTime;
	}
}
