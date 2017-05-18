package com.wqj.common.utils.pojo;

import java.io.Serializable;

public class Globals implements Serializable {
	
	//	session中存放token的key
    public static final String TRANSACTION_TOKEN_KEY = "session_token";
    // 请求参数中token的key
    public static final String TOKEN_KEY = "request_token";
    
    public static String TOKEN_KEY_SUFFIX = "suffer";
    
}
