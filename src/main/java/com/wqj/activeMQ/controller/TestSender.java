package com.wqj.activeMQ.controller;

import java.util.Date;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Session;
import org.apache.activemq.Message;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class TestSender {

	public static void main(String[] args) {
		ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:activeMQ/activemq.xml");
		JmsTemplate jmsTemplate = (JmsTemplate) ctx.getBean("jmsTemplate");
		jmsTemplate.setDefaultDestination(new ActiveMQQueue("DEMO"));
		
		jmsTemplate.send(new MessageCreator() {
			
			public Message createMessage(Session session) throws JMSException {
				MapMessage mm = session.createMapMessage();
				mm.setLong("count", new Date().getTime());
				return (Message) mm;  
			}
		}); 
	}
}
