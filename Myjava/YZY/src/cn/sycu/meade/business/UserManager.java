package cn.sycu.meade.business;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.sycu.meade.common.MD5Util;
import cn.sycu.meade.dao.*;
import cn.sycu.meade.entity.*;

public class UserManager {
	UserDao dao = new UserDao();
	public boolean add(UserBean user){
		clearData(user);
		checkData(user);
		
		try{
			return dao.insert(user) > 0;
		} catch (SQLException e){
			if (e.getMessage().indexOf("LoginId_UNIQUE") >= 0)
				throw new RuntimeException("���û��Ѿ���Ӧ�û���Ϣ��������ӣ��볢���޸ġ�");
			else  if (e.getMessage().indexOf("CardId_UNIQUE") >= 0)
				throw new RuntimeException("��֤�����ѱ�ʹ�á�");
			else
				throw new RuntimeException("����û���Ϣʱ��������ԭ��" + e.getMessage());
		} 
	}
	
	private void clearData(UserBean user) {
		// ��������
		user.setUserName(user.getUserName().trim());
		user.setGender(user.getGender().trim());
		user.setCardId(user.getCardId().trim());
		user.setRemark(user.getRemark().trim());
	}
	
	private void checkData(UserBean user) {
		// ������֤
		// �û�������5���ַ�
		if (user.getUserName().length() < 5)
			throw new RuntimeException("�û�����������5���ַ�");//�׳��쳣
		// Email��ʽ��ȷ
		String regEx = "\\d{15}|\\d{18}|\\d{17}[Xx]";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(user.getCardId());
		if (!matcher.matches())
			throw new RuntimeException("���֤�����ʽ����ȷ");//�׳��쳣
		}
	
	public boolean modify(UserBean user){
		clearData(user);
		checkData(user);
		
		try{
			if (dao.update(user) > 0)
				return true;
			else
				return false;
		} catch (SQLException e){
			if (e.getMessage().indexOf("LoginId_UNIQUE") >= 0)
				throw new RuntimeException("���û��Ѿ���Ӧ�û���Ϣ��������ӣ��볢���޸ġ�");
			else  if (e.getMessage().indexOf("CardId_UNIQUE") >= 0)
				throw new RuntimeException("��֤�����ѱ�ʹ�á�");
			else
				throw new RuntimeException("�޸��û���Ϣʱ��������ԭ��" + e.getMessage());
		}
	}
	
	public UserBean search(int loginId) {
		try{
			return dao.selectUserByLoginId(loginId);
		} catch (SQLException e){
			throw new RuntimeException("��ѯ�û���Ϣʱ��������ԭ��" + e.getMessage());
		}
	}
	
	public UserBean searchCardId(String CardId) {
		try{
			return dao.selectUserByCardId(CardId);
		} catch (SQLException e){
			throw new RuntimeException("��ѯ�û���Ϣʱ��������ԭ��" + e.getMessage());
		}
	}
	
	public boolean existedCardId(String CardId) {
		return searchCardId(CardId) != null;
	}
}



