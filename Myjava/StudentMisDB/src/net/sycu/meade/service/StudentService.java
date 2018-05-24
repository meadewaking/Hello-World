package net.sycu.meade.service;

import java.sql.SQLException;
import java.util.ArrayList;

import net.sycu.meade.dao.StudentDao;
import net.sycu.meade.entity.StudentBean;

public class StudentService {
	StudentDao dao = new StudentDao();
	
	public int add (StudentBean Student) {
		//数据清理
		Student.setName(Student.getName().trim());
		Student.setNumber(Student.getNumber().trim());
		Student.setGender(Student.getGender().trim());
		Student.setPhoneNumber(Student.getPhoneNumber().trim());
		Student.setAddress(Student.getAddress().trim());
		Student.setRemark(Student.getRemark().trim());
		if (Student.getName().length() == 0) {
			throw new RuntimeException("学生姓名不能为空");
		}
		try {
			return dao.insert(Student);
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("添加学生信息出错");
			if (e.getMessage().indexOf("Name_UNIQUE") >= 0) {
				throw new RuntimeException("同名学生已存在");
			}
		}
		return 0;
	}

	public int add(ArrayList<StudentBean> Students) {
		for (int i = 0; i < Students.size(); i++) {
			Students.get(i).setName(Students.get(i).getName().trim());
			Students.get(i).setNumber(Students.get(i).getNumber().trim());
			Students.get(i).setGender(Students.get(i).getGender().trim());
			Students.get(i).setPhoneNumber(Students.get(i).getPhoneNumber().trim());
			Students.get(i).setAddress(Students.get(i).getAddress().trim());
			Students.get(i).setRemark(Students.get(i).getRemark().trim());
			if (Students.get(i).getName().length() == 0) {
				throw new RuntimeException("学生姓名不能为空");
			}
			Students.add(Students.get(i));
		}
		try {
			return dao.insert(Students);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("添加学生信息出错");
		}
	}

	public int remove(StudentBean Student) {
		try {
			return dao.delete(Student);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("删除学生信息出错");
		}
	}

	public int remove(int StudentId) {
		try {
			return dao.delete(StudentId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("删除学生信息出错");
		}
	}

	public int remove(String name) {
		name.trim();
		try {
			return dao.delete(name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("删除学生信息出错");
		}
	}

	public int remove(ArrayList<StudentBean> Students) {
		try {
			return dao.delete(Students);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("删除学生信息出错");
		}
	}

	public int remove(int[] StudentIds) {
		try {
			return dao.delete(StudentIds);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("删除学生信息出错");
		}
	}

	public int updata(StudentBean Student) {
		
		Student.setName(Student.getName().trim());
		Student.setNumber(Student.getNumber().trim());
		Student.setGender(Student.getGender().trim());
		Student.setPhoneNumber(Student.getPhoneNumber().trim());
		Student.setAddress(Student.getAddress().trim());
		Student.setRemark(Student.getRemark().trim());
		try {
			return dao.updata(Student);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("修改学生信息出错");
		}
	}

	public int updata(StudentBean Student, int StudentId) {
		Student.setName(Student.getName().trim());
		Student.setNumber(Student.getNumber().trim());
		Student.setGender(Student.getGender().trim());
		Student.setPhoneNumber(Student.getPhoneNumber().trim());
		Student.setAddress(Student.getAddress().trim());
		Student.setRemark(Student.getRemark().trim());
		try {
			return dao.updata(Student);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("修改学生信息出错");
		}
	}

	public StudentBean search(int StudentId) {
		try {
			return dao.select(StudentId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("查询学生信息出错");
		}
	}

	public StudentBean search(String name) {
	
			try {
				return dao.select(name);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("查询学生信息出错");
			}
	}

	public ArrayList<StudentBean> display() {
		try {
			return dao.select();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("查询学生信息出错");
		}
	}
}
