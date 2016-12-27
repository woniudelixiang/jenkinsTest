package com.wqj.response;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wqj.common.controller.CommonController;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/response")
public class ResponseController extends CommonController {
	// http://www.cnblogs.com/crazy-fox/archive/2012/02/18/2357688.html  
	
	
	
	// http://127.0.0.1:8080/dao-study/response/annotation
	@RequestMapping("/annotation")  
	public @ResponseBody String responseBody() {  
		return "The String ResponseBody";  
	}  

	// http://127.0.0.1:8080/dao-study/response/charset/accept
	@RequestMapping("/charset/accept")  
	public @ResponseBody String responseAcceptHeaderCharset() {  
		return "你好！_(\"Hello world!\")";  
	}  

	// http://127.0.0.1:8080/dao-study/response/charset/produce  
	@RequestMapping(value="/charset/produce", produces="text/plain;charset=UTF-8")  
	public @ResponseBody String responseProducesConditionCharset() {  
		return "你好！_(\"Hello world!\")";
	}  

	// http://127.0.0.1:8080/dao-study/response/entity/status
	@RequestMapping("/entity/status")  
	public ResponseEntity<String> responseEntityStatusCode() {  
		return new ResponseEntity<String>("The String ResponseBody with custom status code (403 Forbidden)",  
				HttpStatus.FORBIDDEN);  
	}  

	// http://127.0.0.1:8080/dao-study/response/entity/headers
	@RequestMapping("/entity/headers")  
	public ResponseEntity<String> responseEntityCustomHeaders() {  
		HttpHeaders headers = new HttpHeaders();  
		headers.setContentType(MediaType.TEXT_PLAIN);  
		return new ResponseEntity<String>("The String ResponseBody with custom header Content-Type=text/plain",  
				headers, HttpStatus.OK);  
	}  
}
