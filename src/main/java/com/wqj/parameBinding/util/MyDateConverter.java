package com.wqj.parameBinding.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.core.convert.converter.Converter;
import com.wqj.common.util.ValidateUtils;
import com.wqj.common.util.regexUtil;

public class MyDateConverter implements Converter<String,Date>{

	@Override
	public Date convert(String source) {
		System.out.println("**********   将字符串日期转哈成Date类型       ************");
		SimpleDateFormat sdf = null;
		if(ValidateUtils.isEmpty(source)){
			return null;
		}
		if(!regexUtil.isDateOrDateTime(source)){
			return null;
		}
		if(regexUtil.isDate(source)){
			sdf = regexUtil.getFormat(regexUtil.PATTERN_DATE);
		}
		if(regexUtil.isDateTime(source)){
			sdf = regexUtil.getFormat(regexUtil.PATTERN_DATETIME);
		}
		try {
			return sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
