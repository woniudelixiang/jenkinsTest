package com.wqj.procedure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wqj.common.controller.CommonController;
import com.wqj.procedure.MyPreparedStatementCallback;

@Controller
@RequestMapping("/procedure")
public class ProcedureController extends CommonController {

	@RequestMapping("/templete")
	@ResponseBody
	public String templete(){
		 String callString = "{call pr(?)}"; 
		MyPreparedStatementCallback action = new MyPreparedStatementCallback(null);
		Object prepareCall = baseImageEntityService.prepareCall(callString, action);
		System.out.println(prepareCall);
		return "";
	}
	
	
}
