package com.wqj.parameBinding.util.formatter;

import java.util.HashSet;
import java.util.Set;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import com.wqj.parameBinding.util.formatter.anotation.DateToLong;

// 将yyyy-MM-dd HH:mm:ss 转换为时间戳
public class DateAnotationFormatter implements AnnotationFormatterFactory<DateToLong>{

	private final Set<Class<?>> fieldTypes; // 可以被DateToLong注解类型注解的字段类型集合
	
	public DateAnotationFormatter() {  
		Set<Class<?>> set = new HashSet<Class<?>>();  
		set.add(Long.class);
		this.fieldTypes = set;  
	}


	@Override
	public Set<Class<?>> getFieldTypes() {
		return fieldTypes;
	}

	@Override
	public Printer<?> getPrinter(DateToLong annotation, Class<?> fieldType) {
		System.out.println("----------------------DateAnotationFormatter  ---->>>> getPrinter--------------------");
		return  new  DateFormatter(annotation);
	}

	@Override
	public Parser<?> getParser(DateToLong annotation, Class<?> fieldType) {
		System.out.println("----------------------DateAnotationFormatter  ---->>>> getParser--------------------");
		return  new  DateFormatter(annotation);
	}

}
