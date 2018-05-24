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
				System.out.println("ͬһ��ѧ������ѡͬһ�γ�����");
			}
			System.out.println("���ѡ����Ϣ����");
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
				System.out.println("ͬһ��ѧ������ѡͬһ�γ�����");
			}
			// TODO Auto-generated catch block
			throw new RuntimeException("���ѡ����Ϣ����");
		}
	}

	public int remove(int StudyCourseId) {
		try {
			return dao.delete(StudyCourseId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("ɾ��ѡ����Ϣ����");
		}
	}

	public int remove(ArrayList<StudyCourseBean> StudyCourses) {
		try {
			return dao.delete(StudyCourses);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("ɾ��ѡ����Ϣ����");
		}
	}

	public int remove(int[] StudyCourseIds) {
		try {
			return dao.delete(StudyCourseIds);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("ɾ��ѡ����Ϣ����");
		}
	}

	public int updata(StudyCourseBean StudyCourse) {
		
		try {
			return dao.updata(StudyCourse);
		} catch (SQLException e) {
			if (e.getMessage().indexOf("UQ_StudenId_CoureseId") >= 0) {
				System.out.println("ͬһ��ѧ������ѡͬһ�γ�����");
			}
			// TODO Auto-generated catch block
			throw new RuntimeException("�޸�ѡ����Ϣ����");
		}
	}

	public int updata(StudyCourseBean StudyCourse, int StudyCourseId) {

		try {
			return dao.updata(StudyCourse);
		} catch (SQLException e) {
			if (e.getMessage().indexOf("UQ_StudenId_CoureseId") >= 0) {
				System.out.println("ͬһ��ѧ������ѡͬһ�γ�����");
			}
			// TODO Auto-generated catch block
			throw new RuntimeException("�޸�ѡ����Ϣ����");
		}
	}

	public StudyCourseBean search(int StudyCourseId) {
		try {
			return dao.select(StudyCourseId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("��ѯѡ����Ϣ����");
		}
	}

	public ArrayList<StudyCourseBean> display() {
		try {
			return dao.select();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("��ѯѡ����Ϣ����");
		}
	}
}
