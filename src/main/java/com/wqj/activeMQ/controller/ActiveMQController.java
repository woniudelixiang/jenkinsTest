package com.wqj.activeMQ.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wqj.activeMQ.pojo.SendBean;
import com.wqj.activeMQ.util.JmsTemplateUtil;
import com.wqj.common.controller.CommonController;
import com.wqj.gson.util.JsonUtil;
import com.wqj.redis.entity.User;

@Controller
@RequestMapping("/activeMQ")
public class ActiveMQController extends CommonController {

	@RequestMapping("/testSend")
	public void testSend(){
		SendBean sendBean = new SendBean();
		sendBean.setQueuesName("testQueue");
		User user = new User(1,"wqj");
		sendBean.setMsgContent(JsonUtil.objectToJson(user));
		JmsTemplateUtil.send(sendBean);
	}
	
	@RequestMapping("/testReceiver")
	public void testReceiver(){
		Map<String, Object> receiver = JmsTemplateUtil.receiver();
	}
}
