package org.zgf.learn.email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.mail.internet.MimeMessage.RecipientType;

/**
 * 测试发送简单邮件：仅包含正文部分
 *  正文部分可以是纯文本文件 或者html 文件
 *  发送邮件，需要配置邮件服务器的地址，其实相当于转发而已
 */
public class Test_01_mail_html {
	
	/** 邮件服务器地址   */
	private static final String MAIL_HOST = "smtp.163.com";
	/** 设置超时时间   */
	private static final int MAIL_TIMEOUT = 1000;
	/** 邮件服务器是否否需要登录验证 */
	private static final boolean MAIL_AUTH = true;
	
	/** 邮箱用户名  */
	private static final String AUTH_USER_ADDRESS = "18702293681@163.com";
	
	/** 邮箱登录密码  */
	private static final String AUTH_USER_PASSWORD = "gaofeng0504";
	
	/** 用户邮箱的昵称*/
	private static final String AUTH_USER_NICKNAME = "键盘上的幽灵";
	
	public static void main(String[] args) throws Exception {
//		1. 设置邮件服务器环境
		Properties serverProperties = new Properties();
//			1. 设置服务器地址
		serverProperties.put("mail.smtp.host", MAIL_HOST);
//			2. 设置是否需要身份验证
		serverProperties.put("mail.smtp.auth", MAIL_AUTH);
//			3. 设置超时时间，默认为用不超时,单位为毫秒
		serverProperties.put("mail.smtp.timeout", MAIL_TIMEOUT);
		
//		2. 设置 认证授权
		Authenticator authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(AUTH_USER_ADDRESS, AUTH_USER_PASSWORD);
			}
		};
		Session session = Session.getInstance(serverProperties, authenticator);
		
//		3. 设置邮件内容
//			1. 创建邮件消息对象
			Message mailMsg = new MimeMessage(session);
//			2. 设置邮件主题
			mailMsg.setSubject("测试主题：五一了");
//			3. 设置邮件发件人
			mailMsg.setFrom(new InternetAddress(MimeUtility.encodeText(AUTH_USER_NICKNAME) +"<" +  AUTH_USER_ADDRESS + ">"));
//			mailMsg.setFrom(new InternetAddress(AUTH_USER_ADDRESS)); //不设置用户邮箱昵称的方式
//			4. 设置收件人
			mailMsg.setRecipient(RecipientType.TO, new InternetAddress("3154007684@qq.com"));
//			mailMsg.setRecipients(type, addresses); //多个收件人
//			5. 设置邮件抄送人
//			mailMsg.setRecipient(RecipientType.CC, new InternetAddress("3154007684@qq.com"));
//			6. 设置邮件密送人
//			mailMsg.setRecipient(RecipientType.BCC, new InternetAddress("3154007684@qq.com"));
//			7. 设置邮件正文
			// 7.1 如果正文是纯文本内容，用 setText即可
//			mailMsg.setText("这是一封测试邮件:" + System.currentTimeMillis());
			// 7.1 如果正文是纯文本内容，用 setContent 方法， 第二个参数可以参考：HTTP Content-type 参数对照表
			mailMsg.setContent("这是一封html 邮件：<a href=\"http://www.baidu.com\">百度</a>", "text/html; charset=utf-8");
		
//		4. 发送邮件
			Transport.send(mailMsg);
		System.out.println("^_^ 邮件发送成功  ^_^");
		
	}


}
