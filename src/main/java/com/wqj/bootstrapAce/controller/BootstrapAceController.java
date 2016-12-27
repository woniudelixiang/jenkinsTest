package com.wqj.bootstrapAce.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wqj.bootstrapAce.entity.BootstrapAceMenu;
import com.wqj.common.controller.CommonController;

@Controller
@RequestMapping("/bootstrapAce")
public class BootstrapAceController extends CommonController {

	@ResponseBody
	@RequestMapping("/index")
	public String index() {
		List<BootstrapAceMenu> rootMenus = bootstrapAceMenuService.getRootMenus();
		List<BootstrapAceMenu> menus = bootstrapAceMenuService.loopQueryMenusByParent(rootMenus);
		Gson gson = new GsonBuilder()
			        .excludeFieldsWithoutExposeAnnotation()
				.create();
		String json = gson.toJson(menus);
		System.out.println("json: " + json);
		return json;
	}
	
}
