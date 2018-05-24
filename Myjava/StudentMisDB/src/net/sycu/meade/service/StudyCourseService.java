package net.sycu.meade.service;

import java.sql.SQLException;
import java.util.ArrayList;

import net.sycu.meade.dao.StudyCourseDao;
import net.sycu.meade.entity.StudyCourseBean;

public class StudyCourseService {
	StudyCourseDao dao = new StudyCourseDao();
	
	public int add (StudyCourseBean StudyCourse) {

		try {
			return dao.insert(StudyCourse);
		} catch (SQLException e) {
			// TODO: handle exception
			if (e.getMessage().indexOf("UQ_StudenId_CoureseId") >= 0) {
				System.out.println("同一名学生不能选同一课程两次");
			}
			System.out.println("添加选课信息出错");
		}
		return 0;
	}

	public int add(ArrayList<StudyCourseBean> StudyCourses) {
		for (int i = 0; i < StudyCourses.size(); i++) {
			StudyCourses.add(StudyCourses.get(i));
		}
		try {
			return dao.insert(StudyCourses);
		} catch (SQLException e) {
			if (e.getMessage().indexOf("UQ_StudenId_CoureseId") >= 0) {
				System.out.println("同一名学生不能选同一课程两次");
			}
			// TODO Auto-generated catch block
			throw new RuntimeException("添加选课信息出错");
		}
	}

	public int remove(int StudyCourseId) {
		try {
			return dao.delete(StudyCourseId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("删除选课信息出错");
		}
	}

	public int remove(ArrayList<StudyCourseBean> StudyCourses) {
		try {
			return dao.delete(StudyCourses);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("删除选课信息出错");
		}
	}

	public int remove(int[] StudyCourseIds) {
		try {
			return dao.delete(StudyCourseIds);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("删除选课信息出错");
		}
	}

	public int updata(StudyCourseBean StudyCourse) {
		
		try {
			return dao.updata(StudyCourse);
		} catch (SQLException e) {
			if (e.getMessage().indexOf("UQ_StudenId_CoureseId") >= 0) {
				System.out.println("同一名学生不能选同一课程两次");
			}
			// TODO Auto-generated catch block
			throw new RuntimeException("修改选课信息出错");
		}
	}

	public int updata(StudyCourseBean StudyCourse, int StudyCourseId) {

		try {
			return dao.updata(StudyCourse);
		} catch (SQLException e) {
			if (e.getMessage().indexOf("UQ_StudenId_CoureseId") >= 0) {
				System.out.println("同一名学生不能选同一课程两次");
			}
			// TODO Auto-generated catch block
			throw new RuntimeException("修改选课信息出错");
		}
	}

	public StudyCourseBean search(int StudyCourseId) {
		try {
			return dao.select(StudyCourseId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("查询选课信息出错");
		}
	}

	public ArrayList<StudyCourseBean> display() {
		try {
			return dao.select();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("查询选课信息出错");
		}
	}
}
