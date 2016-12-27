package com.wqj.webSocket.controller;

import java.io.IOException;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Controller;

@Controller
@ServerEndpoint("/hello")
public class WebSocketPoint {

	@OnOpen
	public void sayHello(Session session){
		System.out.println("收到客户端的请求");
		try {
			// 给客户端发送消息
			session.getBasicRemote().sendText("hello word");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	};
	
}
