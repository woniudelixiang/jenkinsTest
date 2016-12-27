package com.wqj.mytomcate;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {

	private static String host = "127.0.0.1";  
    private static int port = 9999;  
    
	public static void main1(String[] args) {
		ServerSocket serverSocket = null;
		Socket socket = null;  
        InputStream inputStream = null;  
        OutputStream outputStream = null;  
        String data = null;  
		
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("                服务器已启动....");
			while(true){
				// 客户端
				socket = serverSocket.accept();
				System.out.println("-----  客户端已连接成功！ ----");
				// 获取客户端请求的输入流，从而获取客户端的请求信息
				inputStream = socket.getInputStream();
				byte[] buffer = new byte[1024];
				int len = inputStream.read(buffer);
				if(len>0){
					String msg = new String(buffer, 0, len);
					System.out.println("-----------msg-----------:  " + msg);
				}else{
					System.out.println("-----------error-----------");
				}
				
				// =====================  返回给客户端信息       ========================
				outputStream = socket.getOutputStream();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String date = dateFormat.format(new Date());
				
				String html = "<html>"+ 
						           "<head>"+
						                 "<title>中秋快乐</title>"+ 
						       	    "</head>"+ 
							       	 "<body>" +
					                    "当前系统时间： "+ date + 
					                 "</body>"+
						       "</html>";
				
				outputStream.write(html.getBytes());
				outputStream.flush();
				
//				int read = -1;
//				int len = 0;
//				while (len == 0) { 
//					len = inputStream.available();
//				}
//				
//				byte[] buffer = new byte[len];
//				while ( (read = inputStream.read(buffer)) != -1) {
//					
//					outputStream.write(buffer, 0, read);
//				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{  
            try {
            	if(inputStream!=null){
            		inputStream.close();  
            	}
            	if(outputStream!=null){
            		outputStream.close();  
            	}
            	if(socket!=null){
            		socket.close();  
            	}
            	if(serverSocket!=null){
            		serverSocket.close();
            	}
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  


	}

}
