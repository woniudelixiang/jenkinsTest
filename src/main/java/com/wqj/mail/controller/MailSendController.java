package com.wqj.mail.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wqj.common.controller.CommonController;

@Controller
@RequestMapping("/mail")
public class MailSendController extends CommonController {

	@RequestMapping("/sendSyncMail")
	public void sendSyncMail() {
		System.out.println("--------sendSyncMail----------");
		sendMaileUtil.sendSyncMail("2516283178@qq.com", "11111111", "2222222");
	}
}
