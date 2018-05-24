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
				throw new RuntimeException("该用户已经对应用户信息，不能添加，请尝试修改。");
			else  if (e.getMessage().indexOf("CardId_UNIQUE") >= 0)
				throw new RuntimeException("该证件号已被使用。");
			else
				throw new RuntimeException("添加用户信息时出错，错误原因：" + e.getMessage());
		} 
	}
	
	private void clearData(UserBean user) {
		// 数据清理
		user.setUserName(user.getUserName().trim());
		user.setGender(user.getGender().trim());
		user.setCardId(user.getCardId().trim());
		user.setRemark(user.getRemark().trim());
	}
	
	private void checkData(UserBean user) {
		// 数据验证
		// 用户名至少5个字符
		if (user.getUserName().length() < 5)
			throw new RuntimeException("用户名不能少于5个字符");//抛出异常
		// Email格式正确
		String regEx = "\\d{15}|\\d{18}|\\d{17}[Xx]";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(user.getCardId());
		if (!matcher.matches())
			throw new RuntimeException("身份证号码格式不正确");//抛出异常
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
				throw new RuntimeException("该用户已经对应用户信息，不能添加，请尝试修改。");
			else  if (e.getMessage().indexOf("CardId_UNIQUE") >= 0)
				throw new RuntimeException("该证件号已被使用。");
			else
				throw new RuntimeException("修改用户信息时出错，错误原因：" + e.getMessage());
		}
	}
	
	public UserBean search(int loginId) {
		try{
			return dao.selectUserByLoginId(loginId);
		} catch (SQLException e){
			throw new RuntimeException("查询用户信息时出错，错误原因：" + e.getMessage());
		}
	}
	
	public UserBean searchCardId(String CardId) {
		try{
			return dao.selectUserByCardId(CardId);
		} catch (SQLException e){
			throw new RuntimeException("查询用户信息时出错，错误原因：" + e.getMessage());
		}
	}
	
	public boolean existedCardId(String CardId) {
		return searchCardId(CardId) != null;
	}
}



