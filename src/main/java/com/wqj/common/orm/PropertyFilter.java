package com.wqj.common.orm;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;

public class PropertyFilter {

	/** 多个属性间OR关系的分隔符. */
	public static final String OR_SEPARATOR = "_OR_";

	/** 比较类型. */
	public enum MatchType {
		EQ, LIKE, LT, GT, LE, GE, BA, NN, IN;
	}

	/** 数据类型 */
	public enum PropertyType {
		S(String.class), I(Integer.class), L(Long.class), N(Double.class), T(DateTime.class), 
		D(LocalDate.class), B(Boolean.class), F(BigDecimal.class);

		private Class<?> clazz;

		private PropertyType(Class<?> clazz) {
			this.clazz = clazz;
		}

		public Class<?> getValue() {
			return clazz;
		}
	}

	// 当前属性名
	private String[] propertyNames = null;
	// 当前属性比较类型
	private MatchType matchType = null;
	// 当前属性的类类型
	private Class<?> propertyClass = null;
	// 当前属性的值
	private Object matchValue = null;

	public PropertyFilter() {

	}

	/**
	 * @param filterName
	 *            比较属性字符串,含待比较的比较类型、属性值类型及属性列表. eg. LIKES_NAME_OR_LOGIN_NAME
	 * @param value
	 *            待比较的值.
	 */
	public PropertyFilter(final String filterName, final Object value) {
		// filterName=LIKES_username value:王
		// 比较类型+属性值类型 LIKES
		String firstPart = StringUtils.substringBefore(filterName, "_");
		// 比较类型 LIKE
		String matchTypeCode = StringUtils.substring(firstPart, 0, firstPart.length() - 1);
		// 属性值类型 S
		String propertyTypeCode = StringUtils.substring(firstPart, firstPart.length() - 1, firstPart.length());
		try {
			// 比较类型 LIKE
			matchType = Enum.valueOf(MatchType.class, matchTypeCode); // 返回指定名称的枚举常量
		} catch (RuntimeException e) {
			throw new IllegalArgumentException("filter名称" + filterName + "没有按规则编写,无法得到属性比较类型.", e);
		}

		try {
			// 属性值类型的clazz class java.lang.String
			propertyClass = Enum.valueOf(PropertyType.class, propertyTypeCode).getValue();
		} catch (RuntimeException e) {
			throw new IllegalArgumentException("filter名称" + filterName + "没有按规则编写,无法得到属性值类型.", e);
		}

		// 属性名称 username
		String propertyNameStr = StringUtils.substringAfter(filterName, "_");
		Assert.isTrue(StringUtils.isNotBlank(propertyNameStr), "filter名称" + filterName + "没有按规则编写,无法得到属性名称.");
		propertyNames = StringUtils.splitByWholeSeparator(propertyNameStr, PropertyFilter.OR_SEPARATOR);

		if (value instanceof String[]) {
			Object convert = org.apache.commons.beanutils.ConvertUtils.convert((String[]) value, propertyClass);
			this.matchValue = Arrays.asList((Object[]) convert);
		} else {
			this.matchValue = org.apache.commons.beanutils.ConvertUtils.convert((String) value, propertyClass);
		}
	}

	/**
	 * 构建PropertyFilter列表
	 * 
	 * @param filterParamMap
	 * @return
	 */
	public static List<PropertyFilter> build(final Map<String, Object> filterParamMap) {
		String filterName = null; // 页面传过来的名称
		Object filterValue = null; // 页面传过来的值
		String value = null; // 当页面传过来的值时String存放在改变量中
		String[] values = null; // 当页面传过来的值时String数组存放在改变量中
		PropertyFilter filter = null; // 当前这个名称组装的PropertyFilter
		List<PropertyFilter> filterList = Lists.newArrayList(); // 最终返回的list

		if (filterParamMap != null) {
			// 对特殊值（String类型是"空",String[]中有一个是"空"则不构建这个PropertyFilter）
			outer: for (Map.Entry<String, Object> entry : filterParamMap.entrySet()) {
				filterName = entry.getKey();
				filterValue = entry.getValue();

				if (filterValue instanceof String) {
					value = (String) entry.getValue();
					// 如果value值为空,则忽略此filter.
					if (StringUtils.isNotBlank(value)) {
						filter = new PropertyFilter(filterName, value);
						filterList.add(filter);
					}
				} else if (filterValue instanceof String[]) {
					values = (String[]) entry.getValue();
					for (String val : values) {
						// 如果数组 中有一个是空值,则忽略此filter.
						if (StringUtils.isBlank(val)) {
							continue outer;
						}
					}
					filter = new PropertyFilter(filterName, values);
					filterList.add(filter);
				}
			}
		}
		return filterList;
	}

	public String[] getPropertyNames() {
		return propertyNames;
	}

	public void setPropertyNames(String[] propertyNames) {
		this.propertyNames = propertyNames;
	}

	public MatchType getMatchType() {
		return matchType;
	}

	public void setMatchType(MatchType matchType) {
		this.matchType = matchType;
	}

	public Class<?> getPropertyClass() {
		return propertyClass;
	}

	public void setPropertyClass(Class<?> propertyClass) {
		this.propertyClass = propertyClass;
	}

	public Object getMatchValue() {
		return matchValue;
	}

	public void setMatchValue(Object matchValue) {
		this.matchValue = matchValue;
	}
	
	/**
	 * 获取唯一的比较属性名称.
	 */
	public String getPropertyName() {
		Assert.isTrue(propertyNames.length == 1, "There are not only one property in this filter.");
		return propertyNames[0];
	}
	
	/**
	 * 是否比较多个属性.
	 */
	public boolean hasMultiProperties() {
		return (propertyNames.length > 1);
	}

}
