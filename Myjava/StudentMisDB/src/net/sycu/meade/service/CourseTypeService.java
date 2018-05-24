package net.sycu.meade.service;

import java.sql.SQLException;
import java.util.ArrayList;

import net.sycu.meade.dao.CourseTypeDao;
import net.sycu.meade.entity.CourseTypeBean;

public class CourseTypeService {
	CourseTypeDao dao = new CourseTypeDao();
	
	public int add (CourseTypeBean CourseType) {
		//��������
		CourseType.setName(CourseType.getName().trim());
		CourseType.setDescription(CourseType.getDescription().trim());
		if (CourseType.getName().length() == 0) {
			throw new RuntimeException("ѧ����������Ϊ��");
		}
		try {
			return dao.insert(CourseType);
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("���ѧ��״̬��Ϣ����");
			if (e.getMessage().indexOf("Name_UNIQUE") >= 0) {
				throw new RuntimeException("ͬ��ѧ��״̬�Ѵ���");
			}
		}
		return 0;
	}

	public int add(ArrayList<CourseTypeBean> CourseTypes) {
		for (int i = 0; i < CourseTypes.size(); i++) {
			CourseTypes.get(i).setName(CourseTypes.get(i).getName().trim());
			CourseTypes.get(i).setDescription(CourseTypes.get(i).getDescription().trim());
			if (CourseTypes.get(i).getName().length() == 0) {
				throw new RuntimeException("ѧ����������Ϊ��");
			}
			CourseTypes.add(CourseTypes.get(i));
		}
		try {
			return dao.insert(CourseTypes);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("���ѧ��״̬��Ϣ����");
		}
	}

	public int remove(CourseTypeBean CourseType) {
		try {
			return dao.delete(CourseType);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("ɾ��ѧ��״̬��Ϣ����");
		}
	}

	public int remove(int CourseTypeId) {
		try {
			return dao.delete(CourseTypeId);
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

	public int remove(ArrayList<CourseTypeBean> CourseTypes) {
		try {
			return dao.delete(CourseTypes);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("ɾ��ѧ��״̬��Ϣ����");
		}
	}

	public int remove(int[] CourseTypeIds) {
		try {
			return dao.delete(CourseTypeIds);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("ɾ��ѧ��״̬��Ϣ����");
		}
	}

	public int updata(CourseTypeBean CourseType) {
		
		CourseType.setName(CourseType.getName().trim());
		CourseType.setDescription(CourseType.getDescription().trim());
		try {
			return dao.updata(CourseType);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("�޸�ѧ��״̬��Ϣ����");
		}
	}

	public int updata(CourseTypeBean CourseType, int CourseTypeId) {
		CourseType.setName(CourseType.getName().trim());
		CourseType.setDescription(CourseType.getDescription().trim());
		try {
			return dao.updata(CourseType);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("�޸�ѧ��״̬��Ϣ����");
		}
	}

	public CourseTypeBean search(int CourseTypeId) {
		try {
			return dao.select(CourseTypeId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("��ѯѧ��״̬��Ϣ����");
		}
	}

	public CourseTypeBean search(String name) {
	
			try {
				return dao.select(name);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("��ѯѧ��״̬��Ϣ����");
			}
	}

	public ArrayList<CourseTypeBean> display() {
		try {
			return dao.select();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("��ѯѧ��״̬��Ϣ����");
		}
	}
}
