package com.wqj.activeMQ.controller;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Sender {
	static int total = 1;
    static String breakUrl = "tcp://192.168.1.111:61616";
    static  String queueName = "DEMO";
    static ConnectionFactory connectionFactory; //连接工厂
    static Connection connection; //JMS 客户端到JMS Provider 的连接
    static Session session;// Session： 一个发送或接收消息的线程
    
    Sender() throws JMSException{
    	connectionFactory = new ActiveMQConnectionFactory(
    			ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD,breakUrl);
    	
    	// 构造从工厂得到连接对象
    	connection  = connectionFactory.createConnection();
    	//启动
    	connection.start();
    	session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
    }

    public static void main(String[] args) throws JMSException {
       Sender sender = new Sender();
       sender.build();
    }
    
    public void build(){
        try {
            // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
             Destination destination = session.createQueue(queueName);
            // 得到消息生成者【发送者】
             MessageProducer producer = session.createProducer(destination);
            // 设置不持久化，此处学习，实际根据项目决定
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            // 构造消息，此处写死，项目就是参数，或者方法获取
            sendMessage(session, producer);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection)
                    connection.close();
            } catch (Throwable ignore) {
            }
        }
    }

    public static void sendMessage(Session session, MessageProducer producer)
            throws Exception {
    	int i = 0;
    	while(true){
    		System.out.println(" send "+ ++i);
    		TextMessage message = session.createTextMessage("Send message to demo  "+ ++total);
    		producer.send(message);
    		session.commit();
    	}
    }
}
