package com.wqj.returnValue.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wqj.common.controller.CommonController;
import com.wqj.common.util.HttpUtils;
import com.wqj.common.util.JSONHelper;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/returnValue")
public class ReturnValueController extends CommonController {

	//http://www.cnblogs.com/crazy-fox/archive/2012/02/18/2357688.html
	
	/**
	 * SpringMVC常用注解實例詳解3：@ResponseBody
	 * http://www.cnblogs.com/sunang/p/3429895.html
	 * http://blog.csdn.net/ccecwg/article/details/39546743
	 * 
	 */
	/*	在传统的开发过程中，我们的控制controller层通常需要转向一个JSP视图；
	 * 但随着WEB2.0相关技术的崛起，我们很多时候只需要返回数据即可，而不是一个JSP页面。
	 * SPRING MVC3的@ResponseBody使Controller直接返回数据，而不是直接指向具体的视图；
	 * 同时通过MessageConverter和produces="text/plain;charset=UTF-8"可以返回各种格式的数据（XML，json，RSS，TEXT，字节流等)
	*/
	
	
	// 返回void
	@RequestMapping("/void")
	public void resultVoid(HttpServletResponse response) throws IOException {
		String message = "Hello woerd ";
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.write(message);
		writer.flush();
		writer.close();
	}
	
	@RequestMapping("/annotation1")  
	public @ResponseBody String responseBody1() {  
		return "The String ResponseBody";  
	} 
	
	@RequestMapping("/annotation2")  
	@ResponseBody
	public String responseBody2() {  
		return "The String ResponseBody";  
	}
	
	//使用ResponseEntity返回字符串
	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8;") 
	public ResponseEntity<String> test(HttpServletResponse response) { 
		return new ResponseEntity<String>("这是测试", HttpStatus.OK); 
	}
	
	//使用ResponseEntity返回字节流
	@RequestMapping("download")
	public ResponseEntity<byte[]> download(String filepath) throws IOException { 
		HttpHeaders headers = new HttpHeaders(); 
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", "filename.png"); //输出的文件名
		System.out.println("filePath:" + filepath);
		File file = new File(filepath); 
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED); 
	}
	
}
