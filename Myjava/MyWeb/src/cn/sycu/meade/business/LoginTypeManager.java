package cn.sycu.meade.business;

import java.sql.SQLException;
import java.util.List;

import cn.sycu.meade.dao.LoginTypeDao;

import cn.sycu.meade.entity.LoginTypeBean;
import cn.sycu.meade.entity.LoginTypeSearcher;

public class LoginTypeManager {
	LoginTypeDao dao = new LoginTypeDao();

	public boolean add(LoginTypeBean LoginType) {
		clearData(LoginType);
		checkData(LoginType);

		try {
			return dao.insert(LoginType) > 0;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("LoginTypeName_UNIQUE") >= 0)
				throw new RuntimeException("ͬ���������Ѵ��ڣ��������");
			else
				throw new RuntimeException("��Ӳ�����Ϣ��������ԭ��" + e.getMessage());
		}

	}

	private void clearData(LoginTypeBean LoginType) {
		LoginType.setLoginTypeName(LoginType.getLoginTypeName().trim());
		LoginType.setDefaultPage(LoginType.getDefaultPage().trim());
		LoginType.setDescription(LoginType.getDescription().trim());
	}

	private void checkData(LoginTypeBean LoginType) {
		if (LoginType.getLoginTypeName() == null || LoginType.getLoginTypeName().length() == 0)
			throw new RuntimeException("�������Ʋ���Ϊ�ա�");
	}

	public List<LoginTypeBean> search() {
		try {
			return dao.select();

		} catch (SQLException e) {
			throw new RuntimeException("��ȡ������Ϣʱ��������ԭ��" + e.getMessage());
		}

	}

	public boolean remove(int LoginTypeId) {
		try {
			return dao.delete(LoginTypeId) > 0;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("FK_LoginTypes_Logins_LoginTypeId") >= 0)
				throw new RuntimeException("��Ȼ�е�¼��Ϣ�����ڸò��ţ�������Ϣ���ڱ����ã�������ɾ��");
			else
				throw new RuntimeException("ɾ��������Ϣ��������ԭ��" + e.getMessage());
		}

	}

	public boolean modify(LoginTypeBean LoginType) {

		clearData(LoginType);
		checkData(LoginType);

		try {
			return dao.update(LoginType) > 0;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("LoginTypeName_UNIQUE") >= 0)
				throw new RuntimeException("ͬ���������Ѵ��ڣ��������");
			else
				throw new RuntimeException("�޸Ĳ�����Ϣ��������ԭ��" + e.getMessage());
		}

	}

	public LoginTypeBean search(int LoginTypeId) {
		try {
			return dao.select(LoginTypeId);

		} catch (SQLException e) {
			throw new RuntimeException("��ȡ������Ϣʱ��������ԭ��" + e.getMessage());
		}
	}

	public List<LoginTypeBean> search(String sortString) {
		try {
			return dao.select(sortString);

		} catch (SQLException e) {
			throw new RuntimeException("��ȡ������Ϣʱ��������ԭ��" + e.getMessage());
		}
	}

	public List<LoginTypeBean> search(LoginTypeSearcher searcher, String sortString) {
		try {
			return dao.select(searcher, sortString);

		} catch (SQLException e) {
			throw new RuntimeException("��ȡ������Ϣʱ��������ԭ��" + e.getMessage());
		}
	}
}
