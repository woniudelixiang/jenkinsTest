package com.wqj.lottery.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wqj.common.bean.CommonJsonBean;
import com.wqj.common.controller.CommonController;
import com.wqj.common.factory.BeanFactory;
import com.wqj.common.util.JSONHelper;
import com.wqj.lottery.entity.Prize;

/**
 * 抽奖
 * @author Qijun wang
 * @email wqjjob@126.com
 * @date 2016年7月9日 上午10:48:30
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/lottery")
public class LotteryController extends CommonController {

	@RequestMapping("/list")
	public String list() {
		return "lottery/list";
	}
	
	@ResponseBody
	@RequestMapping("/getPrizeInfo")
	public String getPrizeInfo() {
		CommonJsonBean jsonBean = BeanFactory.newInstance(CommonJsonBean.class);
		try {
			List<Prize> prizes = prizeService.findAll();
			jsonBean.setDatas(prizes);
		} catch (Exception e) {
			e.printStackTrace();
			jsonBean.setStatusCode(CommonJsonBean.CODE_FAIL);
		}
		return JSONHelper.toJson(jsonBean);
	}
	
	public static void main(String[] args) {
		Object[][] prizeArr = new  Object[][]{
			//里面的指针转动
			{1,1,14,"一等奖",1},
			{2,346,364,"一等奖",1},
			{3,16,44,"不要灰心",10},
			{4,46,74,"神马也没有",10},
			{5,76,104,"祝您好运",10},
			{6,106,134,"二等奖",2},
			{7,136,164,"再接再厉",10},
			{8,166,194,"神马也没有",10},
			{9,196,224,"运气先攒着",10},
			{10,226,254,"三等奖",5},
			{11,256,284,"要加油哦",10},
			{12,286,314,"神马也没有",10},
			{13,316,344,"谢谢参与",10}
		};
		
		System.out.println(JSONHelper.toJson(prizeArr));

	}
	
	
}
