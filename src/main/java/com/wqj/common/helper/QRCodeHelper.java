package com.wqj.common.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.stereotype.Component;

import com.wqj.common.context.SystemPropertyInit;
import com.wqj.common.util.HttpUtils;
import com.wqj.common.util.LoggerUtils;
import com.wqj.common.util.StringUtils;
import com.wqj.common.util.UploadHelper;

import net.sf.json.JSONObject;

// 在线生成二维码工具类
@Component
public class QRCodeHelper {
	// 生成二维码
	private static final String CLASS_NAME = QRCodeHelper.class.getName();
	private static final String CALL_BACK_URL = "http://api.wwei.cn/wwei.html?version=1.0&apikey=20160715112280&data=";
	private static final String CALL_BACK_STATUS = "status";
	private static final String CALL_BACK_DATA = "data";
	private static final String CALL_BACK_FILEPATH = "qr_filepath";
	private static final String FILE_EXT_PNG = ".png";
	
	// 解析二维码
	private static final String CALL_PARSE_URL = "http://api.wwei.cn/dewwei.html?version=1.0&apikey=20160715112280&data=";
	private static final String RAW_TEXT = "raw_text";
	
	// 美化二维码
	private static final String CALL_BEAUTIFY_URL = "http://pro.wwei.cn/?version=1.0&apikey=20160715112280&data=";
	/*private static final String RAW_TEXT = "raw_text";*/
	
	
	
	/**
	 * 主入口方法
	 * 具体说明：根据给定的访问地址生成二维码图片，并将二维码图片保存到服务器上，同时生成服务器的图片地址并返回
	 * 
	 * @param url
	 * @return
	 */
	public static String getQRCodeImg(String url){
		String codeImg = null;
		
		String qRCodeImgUrl = getQRCodeImgUrl(url);
		if(StringUtils.isNoEmpty(qRCodeImgUrl)){
			codeImg = downQRCodeImg(qRCodeImgUrl);
		}
		return codeImg;
	}
	
	/**
	 * 解析二维码
	 * @param url  二维码图片网络地址
	 * @return  文本内容
	 */
	public static String parseQRCode(String url){
		String rawText = null;
		String  callUrl = CALL_PARSE_URL + StringUtils.strTrim(url);
		LoggerUtils.debug(CLASS_NAME, callUrl);
		String response = HttpUtils.get(callUrl);
		LoggerUtils.debug(CLASS_NAME, response);
		if(StringUtils.isNoEmpty(response)){
			JSONObject json = JSONObject.fromObject(response);
			Integer status = json.getInt(CALL_BACK_STATUS);
			if(status == 1){
				rawText = json.getJSONObject(CALL_BACK_DATA).getString(RAW_TEXT);
			}
		}
		return rawText;
	}
	
	
	
	
	
	public static void main(String[] args) {
		/*String url = "http://wqjpassport.6655.la:24801/qrcode/20160726/b17c1c77-d203-46c5-bd32-afd87b8b0066.png";
		String rawData = parseQRCode(url);
		System.out.println("rawData: " + rawData);*/
		
		String callUrl = "http://pro.wwei.cn/?text=http://wqjpassport.6655.la:24801/fornow_social/app/senior/sdetail/110";
		String qRCodeImgUrl = null;
		String response = HttpUtils.get(callUrl);
		System.out.println("response: " + response);
		if(StringUtils.isNoEmpty(response)){
			JSONObject json = JSONObject.fromObject(response);
			Integer status = json.getInt(CALL_BACK_STATUS);
			if(status == 1){
				qRCodeImgUrl = json.getJSONObject(CALL_BACK_DATA).getString(CALL_BACK_FILEPATH);
			}
		}
		System.out.println("qRCodeImgUrl: " + qRCodeImgUrl);
		
		String downQRCodeImg = downQRCodeImg(qRCodeImgUrl);
		System.out.println("downQRCodeImg: " + downQRCodeImg);
	}
	
	
	/**
	 * 获得二维码的网络地址
	 * 
	 * @param url
	 * @return
	 */
	private static String getQRCodeImgUrl(String url){
		String qRCodeImgUrl = null;
		
		if(StringUtils.isNoEmpty(url)){
			String  callUrl = CALL_BACK_URL+StringUtils.strTrim(url);
			LoggerUtils.debug(CLASS_NAME, callUrl);
			
			String response = HttpUtils.get(callUrl);
			LoggerUtils.debug(CLASS_NAME, response);
			
			if(StringUtils.isNoEmpty(response)){
				JSONObject json = JSONObject.fromObject(response);
				Integer status = json.getInt(CALL_BACK_STATUS);
				if(status == 1){
					qRCodeImgUrl = json.getJSONObject(CALL_BACK_DATA).getString(CALL_BACK_FILEPATH);
				}
			}
		}
		LoggerUtils.debug(CLASS_NAME, qRCodeImgUrl);
		
		return qRCodeImgUrl;
	}
	
	/**
	 * 根据给定的二维码网络地址，下载到本地服务器，并返回本地服务器的图片地址
	 * 
	 * @param qRCodeImgUrl 本地服务器的图片地址
	 * @return
	 */
	private static String downQRCodeImg(String qRCodeImgUrl){
		String filepath = UploadHelper.getQRCodeWholeFileName() + FILE_EXT_PNG;
		FileOutputStream fs = null;
		InputStream inStream = null;
		try {
			// 下载网络文件
			int byteread = 0;
			URL urls = new URL(qRCodeImgUrl);
			URLConnection conn = urls.openConnection();
			inStream = conn.getInputStream();
			String wholeFileName = SystemPropertyInit.getInstance().getProperty("file.path") + filepath;
			
			// 创建文件夹
			File outFile = new File(wholeFileName);
			if (!outFile.exists()) {
				UploadHelper.makeDir(outFile.getParentFile());
				outFile.createNewFile();
			}
			
			// 写文件流到服务器中
			fs = new FileOutputStream(outFile);
			byte[] buffer = new byte[1204];
			while ((byteread = inStream.read(buffer)) != -1) {
				fs.write(buffer, 0, byteread);
			}
			LoggerUtils.debug(CLASS_NAME, filepath);
			
			return filepath;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fs != null) {
					fs.close();
				}
				if (inStream != null) {
					inStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
//	public static void main(String[] args) {
//		QRCodeHelper.getQRCodeImg("http://offer.51lx.com/webChat/materials/sale/index");
//	}
}
