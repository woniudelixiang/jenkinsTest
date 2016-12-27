package com.wqj.toLeadExcel.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wqj.common.controller.CommonController;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/toLeadExcel")
public class ToLeadExcelController extends CommonController {


	@RequestMapping("/list")
	public String list() {
		return "toLeadExcel";
	}

	@SuppressWarnings({ "resource", "unused" })
	public  List<Map<String, String>> parseExcel(HttpServletRequest request){
		List<Map<String, String>> list = Lists.newArrayList();
		Map<String, String> map = null;
		Workbook book = null;
		
		try {
			MultipartFile mpf;
			if (request instanceof MultipartHttpServletRequest) {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				Iterator<String> itr = multipartRequest.getFileNames();
				while (itr.hasNext()) {
					mpf = multipartRequest.getFile(itr.next());
					long size = mpf.getSize();
					if(size>0){
						map = Maps.newHashMap();
						
						try {
							book = new HSSFWorkbook(mpf.getInputStream());
							System.out.println("*******方式一************");
						} catch (Exception e1) {
							book = new XSSFWorkbook(mpf.getInputStream());
							System.out.println("********方式二***********");
						}
						
						Sheet sheet = book.getSheetAt(0);
						//Iterator<Row> rows = sheet.rowIterator();
						System.out.println("-------------rows size : "+(sheet.getLastRowNum()+1));
						Row row = null;
						Cell cell = null;
						String cellStr = "";
						if(sheet!=null){
							for (int rownum = 0; rownum <= sheet.getLastRowNum(); rownum++) {
								 row = sheet.getRow(rownum);
								 cellStr = "";
								 //当某一行全部没有数据是row=null
								 if(row!=null){
									 for (int cellnum = 0; cellnum < row.getLastCellNum(); cellnum++) {
										 cell = row.getCell(cellnum);
										 //当某个单元格没有数据时 cell=null
										if(cell!=null){
											 cellStr += (rownum+","+cellnum)+" ,"+cell.getStringCellValue()+"   ";
										}else{
											 cellStr += (rownum+","+cellnum)+" ,"+null+"   ";
										}
									}
									 System.out.println(cellStr);
								 }
							}
						}
					
						
						/*	while(rows.hasNext()){
							Row row = rows.next();
							System.out.println("-------------cells size : "+(row.getLastCellNum()));
							
							for(int i = 0; i<row.getLastCellNum() ; i++){
								Cell cell =row.getCell(i);
								String cellStr = cell.getStringCellValue();
								System.out.print(" ,cellStr: " + cellStr);
							}
							System.out.println();
						}*/
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}



	/**
	 * 导入Excel
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	public String importExcel(HttpServletRequest request){
		System.out.println("************* 导入Excel *************");
		List<Map<String, String>> parseExcel = parseExcel(request);
		
		return "toLeadExcel";
	}











}
