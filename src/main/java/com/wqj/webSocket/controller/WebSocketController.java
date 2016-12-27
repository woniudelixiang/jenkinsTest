package com.wqj.webSocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/websocket")
public class WebSocketController {

	@RequestMapping("/list")
	public String list(){
		return "/webSocket/list";
	};
	
	
}
