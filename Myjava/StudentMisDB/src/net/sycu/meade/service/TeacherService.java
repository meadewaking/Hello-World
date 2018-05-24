package net.sycu.meade.service;

import java.sql.SQLException;
import java.util.ArrayList;

import net.sycu.meade.dao.TeacherDao;
import net.sycu.meade.entity.TeacherBean;

public class TeacherService {
	TeacherDao dao = new TeacherDao();
	
	public int add (TeacherBean Teacher) {
		//数据清理
		Teacher.setName(Teacher.getName().trim());
		Teacher.setNumber(Teacher.getNumber().trim());
		Teacher.setGender(Teacher.getGender().trim());
		Teacher.setPhoneNumber(Teacher.getPhoneNumber().trim());
		Teacher.setAddress(Teacher.getAddress().trim());
		Teacher.setRemark(Teacher.getRemark().trim());
		if (Teacher.getName().length() == 0) {
			throw new RuntimeException("教师姓名不能为空");
		}
		try {
			return dao.insert(Teacher);
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("添加教师信息出错");
			if (e.getMessage().indexOf("Name_UNIQUE") >= 0) {
				throw new RuntimeException("同名教师已存在");
			}
		}
		return 0;
	}

	public int add(ArrayList<TeacherBean> Teachers) {
		for (int i = 0; i < Teachers.size(); i++) {
			Teachers.get(i).setName(Teachers.get(i).getName().trim());
			Teachers.get(i).setNumber(Teachers.get(i).getNumber().trim());
			Teachers.get(i).setGender(Teachers.get(i).getGender().trim());
			Teachers.get(i).setPhoneNumber(Teachers.get(i).getPhoneNumber().trim());
			Teachers.get(i).setAddress(Teachers.get(i).getAddress().trim());
			Teachers.get(i).setRemark(Teachers.get(i).getRemark().trim());
			if (Teachers.get(i).getName().length() == 0) {
				throw new RuntimeException("教师姓名不能为空");
			}
			Teachers.add(Teachers.get(i));
		}
		try {
			return dao.insert(Teachers);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("添加教师信息出错");
		}
	}

	public int remove(TeacherBean Teacher) {
		try {
			return dao.delete(Teacher);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("删除教师信息出错");
		}
	}

	public int remove(int TeacherId) {
		try {
			return dao.delete(TeacherId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("删除教师信息出错");
		}
	}

	public int remove(String name) {
		name.trim();
		try {
			return dao.delete(name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("删除教师信息出错");
		}
	}

	public int remove(ArrayList<TeacherBean> Teachers) {
		try {
			return dao.delete(Teachers);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("删除教师信息出错");
		}
	}

	public int remove(int[] TeacherIds) {
		try {
			return dao.delete(TeacherIds);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("删除教师信息出错");
		}
	}

	public int updata(TeacherBean Teacher) {
		
		Teacher.setName(Teacher.getName().trim());
		Teacher.setNumber(Teacher.getNumber().trim());
		Teacher.setGender(Teacher.getGender().trim());
		Teacher.setPhoneNumber(Teacher.getPhoneNumber().trim());
		Teacher.setAddress(Teacher.getAddress().trim());
		Teacher.setRemark(Teacher.getRemark().trim());
		try {
			return dao.updata(Teacher);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("修改教师信息出错");
		}
	}

	public int updata(TeacherBean Teacher, int TeacherId) {
		Teacher.setName(Teacher.getName().trim());
		Teacher.setNumber(Teacher.getNumber().trim());
		Teacher.setGender(Teacher.getGender().trim());
		Teacher.setPhoneNumber(Teacher.getPhoneNumber().trim());
		Teacher.setAddress(Teacher.getAddress().trim());
		Teacher.setRemark(Teacher.getRemark().trim());
		try {
			return dao.updata(Teacher);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("修改教师信息出错");
		}
	}

	public TeacherBean search(int TeacherId) {
		try {
			return dao.select(TeacherId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("查询教师信息出错");
		}
	}

	public TeacherBean search(String name) {
	
			try {
				return dao.select(name);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("查询教师信息出错");
			}
	}

	public ArrayList<TeacherBean> display() {
		try {
			return dao.select();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("查询教师信息出错");
		}
	}
}
