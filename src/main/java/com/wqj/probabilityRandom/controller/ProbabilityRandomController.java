package com.wqj.probabilityRandom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wqj.common.controller.CommonController;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/pr")
public class ProbabilityRandomController extends CommonController {


	@RequestMapping("/list")  
	public String probability_random() {  
		return "/probability_random/list";  
	}  
	
	
}
