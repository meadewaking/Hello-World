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
				throw new RuntimeException("同名登录状态已存在，不能添加");
			else
				throw new RuntimeException("添加登录状态出错，错误原因：" + e.getMessage());
		}
	}
	
	private void clearData(LoginStateBean loginState) {
		// 数据清理
		loginState.setLoginStateName(loginState.getLoginStateName().trim());
		loginState.setDescription(loginState.getDescription().trim());
	}
	
	private void checkData(LoginStateBean loginState) {
		// 数据清理
		if(loginState.getLoginStateName() == null || loginState.getLoginStateName().length() == 0)
		throw new RuntimeException("登录状态不能为空。");
	}
	
	public boolean remove(int loginStateId) {
		try {
			return dao.delete(loginStateId) > 0;
		} catch (SQLException e){
			if (e.getMessage().indexOf("FK_LoginStates_Logins_TypeId") >= 0)
				throw new RuntimeException("仍然有登录信息隶属于该登录状态（登录状态正在被引用），不能删除。");
			else
				throw new RuntimeException("删除登录状态出错，错误原因：" + e.getMessage());
		}
	}

	public boolean modify(LoginStateBean loginState) {
		clearData(loginState);
		checkData(loginState);
		
		try {
			return dao.update(loginState) > 0;
		} catch (SQLException e){
			if (e.getMessage().indexOf("LoginStateName_UNIQUE") >= 0)
				throw new RuntimeException("同名登录状态已存在，不能添加");
			else
				throw new RuntimeException("添加登录状态出错，错误原因：" + e.getMessage());
		}
	}

	public LoginStateBean search(int loginStateId) {
		try {
			return dao.select(loginStateId);
		} catch (SQLException e) {
			throw new RuntimeException("获取登录状态时出错,错误原因:" + e.getMessage());
		}
	}
	
	public List<LoginStateBean> search() {
		try {
			return dao.select();
		} catch (SQLException e) {
			throw new RuntimeException("获取登录状态时出错,错误原因:" + e.getMessage());
		}
	}

	public List<LoginStateBean> search(String sortString) {
		try {
			return dao.select(sortString);
		} catch (SQLException e) {
			throw new RuntimeException("获取登录状态时出错,错误原因:" + e.getMessage());
		}
	}
	
	public List<LoginStateBean> search(LoginStateSearcher searcher, String sortString) {
		try {
			return dao.select(searcher,sortString);
		} catch (SQLException e) {
			throw new RuntimeException("获取登录状态时出错,错误原因:" + e.getMessage());
		}
	}
}
