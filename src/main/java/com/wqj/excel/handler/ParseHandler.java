package com.wqj.excel.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import com.google.common.collect.Maps;
import com.wqj.excel.bean.BeanComparator;
import com.wqj.excel.bean.ExcelContextBean;
import com.wqj.excel.bean.Resource;
import com.wqj.excel.cons.ConsAtt;

public class ParseHandler extends DefaultHandler{
	
	private Resource resource;
	private Map<String,Resource> map;
	private List<ExcelContextBean> contextlist;
	private ExcelContextBean excelContext;

	@Override
	public void startDocument() throws SAXException {
		map = Maps.newLinkedHashMap();
	}
	
	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		System.out.println("*************　startElement　******************　"+ qName);
		if(ConsAtt.RESOURCE.equals(qName)){
			if(resource != null && resource.getId().equals(attributes.getValue(ConsAtt.ID)))
				throw  new  UnknownError(" ID  repeat --------");
			resource = new Resource();
			resource.setId(attributes.getValue(ConsAtt.ID));
			resource.setName(attributes.getValue(ConsAtt.NAME));
		}else if(ConsAtt.HEAD.equals(qName)){
			if(contextlist == null)
				contextlist  = new ArrayList<ExcelContextBean>();
			excelContext = new ExcelContextBean();
			excelContext.setColspan(Integer.parseInt(attributes.getValue(ConsAtt.COLSPAN)==null?"1":attributes.getValue(ConsAtt.COLSPAN)));
			excelContext.setRowIndex(Integer.parseInt(attributes.getValue(ConsAtt.ROW_INDEX)==null?"0":attributes.getValue(ConsAtt.ROW_INDEX)));
			String field =attributes.getValue(ConsAtt.FIELD);
			excelContext.setField(field);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(ConsAtt.RESOURCE.equals(qName)){
			
			contextlist = sortList(contextlist);
			
			checkfield(contextlist);
			
			resource.setContext(contextlist);
			map.put(resource.getId(), resource);
			contextlist = null;
		}else if(ConsAtt.HEAD.equals(qName)){
			contextlist.add(excelContext);
		}
	}

	private void checkfield(List<ExcelContextBean> contextlist2) {
		int lastRowIndex =contextlist2.get(contextlist2.size()-1).getRowIndex();
		for(ExcelContextBean e : contextlist2){
			if(e.getRowIndex() == lastRowIndex && e.getField()==null){
				try {
					throw new Exception(" 未定义field 属性? ");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	private List<ExcelContextBean> sortList(List<ExcelContextBean> contextlist2) {
		ExcelContextBean[] e = new ExcelContextBean[contextlist2.size()];
		e =contextlist2.toArray(e);
		Arrays.sort(e, new BeanComparator());
		contextlist2 = Arrays.asList(e);
		return contextlist2;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String value  = new String(ch,start,length);
		if(!value.trim().equals("")){
			excelContext.setHeadName(value);
		}
	}


	public Map<String,Resource> getMap(){
		return this.map;
	}
}
