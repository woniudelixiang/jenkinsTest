package com.wqj.ajaxFileUpload.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wqj.common.bean.CommonJsonBean;
import com.wqj.common.controller.CommonController;
import com.wqj.common.util.JSONHelper;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value={"/ajaxFileUpload"})
public class AjaxFileUploadController extends CommonController {

	@RequestMapping(value="/list")
	public String list(){
		return "ajaxFileUpload/list";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/upload")
	public String upload(@RequestParam MultipartFile[] fileToUpload){
		if(fileToUpload!=null){
			for (MultipartFile multipartFile : fileToUpload) {
				System.out.println("上传的文件名："  + multipartFile.getOriginalFilename()) ;
			}
		}
		
		CommonJsonBean jsonBean = new CommonJsonBean(); 
		jsonBean.setMessage("成功了...");
		
		return JSONHelper.toJson(jsonBean);
	}
	
}
