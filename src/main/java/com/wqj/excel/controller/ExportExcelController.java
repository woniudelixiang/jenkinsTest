package com.wqj.excel.controller;

import java.io.FileOutputStream;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Lists;
import com.wqj.common.controller.CommonController;
import com.wqj.excel.ExportExcelUtils;
import com.wqj.excel.entity.Excel;


@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/exportExcel")
public class ExportExcelController extends CommonController {

	@RequestMapping("/list")
	public String list() {
		
		List<Excel> list = Lists.newArrayList();
		
		list.add(new Excel("男","01号"));
		list.add(new Excel("男","02号"));
		list.add(new Excel("男","03号"));
		try {
			ExportExcelUtils.exportExcel(list, "sheet1", new FileOutputStream("e:/excel.xls"), "export1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "drop_load";
	}
	
	
	
}
