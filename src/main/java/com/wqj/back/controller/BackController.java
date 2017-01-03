package com.wqj.back.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.wqj.common.bean.FilterParam;
import com.wqj.common.bean.Page;
import com.wqj.common.bean.ParamMap;
import com.wqj.common.controller.CommonController;
import com.wqj.common.orm.PropertyFilter;

@Controller
@RequestMapping("/back")
public class BackController extends CommonController {

	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public String list(Page page, FilterParam filterParam) {
		System.out.println(filterParam);
		List<PropertyFilter> filters = PropertyFilter.build(filterParam.getFilterParamMap());
		
		userService.findPage(page,filters);
		return "/back/list";
	}

	@RequestMapping(value="/toEdit", method={RequestMethod.POST})
	public String list2(Page page, FilterParam filterParam, ParamMap<String, String> paramMap) {
		System.out.println("list2==========>>>>> userId: " + paramMap.getParam().get("userId"));
		System.out.println("list2=========>>>>>>>> pageNum: " + page.getPageNum() +"  pageSize: " + page.getPageSize());
		return "/back/edit";
	}
	
	@RequestMapping(value="/forwardList", method={RequestMethod.POST})
	public String forwardList(Page page, ParamMap<String, String> paramMap) {
//		System.out.println("userId: " + paramMap.getParam().get("userId"));
		System.out.println("forwardList=========>>>>>>>> pageNum: " + page.getPageNum()+"  pageSize: " + page.getPageSize());
		return forward("/list");
	}
}
