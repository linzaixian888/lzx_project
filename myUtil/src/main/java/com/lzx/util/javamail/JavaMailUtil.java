package com.lzx.util.javamail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailUtil {
	/**
	 * 发送邮件的服务器的IP
	 */
	private String mailServerHost;
	/**
	 * 发送邮件的服务器的端口
	 */
	private int mailServerPort = 25;
	/**
	 * 邮件发送者的地址
	 */
	private String fromAddress;
	/**
	 * 登陆邮件发送服务器的用户名
	 */
	private String username;
	/**
	 * 登陆邮件发送服务器的密码
	 */
	private String password;
	/**
	 * 发送邮件的session
	 */
	private Session sendMailSession;
	public JavaMailUtil(String username, String password) {
		this(null, 25, null, username, password);
	}

	public JavaMailUtil(String mailServerHost, int mailServerPort,
			String fromAddress, String username, String password) {
		this.mailServerHost = mailServerHost;
		this.mailServerPort = mailServerPort;
		this.fromAddress = fromAddress;
		this.username = username;
		this.password = password;
		init();
	}

	/**
	 * 初始化
	 */
	private void init() {
		if (mailServerHost == null) {
			mailServerHost = "smtp." + username.split("@")[1];
		}
		if (fromAddress == null) {
			fromAddress = username;
		}
		Properties p = new Properties();
//		p.put("mail.smtp.host", this.mailServerHost);
		p.put("mail.smtp.port", this.mailServerPort);
		p.put("mail.transport.protocol", "smtp");
		//没有设置密码则对该帐号不进行帐号验证
		if(password==null||"".equals(password.trim())){
			p.put("mail.smtp.auth", false);
		}else{
			p.put("mail.smtp.auth", true);
		}
		sendMailSession = Session.getDefaultInstance(p);
	}

	public void send(String subject, String content, String... toAddrs)
			throws Exception {
		Message mailMessage = new MimeMessage(sendMailSession);
		mailMessage.setFrom(new InternetAddress(fromAddress));
		int len = toAddrs.length;
		Address[] addrs = new Address[len];
		for (int i = 0; i < len; i++) {
			addrs[i] = new InternetAddress(toAddrs[i]);
		}
		mailMessage.setRecipients(Message.RecipientType.TO, addrs);
		// 设置邮件消息的主题
		mailMessage.setSubject(subject);
		// 设置邮件消息发送的时间
		mailMessage.setSentDate(new Date());
		mailMessage.setText(content);
		Transport transport=sendMailSession.getTransport();
		transport.connect(mailServerHost,username, password);
		transport.sendMessage(mailMessage, addrs);
		transport.close();
	}
	public static void main(String[] args) throws Exception {
//		/or(int i=0;i<100;i++){
//			JavaMailUtil util=new JavaMailUtil("linzx@chinabluedon.cn", "linzaixian");
//			util.send("好人", "不好", "linzaixian888@qq.com","linzaixian888@126.com");
//			util=new JavaMailUtil("linzaixian888@qq.com", "linzaixian11");
		JavaMailUtil	util=new JavaMailUtil("smtp.chinabluedon.cn",25,"linzx@chinabluedon.cn","linzx@chinabluedon.cn","linzaixian");
			util.send("好人", "不好", "linzaixian888@qq.com");
//		}
		
	}

}
