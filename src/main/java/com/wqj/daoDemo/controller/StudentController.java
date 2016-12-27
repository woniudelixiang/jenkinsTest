package com.wqj.daoDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.wqj.common.controller.CommonController;

@Controller
@RequestMapping(value={"/student"} ,method={RequestMethod.GET,RequestMethod.POST})
public class StudentController extends CommonController{

	
}
