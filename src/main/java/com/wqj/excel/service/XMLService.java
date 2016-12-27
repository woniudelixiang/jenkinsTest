package com.wqj.excel.service;

import java.io.InputStream;
import java.util.Map;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.wqj.excel.bean.Resource;
import com.wqj.excel.handler.ParseHandler;

/**
 * 
 * @author LE CHEN
 *
 */
public class XMLService {
	
	public static Map<String,Resource> getExcelContexts(InputStream  stream,ParseHandler handler) throws Exception {
		SAXParserFactory factory = createFactory();
		SAXParser parser = factory.newSAXParser();  
		parser.parse(stream, handler);  
		return handler.getMap();
	}
	
	
	
	private static SAXParserFactory createFactory(){
		return SAXParserFactory.newInstance();
	}

}
