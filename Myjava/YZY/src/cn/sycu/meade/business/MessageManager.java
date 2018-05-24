package cn.sycu.meade.business;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.sycu.meade.common.MD5Util;
import cn.sycu.meade.dao.MessageDao;
import cn.sycu.meade.entity.*;

public class MessageManager {
	MessageDao dao = new MessageDao();
	public boolean add(MessageBean message){
		clearData(message);
		checkData(message);
		
		try{
			return dao.insert(message) > 0;
		} catch (SQLException e){
			if (e.getMessage().indexOf("FK_Departments_Messages_DepartmentId") >= 0)
				throw new RuntimeException("�ò����Ѵ���");
			else if (e.getMessage().indexOf("FK_Logins_Messages_PublisherId") >= 0)
				throw new RuntimeException("�õ�¼������");
			else
				throw new RuntimeException("�����Ϣ��������ԭ��" + e.getMessage());
		}
	}
	
	private void clearData(MessageBean message) {
		// ��������
		message.setTitle(message.getTitle().trim());// ȥ�����ҿո�
		message.setContent(message.getContent().trim());// ȥ�����ҿո�
		message.setPublishIP(message.getPublishIP().trim());// ȥ�����ҿո�
	}
	
	private void checkData(MessageBean message) {
		// ������֤
		// ��¼������5���ַ�
		if (message.getTitle().length() < 5)
			throw new RuntimeException("���ⲻ������5���ַ�");//�׳��쳣
		// ����������5���ַ�
		if (message.getContent().length() < 6)
			throw new RuntimeException("���ݲ�������20���ַ�");//�׳��쳣
		// Email��ʽ��ȷ
		String regEx = "";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = null;
		
		// IP��ַ��ʽ��ȷ
		regEx = "((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))";
		pattern = Pattern.compile(regEx);
		
		matcher = pattern.matcher(message.getPublishIP());
		if (!matcher.matches())
			throw new RuntimeException("����IP��ַ��ʽ����ȷ");//�׳��쳣
	}
	
	public boolean modify(MessageBean message){
		clearData(message);
		checkData(message);
		
		try{
			if (dao.update(message) > 0)
				return true;
			else
				return false;
		} catch (SQLException e){
			if (e.getMessage().indexOf("FK_Departments_Messages_DepartmentId") >= 0)
				throw new RuntimeException("�ò����Ѵ���");
			else if (e.getMessage().indexOf("FK_Logins_Messages_PublisherId") >= 0)
				throw new RuntimeException("�õ�¼������");
			else
				throw new RuntimeException("�����Ϣ��������ԭ��" + e.getMessage());
		}
	}

	public List<MessageBean> search(MessageSearcher searcher, String sortString) {
		try {
			return dao.select(searcher, sortString);
		} catch (SQLException e) {
			throw new RuntimeException("��ȡ������Ϣʱ����,����ԭ��:" + e.getMessage());
		}
	}

	public boolean remove(int messageId) {
		try {
			return dao.delete(messageId) > 0;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("FK_Departments_Logins_DepartmentId") >= 0)
				throw new RuntimeException("��Ȼ�е�¼��Ϣ�����ڸò��ţ�������Ϣ���ڱ����ã�������ɾ����");
			else
				throw new RuntimeException("ɾ��������Ϣ��������ԭ��" + e.getMessage());
		}
	}
}

