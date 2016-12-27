package com.wqj.zyupload.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wqj.common.controller.CommonController;
import com.wqj.jqueryFileUpload.controller.JQueryFileUploadUtil;
import com.wqj.jqueryFileUpload.entity.Image;
import com.wqj.jqueryFileUpload.entity.ImgUser;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/zyupload")
public class ZyuploadController extends CommonController {

	// 跳转到图片上传页面
	@RequestMapping("/list")
	public String list() {

		return "tailor_list";
	}

	// 文件上传
	@SuppressWarnings("unchecked")
	@RequestMapping("/upload")
	@ResponseBody
	public String upload(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			 System.out.println("--------------");
			String username  = request.getParameter("username");
	        System.out.println("username: " + username);
	        
			// 拷贝上传的文件
			List<Map<String, String>> uploadList = ZyuploadUtil.upload(request);
			// 将文件信息保存到数据库

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

}
