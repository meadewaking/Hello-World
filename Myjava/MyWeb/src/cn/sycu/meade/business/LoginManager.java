package cn.sycu.meade.business;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.sycu.meade.common.MD5Util;
import cn.sycu.meade.dao.LoginDao;
import cn.sycu.meade.entity.LoginBean;

public class LoginManager {
	LoginDao dao = new LoginDao();

	public boolean add(LoginBean login) {
		clearData(login);
		checkData(login);

		try {
			String password = MD5Util.md5Encode(login.getPassword());
			login.setPassword(password);
			if (dao.insert(login) > 0)
				return true;
			else
				return false;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("LoginName_UNIQUE") >= 0)
				throw new RuntimeException("��ͬ��¼���Ѵ���");
			else if (e.getMessage().indexOf("Email_UNIQUE") >= 0)
				throw new RuntimeException("��ͬEmail�Ѵ���");
			else if (e.getMessage().indexOf("FK_Department_Logins_DepartmentId") >= 0)
				throw new RuntimeException("�ò����Ѵ���");
			else if (e.getMessage().indexOf("FK_LoginStates_Logins_StateId") >= 0)
				throw new RuntimeException("�õ�¼״̬������");
			else if (e.getMessage().indexOf("FK_LoginTypes_Logins_TypeId") >= 0)
				throw new RuntimeException("�õ�¼���Ͳ�����");
			else
				throw new RuntimeException("��ӵ�¼��Ϣʱ��������ԭ��" + e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException("������ܴ����������ԭ��" + e.getMessage());
		}
	}

	public boolean update(LoginBean login) {

		try {
			if (dao.update(login) > 0)
				return true;
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("���µ�¼��Ϣʱ��������ԭ��" + e.getMessage());
		}
	}

	private void clearData(LoginBean login) {
		// ��������
		login.setLoginName(login.getLoginName().trim());
		login.setEmail(login.getEmail().trim());
		login.setNickname(login.getNickname().trim());
		login.setRegisterIp(login.getRegisterIp().trim());
		login.setLastLoginIp(login.getLastLoginIp().trim());
		login.setRemark(login.getRemark().trim());
	}

	private void checkData(LoginBean login) {
		// ������֤
		// ��¼������5���ַ�
		if (login.getLoginName().length() < 5)
			throw new RuntimeException("��¼����������5���ַ�");// �׳��쳣
		// ����������5���ַ�
		if (login.getPassword().length() < 6)
			throw new RuntimeException("���벻������6���ַ�");// �׳��쳣
		// Email��ʽ��ȷ
		String regEx = "\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(login.getEmail());
		if (!matcher.matches())
			throw new RuntimeException("Email��ʽ����ȷ");// �׳��쳣
		// IP��ַ��ʽ��ȷ
		regEx = "((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))";
		pattern = Pattern.compile(regEx);

		matcher = pattern.matcher(login.getRegisterIp());
		if (!matcher.matches())
			throw new RuntimeException("IP��ַ��ʽ����ȷ");// �׳��쳣

		matcher = pattern.matcher(login.getLastLoginIp());
		if (!matcher.matches())
			throw new RuntimeException("����½IP��ַ��ʽ����ȷ");// �׳��쳣
	}

	public LoginBean isLogin(String loginName, String password) {
		LoginBean login = null;

		try {
			login = dao.selectByLoginName(loginName); // ��ѯ���ݿ��е��û���������

			if (login != null && login.getPassword().compareTo(password) == 0)
				return login; // ��ѯ��Ϣ��Ϊ��������ƥ���򷵻ز�ѯ��Ϣ
		} catch (SQLException e) {
			throw new RuntimeException("��֤��¼��Ϣʱ�д�����ԭ��" + e.getMessage());
		}
		return null;
	}

	public boolean modifyPassword(String loginName, String oldPassword, String newPassword) {
		LoginBean login = null;

		try {
			login = dao.selectByLoginName(loginName);
			oldPassword = MD5Util.md5Encode(oldPassword);
			if (isLogin(loginName, oldPassword) != null) {
				newPassword = MD5Util.md5Encode(newPassword); // md5����
				login.setPassword(newPassword);

				if (dao.update(login) > 0)
					return true;
				else
					return false;
			} else {
				throw new RuntimeException("ԭ������󣬲����޸����룡");
			}
		} catch (SQLException e) {
			String str = "�޸�����ʱ��������ԭ��" + e.getMessage();
			throw new RuntimeException(str);
		} catch (Exception e) {
			throw new RuntimeException("�޸�����ʱ������ܴ����������ԭ��" + e.getMessage());
		}
	}

	public LoginBean search(String loginName) {
		try {
			return dao.selectByLoginName(loginName);
		} catch (SQLException e) {
			throw new RuntimeException("���ҵ�¼��Ϣʱ��������ԭ��" + e.getMessage());
		}
	}

	public boolean modify(LoginBean login) {
		clearData(login);
		checkData(login);

		try {
			if (dao.update(login) > 0)
				return true;
			else
				return false;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("LoginName_UNIQUE") >= 0)
				throw new RuntimeException("��ͬ��¼���Ѵ���");
			else if (e.getMessage().indexOf("Email_UNIQUE") >= 0)
				throw new RuntimeException("��ͬEmail�Ѵ���");
			else if (e.getMessage().indexOf("FK_Department_Logins_DepartmentId") >= 0)
				throw new RuntimeException("�ò����Ѵ���");
			else if (e.getMessage().indexOf("FK_LoginStates_Logins_StateId") >= 0)
				throw new RuntimeException("�õ�¼״̬������");
			else if (e.getMessage().indexOf("FK_LoginTypes_Logins_TypeId") >= 0)
				throw new RuntimeException("�õ�¼���Ͳ�����");
			else
				throw new RuntimeException("�޸ĵ�¼��Ϣʱ��������ԭ��" + e.getMessage());
		}
	}

	public boolean IsAdmin(LoginBean login) {
		return login.getTypeId() == 1;
	}

	public boolean IsUser(LoginBean login) {
		return login.getTypeId() == 2;
	}
}
