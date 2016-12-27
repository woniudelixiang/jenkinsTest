package com.wqj.activeMQ.util;

import java.util.Map;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Session;
import org.apache.activemq.Message;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import com.wqj.activeMQ.pojo.SendBean;

@Component
public class JmsTemplateUtil {

	/**
	 * 发送消息
	 * @param sendBean
	 */
	public static void send(final SendBean sendBean){
	 jmsTemplate.setDefaultDestination(new ActiveMQQueue(sendBean.getQueuesName()));
		jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
			/*	TextMessage createTextMessage = session.createTextMessage("11112222");*/
				MapMessage message = session.createMapMessage();
				message.setString(SendBean.MSG_CONTENT_KEY, sendBean.getMsgContent());
				return (Message) message;  
			}
		});
	}
	
	/**
	 * 接收消息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> receiver(){
		Map<String, Object> resultMap = (Map<String, Object>) jmsTemplate.receiveAndConvert();
		return resultMap;
	}

	
	@Autowired
	private static JmsTemplate jmsTemplate;
	
	public static JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public static void setJmsTemplate(JmsTemplate jmsTemplate) {
		JmsTemplateUtil.jmsTemplate = jmsTemplate;
	}
}
