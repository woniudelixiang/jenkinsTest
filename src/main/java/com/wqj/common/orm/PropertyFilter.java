package com.wqj.common.orm;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;
import com.wqj.common.util.ConvertUtils;
import com.wqj.common.util.DateUtils;
import com.wqj.common.util.ServletUtils;

public class PropertyFilter {

	/** 多个属性间OR关系的分隔符. */
	public static final String OR_SEPARATOR = "_OR_";

	/** 比较类型. */
	public enum MatchType {
		EQ, LIKE, LT, GT, LE, GE, BA, NN, IN;
	}

	/** 数据类型 */
	public enum PropertyType {
		S(String.class), I(Integer.class), L(Long.class), N(Double.class), T(DateTime.class), D(LocalDate.class), B(Boolean.class),F(BigDecimal.class);

		private Class<?> clazz;

		private PropertyType(Class<?> clazz) {
			this.clazz = clazz;
		}

		public Class<?> getValue() {
			return clazz;
		}
	}
	
	//属性数据类型
	private MatchType matchType = null;
	//属性的值
	private Object matchValue = null;
	//属性的类类型
	private Class<?> propertyClass = null;
	//属性名
	private String[] propertyNames = null;

	public PropertyFilter() {
	}
//=======================================================================================================
	
	/**
	 * 从HttpRequest中创建PropertyFilter列表, 默认Filter属性名前缀为filter.
	 * 
	 * @see #buildFromHttpRequest(HttpServletRequest, String)
	 */
	public static List<PropertyFilter> buildFromHttpRequest(final HttpServletRequest request) {
		return buildFromHttpRequest(request, "filter");
	}

	/**
	 * 从HttpRequest中创建PropertyFilter列表
	 * PropertyFilter命名规则为Filter属性前缀_比较类型属性类型_属性名.
	 * 
	 * eg. filter_EQS_name filter_LIKES_name_OR_email
	 */
	public static List<PropertyFilter> build(final Map<String, Object> filterParamMap) {
		List<PropertyFilter> filterList = Lists.newArrayList();
		// 分析参数Map,构造PropertyFilter列表
		if(filterParamMap!=null){
			outer: for (Map.Entry<String, Object> entry : filterParamMap.entrySet()) {
				String filterName = entry.getKey();
				Object filterValue = entry.getValue();
				
				if (filterValue instanceof String) {
					String value = (String) entry.getValue();
					// 如果value值为空,则忽略此filter.
					if (StringUtils.isNotBlank(value)) {
						PropertyFilter filter = new PropertyFilter(filterName, value);
						filterList.add(filter);
					}
				} else if (filterValue instanceof String[]) {
					String[] values = (String[]) entry.getValue();
					for (String value : values) {
						if (StringUtils.isBlank(value)) {
							continue outer;
						}
					}
					PropertyFilter filter = new PropertyFilter(filterName, values);
					filterList.add(filter);
				}
			}
		}
		return filterList;
	}
	
	public static List<PropertyFilter> buildFromHttpRequest(final HttpServletRequest request, final String filterPrefix) {
		List<PropertyFilter> filterList = Lists.newArrayList();
		Assert.notNull(request, "Request must not be null");
		
		// 从request中获取含属性前缀名的参数,构造去除前缀名后的参数Map.
		Map<String, Object> filterParamMap = ServletUtils.getParametersStartingWith(request, filterPrefix + "_");
//		for(Map.Entry<String, Object> entry : filterParamMap.entrySet()) {
//			String filterName = entry.getKey();
//			Object filterValue = entry.getValue();
//			System.out.println("filterName:  " + filterName + "  filterValue: " + filterValue);
//		}
		
		
		
		// 分析参数Map,构造PropertyFilter列表
		outer: for (Map.Entry<String, Object> entry : filterParamMap.entrySet()) {
			String filterName = entry.getKey();
			Object filterValue = entry.getValue();
			
			if (filterValue instanceof String) {
				String value = (String) entry.getValue();
				// 如果value值为空,则忽略此filter.
				if (StringUtils.isNotBlank(value)) {
					PropertyFilter filter = new PropertyFilter(filterName, value);
					filterList.add(filter);
				}
			} else if (filterValue instanceof String[]) {
				String[] values = (String[]) entry.getValue();
				for (String value : values) {
					if (StringUtils.isBlank(value)) {
						continue outer;
					}
				}
				PropertyFilter filter = new PropertyFilter(filterName, values);
				filterList.add(filter);
			}
		}
		return filterList;
	}
	
	/**
	 * @param filterName
	 *            比较属性字符串,含待比较的比较类型、属性值类型及属性列表. eg. LIKES_NAME_OR_LOGIN_NAME
	 * @param value
	 *            待比较的值.
	 */
	public PropertyFilter(final String filterName, final Object value) {
		//filterName=LIKES_username  value:王
		
		//比较类型+属性值类型  LIKES
		String firstPart = StringUtils.substringBefore(filterName, "_");
		//比较类型  LIKE
		String matchTypeCode = StringUtils.substring(firstPart, 0, firstPart.length() - 1);
		//属性值类型 S
		String propertyTypeCode = StringUtils.substring(firstPart, firstPart.length() - 1, firstPart.length());
		
		try {
			//比较类型  LIKE
			matchType = Enum.valueOf(MatchType.class, matchTypeCode);
		} catch (RuntimeException e) {
			throw new IllegalArgumentException("filter名称" + filterName + "没有按规则编写,无法得到属性比较类型.", e);
		}

		try {
			//属性值类型的clazz   class java.lang.String  
			propertyClass = Enum.valueOf(PropertyType.class, propertyTypeCode).getValue();
		} catch (RuntimeException e) {
			throw new IllegalArgumentException("filter名称" + filterName + "没有按规则编写,无法得到属性值类型.", e);
		}
		
		//属性名称  username
		String propertyNameStr = StringUtils.substringAfter(filterName, "_");
		Assert.isTrue(StringUtils.isNotBlank(propertyNameStr), "filter名称" + filterName + "没有按规则编写,无法得到属性名称.");
		propertyNames = StringUtils.splitByWholeSeparator(propertyNameStr, PropertyFilter.OR_SEPARATOR);
		
		if(value instanceof String[]) {
			String[] values = (String[])  value;
			if (matchType.equals(MatchType.BA)) {
				if (values.length == 2) {
					if (propertyClass == DateTime.class) {
						List<DateTime> matchValues = Lists.newArrayList();
						DateTime date1 = new DateTime(values[0]);
						DateTime date2 = new DateTime(values[1]);
						
						if (date1.isBefore(date2)) {
							matchValues.add(date1);
							//matchValues.add(date2.plusDays(1));
							matchValues.add(com.wqj.common.util.DateUtils.getFinalDateTime(date2));
						} else {
							matchValues.add(date2);
							//matchValues.add(date1.plusDays(1));
							matchValues.add(com.wqj.common.util.DateUtils.getFinalDateTime(date1));
						}
						this.matchValue = matchValues;
					} else if (propertyClass == LocalDate.class) {
						List<LocalDate> matchValues = Lists.newArrayList();
						LocalDate date1 = new LocalDate(values[0]);
						LocalDate date2 = new LocalDate(values[1]);
						if (date1.isBefore(date2)) {
							matchValues.add(date1);
							matchValues.add(date2.plusDays(1));
						} else {
							matchValues.add(date2);
							matchValues.add(date1.plusDays(1));
						}
						this.matchValue = matchValues;
					}else if(propertyClass == Long.class){
						List<Long> matchValues = Lists.newArrayList();
						DateTime date1 = new DateTime(values[0]);
						DateTime date2 = new DateTime(values[1]);
						if (date1.isBefore(date2)) {
							matchValues.add(DateUtils.strToLongStart(date1));
							matchValues.add(DateUtils.strToLongEnd(date2));
						} else {
							matchValues.add(DateUtils.strToLongStart(date2));
							matchValues.add(DateUtils.strToLongEnd(date1));
						}
						this.matchValue = matchValues;
					}
				}
			}else if(matchType.equals(MatchType.IN)){
				if(propertyClass == Long.class){
					List<Long> matchValues = Lists.newArrayList();
					for(String str : values){
						matchValues.add(Long.parseLong(str));
					}
					this.matchValue = matchValues;
				}
			}
		}else{
			this.matchValue = ConvertUtils.convertStringToObject((String) value, propertyClass);
		}
	}

	/**
	 * 获取比较值的类型.
	 */
	public Class<?> getPropertyClass() {
		return propertyClass;
	}

	/**
	 * 获取比较方式.
	 */
	public MatchType getMatchType() {
		return matchType;
	}

	/**
	 * 获取比较值.
	 */
	public Object getMatchValue() {
		return matchValue;
	}

	/**
	 * 获取比较属性名称列表.
	 */
	public String[] getPropertyNames() {
		return propertyNames;
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
