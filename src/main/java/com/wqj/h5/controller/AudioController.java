package com.wqj.h5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wqj.common.controller.CommonController;

@Controller
@RequestMapping("/audio")
public class AudioController extends CommonController {

	@RequestMapping("/list")
	public String list() {

		return "h5/audio/list";
	}

}
