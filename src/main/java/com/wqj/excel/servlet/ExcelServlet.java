package com.wqj.excel.servlet;

import java.util.Map;
import javax.servlet.ServletException;

import com.wqj.excel.bean.Resource;
import com.wqj.excel.bean.XMLReaderBean;
import com.wqj.excel.factory.ExcelFactoryBean;
import com.wqj.excel.handler.ParseHandler;
import com.wqj.excel.service.XMLService;

@SuppressWarnings("serial")
public class ExcelServlet extends AbstractPropertyConfigServlet {
	
	@Override
	protected void initServletBean() throws ServletException {
		System.out.println("*********************** initServletBean ****************************");
		try {
			System.out.println(" Initializing excel ExcelServlet ['"+this.getServletName()+" ']");
			XMLReaderBean reader = new XMLReaderBean(getDefaultConfigLocation(), this.getServletConfig());
			
			ParseHandler handler = new ParseHandler();
			
			Map<String,Resource> map =XMLService.getExcelContexts(reader.createInputStream(), handler);
			
			ExcelFactoryBean excelUtils = ExcelFactoryBean.createInstance();
			excelUtils.getMap().putAll(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	







	

	
	

	
	
	
	

}
