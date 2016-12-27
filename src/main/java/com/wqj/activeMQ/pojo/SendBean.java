package com.wqj.activeMQ.pojo;

public class SendBean {

	public static final String MSG_CONTENT_KEY = "msgContent";  
	
	//队列名称
	private String queuesName;
	//发送内容
	private String  msgContent;
	
	public SendBean() {
		this.queuesName = "DEMO";
		this.msgContent = "";
	}
	
	public String getQueuesName() {
		return queuesName;
	}
	public void setQueuesName(String queuesName) {
		this.queuesName = queuesName;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}  
}
