package com.wqj.common;

import com.wqj.common.context.SystemPropertyInit;
import com.wqj.common.util.ValidateUtils;

public class Const {
	public final static String FORNOW_PREFIX = "fornow_";
	public final static String SESSION_NAME = "currentUser";
	public final static String GSOONBUILDER = "gsonBuilder";
	
	

	/** ------- NULL值 ------- **/
	public final static int NULL_VALUE_INT = -99;
	public final static long NULL_VALUE_LONG = -99;
	public final static double NULL_VALUE_DOUBLE = -99;
	public final static String NULL_VALUE_STRING = "";
	public final static String NULL_VALUE_DISPLAY = "-";
	
	/** ------- ZERO值 ------- **/
	public final static int ZERO_VALUE_INT = 0;
	public final static long ZERO_VALUE_LONG = 0;
	public final static double ZERO_VALUE_DOUBLE = 0.0;

	/** ------- ONE值 ------- **/
	public final static int ONE_VALUE_INT = 1;
	public final static long ONE_VALUE_LONG = 1;
	/** ------- TWO值 ------- **/
	public final static int TWO_VALUE_INT = 2;
	public final static long TWO_VALUE_LONG = 2;
	/** ------- THREE值 ------- **/
	public final static int THREE_VALUE_INT = 3;
	public final static long THREE_VALUE_LONG = 3;
	/** ------- FOUR值 ------- **/
	public final static int FOUR_VALUE_INT = 4;
	public final static long FOUR_VALUE_LONG = 4;
	/** ------- FIVE值 ------- **/
	public final static int FIVE_VALUE_INT = 5;
	public final static long FIVE_VALUE_LONG = 5;
	/** ------- SIX值 ------- **/
	public final static int SIX_VALUE_INT = 6;
	public final static long SIX_VALUE_LONG = 6;
	/** ------- SEVEN值 ------- **/
	public final static int SEVEN_VALUE_INT = 7;
	public final static long SEVEN_VALUE_LONG = 7;

	/** ------- 最大值 ------- **/
	public final static double MAX_VALUE_LONG = 99999999;

	/** ------- BOOLEAN值 ------- **/
	public final static int TRUE = 1;
	public final static int FALSE = 0;

	/** ------- 禁用启用 ------- **/
	public final static int ENABLE = 1;
	public final static int DISABLE = 0;

	/** ------ 是否 -------- **/
	public final static int YES = 1;
	public final static int NO = 0;

	/** 帐号状态 **/
	public static final int USER_ACTIVATION = 1; // 激活
	public static final int USER_FREEZE = 0; // 冻结
	public static final int USER_DISABLE = -1; // 禁用

	/** 性别 **/
	public static final int SEX_MAN = 1; // 男
	public static final int SEX_WOMAN = 0; // 女
	public static final int SEX_UNKNOW = -1; // 未知

	/** 时间 **/
	public static final int DAY_OF_YEAR = 365;
	public static final long YEAR_MILLLIS = 31536000000L;

	/** 统计数据类型 **/
	public static final int DATA_DETAIL = 1;
	public static final int DATA_OUTER = 0;
	public static final int DATA_TOTAL = -1;
	public static final int DATA_INVALID = -2;

	/** 图片 **/
	public static final String PIC_PATH = "/images/";
	public static final String ARROW_RED = PIC_PATH + "arrow_red.png";
	public static final String ARROW_GREEN = PIC_PATH + "arrow_green.png";
	public static final String ARROW_BLACK = PIC_PATH + "arrow_black.png";

	/** 操作 **/
	public static final String insert = "insert";
	public static final String update = "update";
	public static final String delete = "delete";
	public static final String nonParentUpdate = "non_parent_update";

	/** 账户金额的操作类型 **/
	// 1、充值 2、付款 3、收款 4、提现
	public static final int RECHARGE = 1;
	public static final int PAY = 2;
	public static final int RECEIVABLES = 3;
	public static final int WITHDRAWAL = 4;

	/** 支付方式 **/
	// 1、支付宝2、微信3、学币
	public static final int ZHIFUBAO = 1;
	public static final int WEIXIN = 2;
	public static final int XUEBI = 3;
	
	/**预约状态**/
	public static final int APPOINT_CREATE=0;//未支付
	public static final int APPOINT_PAID=1;//已支付
	public static final int APPOINT_REFUSE=2;//学长拒绝
	public static final int APPOINT_ACCEPT=3;//学长接受
	public static final int APPOINT_REFUNDING=4;//退款中
	public static final int APPOINT_REFUNDED=5;//已退款
	public static final int APPOINT_COMPLETE=6;//完成

	/* 上传图片的类别   1、学长头像 2、动态 图片 3、话题图片   4、公开课一级推荐    5、一级推荐     6.学长一级推荐   7.话题的一级推荐   8 公开课 的添加图片
	* 9.学长个人主页     10 院校背景图片
	*/
	public static final int HEADER = 1;
	public static final int TOPIC = 2;
	public static final int CONVERSATION = 3;
	public static final int PUBLICCLASS = 4;
	public static final int RECOMMEND = 5;
	public static final int SENIOR = 6;
	public static final int CONVERSATIONREC = 7;
	public static final int PUBLICCLASSADD = 8;
	public static final int HOMEPAGE = 9;
	public static final int SCHOOLBACKDROP = 10;
	public static final int VERSION = 11;
	/***
	 * 图片地址
	 */
	public final static String IMAGE_HOST = SystemPropertyInit.getInstance().getProperty("image.port");
	/** application变量名 **/
	public static final String APP_MENU = "appMenus";

	public static final String getSexPic(int i) {
		String retVal = "";
		switch (i) {
		case SEX_MAN:
			retVal = "<a href='#' class='sweet-tooltip' data-style-tooltip='tooltip-mini-slick' data-text-tooltip='男性'><img src="
					+ "/images/ico_man.png" + " width=16 height=16></a>";
			break;
		case SEX_WOMAN:
			retVal = "<a href='#' class='sweet-tooltip' data-style-tooltip='tooltip-mini-slick' data-text-tooltip='女性'><img src="
					+ "/images/ico_woman.png" + " width=16 height=16></a>";
			break;
		case SEX_UNKNOW:
			retVal = "<a href='#' class='sweet-tooltip' data-style-tooltip='tooltip-mini-slick' data-text-tooltip='未填写'><img src="
					+ "/images/ico_unknown.png" + " width=16 height=16></a>";
			break;
		default:
			retVal = "";
		}
		return retVal;
	}

	public static final String getAccountActivePic(int i) {
		String retVal = "";
		switch (i) {
		case USER_ACTIVATION:
			retVal = "";
			break;
		case USER_FREEZE:
			retVal = "<a href='#' class='sweet-tooltip' data-style-tooltip='tooltip-mini-slick' data-text-tooltip='帐号冻结'><img src="
					+ "/images/ico_lock-16.png" + " width=16 height=16></a>";
			break;
		default:
			retVal = "";
		}
		return retVal;
	}

	public static final String getMemoPic(String memo) {
		String retVal = "<img src=" + "/images/ico_comment.png" + " width=16 height=16>";
		if (memo == null || memo.length() == 0) {
			retVal = "";
		}
		return retVal;
	}
	
	/**
	 * ONS参数
	 */
	//课堂改变状态（预约）
	public static final String ONS_TAG_CHANGEAPPSTATUS = "changeAppStatus";
	//解散群
	public static final String ONS_TAG_DESTROYGROUP = "destroyGroup";
	//发送邮件通知主讲人
	public static final String ONS_TAG_SENDEMAIL = "sendEmail";
	//虚拟报名
	public static final String ONS_TAG_VIRTUALAPPLY  = "virtualApply";
	
	
	//进入课堂
	/*String FORNOW_ACTIVITY_PUBLIC_CLASS=FORNOW_PREFIX + "activity_public_class_";
	String FORNOW_CLASS_ROOM = FORNOW_PREFIX+"class_room";*/
	public static final String FN = "FN";
	public static final	String CLASSENTRY = "fsp";
	public static final String A = "A";
	public static final String AVAILABLEFEE = "AVAILABLEFEE";
	public static final	String TOTLECOUPONS = "TOTLECOUPONS";
	
	public final static String ZERO_TIME_STRING = " 00:00:00";
	public final static String FINAL_TIME_STRING = " 23:59:59";
	
	
	//产生m到n之间的随机数 【m,n)
	
	public static Integer getRandom(Integer m,Integer n) {
		Integer result = 0;
		Integer t;
		if(ValidateUtils.isNotEmpty(m) && ValidateUtils.isNotEmpty(n)){
			if(m.intValue()>n.intValue()){
				t = m;
				m = n;
				n = t;
			}
			result = m+(int)(Math.random()*(n-m));
		}
		return result;
	}
	
	
	public static String getMsg(Integer type) {
		String msg="";
		switch (type) {
		case 1:
			msg="尊敬的学长大人，您发布的话题已经有学生预约啦，点击查看详情";
			break;
		case 2:
			msg="尊敬学长大人，您还有预约话题没有确认，点击查看详情";
			break;
		case 3:
			msg="学长忙碌中，您预约的话题被拒绝了，请重新预约或预约其他学长";
			break;
		case 4:
			msg="小主，您预约的话题学长已经同意，请您提前进入调试设备哦";
			break;
		case 5:
			msg="小主，您的话题马上开始了，请您准时进入课堂哦";
			break;
		case 6:
			msg="尊敬的学长大人，您的话题马上开始了，请您提前进入调试设备哦";
			break;
		case 7:
			msg="尊敬的学长大人，您的话题已被学生取消，点击查看详情";
			break;
		case 8:
			msg="学长忙碌中，您预约的话题被取消了，请重新预约或预约其他学长";
			break;
		case 9:
			msg="小主，话题已经结束，请您对话题进行评价";
			break;
		case 10:
			msg="小主，您的公开课马上开始了，请您提前30分钟进入课堂哦";
			break;
		case 11:
			msg="尊敬的学长大人，您的公开课马上开始了，请您提前30分钟进入调试设备哦";
			break;
		}
		return msg;
	}
	
}
