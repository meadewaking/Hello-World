package cn.sycu.meade.common;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {
	public static String SmtpHost = "smtp.163.com";
	public static String SmtpAuth = "true";
	public static void sendEmail(String title, String content, String fromEmailAddress, String toEmailAddress, String userName, String password) {
        Properties props = new Properties(); //也可用Properties props = System.getProperties();
        props.put("mail.smtp.host", SmtpHost); //存储发送邮件服务器的信息
        props.put("mail.smtp.auth", SmtpAuth); //同时通过验证
        Session s = Session.getInstance(props); //根据属性新建一个邮件会话
        // s.setDebug(true);

        MimeMessage message = new MimeMessage(s); //由邮件会话新建一个消息对象

        //设置邮件
        InternetAddress from;
		try {
			from = new InternetAddress(fromEmailAddress);
	        message.setFrom(from); //设置发件人
	        InternetAddress to = new InternetAddress(toEmailAddress);
	        message.setRecipient(Message.RecipientType.TO, to); //设置收件人,并设置其接收类型为TO
	        message.setSubject(title); //设置主题
	        message.setText(content); //设置信件内容
	        message.setSentDate(new Date()); //设置发信时间
	
	        //发送邮件
	        message.saveChanges(); //存储邮件信息
	        Transport transport = s.getTransport("smtp");
	        transport.connect(SmtpHost, userName, password); //以smtp方式登录邮箱
	        transport.sendMessage(message, message.getAllRecipients()); //发送邮件,其中第二个参数是所有
	        //已设好的收件人地址
	        transport.close();
		} catch (AddressException e) {
			throw new RuntimeException("发送邮件时地址未找到，发送失败！"); 
		} catch (MessagingException e) {
			throw new RuntimeException("无法发送邮件，请稍候再试！"); 
        } catch (Exception e) {
			throw new RuntimeException("发送邮件失败！"); 
		}
	}
}
