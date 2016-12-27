package com.wqj.mytomcate.version_1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {

	private static String host = "127.0.0.1";  
    private static int port = 8888;  
    
	public static void main(String[] args) {
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
				Request request  = new Request(inputStream);
				
				// =====================  返回给客户端信息       ========================
				outputStream = socket.getOutputStream();
				Response response = new Response(outputStream);
				
				// ============== 处理请求和响应的业务逻辑  ===============
				String uri = request.getUri();
				if(uri!=null && isStatic(uri)){
					// 响应静态资源
					response.writeFile(uri.substring(1));
				}else{
					 response.write("11111111111");
				}
				
				if(socket!=null){
            		socket.close();  
            	}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{  
            try {
            	if(outputStream!=null){
            		outputStream.close();  
            	}
            	
            	if(inputStream!=null){
            		inputStream.close();  
            	}
            	
            	if(serverSocket!=null){
            		serverSocket.close();
            	}
            	
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  


	}

	
	public static boolean isStatic(String uri){
		boolean isStatic = false;
		String suffixs[] = {"ccs","js","jpg","png","jpeg","html"};
		for (String suffix : suffixs) {
			if(uri.endsWith("."+suffix)){
				isStatic = true;
				break;
			}
		}
		return isStatic;
	}
	
	
}
