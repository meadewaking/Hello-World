package cn.sycu.meade.business;

import java.sql.SQLException;
import java.util.List;

import cn.sycu.meade.dao.*;
import cn.sycu.meade.entity.*;

public class ModifyPasswordLogManager {
	ModifyPasswordLogDao dao = new ModifyPasswordLogDao();
	
	public boolean add(ModifyPasswordLogBean modifyPasswordLog) {
		clearData(modifyPasswordLog);
		checkData(modifyPasswordLog);
		
		try {
			return dao.insert(modifyPasswordLog) > 0;
		} catch (SQLException e) {
			throw new RuntimeException("添加密码修改日志出错，错误原因：" + e.getMessage());
		}
	}
	
	private void clearData(ModifyPasswordLogBean modifyPasswordLog) {
		modifyPasswordLog.setModifyIp(modifyPasswordLog.getModifyIp().trim());		
	}
	
	private void checkData(ModifyPasswordLogBean modifyPasswordLog) {
	}
	
	public boolean remove(int modifyPasswordLogId) {
		try {
			return dao.delete(modifyPasswordLogId) > 0;
		} catch (SQLException e) {
			throw new RuntimeException("删除密码修改日志出错，错误原因：" + e.getMessage());
		}
	}
	
	public boolean modify(ModifyPasswordLogBean modifyPasswordLog) {
		clearData(modifyPasswordLog);
		checkData(modifyPasswordLog);
		
		try {
			return dao.update(modifyPasswordLog) > 0;
		} catch (SQLException e) {
			throw new RuntimeException("添加密码修改日志出错，错误原因：" + e.getMessage());
		}
	}
	
	public List<ModifyPasswordLogBean> search() {
		try {
			return dao.select();
		} catch (SQLException e) {
			throw new RuntimeException("获取密码修改日志时出错,错误原因:" + e.getMessage());
		}
	}

	public ModifyPasswordLogBean search(int modifyPasswordLogId) {
		try {
			return dao.select(modifyPasswordLogId);
		} catch (SQLException e) {
			throw new RuntimeException("获取密码修改日志时出错,错误原因:" + e.getMessage());
		}
	}

	public List<ModifyPasswordLogBean> search(String sortString) {
		try {
			return dao.select(sortString);
		} catch (SQLException e) {
			throw new RuntimeException("获取密码修改日志时出错,错误原因:" + e.getMessage());
		}
	}
	
	public List<ModifyPasswordLogBean> search(ModifyPasswordLogSearcher searcher, String sortString) {
		try {
			return dao.select(searcher, sortString);
		} catch (SQLException e) {
			throw new RuntimeException("获取密码修改日志时出错,错误原因:" + e.getMessage());
		}
	}
}
