package com.wqj.common.context;

import java.lang.reflect.Type;
import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.joda.time.DateTime;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.wqj.common.Const;
import com.wqj.common.util.DateUtils;

@SuppressWarnings("serial")
public class ApplicationServlet extends HttpServlet {

	public void init() throws ServletException {
		try {
			ServletContext sc = getServletContext();
			// GsonBuilder
			GsonBuilder gsonBuilder = new GsonBuilder()  
				.registerTypeHierarchyAdapter(DateTime.class,  
						new JsonSerializer<DateTime>() {
					public JsonElement serialize(DateTime dateTie,  
							Type typeOfSrc,  
							JsonSerializationContext context) {  
						return new JsonPrimitive(DateUtils.dateTimetoStringDATETIME(dateTie));  
					}  
				})
				.registerTypeHierarchyAdapter(Date.class,  
						new JsonSerializer<Date>() {
					public JsonElement serialize(Date date,  
							Type typeOfSrc,  
							JsonSerializationContext context) {  
						return new JsonPrimitive(DateUtils.datetime2String(date));  
					}  
				});
			sc.setAttribute(Const.GSOONBUILDER, gsonBuilder);
			System.out.println("------------------------------------ApplicationServlet------------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
