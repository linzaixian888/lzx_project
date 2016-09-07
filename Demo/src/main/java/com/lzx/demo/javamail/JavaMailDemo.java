package com.lzx.demo.javamail;

public class JavaMailDemo {
	public static void main(String[] args) {
		MailSenderInfo mailInfo = new MailSenderInfo();    
	     mailInfo.setMailServerHost("smtp.163.com");    
	      mailInfo.setMailServerPort("25");    
	      mailInfo.setValidate(true);    
	      mailInfo.setUserName("linzaixian888@163.com");    
	      mailInfo.setPassword("linzaixian11");//您的邮箱密码    
	      mailInfo.setFromAddress("linzaixian888@163.com");    
	      mailInfo.setToAddress("136459338@qq.com");    
	      mailInfo.setSubject("标题");    
	      mailInfo.setContent("内容");    
	         //这个类主要来发送邮件   
	      SimpleMailSender sms = new SimpleMailSender();   
	      sms.sendTextMail(mailInfo);//发送文体格式    
//	      sms.sendHtmlMail(mailInfo);//发送html格式   
	}
}
