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
				throw new RuntimeException("相同登录名已存在");
			else if (e.getMessage().indexOf("Email_UNIQUE") >= 0)
				throw new RuntimeException("相同Email已存在");
			else if (e.getMessage().indexOf("FK_Department_Logins_DepartmentId") >= 0)
				throw new RuntimeException("该部门已存在");
			else if (e.getMessage().indexOf("FK_LoginStates_Logins_StateId") >= 0)
				throw new RuntimeException("该登录状态不存在");
			else if (e.getMessage().indexOf("FK_LoginTypes_Logins_TypeId") >= 0)
				throw new RuntimeException("该登录类型不存在");
			else
				throw new RuntimeException("添加登录信息时出错，错误原因：" + e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException("密码加密处理出错，错误原因：" + e.getMessage());
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
			throw new RuntimeException("更新登录信息时出错，错误原因：" + e.getMessage());
		}
	}

	private void clearData(LoginBean login) {
		// 数据清理
		login.setLoginName(login.getLoginName().trim());
		login.setEmail(login.getEmail().trim());
		login.setNickname(login.getNickname().trim());
		login.setRegisterIp(login.getRegisterIp().trim());
		login.setLastLoginIp(login.getLastLoginIp().trim());
		login.setRemark(login.getRemark().trim());
	}

	private void checkData(LoginBean login) {
		// 数据验证
		// 登录名至少5个字符
		if (login.getLoginName().length() < 5)
			throw new RuntimeException("登录名不能少于5个字符");// 抛出异常
		// 密码名至少5个字符
		if (login.getPassword().length() < 6)
			throw new RuntimeException("密码不能少于6个字符");// 抛出异常
		// Email格式正确
		String regEx = "\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(login.getEmail());
		if (!matcher.matches())
			throw new RuntimeException("Email格式不正确");// 抛出异常
		// IP地址格式正确
		regEx = "((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))";
		pattern = Pattern.compile(regEx);

		matcher = pattern.matcher(login.getRegisterIp());
		if (!matcher.matches())
			throw new RuntimeException("IP地址格式不正确");// 抛出异常

		matcher = pattern.matcher(login.getLastLoginIp());
		if (!matcher.matches())
			throw new RuntimeException("最后登陆IP地址格式不正确");// 抛出异常
	}

	public LoginBean isLogin(String loginName, String password) {
		LoginBean login = null;

		try {
			login = dao.selectByLoginName(loginName); // 查询数据库中的用户名和密码

			if (login != null && login.getPassword().compareTo(password) == 0)
				return login; // 查询信息不为空且密码匹配则返回查询信息
		} catch (SQLException e) {
			throw new RuntimeException("验证登录信息时有错，错误原因：" + e.getMessage());
		}
		return null;
	}

	public boolean modifyPassword(String loginName, String oldPassword, String newPassword) {
		LoginBean login = null;

		try {
			login = dao.selectByLoginName(loginName);
			oldPassword = MD5Util.md5Encode(oldPassword);
			if (isLogin(loginName, oldPassword) != null) {
				newPassword = MD5Util.md5Encode(newPassword); // md5加密
				login.setPassword(newPassword);

				if (dao.update(login) > 0)
					return true;
				else
					return false;
			} else {
				throw new RuntimeException("原密码错误，不能修改密码！");
			}
		} catch (SQLException e) {
			String str = "修改密码时出错，错误原因：" + e.getMessage();
			throw new RuntimeException(str);
		} catch (Exception e) {
			throw new RuntimeException("修改密码时密码加密处理出错，错误原因：" + e.getMessage());
		}
	}

	public LoginBean search(String loginName) {
		try {
			return dao.selectByLoginName(loginName);
		} catch (SQLException e) {
			throw new RuntimeException("查找登录信息时出错，错误原因：" + e.getMessage());
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
				throw new RuntimeException("相同登录名已存在");
			else if (e.getMessage().indexOf("Email_UNIQUE") >= 0)
				throw new RuntimeException("相同Email已存在");
			else if (e.getMessage().indexOf("FK_Department_Logins_DepartmentId") >= 0)
				throw new RuntimeException("该部门已存在");
			else if (e.getMessage().indexOf("FK_LoginStates_Logins_StateId") >= 0)
				throw new RuntimeException("该登录状态不存在");
			else if (e.getMessage().indexOf("FK_LoginTypes_Logins_TypeId") >= 0)
				throw new RuntimeException("该登录类型不存在");
			else
				throw new RuntimeException("修改登录信息时出错，错误原因：" + e.getMessage());
		}
	}

	public boolean IsAdmin(LoginBean login) {
		return login.getTypeId() == 1;
	}

	public boolean IsUser(LoginBean login) {
		return login.getTypeId() == 2;
	}
}
