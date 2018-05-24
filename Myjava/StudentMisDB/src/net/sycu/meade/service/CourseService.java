package net.sycu.meade.service;

import java.sql.SQLException;
import java.util.ArrayList;

import net.sycu.meade.dao.CourseDao;
import net.sycu.meade.entity.CourseBean;

public class CourseService {
	CourseDao dao = new CourseDao();
	
	public int add (CourseBean Course) {
		//数据清理
		Course.setName(Course.getName().trim());
		Course.setNumber(Course.getNumber().trim());
		if (Course.getName().length() == 0) {
			throw new RuntimeException("课程名称不能为空");
		}
		try {
			return dao.insert(Course);
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("添加课程信息出错");
			if (e.getMessage().indexOf("Name_UNIQUE") >= 0) {
				throw new RuntimeException("同名课程已存在");
			}
		}
		return 0;
	}

	public int add(ArrayList<CourseBean> Courses) {
		for (int i = 0; i < Courses.size(); i++) {
			Courses.get(i).setName(Courses.get(i).getName().trim());
			Courses.get(i).setNumber(Courses.get(i).getNumber().trim());
			if (Courses.get(i).getName().length() == 0) {
				throw new RuntimeException("课程名称不能为空");
			}
			Courses.add(Courses.get(i));
		}
		try {
			return dao.insert(Courses);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("添加课程信息出错");
		}
	}

	public int remove(CourseBean Course) {
		try {
			return dao.delete(Course);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("删除课程信息出错");
		}
	}

	public int remove(int CourseId) {
		try {
			return dao.delete(CourseId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("删除课程信息出错");
		}
	}

	public int remove(String name) {
		name.trim();
		try {
			return dao.delete(name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("删除课程信息出错");
		}
	}

	public int remove(ArrayList<CourseBean> Courses) {
		try {
			return dao.delete(Courses);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("删除课程信息出错");
		}
	}

	public int remove(int[] CourseIds) {
		try {
			return dao.delete(CourseIds);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("删除课程信息出错");
		}
	}

	public int updata(CourseBean Course) {
		
		Course.setName(Course.getName().trim());
		Course.setNumber(Course.getNumber().trim());
		try {
			return dao.updata(Course);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("修改课程信息出错");
		}
	}

	public int updata(CourseBean Course, int CourseId) {
		Course.setName(Course.getName().trim());
		Course.setNumber(Course.getNumber().trim());
		try {
			return dao.updata(Course);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("修改课程信息出错");
		}
	}

	public CourseBean search(int CourseId) {
		try {
			return dao.select(CourseId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("查询课程信息出错");
		}
	}

	public CourseBean search(String name) {
	
			try {
				return dao.select(name);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("查询课程信息出错");
			}
	}

	public ArrayList<CourseBean> display() {
		try {
			return dao.select();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("查询课程信息出错");
		}
	}
}
