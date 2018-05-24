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
				throw new RuntimeException("同名部门名已存在，不能添加");
			else
				throw new RuntimeException("添加部门信息出错，错误原因：" + e.getMessage());
		}

	}

	private void clearData(LoginTypeBean LoginType) {
		LoginType.setLoginTypeName(LoginType.getLoginTypeName().trim());
		LoginType.setDefaultPage(LoginType.getDefaultPage().trim());
		LoginType.setDescription(LoginType.getDescription().trim());
	}

	private void checkData(LoginTypeBean LoginType) {
		if (LoginType.getLoginTypeName() == null || LoginType.getLoginTypeName().length() == 0)
			throw new RuntimeException("部门名称不能为空。");
	}

	public List<LoginTypeBean> search() {
		try {
			return dao.select();

		} catch (SQLException e) {
			throw new RuntimeException("获取部门信息时出错，错误原因：" + e.getMessage());
		}

	}

	public boolean remove(int LoginTypeId) {
		try {
			return dao.delete(LoginTypeId) > 0;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("FK_LoginTypes_Logins_LoginTypeId") >= 0)
				throw new RuntimeException("仍然有登录信息隶属于该部门（部门信息正在被引用），不能删除");
			else
				throw new RuntimeException("删除部门信息出错，错误原因：" + e.getMessage());
		}

	}

	public boolean modify(LoginTypeBean LoginType) {

		clearData(LoginType);
		checkData(LoginType);

		try {
			return dao.update(LoginType) > 0;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("LoginTypeName_UNIQUE") >= 0)
				throw new RuntimeException("同名部门名已存在，不能添加");
			else
				throw new RuntimeException("修改部门信息出错，错误原因：" + e.getMessage());
		}

	}

	public LoginTypeBean search(int LoginTypeId) {
		try {
			return dao.select(LoginTypeId);

		} catch (SQLException e) {
			throw new RuntimeException("获取部门信息时出错，错误原因：" + e.getMessage());
		}
	}

	public List<LoginTypeBean> search(String sortString) {
		try {
			return dao.select(sortString);

		} catch (SQLException e) {
			throw new RuntimeException("获取部门信息时出错，错误原因：" + e.getMessage());
		}
	}

	public List<LoginTypeBean> search(LoginTypeSearcher searcher, String sortString) {
		try {
			return dao.select(searcher, sortString);

		} catch (SQLException e) {
			throw new RuntimeException("获取部门信息时出错，错误原因：" + e.getMessage());
		}
	}
}
