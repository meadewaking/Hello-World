package net.sycu.meade.service;

import java.sql.SQLException;
import java.util.ArrayList;

import net.sycu.meade.dao.TeacherStateDao;
import net.sycu.meade.entity.TeacherStateBean;

public class TeacherStateService {
	TeacherStateDao dao = new TeacherStateDao();
	
	public int add (TeacherStateBean TeacherState) {
		//��������
		TeacherState.setName(TeacherState.getName().trim());
		TeacherState.setDescription(TeacherState.getDescription().trim());
		if (TeacherState.getName().length() == 0) {
			throw new RuntimeException("��ʦ��������Ϊ��");
		}
		try {
			return dao.insert(TeacherState);
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("��ӽ�ʦ״̬��Ϣ����");
			if (e.getMessage().indexOf("Name_UNIQUE") >= 0) {
				throw new RuntimeException("ͬ����ʦ״̬�Ѵ���");
			}
		}
		return 0;
	}

	public int add(ArrayList<TeacherStateBean> TeacherStates) {
		for (int i = 0; i < TeacherStates.size(); i++) {
			TeacherStates.get(i).setName(TeacherStates.get(i).getName().trim());
			TeacherStates.get(i).setDescription(TeacherStates.get(i).getDescription().trim());
			if (TeacherStates.get(i).getName().length() == 0) {
				throw new RuntimeException("��ʦ��������Ϊ��");
			}
			TeacherStates.add(TeacherStates.get(i));
		}
		try {
			return dao.insert(TeacherStates);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("��ӽ�ʦ״̬��Ϣ����");
		}
	}

	public int remove(TeacherStateBean TeacherState) {
		try {
			return dao.delete(TeacherState);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("ɾ����ʦ״̬��Ϣ����");
		}
	}

	public int remove(int TeacherStateId) {
		try {
			return dao.delete(TeacherStateId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("ɾ����ʦ״̬��Ϣ����");
		}
	}

	public int remove(String name) {
		name.trim();
		try {
			return dao.delete(name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("ɾ����ʦ״̬��Ϣ����");
		}
	}

	public int remove(ArrayList<TeacherStateBean> TeacherStates) {
		try {
			return dao.delete(TeacherStates);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("ɾ����ʦ״̬��Ϣ����");
		}
	}

	public int remove(int[] TeacherStateIds) {
		try {
			return dao.delete(TeacherStateIds);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("ɾ����ʦ״̬��Ϣ����");
		}
	}

	public int updata(TeacherStateBean TeacherState) {
		
		TeacherState.setName(TeacherState.getName().trim());
		TeacherState.setDescription(TeacherState.getDescription().trim());
		try {
			return dao.updata(TeacherState);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("�޸Ľ�ʦ״̬��Ϣ����");
		}
	}

	public int updata(TeacherStateBean TeacherState, int TeacherStateId) {
		TeacherState.setName(TeacherState.getName().trim());
		TeacherState.setDescription(TeacherState.getDescription().trim());
		try {
			return dao.updata(TeacherState);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("�޸Ľ�ʦ״̬��Ϣ����");
		}
	}

	public TeacherStateBean search(int TeacherStateId) {
		try {
			return dao.select(TeacherStateId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("��ѯ��ʦ״̬��Ϣ����");
		}
	}

	public TeacherStateBean search(String name) {
	
			try {
				return dao.select(name);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("��ѯ��ʦ״̬��Ϣ����");
			}
	}

	public ArrayList<TeacherStateBean> display() {
		try {
			return dao.select();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("��ѯ��ʦ״̬��Ϣ����");
		}
	}
}
