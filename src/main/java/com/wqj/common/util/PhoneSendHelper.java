package com.wqj.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.cloopen.rest.sdk.CCPRestSDK;
/**
 * 手机验证码发送类
 * 
 * @author Jiafa Lv
 * @email simon-jiafa@126.com
 * @date 2014-6-13 下午3:52:22
 */
@SuppressWarnings("unchecked")
@Component
public class PhoneSendHelper {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final String CLASS_NAME = PhoneSendHelper.class.getName();
	
	// 云通讯
	private static final String SERVER_IP = "app.cloopen.com";
	private static final String SERVER_PORT = "8883";
	
	private static final String ACCOUNT_SID = "aaf98f8947624a55014766694aa3013a";
	private static final String ACCOUNT_TOKEN = "d64479cace0048968da3af8e4f341ae6";
	
	private static final String APPLICATION_ID = "8a48b551473976010147666bf8ca134d";
	
	private static final String WAIT_TIME = "1";
	private static final String TEMPLATE_VALID_ID = "2802";
	
	private static final String RETURN_STATUS = "000000";
	
	// 短信网
	private static final String SMSAPI_URL = "http://smsapi.c123.cn/OpenPlatform/OpenApi?action=sendOnce&ac=1001@501087350001&authkey=3DEDCC10EC63AB9F85D86183ABB32819&cgid=3395&csid=50108735&c=";
	private static final String SMSAPI_PHONE = "&m=";
	
	/**
	 * 发送手机验证码
	 * 
	 * @param code
	 * @param phoneList
	 * @return
	 */
	public boolean phoneSend(String code, List<String> phoneList, int index) {
		HashMap<String, Object> result = null;
		
		CCPRestSDK restAPI = new CCPRestSDK();
		// 初始化服务器地址和端口
		restAPI.init(SERVER_IP, SERVER_PORT);
		// 初始化主帐号名称和主帐号令牌
		restAPI.setAccount(ACCOUNT_SID, ACCOUNT_TOKEN);
		// 初始化应用ID
		restAPI.setAppId(APPLICATION_ID);
		String phone = getPhoneList(phoneList);
		result = restAPI.sendTemplateSMS(phone, TEMPLATE_VALID_ID, new String[]{code,WAIT_TIME});
		
		if(RETURN_STATUS.equals(result.get("statusCode"))){
			//正常返回输出data包体信息（map）
			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
			Set<String> keySet = data.keySet();
			for(String key:keySet){
				Object object = data.get(key);
				logger.debug(CLASS_NAME, key +" = "+object);
			}
			return true;
		}else{
			String statusCode = (String) result.get("statusCode");
			String statusMsg = (String) result.get("statusMsg");
			String message = "错误码=" + statusCode +" 错误信息= "+statusMsg;
			//异常返回输出错误码和错误信息
			logger.debug(CLASS_NAME, message);
			
			if(index < 2){
				logger.debug(CLASS_NAME, "错误发送，重新发送第"+index);
				
				index ++;
				return phoneSend(code, phoneList, index);
			}else{
				return false;
			}
		}
	}
	
	/**
	 * 发送手机语音验证码
	 * 
	 * @param code
	 * @param phoneList
	 * @return
	 */
	public boolean phoneSendVoice(String code, List<String> phoneList, int index) {
		HashMap<String, Object> result = null;
		
		CCPRestSDK restAPI = new CCPRestSDK();
		// 初始化服务器地址和端口
		restAPI.init(SERVER_IP, SERVER_PORT);
		// 初始化主帐号名称和主帐号令牌
		restAPI.setAccount(ACCOUNT_SID, ACCOUNT_TOKEN);
		// 初始化应用ID
		restAPI.setAppId(APPLICATION_ID);
		String phone = getPhoneList(phoneList);
		result = restAPI.voiceVerify(code, phone,"4008008180", "3", "");
		
		if(RETURN_STATUS.equals(result.get("statusCode"))){
			//正常返回输出data包体信息（map）
			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
			Set<String> keySet = data.keySet();
			for(String key:keySet){
				Object object = data.get(key);
				logger.debug(CLASS_NAME, key +" = "+object);
			}
			
			return true;
		}else{
			String statusCode = (String) result.get("statusCode");
			String statusMsg = (String) result.get("statusMsg");
			String message = "错误码=" + statusCode +" 错误信息= "+statusMsg;
			//异常返回输出错误码和错误信息
			logger.debug(CLASS_NAME, message);
			
			if(index < 2){
				logger.debug(CLASS_NAME, "错误发送，重新发送第"+index);
				
				index ++;
				return phoneSend(code, phoneList, index);
			}else{
				return false;
			}
		}
	}
	
	/**
	 * 发任何短信信息接口
	 * 
	 * @param content 内容
	 * @param phoneList 手机数组
	 * @return
	 */
	public boolean sendSMSMsg(String content, List<String> phoneList) {
		String phone = getPhoneList(phoneList);
		StringBuffer sb = new StringBuffer(SMSAPI_URL);
		sb.append(content).append(SMSAPI_PHONE).append(phone);
		String response = HttpUtils.get(StringUtils.replaceStr(sb.toString().trim(), StringUtils.FEMPTY, ""));
		if(StringUtils.isNoEmpty(response)){
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		PhoneSendHelper phoneSendHelper=new PhoneSendHelper();
		StringBuffer sb =new StringBuffer();
		sb.append("王启军向你发送了一条短信，请注意查收...");
		List<String> phoneList = new ArrayList<String>();
		//phoneList.add("18351458870");
		phoneList.add("18351821751");
		boolean isSendSuccess =phoneSendHelper.phoneSendVoice(sb.toString(), phoneList,0);
		System.out.println("短信发送状态："+isSendSuccess);
	}
	
	
	/**
	 * 获得手机列表字符串
	 * 
	 * @param phoneList
	 * @return
	 */
	private String getPhoneList(List<String> phoneList) {
		StringBuffer sb = new StringBuffer();

		for (String phone : phoneList) {
			sb.append(phone).append(",");
		}

		sb.deleteCharAt(sb.length() - 1);

		return sb.toString();
	}

	
	
}
