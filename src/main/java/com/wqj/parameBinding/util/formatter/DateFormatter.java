package com.wqj.parameBinding.util.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import com.wqj.common.util.DateUtils;
import com.wqj.parameBinding.util.formatter.anotation.DateToLong;

public class DateFormatter implements Formatter<Long>{

	
	private DateToLong dateToLong;
	
	public DateFormatter(DateToLong dateToLong) {
		this.dateToLong = dateToLong;
	}

	@Override
	public String print(Long object, Locale locale) {
		return ""+object;
	}

	@Override
	public Long parse(String text, Locale locale) throws ParseException {
		return DateUtils.strSLong(text+" "+dateToLong.affter());
	}

}
