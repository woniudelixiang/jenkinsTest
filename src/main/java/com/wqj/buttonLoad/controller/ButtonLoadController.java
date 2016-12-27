package com.wqj.buttonLoad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wqj.common.controller.CommonController;
import com.wqj.common.util.JSONHelper;
import com.wqj.jqueryFileUpload.entity.ImgUser;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/buttonLoad")
public class ButtonLoadController extends CommonController {

	
		@RequestMapping("/list")
		public String list() {

			return "button_load";
		}

		// 加载已存在的图片
		@RequestMapping(value = "/submitForm")
		@ResponseBody
		public String submitForm() {
			System.out.println("**********   submitForm   *************");
			//查询出需呀加载的图片
			ImgUser iu = new ImgUser();
		  return JSONHelper.toJson(iu);
		}
	
}
