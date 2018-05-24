package cn.sycu.meade.business;

import java.sql.SQLException;
import java.util.List;

import cn.sycu.meade.dao.NoteDao;
import cn.sycu.meade.entity.*;

public class NoteManager {
	NoteDao dao = new NoteDao();
	public boolean add(NoteBean note){
		
		try{
			return dao.insert(note) > 0;
		} catch (SQLException e){
			if (e.getMessage().indexOf("FK_Departments_Notes_DepartmentId") >= 0)
				throw new RuntimeException("该部门已存在");
			else if (e.getMessage().indexOf("FK_Logins_Notes_PublisherId") >= 0)
				throw new RuntimeException("该登录不存在");
			else
				throw new RuntimeException("添加消息出错，错误原因：" + e.getMessage());
		}
	}
	
	public List<NoteBean> search(NoteSearcher searcher, String sortString) {
		try {
			return dao.select(searcher, sortString);
		} catch (SQLException e) {
			throw new RuntimeException("获取个人信息时出错,错误原因:" + e.getMessage());
		}
	}

	public boolean remove(int noteId) {
		try {
			return dao.delete(noteId) > 0;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("FK_Departments_Logins_DepartmentId") >= 0)
				throw new RuntimeException("个人信息正在被引用，不能删除。");
			else
				throw new RuntimeException("删除个人信息出错，错误原因：" + e.getMessage());
		}
	}
}

