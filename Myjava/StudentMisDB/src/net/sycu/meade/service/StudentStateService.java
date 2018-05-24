package net.sycu.meade.service;


import java.sql.*;
import java.util.ArrayList;

import net.sycu.meade.dao.StudentStateDao;
import net.sycu.meade.entity.StudentStateBean;

public class StudentStateService {

	StudentStateDao dao = new StudentStateDao();
	
	public int add (StudentStateBean StudentState) {
		//��������
		StudentState.setName(StudentState.getName().trim());
		StudentState.setDescription(StudentState.getDescription().trim());
		if (StudentState.getName().length() == 0) {
			throw new RuntimeException("ѧ����������Ϊ��");
		}
		try {
			return dao.insert(StudentState);
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("���ѧ��״̬��Ϣ����");
			if (e.getMessage().indexOf("Name_UNIQUE") >= 0) {
				throw new RuntimeException("ͬ��ѧ��״̬�Ѵ���");
			}
		}
		return 0;
	}

	public int add(ArrayList<StudentStateBean> StudentStates) {
		for (int i = 0; i < StudentStates.size(); i++) {
			StudentStates.get(i).setName(StudentStates.get(i).getName().trim());
			StudentStates.get(i).setDescription(StudentStates.get(i).getDescription().trim());
			if (StudentStates.get(i).getName().length() == 0) {
				throw new RuntimeException("ѧ����������Ϊ��");
			}
			StudentStates.add(StudentStates.get(i));
		}
		try {
			return dao.insert(StudentStates);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("���ѧ��״̬��Ϣ����");
		}
	}

	public int remove(StudentStateBean StudentState) {
		try {
			return dao.delete(StudentState);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("ɾ��ѧ��״̬��Ϣ����");
		}
	}

	public int remove(int StudentStateId) {
		try {
			return dao.delete(StudentStateId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("ɾ��ѧ��״̬��Ϣ����");
		}
	}

	public int remove(String name) {
		name.trim();
		try {
			return dao.delete(name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("ɾ��ѧ��״̬��Ϣ����");
		}
	}

	public int remove(ArrayList<StudentStateBean> StudentStates) {
		try {
			return dao.delete(StudentStates);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("ɾ��ѧ��״̬��Ϣ����");
		}
	}

	public int remove(int[] StudentStateIds) {
		try {
			return dao.delete(StudentStateIds);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("ɾ��ѧ��״̬��Ϣ����");
		}
	}

	public int updata(StudentStateBean StudentState) {
		
		StudentState.setName(StudentState.getName().trim());
		StudentState.setDescription(StudentState.getDescription().trim());
		try {
			return dao.updata(StudentState);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("�޸�ѧ��״̬��Ϣ����");
		}
	}

	public int updata(StudentStateBean StudentState, int StudentStateId) {
		StudentState.setName(StudentState.getName().trim());
		StudentState.setDescription(StudentState.getDescription().trim());
		try {
			return dao.updata(StudentState);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("�޸�ѧ��״̬��Ϣ����");
		}
	}

	public StudentStateBean search(int StudentStateId) {
		try {
			return dao.select(StudentStateId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("��ѯѧ��״̬��Ϣ����");
		}
	}

	public StudentStateBean search(String name) {
	
			try {
				return dao.select(name);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("��ѯѧ��״̬��Ϣ����");
			}
	}

	public ArrayList<StudentStateBean> display() {
		try {
			return dao.select();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("��ѯѧ��״̬��Ϣ����");
		}
	}
}
