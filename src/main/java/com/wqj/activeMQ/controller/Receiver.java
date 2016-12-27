package com.wqj.activeMQ.controller;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Receiver {

	static int total = 1;

	static String breakUrl = "tcp://192.168.1.111:61616";

	static  String queueName = "DEMO";

	static ConnectionFactory connectionFactory; //连接工厂

	static Connection connection; //JMS 客户端到JMS Provider 的连接

	static Session session;// Session： 一个发送或接收消息的线程

	Receiver() throws JMSException{
		connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD,breakUrl);
		// 构造从工厂得到连接对象
		connection  = connectionFactory.createConnection();
		//启动
		connection.start();

		session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
	}

	public static void main(String[] args) throws JMSException {
		Receiver receive   = new Receiver();
		receive.receive();
	}
	
	public void receive(){
		Destination destination;
		// 消费者，消息接收者
		MessageConsumer consumer;
		try {
			// 获取操作连接
			session = connection.createSession(Boolean.FALSE,
					Session.AUTO_ACKNOWLEDGE);
			// 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
			destination = session.createQueue(queueName);
			consumer = session.createConsumer(destination);
			consumer.setMessageListener(new AsyncListen());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
	}
}