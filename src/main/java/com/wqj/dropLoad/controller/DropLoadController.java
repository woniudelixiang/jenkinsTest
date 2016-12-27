package com.wqj.dropLoad.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wqj.common.bean.Page;
import com.wqj.common.controller.CommonController;
import com.wqj.common.util.JSONHelper;
import com.wqj.dropLoad.entity.BaseLanguage;



@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/dropLoad")
public class DropLoadController extends CommonController {

	@RequestMapping("/list")
	public String list() {
		return "drop_load";
	}
	
//	@ResponseBody
//	@RequestMapping("/updateJson")
//	public String updateJson(){
//		String json = "{" +
//				"\"lists\":[" +
//				" {" +
//				" \"title\":\"Apple 苹果 iPhone 6 Plus 16G 4G手机 金色 公开版（三网通用A1524)\"," +
//				"\"pic\":\"http://d8.yihaodianimg.com/N05/M00/24/DF/CgQI0lQ3umiAYat_AAMa5tG_TXY20001_80x80.jpg\"," +
//				"\"link\":\"#1\"," +
//				" \"date\":\"2015-01-01\"" +
//				" }," +
//				"{" +
//				"\"title\":\"Meizu 魅族 魅蓝note 移动4G手机16G版 蓝色 双卡双待\"," +
//				"\"pic\":\"http://d7.yihaodianimg.com/N06/M06/A3/BE/CgQIzlSrnfaAHAzvAAJ8k2SN73Q20901_80x80.jpg\"," +
//				"\"link\":\"#2\"," +
//				"\"date\":\"2015-01-02\"" +
//         		"}," +
//         		"{" +
//         		"\"title\":\"Meizu 魅族 MX4 Pro 移动4G手机16G版 金色\"," +
//         		"\"pic\":\"http://d6.yihaodianimg.com/N06/M0B/10/41/CgQIzlUGpeeAXg4zAAOEHqy2RVU97901_80x80.jpg\"," +
//         		"\"link\":\"#3\"," +
//         		" \"date\":\"2015-01-03\"" +
//         		"}," +
//         		"{" +
//         		"\"title\":\"华为 Mate7 MT7-CL00 标准版 电信4G手机 月光银 双卡双待双通 16G存储内存版\"," +
//         		"\"pic\":\"http://d6.yihaodianimg.com/N07/M04/F0/85/CgQI0FRHF-KAbTYpAANdzqOuu-411501_80x80.jpg\"," +
//         		"\"link\":\"#4\"," +
//         		"\"date\":\"2015-01-04\"" +
//         		"}," +
//         		"{" +
//         		" \"title\":\"小米 小米4 MI4 4G智能手机 白色 联通合约版\"," +
//         		" \"pic\":\"http://d9.yihaodianimg.com/N06/M0B/F2/B7/CgQIzlTz89-AfGaAAAJZPMkqd-476801_80x80.jpg\"," +
//         		"\"link\":\"#5\"," +
//         		"\"date\":\"2015-01-05\"" +
//         		"}," +
//         		"{" +
//         		" \"title\":\"Lenovo 联想 笋尖 S60-t 樱花粉 移动4G手机 双卡双待\"," +
//         		" \"pic\":\"http://d8.yihaodianimg.com/V00/M03/D9/36/CgQDslT9YrKAMMsoAABq-UGgxY401201_80x80.jpg\"," +
//         		"\"link\":\"#6\"," +
//         		"\"date\":\"2015-01-06\"" +
//         		"}," +
//         		"{" +
//         		" \"title\":\"Sony 索尼 Xperia Z2 L50U 4G智能手机 白色 联通定制\"," +
//         		"\"pic\":\"http://d9.yihaodianimg.com/N04/M07/C6/07/CgQDr1Ny7EOAax5LAAENzd6wWAI46401_80x80.jpg\"," +
//         		"\"link\":\"#7\"," +
//         		"\"date\":\"2015-01-07\"" +
//         		"}," +
//         		"{" +
//         		" \"title\":\"Nubia 努比亚 大牛3 Z7 Max （NX505H）联通移动双4G手机 黑色 双卡双待\"," +
//         		" \"pic\":\"http://d8.yihaodianimg.com/N03/M04/82/54/CgQCs1Ppq7uAFJV1AAanTDJTG3091901_80x80.jpg\"," +
//         		"\"link\":\"#8\"," +
//         		" \"date\":\"2015-01-08\"" +
//         		"}," +
//         		"{" +
//         		"\"title\":\"Lenovo 联想 乐檬 K3（K30-W）16G 典雅黄 联通4G手机 双卡双待\"," +
//         		"\"pic\":\"http://d6.yihaodianimg.com/N06/M0A/E7/B7/ChEbu1T_9eyAcy-fAAFIOWgIFnY38501_80x80.jpg\"," +
//         		"\"link\":\"#9\"," +
//         		"\"date\":\"2015-01-09\"" +
//         		"}," +
//         		"{" +
//         		"\"title\":\"华为 荣耀 3X畅玩版 G750-T01 移动3G手机 手机 白色\"," +
//         		"\"pic\":\"http://d8.yihaodianimg.com/N02/M0A/A5/5B/CgQCsFM-HxyAP2alAARr1DWBiAM30401_80x80.jpg\"," +
//         		"\"link\":\"#10\"," +
//         		"\"date\":\"2015-01-10\"" +
//         		"}" +
//         		"]" +
//         		"}";
//		return json;
//	}
	
	
	@ResponseBody
	@RequestMapping(value="/updateJson")
	public String updateJson(Page page,long id){
		System.out.println("id: " + id);
		List<BaseLanguage> findPage = baseLanguageService.getLanguageByParam(id);
		return JSONHelper.toJson(findPage);
	}

	
}
