package cn.sycu.meade.business;

import java.sql.SQLException;
import java.util.List;

import cn.sycu.meade.dao.LoginLogDao;
import cn.sycu.meade.entity.LoginLogBean;
import cn.sycu.meade.entity.LoginLogSearcher;



public class LoginLogManager {
	LoginLogDao dao = new LoginLogDao();
	
	public boolean add(LoginLogBean loginLog){
		clearData(loginLog);
		checkData(loginLog);
		
		try {
			return dao.insert(loginLog) > 0;
		} catch (SQLException e){
				throw new RuntimeException("��ӵ�¼��־��������ԭ��" + e.getMessage());
		}
	}
	
	private void clearData(LoginLogBean loginLog) {
		// ��������
		loginLog.setLoginIp(loginLog.getLoginIp().trim());
	}
	
	private void checkData(LoginLogBean loginLog) {
		// �������
		
	}
	
	public boolean remove(int loginLogId) {
		try {
			return dao.delete(loginLogId) > 0;
		} catch (SQLException e){
				throw new RuntimeException("ɾ����¼��־��������ԭ��" + e.getMessage());
		}
	}

	public boolean modify(LoginLogBean loginLog) {
		clearData(loginLog);
		checkData(loginLog);
		
		try {
			return dao.update(loginLog) > 0;
		} catch (SQLException e){
				throw new RuntimeException("��ӵ�¼��־��������ԭ��" + e.getMessage());
		}
	}

	public LoginLogBean search(int loginLogId) {
		try {
			return dao.select(loginLogId);
		} catch (SQLException e) {
			throw new RuntimeException("��ȡ��¼��־ʱ����,����ԭ��:" + e.getMessage());
		}
	}
	
	public List<LoginLogBean> search() {
		try {
			return dao.select();
		} catch (SQLException e) {
			throw new RuntimeException("��ȡ��¼��־ʱ����,����ԭ��:" + e.getMessage());
		}
	}

	public List<LoginLogBean> search(String sortString) {
		try {
			return dao.select(sortString);
		} catch (SQLException e) {
			throw new RuntimeException("��ȡ��¼��־ʱ����,����ԭ��:" + e.getMessage());
		}
	}
	
	public List<LoginLogBean> search(LoginLogSearcher searcher, String sortString) {
		try {
			return dao.select(searcher,sortString);
		} catch (SQLException e) {
			throw new RuntimeException("��ȡ��¼��־ʱ����,����ԭ��:" + e.getMessage());
		}
	}
}
