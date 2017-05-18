package com.wqj.sitemesh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wqj.common.controller.CommonController;

@Controller
@RequestMapping("/siteMesh")
public class SiteMeshController extends CommonController {

	@RequestMapping("/list")
	public String list() {
		System.out.println("siteMesh 测试.......");
		return "/sitemesh/list";
	}
}
