package com.wqj.parameBinding.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.springframework.format.Formatter;
import com.wqj.common.util.ValidateUtils;
import com.wqj.common.util.regexUtil;

public class MyDateFormatter implements Formatter<Date> {

	@Override
	public String print(Date object, Locale locale) {
		return null;
	}
	
	//将字符串日期转哈成Date类型
	@Override
	public Date parse(String text, Locale locale) throws ParseException {
		System.out.println("**********   将字符串日期转哈成Date类型       ************");
		SimpleDateFormat sdf = null;
		
		if(ValidateUtils.isEmpty(text)){
			return null;
		}
		
		if(!regexUtil.isDateOrDateTime(text)){
			return null;
		}
		
		if(regexUtil.isDate(text)){
			sdf = regexUtil.getFormat(regexUtil.PATTERN_DATE);
		}
		
		if(regexUtil.isDateTime(text)){
			sdf = regexUtil.getFormat(regexUtil.PATTERN_DATETIME);
		}
		
		return sdf.parse(text);
	}
}
