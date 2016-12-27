package com.wqj.daoDemo.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.google.common.collect.Lists;
import com.wqj.common.annotation.AuthorAnnotation;
import com.wqj.common.bean.ReturnTypeInfo;
import com.wqj.common.controller.CommonController;
import com.wqj.common.enums.AuthodType;
import com.wqj.common.enums.AuthorName;
import com.wqj.common.enums.ReturnType;
import com.wqj.common.util.HttpUtils;
import com.wqj.common.util.JSONHelper;
import com.wqj.common.util.PhoneValidHelper;
import com.wqj.common.util.StringUtils;

@Controller
@RequestMapping(value={"/advices"})
public class AdvicesController extends CommonController{
	
	@RequestMapping(value="/list")
	@AuthorAnnotation(authorName=AuthorName.wangqijun)
	public String list(HttpServletRequest request){
		return "advices_list";
	}
	
	/**
	 * 发送手机验证码
	 */
	@RequestMapping("/sendPhoneCode/{phone}")
	public void sendPhoneCode(@PathVariable String phone,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		try {
			String code=PhoneValidHelper.getValidCode(phone, session);
			if(StringUtils.isNoEmpty(phone)&&StringUtils.isNoEmpty(code)){
				//发送手机验证码
				List<String> phoneList=Lists.newArrayList(phone);
				boolean isSendSuccess=phoneSendHelper.phoneSend(code, phoneList, 0);
				String jsonStr="";
				if(isSendSuccess){
					 jsonStr=JSONHelper.toJson(new ReturnTypeInfo(ReturnType.ONE.getType()));
				}else{
					 jsonStr=JSONHelper.toJson(new ReturnTypeInfo(ReturnType.TWO.getType()));
				}
				HttpUtils.respWrite(response, jsonStr);
			}
		} catch (Exception e) {
			//saveException(AuthodType.wangqijun.getName(), SIMPLE_CLASS_NAME,"sendPhoneCode()", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送语音验证码
	 */
	@RequestMapping("/sendVoiceCode/{phone}")
	public void sendVoiceCode(@PathVariable String phone,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		try {
			String code=PhoneValidHelper.getValidCode(phone, session);
			if(StringUtils.isNoEmpty(phone)&&StringUtils.isNoEmpty(code)){
				//发送手机验证码
				List<String> phoneList=Lists.newArrayList(phone);
				boolean isSendSuccess=phoneSendHelper.phoneSendVoice(code, phoneList, 0);
				String jsonStr="";
				if(isSendSuccess){
					jsonStr=JSONHelper.toJson(new ReturnTypeInfo(ReturnType.ONE.getType()));
				}else{
					jsonStr=JSONHelper.toJson(new ReturnTypeInfo(ReturnType.TWO.getType()));
				}
				HttpUtils.respWrite(response, jsonStr);
			}
		} catch (Exception e) {
			//saveException(AuthodType.wangqijun.getName(), SIMPLE_CLASS_NAME,"sendVoiceCode()", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送手机短信------------------TODO
	 */
	@RequestMapping("/sendPhoneShorMessage/{phone}")
	public void sendPhoneShorMessage(@PathVariable String phone,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		try {
			if(StringUtils.isNoEmpty(phone)){
				StringBuffer sb =new StringBuffer();
				sb.append("王启军向你发送了一条短信，请注意查收...");
				List<String> phoneList = new ArrayList<String>();
				phoneList.add(phone);
				boolean isSendSuccess =phoneSendHelper.sendSMSMsg(sb.toString(), phoneList);
				String jsonStr="";
				if(isSendSuccess){
					jsonStr=JSONHelper.toJson(new ReturnTypeInfo(ReturnType.ONE.getType()));
				}else{
					jsonStr=JSONHelper.toJson(new ReturnTypeInfo(ReturnType.TWO.getType()));
				}
				HttpUtils.respWrite(response, jsonStr);
			}
		} catch (Exception e) {
			//saveException(AuthodType.wangqijun.getName(), SIMPLE_CLASS_NAME,"sendPhoneShorMessage()", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送邮件
	 */
	@RequestMapping("/sendMail")
	public void sendMail(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		try {
			String mailTitle = "邮件的标题";
			String mailContent="邮件的内容";
//			sendMaileUtil.sendSyncMail("1051384057@qq.com", mailTitle, mailContent);
//			sendMaileUtil.sendSyncMail("2516283178@qq.com", mailTitle, mailContent);
			//sendMaileUtil.sendSyncMail("wqjjob@126.com", mailTitle, mailContent);
			sendMaileUtil.sendTextMail("2516283178@qq.com", mailTitle, mailContent);
			String jsonStr=JSONHelper.toJson(new ReturnTypeInfo(ReturnType.ONE.getType()));
			HttpUtils.respWrite(response, jsonStr);
		} catch (Exception e) {
			saveException(AuthodType.wangqijun.getName(), SIMPLE_CLASS_NAME,"sendMail()", e);
			e.printStackTrace();
		}
	}
	
	
	
}
