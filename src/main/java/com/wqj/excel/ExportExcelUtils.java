package com.wqj.excel;

import java.io.File;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import com.google.common.collect.Maps;
import com.wqj.common.util.ReflectionUtils;
import com.wqj.common.util.StringUtils;
import com.wqj.excel.base.AbstractExportExcelUtils;
import com.wqj.excel.bean.BeanComparator;
import com.wqj.excel.bean.ExcelContextBean;
import com.wqj.excel.bean.Resource;

public class ExportExcelUtils extends AbstractExportExcelUtils{
	private  static String[] heads;
	private static int row;
	private static int lastrows;

	/**
	 * 
	 * @param list      
	 * @param name     sheet名称       如：sheet1
	 * @param output   输出路径      如：new FileOutputStream("e:/excel.xls")
	 * @param resourceId  资源id   如：export1
	 * <resource id = "export1" name="">
	 *	  <head rowIndex = "0" colspan="2" >biaoti1</head>
	 *	  <head  rowIndex = "0" >biaoti2</head>
	 *	  <head  rowIndex = "1" field="sex">erjibiaoti</head>
	 *	  <head  rowIndex = "1" field = "name">erjibiaoti2</head>
	 *	</resource>
	 * @throws Exception  异常
	 */
	public  static <T> void exportExcel(List<T> list,String name,OutputStream output,String resourceId) throws Exception{
		if( output == null  ){
			throw new Exception(" OutputStream can't not be null  ");
		}
		logger.info("[ "+ExportExcelUtils.class.getName()+" ] invoke exportExcel( ... ) ");
		
		Resource res = getRes(resourceId);
		
		//获取excelcontxt集合
		List<ExcelContextBean> contexts =  res.getContext();
		
		//获取最后一行
		gainLastRows(contexts);
		
		WritableWorkbook book = Workbook.createWorkbook(output);
		
		WritableSheet sheet = book.createSheet(name, 0);
		//初始化map 添加表头 
		Map<String,String> headAndField = Maps.newLinkedHashMap();
		createHeadAndbuildMap(contexts, headAndField, sheet);
		
		//将excel标题  赋给heads
		heads = headAndField.keySet().toArray(new String[headAndField.size()]);
		//填充excel
		fillExcel(sheet,list,headAndField);
		book.write();
		book.close();
		output.flush();
		output.close();
	}
	
	private static void gainLastRows(List<ExcelContextBean> contexts) {
		ExcelContextBean[] beans = new ExcelContextBean[contexts.size()];
		Arrays.sort(contexts.toArray(beans),new BeanComparator());
		lastrows = beans[beans.length -1].getRowIndex();

	}

	private static void createHeadAndbuildMap(List<ExcelContextBean> contexts,
			Map<String,String> headAndField,WritableSheet sheet) throws RowsExceededException, WriteException{
		int num = 0;
		for(int  i = 0; i< contexts.size() ; i++){
			ExcelContextBean bean = contexts.get(i);
			if(row != bean.getRowIndex()){
				row = bean.getRowIndex();
				num = 0;
			}
			if(row==lastrows){
				headAndField.put(bean.getHeadName(), bean.getField());
			}
			
			sheet.mergeCells(num, bean.getRowIndex(), num+bean.getColspan()-1, bean.getRowIndex());
			
			 WritableCellFormat wcf_title = new WritableCellFormat(); // 单元格定义  
	         wcf_title.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式  
			
			Label label = new Label(num, row, bean.getHeadName(),wcf_title);
			num = num+bean.getColspan();
			sheet.addCell(label);
			
		}
	}

	private static <T> void fillExcel(WritableSheet sheet, List<T> list,Map<String,String> colAndFieldName) throws Exception {
		System.out.println(heads.length);
		int i =row;
		for(T t : list){
			for(int j = 0 ; j<heads.length ; j++){
				Field field =ReflectionUtils.getAccessibleField(t,colAndFieldName.get(heads[j]) );

				if(field == null){
					throw new IllegalArgumentException(" not find field [ '"+colAndFieldName.get(heads[j])+"' ] in taget "+t);
				}
				sheet.addCell(new Label(j,i+1,StringUtils.getStr(ReflectionUtils.getFieldValue(t, field.getName()))));
			}
			i++;
		}
	}

	@Override
	public void setMap(Map<String, Resource> map) {
		try {
			throw new Exception( " No operation " );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String args[]){
		try{
			WritableWorkbook book = Workbook.createWorkbook(new File("e:/test.xls"));
			WritableSheet sheet = book.createSheet("test1", 0);
			sheet.mergeCells(1,1,0,0);
			book.write();
			book.close();
		}catch (Exception e) {
			System.out.print(e);
		}
	}
}
