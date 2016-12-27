package com.wqj.mytomcate.version_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {
	
	public static String getFileContent(String path){
		StringBuffer sb = new StringBuffer();
		FileReader reader = null;
		BufferedReader bufferedReader = null;
		try {
			System.out.println("path: " + path);
			reader = new FileReader(path);
			bufferedReader = new BufferedReader(reader);
			String line = null;
			while((line = bufferedReader.readLine()) != null){
				sb.append(line);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(bufferedReader != null){
					bufferedReader.close();
				}
				if(reader != null){
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		String fileContent = getFileContent("src/main/webapp/index.html");
		System.out.println(fileContent);
	}

}
