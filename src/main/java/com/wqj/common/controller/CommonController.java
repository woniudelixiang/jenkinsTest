package com.wqj.common.controller;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wqj.bootstrapAce.service.BootstrapAceMenuService;
import com.wqj.common.context.SystemPropertyInit;
import com.wqj.common.enums.ViewName;
import com.wqj.common.util.MailSendHelper;
import com.wqj.common.util.PhoneSendHelper;
import com.wqj.daoDemo.service.UserService;
import com.wqj.dropLoad.service.BaseLanguageService;
import com.wqj.gson.service.SuperAppointmentService;
import com.wqj.jqueryFileUpload.service.BaseImageEntityService;
import com.wqj.jqueryFileUpload.service.ImageService;
import com.wqj.jqueryFileUpload.service.ImgUserService;
import com.wqj.lottery.service.PrizeService;
import com.wqj.ztree.service.ZtreeService;

/**
 * 公共的Controller
 *
 * @author Qijun wang
 * @param <L>
 * @email wqjjob@126.com
 * @date 2016-1-27 下午2:09:45
 */
public abstract class CommonController {
	protected final String CLASS_NAME = this.getClass().getName();
	protected final String SIMPLE_CLASS_NAME = this.getClass().getSimpleName();

	/**
	 * 同一个Controller中的 转发
	 * 
	 * @param action
	 * @return
	 */
	protected String forward(String action) {
		//获取当前Controller的RequestMapping注解的value的第一个值
		String path = this.getClass().getAnnotation(RequestMapping.class).value()[0];
		String resultPath = path + "/" + action;
		System.out.println("forward  --  resultPath:"+resultPath);
		return ViewName.forward.getValue()+resultPath;
	}
	
	/**
	 * 同一个Controller中的 转发
	 * 
	 * @param action
	 * @return
	 */
	protected String diffForward(String action) {
		return ViewName.forward.getValue()+action;
	}
	
	/**
	 * 同一个Controller中的 重定向
	 * 
	 * @param action
	 * @return
	 */
	protected String redirect(String action) {
		String path = this.getClass().getAnnotation(RequestMapping.class).value()[0];
		String resultPath = path + "/" + action;
		System.out.println("resultPath:"+resultPath);
		return ViewName.redirect.getValue() + resultPath;
	}
	
	/**
	 * 不同Controller之间的 重定向
	 * 
	 * @param action
	 * @return
	 */
	protected String diffRedirect(String action) {
		return ViewName.redirect.getValue() + action;
	}
	
	/**
	 * 发送异常信息
	 * 
	 * @param authorName
	 * @param modelName
	 * @param functionName
	 * @param e
	 */
	protected void saveException(String authorName, String modelName,
			String functionName, Exception e) {
		try {
			// Exception的具体说明
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(baos));
			String exception = baos.toString();
			
			// 获得当前的IP地址
			InetAddress addr = InetAddress.getLocalHost();
			String ip = addr.getHostAddress().toString();// 获得本机IP
			
			// 发送错误的邮件
			String mailContent = "Senior:[" + ip + "(" 
					+ SystemPropertyInit.getInstance().getProperty("server.host") + ")]"
					+ authorName + ":" + modelName + "==>" + functionName;
			// 发送错误的邮件
			sendMaileUtil.sendSyncMail("simon-jiafa@126.com", mailContent, exception);
			sendMaileUtil.sendSyncMail("l_chengzi@sina.com", mailContent, exception);// 陈乐
			sendMaileUtil.sendSyncMail("wqjjob@126.com", mailContent, exception); // 王启军
			sendMaileUtil.sendSyncMail("1051384057@qq.com", mailContent, exception); // 葛升
			sendMaileUtil.sendSyncMail("18551745986@163.com", mailContent, exception); // 朱子明
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
	}
	
	
	@Autowired
	public  UserService userService;
	@Autowired
	public PhoneSendHelper phoneSendHelper;
	@Autowired
	public MailSendHelper sendMaileUtil;
	@Autowired
	public ImageService imageService;
	@Autowired
	public BaseImageEntityService baseImageEntityService;
	@Autowired
	public ImgUserService imgUserService;
	@Autowired
	public BaseLanguageService baseLanguageService;
	@Autowired
	public PrizeService prizeService;
	@Autowired
	public ZtreeService ztreeService;
	@Autowired
	public BootstrapAceMenuService bootstrapAceMenuService;
	@Autowired
	public SuperAppointmentService superAppointmentService;
}
