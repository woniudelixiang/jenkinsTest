package com.wqj.webuploader.controller;

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
@RequestMapping("/webuploader")
public class WebuploaderController extends CommonController {

	@RequestMapping("/list")
	public String list(){
		return "webuploader/list";
	}
	
	@ResponseBody
	@RequestMapping("/upload")
    public String uploadHeadPic(@RequestParam("file")MultipartFile[] file,HttpServletRequest request,HttpServletResponse response){
        CommonJsonBean jsonBean = new CommonJsonBean();
		try {
			MultipartFile multipartFile = null;
			for (int i = 0; i < file.length; i++) {
				multipartFile = file[i];
				 String originalFilename = multipartFile.getOriginalFilename();
        		 System.out.println("originalFilename:  " + originalFilename) ;
        		 System.out.println(i);
        		 if("banner.jpg".equals(originalFilename)){
        			 jsonBean.setStatusCode(1);
        			 return JSONHelper.toJson(jsonBean);
        		 }
        		 Thread.sleep(1000*5);
			}
         } catch (Exception e) {
             e.printStackTrace();
         }
		return JSONHelper.toJson(jsonBean);
    }
}
