/*****************************************************************************
 *
 *                      FORNOW PROPRIETARY INFORMATION
 *
 *          The information contained herein is proprietary to ForNow
 *           and shall not be reproduced or disclosed in whole or in part
 *                    or used for any design or manufacture
 *              without direct written authorization from ForNow.
 *
 *            Copyright (c) 2014 by ForNow.  All rights reserved.
 *
 *****************************************************************************/
package com.wqj.common.util;

import java.io.File;
import java.util.Date;
import java.util.Map;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.wqj.common.context.SystemPropertyInit;

/**
 * @author Jiafa Lv
 * @date Apr 23, 2014 9:06:16 AM
 * @email simon-jiafa@126.com
 * 
 */
@Component
public class MailSendHelper {
	 @Autowired
	private JavaMailSender javaMailSender;
	
	 private static final String CLASS_NAME = MailSendHelper.class.getName();

	/**
	 * 发送的文本测试邮件
	 * 
	 * @param to
	 * @param mailSubject
	 * @param mailBody
	 */
	public void sendTextMail(String toEmail,
			String mailSubject, String mailBody) {
			LoggerUtils.debug(CLASS_NAME, "准备发送文本形式的邮件");
		SimpleMailMessage mail1 = new SimpleMailMessage();
		String from = SystemPropertyInit.getInstance().getProperty("mail.form");
		mail1.setFrom(from);// 发送人名片
		mail1.setTo(toEmail);// 收件人邮箱
		mail1.setSubject(mailSubject);// 邮件主题
		mail1.setSentDate(new Date());// 邮件发送时间
		mail1.setText(mailBody);
		
		SimpleMailMessage mail2 = new SimpleMailMessage();
		mail2.setFrom(from);// 发送人名片
		mail2.setTo(toEmail);// 收件人邮箱
		mail2.setSubject(mailSubject);// 邮件主题
		mail2.setSentDate(new Date());// 邮件发送时间
		mail2.setText(mailBody);
		
		// 群发
		SimpleMailMessage[] mailMessages = { mail1,mail2 };
		javaMailSender.send(mailMessages);

		LoggerUtils.debug(CLASS_NAME, "文本形式的邮件发送成功！！！");
	}

	
	/**
	 * 以 HTML脚本形式邮件发送
	 * 
	 * @param toEmail       收件人邮箱 
	 * @param mailSubject   主题
	 * @param mailBody      邮件内容
	 */
	private void sendHtmlMail(String toEmail, String mailSubject,String mailBody) {
		 JavaMailSender mailSender = javaMailSender;
		 MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			LoggerUtils.debug(CLASS_NAME, "HTML脚本形式邮件正在发送");
			// 设置utf-8或GBK编码，否则邮件会有乱码
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true,
					SystemPropertyInit.getInstance().getProperty("mail.code"));
			// 设置发件人邮箱
			String from = SystemPropertyInit.getInstance().getProperty("mail.form");
			helper.setFrom(new InternetAddress("\"" + MimeUtility.encodeText("王启军测试邮件发送功能") + "\" <" + from + ">"));
			// 设置收件人邮箱
			helper.setTo(toEmail);
			// 设置抄送人邮箱
			helper.setCc(InternetAddress.parse(MimeUtility.encodeText("2516283178@qq.com")
			 + "," + MimeUtility.encodeText("1051384057@qq.com")));
			// 邮件发送时间
			helper.setSentDate(new Date());
			// 主题
			helper.setSubject(mailSubject);
			// 邮件内容，注意加参数true，表示启用html格式
			helper.setText(mailBody, true);
			// 设置回复邮箱
			helper.setReplyTo(new InternetAddress(from));
			// 发送
			mailSender.send(mimeMessage);
			LoggerUtils.debug(CLASS_NAME, "HTML脚本形式邮件发送成功！！！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 以附件的形式发送邮件
	 * 
	 * @param toEmail
	 *            收件人eamil 地址
	 * @param toName
	 *            收件人昵称
	 * @param mailSubject
	 *            主题
	 * @param mailBody
	 *            内容体
	 * @param files
	 *            附件
	 */
	public void sendFileMail(String toEmail, String toName,
			String mailSubject, String mailBody, File[] files) {
		 JavaMailSender mailSender = javaMailSender;
		 MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			LoggerUtils.debug(CLASS_NAME, "带附件和图片的邮件正在发送");

			// 设置utf-8或GBK编码，否则邮件会有乱码
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true,
					SystemPropertyInit.getInstance().getProperty("mail.code"));
			// 设置发送人名片
			String from = SystemPropertyInit.getInstance().getProperty(
					"mail.form");
			helper.setFrom(from);
			
			// 设置收件人邮箱
			helper.setTo(new InternetAddress("\""
					+ MimeUtility.encodeText(toName) + "\" <" + toEmail + ">"));

			// 设置回复地址
			// helper.setReplyTo(new InternetAddress("@qq.com"));

			// 设置收件人抄送的名片和地址(相当于群发了)
			// helper.setCc(InternetAddress.parse(MimeUtility.encodeText("邮箱001")
			// + " <@163.com>," + MimeUtility.encodeText("邮箱002")
			// + " <@foxmail.com>"));

			// 主题
			helper.setSubject(mailSubject);
			// 邮件内容，注意加参数true，表示启用html格式
			helper.setText(mailBody,true);
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++)
					// 加入附件
					helper.addAttachment(
							MimeUtility.encodeText(files[i].getName()),
							files[i]);
			}
			// 加入插图
			// helper.addInline(MimeUtility.encodeText("pic01"), new File(
			// "c:/temp/2dd24be463.jpg"));
			// 发送
			 mailSender.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LoggerUtils.debug(CLASS_NAME, "带附件和图片的邮件发送成功！！！");
	}
	
	/**
	 * 以附件的形式发送邮件
	 * 
	 * @param toEmail
	 *            收件人eamil 地址
	 * @param toName
	 *            收件人昵称
	 * @param mailSubject
	 *            主题
	 * @param mailBody
	 *            内容体
	 * @param files
	 *            附件
	 */
	public void sendFileMail(String toEmail, String toName,
			String mailSubject, String mailBody, Map<String,InputStreamSource> files) {
		 JavaMailSender mailSender = javaMailSender;
		 MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			LoggerUtils.debug(CLASS_NAME, "带附件和图片的邮件正在发送");

			// 设置utf-8或GBK编码，否则邮件会有乱码
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true,
					SystemPropertyInit.getInstance().getProperty("mail.code"));
			// 设置发送人名片
			String from = SystemPropertyInit.getInstance().getProperty(
					"mail.form");
			helper.setFrom(from);
			
			// 设置收件人邮箱
			helper.setTo(new InternetAddress("\""
					+ MimeUtility.encodeText(toName) + "\" <" + toEmail + ">"));

			// 设置回复地址
			// helper.setReplyTo(new InternetAddress("@qq.com"));

			// 设置收件人抄送的名片和地址(相当于群发了)
			// helper.setCc(InternetAddress.parse(MimeUtility.encodeText("邮箱001")
			// + " <@163.com>," + MimeUtility.encodeText("邮箱002")
			// + " <@foxmail.com>"));

			// 主题
			helper.setSubject(mailSubject);
			// 邮件内容，注意加参数true，表示启用html格式
			helper.setText(mailBody,true);
			if (files != null && files.size() > 0) {
				for (String fileName : files.keySet())
					// 加入附件
					helper.addAttachment(
							MimeUtility.encodeText(fileName),
							files.get(fileName));
			}
			// 加入插图
			// helper.addInline(MimeUtility.encodeText("pic01"), new File(
			// "c:/temp/2dd24be463.jpg"));
			// 发送
			 mailSender.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LoggerUtils.debug(CLASS_NAME, "带附件和图片的邮件发送成功！！！");
	}
	
	/**
	 * 
	 * 
	 * @param subject
	 * @param mail
	 * @param content
	 */
	public synchronized void sendSyncMail(final String mail, 
			final String subject,
			final String content) {
		new Thread() {
			public void run() {
				sendHtmlMail(mail, subject, content);
			}
		}.start();
	}
}
