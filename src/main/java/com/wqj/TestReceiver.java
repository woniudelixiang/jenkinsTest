package com.wqj;

import java.util.Date;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

public class TestReceiver {
	public static void main(String[] args) {

		ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
		JmsTemplate jmsTemplate = (JmsTemplate) ctx.getBean("jmsTemplate");   
		Map<String, Object> mm =  (Map<String, Object>) jmsTemplate.receiveAndConvert();
		System.out.println("收到消息：" + new Date((Long)mm.get("count")));
	}
}
