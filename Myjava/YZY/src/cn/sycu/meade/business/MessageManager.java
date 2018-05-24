package cn.sycu.meade.business;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.sycu.meade.common.MD5Util;
import cn.sycu.meade.dao.MessageDao;
import cn.sycu.meade.entity.*;

public class MessageManager {
	MessageDao dao = new MessageDao();
	public boolean add(MessageBean message){
		clearData(message);
		checkData(message);
		
		try{
			return dao.insert(message) > 0;
		} catch (SQLException e){
			if (e.getMessage().indexOf("FK_Departments_Messages_DepartmentId") >= 0)
				throw new RuntimeException("该部门已存在");
			else if (e.getMessage().indexOf("FK_Logins_Messages_PublisherId") >= 0)
				throw new RuntimeException("该登录不存在");
			else
				throw new RuntimeException("添加消息出错，错误原因：" + e.getMessage());
		}
	}
	
	private void clearData(MessageBean message) {
		// 数据清理
		message.setTitle(message.getTitle().trim());// 去掉左右空格
		message.setContent(message.getContent().trim());// 去掉左右空格
		message.setPublishIP(message.getPublishIP().trim());// 去掉左右空格
	}
	
	private void checkData(MessageBean message) {
		// 数据验证
		// 登录名至少5个字符
		if (message.getTitle().length() < 5)
			throw new RuntimeException("标题不能少于5个字符");//抛出异常
		// 密码名至少5个字符
		if (message.getContent().length() < 6)
			throw new RuntimeException("内容不能少于20个字符");//抛出异常
		// Email格式正确
		String regEx = "";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = null;
		
		// IP地址格式正确
		regEx = "((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))";
		pattern = Pattern.compile(regEx);
		
		matcher = pattern.matcher(message.getPublishIP());
		if (!matcher.matches())
			throw new RuntimeException("发布IP地址格式不正确");//抛出异常
	}
	
	public boolean modify(MessageBean message){
		clearData(message);
		checkData(message);
		
		try{
			if (dao.update(message) > 0)
				return true;
			else
				return false;
		} catch (SQLException e){
			if (e.getMessage().indexOf("FK_Departments_Messages_DepartmentId") >= 0)
				throw new RuntimeException("该部门已存在");
			else if (e.getMessage().indexOf("FK_Logins_Messages_PublisherId") >= 0)
				throw new RuntimeException("该登录不存在");
			else
				throw new RuntimeException("添加消息出错，错误原因：" + e.getMessage());
		}
	}

	public List<MessageBean> search(MessageSearcher searcher, String sortString) {
		try {
			return dao.select(searcher, sortString);
		} catch (SQLException e) {
			throw new RuntimeException("获取部门信息时出错,错误原因:" + e.getMessage());
		}
	}

	public boolean remove(int messageId) {
		try {
			return dao.delete(messageId) > 0;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("FK_Departments_Logins_DepartmentId") >= 0)
				throw new RuntimeException("仍然有登录信息隶属于该部门（部门信息正在被引用），不能删除。");
			else
				throw new RuntimeException("删除部门信息出错，错误原因：" + e.getMessage());
		}
	}
}

