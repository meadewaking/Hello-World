package cn.sycu.meade.business;

import java.sql.SQLException;
import java.util.List;

import cn.sycu.meade.dao.*;
import cn.sycu.meade.entity.*;

public class LoginStateManager {
	LoginStateDao dao = new LoginStateDao();
	
	public boolean add(LoginStateBean loginState){
		clearData(loginState);
		checkData(loginState);
		
		try {
			return dao.insert(loginState) > 0;
		} catch (SQLException e){
			if (e.getMessage().indexOf("LoginStateName_UNIQUE") >= 0)
				throw new RuntimeException("ͬ����¼״̬�Ѵ��ڣ��������");
			else
				throw new RuntimeException("��ӵ�¼״̬��������ԭ��" + e.getMessage());
		}
	}
	
	private void clearData(LoginStateBean loginState) {
		// ��������
		loginState.setLoginStateName(loginState.getLoginStateName().trim());
		loginState.setDescription(loginState.getDescription().trim());
	}
	
	private void checkData(LoginStateBean loginState) {
		// ��������
		if(loginState.getLoginStateName() == null || loginState.getLoginStateName().length() == 0)
		throw new RuntimeException("��¼״̬����Ϊ�ա�");
	}
	
	public boolean remove(int loginStateId) {
		try {
			return dao.delete(loginStateId) > 0;
		} catch (SQLException e){
			if (e.getMessage().indexOf("FK_LoginStates_Logins_TypeId") >= 0)
				throw new RuntimeException("��Ȼ�е�¼��Ϣ�����ڸõ�¼״̬����¼״̬���ڱ����ã�������ɾ����");
			else
				throw new RuntimeException("ɾ����¼״̬��������ԭ��" + e.getMessage());
		}
	}

	public boolean modify(LoginStateBean loginState) {
		clearData(loginState);
		checkData(loginState);
		
		try {
			return dao.update(loginState) > 0;
		} catch (SQLException e){
			if (e.getMessage().indexOf("LoginStateName_UNIQUE") >= 0)
				throw new RuntimeException("ͬ����¼״̬�Ѵ��ڣ��������");
			else
				throw new RuntimeException("��ӵ�¼״̬��������ԭ��" + e.getMessage());
		}
	}

	public LoginStateBean search(int loginStateId) {
		try {
			return dao.select(loginStateId);
		} catch (SQLException e) {
			throw new RuntimeException("��ȡ��¼״̬ʱ����,����ԭ��:" + e.getMessage());
		}
	}
	
	public List<LoginStateBean> search() {
		try {
			return dao.select();
		} catch (SQLException e) {
			throw new RuntimeException("��ȡ��¼״̬ʱ����,����ԭ��:" + e.getMessage());
		}
	}

	public List<LoginStateBean> search(String sortString) {
		try {
			return dao.select(sortString);
		} catch (SQLException e) {
			throw new RuntimeException("��ȡ��¼״̬ʱ����,����ԭ��:" + e.getMessage());
		}
	}
	
	public List<LoginStateBean> search(LoginStateSearcher searcher, String sortString) {
		try {
			return dao.select(searcher,sortString);
		} catch (SQLException e) {
			throw new RuntimeException("��ȡ��¼״̬ʱ����,����ԭ��:" + e.getMessage());
		}
	}
}
