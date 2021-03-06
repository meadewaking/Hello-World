package net.sycu.meade.service;

import java.sql.SQLException;
import java.util.ArrayList;

import net.sycu.meade.dao.TeachCourseDao;
import net.sycu.meade.entity.TeachCourseBean;

public class TeachCourseService {
	TeachCourseDao dao = new TeachCourseDao();
	
	public int add (TeachCourseBean TeachCourse) {

		try {
			return dao.insert(TeachCourse);
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("添加授课信息出错");
		}
		return 0;
	}

	public int add(ArrayList<TeachCourseBean> TeachCourses) {
		for (int i = 0; i < TeachCourses.size(); i++) {
			TeachCourses.add(TeachCourses.get(i));
		}
		try {
			return dao.insert(TeachCourses);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("添加授课信息出错");
		}
	}

	public int remove(int TeachCourseId) {
		try {
			return dao.delete(TeachCourseId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("删除授课信息出错");
		}
	}

	public int remove(ArrayList<TeachCourseBean> TeachCourses) {
		try {
			return dao.delete(TeachCourses);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("删除授课信息出错");
		}
	}

	public int remove(int[] TeachCourseIds) {
		try {
			return dao.delete(TeachCourseIds);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("删除授课信息出错");
		}
	}

	public int updata(TeachCourseBean TeachCourse) {
		
		try {
			return dao.updata(TeachCourse);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("修改授课信息出错");
		}
	}

	public int updata(TeachCourseBean TeachCourse, int TeachCourseId) {

		try {
			return dao.updata(TeachCourse);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("修改授课信息出错");
		}
	}

	public TeachCourseBean search(int TeachCourseId) {
		try {
			return dao.select(TeachCourseId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("查询授课信息出错");
		}
	}

	public ArrayList<TeachCourseBean> display() {
		try {
			return dao.select();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("查询授课信息出错");
		}
	}
}
