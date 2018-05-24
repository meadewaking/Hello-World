package net.sycu.meade.service;

import java.sql.SQLException;
import java.util.ArrayList;

import net.sycu.meade.dao.ClazzDao;
import net.sycu.meade.entity.ClazzBean;

public class ClazzService {
	ClazzDao dao = new ClazzDao();
	
	public int add (ClazzBean Clazz) {
		//数据清理
		Clazz.setName(Clazz.getName().trim());	//去掉左右空格
		Clazz.setDescription(Clazz.getDescription().trim());
		if (Clazz.getName().length() == 0) {	//输入验证
			throw new RuntimeException("班级名称不能为空");
		}
		try {
			return dao.insert(Clazz);
		} catch (SQLException e) {		//捕捉dao中抛出的异常
			// TODO: handle exception
			System.out.println("添加班级信息出错");
			if (e.getMessage().indexOf("Name_UNIQUE") >= 0) {
				throw new RuntimeException("同名班级已存在");
			}
		}
		return 0;
	}

	public int add(ArrayList<ClazzBean> Clazzs) {
		for (int i = 0; i < Clazzs.size(); i++) {
			Clazzs.get(i).setName(Clazzs.get(i).getName().trim());		//数据清理
			Clazzs.get(i).setDescription(Clazzs.get(i).getDescription().trim());
			if (Clazzs.get(i).getName().length() == 0) {		//输入验证
				throw new RuntimeException("班级名称不能为空");
			}
			Clazzs.add(Clazzs.get(i));
		}
		try {
			return dao.insert(Clazzs);		//实际由dao执行
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("添加班级信息出错");
		}
	}

	public int remove(ClazzBean Clazz) {//删除一条信息
		try {
			return dao.delete(Clazz);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("删除班级信息出错");
		}
	}

	public int remove(int ClazzId) {//根据主键删除一条信息
		try {
			return dao.delete(ClazzId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("删除班级信息出错");
		}
	}

	public int remove(String name) { // 根据唯一键删除
		name.trim();
		try {
			return dao.delete(name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("删除班级信息出错");
		}
	}

	public int remove(ArrayList<ClazzBean> Clazzs) {	//多条删除
		try {
			return dao.delete(Clazzs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("删除班级信息出错");
		}
	}

	public int remove(int[] ClazzIds) {		//根据主键多条删除
		try {
			return dao.delete(ClazzIds);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("删除班级信息出错");
		}
	}

	public int updata(ClazzBean Clazz) {//修改
		
		Clazz.setName(Clazz.getName().trim());
		Clazz.setDescription(Clazz.getDescription().trim());
		try {
			return dao.updata(Clazz);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("修改班级信息出错");
		}
	}

	public int updata(ClazzBean Clazz, int ClazzId) {//可以修改主键
		Clazz.setName(Clazz.getName().trim());
		Clazz.setDescription(Clazz.getDescription().trim());
		try {
			return dao.updata(Clazz);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("修改班级信息出错");
		}
	}

	public ClazzBean search(int ClazzId) {	//根据主键查询
		try {
			return dao.select(ClazzId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("查询班级信息出错");
		}
	}

	public ClazzBean search(String name) {	//根据唯一键查询
	
			try {
				return dao.select(name);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("查询班级信息出错");
			}
	}

	public ArrayList<ClazzBean> display() {		//查询全部
		try {
			return dao.select();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("查询班级信息出错");
		}
	}
}
