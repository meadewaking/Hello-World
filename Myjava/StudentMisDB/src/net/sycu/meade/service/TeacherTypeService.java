package net.sycu.meade.service;

import java.sql.SQLException;
import java.util.ArrayList;

import net.sycu.meade.dao.TeacherTypeDao;
import net.sycu.meade.entity.TeacherTypeBean;

public class TeacherTypeService {
	TeacherTypeDao dao = new TeacherTypeDao();
	
	public int add (TeacherTypeBean TeacherType) {
		//数据清理
		TeacherType.setName(TeacherType.getName().trim());
		TeacherType.setDescription(TeacherType.getDescription().trim());
		if (TeacherType.getName().length() == 0) {
			throw new RuntimeException("学生姓名不能为空");
		}
		try {
			return dao.insert(TeacherType);
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("添加学生状态信息出错");
			if (e.getMessage().indexOf("Name_UNIQUE") >= 0) {
				throw new RuntimeException("同名学生状态已存在");
			}
		}
		return 0;
	}

	public int add(ArrayList<TeacherTypeBean> TeacherTypes) {
		for (int i = 0; i < TeacherTypes.size(); i++) {
			TeacherTypes.get(i).setName(TeacherTypes.get(i).getName().trim());
			TeacherTypes.get(i).setDescription(TeacherTypes.get(i).getDescription().trim());
			if (TeacherTypes.get(i).getName().length() == 0) {
				throw new RuntimeException("学生姓名不能为空");
			}
			TeacherTypes.add(TeacherTypes.get(i));
		}
		try {
			return dao.insert(TeacherTypes);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("添加学生状态信息出错");
		}
	}

	public int remove(TeacherTypeBean TeacherType) {
		try {
			return dao.delete(TeacherType);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("删除学生状态信息出错");
		}
	}

	public int remove(int TeacherTypeId) {
		try {
			return dao.delete(TeacherTypeId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("删除学生状态信息出错");
		}
	}

	public int remove(String name) {
		name.trim();
		try {
			return dao.delete(name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("删除学生状态信息出错");
		}
	}

	public int remove(ArrayList<TeacherTypeBean> TeacherTypes) {
		try {
			return dao.delete(TeacherTypes);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("删除学生状态信息出错");
		}
	}

	public int remove(int[] TeacherTypeIds) {
		try {
			return dao.delete(TeacherTypeIds);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("删除学生状态信息出错");
		}
	}

	public int updata(TeacherTypeBean TeacherType) {
		
		TeacherType.setName(TeacherType.getName().trim());
		TeacherType.setDescription(TeacherType.getDescription().trim());
		try {
			return dao.updata(TeacherType);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("修改学生状态信息出错");
		}
	}

	public int updata(TeacherTypeBean TeacherType, int TeacherTypeId) {
		TeacherType.setName(TeacherType.getName().trim());
		TeacherType.setDescription(TeacherType.getDescription().trim());
		try {
			return dao.updata(TeacherType);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("修改学生状态信息出错");
		}
	}

	public TeacherTypeBean search(int TeacherTypeId) {
		try {
			return dao.select(TeacherTypeId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("查询学生状态信息出错");
		}
	}

	public TeacherTypeBean search(String name) {
	
			try {
				return dao.select(name);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("查询学生状态信息出错");
			}
	}

	public ArrayList<TeacherTypeBean> display() {
		try {
			return dao.select();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("查询学生状态信息出错");
		}
	}
}
