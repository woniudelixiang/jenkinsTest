package com.wqj.common.context;

import java.text.SimpleDateFormat;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.joda.time.DateTime;
import com.wqj.common.util.DateUtils;

/**
 * 注册自定义转换器
 * @author Qijun wang
 * @email wqjjob@126.com
 * @date 2016年7月11日 上午11:54:30
 */
@SuppressWarnings("serial")
public class PropertyTypeConvert extends HttpServlet {
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("-------------------  注册自定义转换器    ---------------------");
		
		// Object ---> DateTime
		ConvertUtils.register(new Converter() {
			@Override
			public Object convert(@SuppressWarnings("rawtypes") Class clazz, Object obj) {
				SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.PATTERN_DATETIME);
				return DateUtils.toDateTime(sdf.format(obj));
			}
		}, DateTime.class);
		
		
		
		
		
	}

}
