package com.wqj.activeMQ.controller;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import com.wqj.activeMQ.pojo.SendBean; 

public class AsyncListen implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("---------AsyncListen-----------");
			MapMessage map = (MapMessage) message;
			Object object = map.getObject((SendBean.MSG_CONTENT_KEY));
			System.out.println(object);
			
			// 处理接受这的业务逻辑   TODO
			// WebsocketController.broadcast("message", object);
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
