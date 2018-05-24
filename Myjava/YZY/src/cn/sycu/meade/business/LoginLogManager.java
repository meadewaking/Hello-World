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
				throw new RuntimeException("添加登录日志出错，错误原因：" + e.getMessage());
		}
	}
	
	private void clearData(LoginLogBean loginLog) {
		// 数据清理
		loginLog.setLoginIp(loginLog.getLoginIp().trim());
	}
	
	private void checkData(LoginLogBean loginLog) {
		// 检查数据
		
	}
	
	public boolean remove(int loginLogId) {
		try {
			return dao.delete(loginLogId) > 0;
		} catch (SQLException e){
				throw new RuntimeException("删除登录日志出错，错误原因：" + e.getMessage());
		}
	}

	public boolean modify(LoginLogBean loginLog) {
		clearData(loginLog);
		checkData(loginLog);
		
		try {
			return dao.update(loginLog) > 0;
		} catch (SQLException e){
				throw new RuntimeException("添加登录日志出错，错误原因：" + e.getMessage());
		}
	}

	public LoginLogBean search(int loginLogId) {
		try {
			return dao.select(loginLogId);
		} catch (SQLException e) {
			throw new RuntimeException("获取登录日志时出错,错误原因:" + e.getMessage());
		}
	}
	
	public List<LoginLogBean> search() {
		try {
			return dao.select();
		} catch (SQLException e) {
			throw new RuntimeException("获取登录日志时出错,错误原因:" + e.getMessage());
		}
	}

	public List<LoginLogBean> search(String sortString) {
		try {
			return dao.select(sortString);
		} catch (SQLException e) {
			throw new RuntimeException("获取登录日志时出错,错误原因:" + e.getMessage());
		}
	}
	
	public List<LoginLogBean> search(LoginLogSearcher searcher, String sortString) {
		try {
			return dao.select(searcher,sortString);
		} catch (SQLException e) {
			throw new RuntimeException("获取登录日志时出错,错误原因:" + e.getMessage());
		}
	}
}
