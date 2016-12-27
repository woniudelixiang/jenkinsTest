package com.wqj.mytomcate.version_1;

import java.io.IOException;
import java.io.InputStream;

/**
 * 解析请求类
 * @author Qijun wang
 * @email wqjjob@126.com
 * @date 2016年11月2日 上午11:04:25
 */
public class Request {

	private String uri;
	
	public Request(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[1024];
		int len = inputStream.read(buffer);
		if(len>0){
			String msg = new String(buffer, 0, len);
			// 获取请求支援路径
			uri = msg.substring(msg.indexOf("/"), msg.indexOf("HTTP/1.1")-1);
			System.out.println("----------->>>  uri:  " + uri);
		}else{
			System.out.println("-----------error-----------");
		}
	}

	public String getUri() {
		return uri;
	}
}
