package com.wqj.mytomcate.version_1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Response {
	private OutputStream outputStream;

	public Response(OutputStream outputStream) throws IOException {
		this.outputStream = outputStream;
	}


	public void write(String content){
		try {
			outputStream.write(content.getBytes());
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(outputStream != null){
					outputStream.close();  
				}
			} catch (Exception e) {  
				e.printStackTrace();  
			}  
		}

	}


	/**
	 * 响应静态资源文件
	 * @param path
	 */
	 public void writeHtmlFile(String path){
		 try {
			 String fileContent = FileUtils.getFileContent(path);
			 System.out.println("fileContent: " + fileContent);
			 outputStream.write(fileContent.getBytes());
			 outputStream.flush();
		 } catch (IOException e) {
			 e.printStackTrace();
		 }finally{
			 try {
				 if(outputStream != null){
					 outputStream.close();  
				 }
			 } catch (Exception e) {  
				 e.printStackTrace();  
			 }  
		 }
	 }

	 
	 public  void writeFile(String path){
		 FileInputStream inputStream = null;
		 try {
			 inputStream = new FileInputStream(path);
			 byte[] buff = new byte[512];
			 int len = 0;
			 while((len = inputStream.read(buff)) != -1){
				 outputStream.write(buff, 0, len);
			 }
			 outputStream.flush();
		 } catch (Exception e) {
			 e.printStackTrace();
		 } finally{
			 try {
				 if(inputStream!=null){
					 inputStream.close();
				 }
				 if(outputStream != null){
					 outputStream.close();  
				 }
			 } catch (Exception e) {  
				 e.printStackTrace();  
			 }  
		 }
	 }
}
