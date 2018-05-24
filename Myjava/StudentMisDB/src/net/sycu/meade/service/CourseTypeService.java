package net.sycu.meade.service;

import java.sql.SQLException;
import java.util.ArrayList;

import net.sycu.meade.dao.CourseTypeDao;
import net.sycu.meade.entity.CourseTypeBean;

public class CourseTypeService {
	CourseTypeDao dao = new CourseTypeDao();
	
	public int add (CourseTypeBean CourseType) {
		//数据清理
		CourseType.setName(CourseType.getName().trim());
		CourseType.setDescription(CourseType.getDescription().trim());
		if (CourseType.getName().length() == 0) {
			throw new RuntimeException("学生姓名不能为空");
		}
		try {
			return dao.insert(CourseType);
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("添加学生状态信息出错");
			if (e.getMessage().indexOf("Name_UNIQUE") >= 0) {
				throw new RuntimeException("同名学生状态已存在");
			}
		}
		return 0;
	}

	public int add(ArrayList<CourseTypeBean> CourseTypes) {
		for (int i = 0; i < CourseTypes.size(); i++) {
			CourseTypes.get(i).setName(CourseTypes.get(i).getName().trim());
			CourseTypes.get(i).setDescription(CourseTypes.get(i).getDescription().trim());
			if (CourseTypes.get(i).getName().length() == 0) {
				throw new RuntimeException("学生姓名不能为空");
			}
			CourseTypes.add(CourseTypes.get(i));
		}
		try {
			return dao.insert(CourseTypes);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("添加学生状态信息出错");
		}
	}

	public int remove(CourseTypeBean CourseType) {
		try {
			return dao.delete(CourseType);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("删除学生状态信息出错");
		}
	}

	public int remove(int CourseTypeId) {
		try {
			return dao.delete(CourseTypeId);
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

	public int remove(ArrayList<CourseTypeBean> CourseTypes) {
		try {
			return dao.delete(CourseTypes);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("删除学生状态信息出错");
		}
	}

	public int remove(int[] CourseTypeIds) {
		try {
			return dao.delete(CourseTypeIds);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("删除学生状态信息出错");
		}
	}

	public int updata(CourseTypeBean CourseType) {
		
		CourseType.setName(CourseType.getName().trim());
		CourseType.setDescription(CourseType.getDescription().trim());
		try {
			return dao.updata(CourseType);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("修改学生状态信息出错");
		}
	}

	public int updata(CourseTypeBean CourseType, int CourseTypeId) {
		CourseType.setName(CourseType.getName().trim());
		CourseType.setDescription(CourseType.getDescription().trim());
		try {
			return dao.updata(CourseType);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("修改学生状态信息出错");
		}
	}

	public CourseTypeBean search(int CourseTypeId) {
		try {
			return dao.select(CourseTypeId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("查询学生状态信息出错");
		}
	}

	public CourseTypeBean search(String name) {
	
			try {
				return dao.select(name);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("查询学生状态信息出错");
			}
	}

	public ArrayList<CourseTypeBean> display() {
		try {
			return dao.select();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("查询学生状态信息出错");
		}
	}
}
