package net.sycu.meade.service;

import java.sql.SQLException;
import java.util.ArrayList;

import net.sycu.meade.dao.ClazzDao;
import net.sycu.meade.entity.ClazzBean;

public class ClazzService {
	ClazzDao dao = new ClazzDao();
	
	public int add (ClazzBean Clazz) {
		//��������
		Clazz.setName(Clazz.getName().trim());	//ȥ�����ҿո�
		Clazz.setDescription(Clazz.getDescription().trim());
		if (Clazz.getName().length() == 0) {	//������֤
			throw new RuntimeException("�༶���Ʋ���Ϊ��");
		}
		try {
			return dao.insert(Clazz);
		} catch (SQLException e) {		//��׽dao���׳����쳣
			// TODO: handle exception
			System.out.println("��Ӱ༶��Ϣ����");
			if (e.getMessage().indexOf("Name_UNIQUE") >= 0) {
				throw new RuntimeException("ͬ���༶�Ѵ���");
			}
		}
		return 0;
	}

	public int add(ArrayList<ClazzBean> Clazzs) {
		for (int i = 0; i < Clazzs.size(); i++) {
			Clazzs.get(i).setName(Clazzs.get(i).getName().trim());		//��������
			Clazzs.get(i).setDescription(Clazzs.get(i).getDescription().trim());
			if (Clazzs.get(i).getName().length() == 0) {		//������֤
				throw new RuntimeException("�༶���Ʋ���Ϊ��");
			}
			Clazzs.add(Clazzs.get(i));
		}
		try {
			return dao.insert(Clazzs);		//ʵ����daoִ��
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("��Ӱ༶��Ϣ����");
		}
	}

	public int remove(ClazzBean Clazz) {//ɾ��һ����Ϣ
		try {
			return dao.delete(Clazz);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("ɾ���༶��Ϣ����");
		}
	}

	public int remove(int ClazzId) {//��������ɾ��һ����Ϣ
		try {
			return dao.delete(ClazzId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("ɾ���༶��Ϣ����");
		}
	}

	public int remove(String name) { // ����Ψһ��ɾ��
		name.trim();
		try {
			return dao.delete(name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("ɾ���༶��Ϣ����");
		}
	}

	public int remove(ArrayList<ClazzBean> Clazzs) {	//����ɾ��
		try {
			return dao.delete(Clazzs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("ɾ���༶��Ϣ����");
		}
	}

	public int remove(int[] ClazzIds) {		//������������ɾ��
		try {
			return dao.delete(ClazzIds);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("ɾ���༶��Ϣ����");
		}
	}

	public int updata(ClazzBean Clazz) {//�޸�
		
		Clazz.setName(Clazz.getName().trim());
		Clazz.setDescription(Clazz.getDescription().trim());
		try {
			return dao.updata(Clazz);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("�޸İ༶��Ϣ����");
		}
	}

	public int updata(ClazzBean Clazz, int ClazzId) {//�����޸�����
		Clazz.setName(Clazz.getName().trim());
		Clazz.setDescription(Clazz.getDescription().trim());
		try {
			return dao.updata(Clazz);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("�޸İ༶��Ϣ����");
		}
	}

	public ClazzBean search(int ClazzId) {	//����������ѯ
		try {
			return dao.select(ClazzId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("��ѯ�༶��Ϣ����");
		}
	}

	public ClazzBean search(String name) {	//����Ψһ����ѯ
	
			try {
				return dao.select(name);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("��ѯ�༶��Ϣ����");
			}
	}

	public ArrayList<ClazzBean> display() {		//��ѯȫ��
		try {
			return dao.select();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("��ѯ�༶��Ϣ����");
		}
	}
}
