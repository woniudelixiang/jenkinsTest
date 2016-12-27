package com.wqj.zxing;

import java.util.Map;
import com.google.common.collect.Maps;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;

public class JqueryQrcodeController {
	public static String contents ="https://www.baidu.com/";
	public static BarcodeFormat format = BarcodeFormat.QR_CODE;
	public static Integer width = 300;
	public static Integer height = 300;
	public static String ext= "png";
	public static Map<EncodeHintType, Object> hints = Maps.newHashMap();
	
	public static void main(String[] args) throws Exception {
		
		
		
		
	}

}
