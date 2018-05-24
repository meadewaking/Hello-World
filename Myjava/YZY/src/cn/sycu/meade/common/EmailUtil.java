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
        Properties props = new Properties(); //Ҳ����Properties props = System.getProperties();
        props.put("mail.smtp.host", SmtpHost); //�洢�����ʼ�����������Ϣ
        props.put("mail.smtp.auth", SmtpAuth); //ͬʱͨ����֤
        Session s = Session.getInstance(props); //���������½�һ���ʼ��Ự
        // s.setDebug(true);

        MimeMessage message = new MimeMessage(s); //���ʼ��Ự�½�һ����Ϣ����

        //�����ʼ�
        InternetAddress from;
		try {
			from = new InternetAddress(fromEmailAddress);
	        message.setFrom(from); //���÷�����
	        InternetAddress to = new InternetAddress(toEmailAddress);
	        message.setRecipient(Message.RecipientType.TO, to); //�����ռ���,���������������ΪTO
	        message.setSubject(title); //��������
	        message.setText(content); //�����ż�����
	        message.setSentDate(new Date()); //���÷���ʱ��
	
	        //�����ʼ�
	        message.saveChanges(); //�洢�ʼ���Ϣ
	        Transport transport = s.getTransport("smtp");
	        transport.connect(SmtpHost, userName, password); //��smtp��ʽ��¼����
	        transport.sendMessage(message, message.getAllRecipients()); //�����ʼ�,���еڶ�������������
	        //����õ��ռ��˵�ַ
	        transport.close();
		} catch (AddressException e) {
			throw new RuntimeException("�����ʼ�ʱ��ַδ�ҵ�������ʧ�ܣ�"); 
		} catch (MessagingException e) {
			throw new RuntimeException("�޷������ʼ������Ժ����ԣ�"); 
        } catch (Exception e) {
			throw new RuntimeException("�����ʼ�ʧ�ܣ�"); 
		}
	}
}
