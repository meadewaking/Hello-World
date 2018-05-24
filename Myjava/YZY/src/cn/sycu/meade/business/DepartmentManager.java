package cn.sycu.meade.business;

import java.sql.SQLException;
import java.util.List;

import cn.sycu.meade.dao.DepartmentDao;

import cn.sycu.meade.entity.DepartmentBean;
import cn.sycu.meade.entity.DepartmentSearcher;

public class DepartmentManager {
	DepartmentDao dao = new DepartmentDao();

	public boolean add(DepartmentBean department) {
		clearData(department);
		checkData(department);

		try {
			return dao.insert(department) > 0;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("DepartmentName_UNIQUE") >= 0)
				throw new RuntimeException("ͬ���������Ѵ��ڣ��������");
			else
				throw new RuntimeException("��Ӳ�����Ϣ��������ԭ��" + e.getMessage());
		}

	}

	private void clearData(DepartmentBean department) {
		department.setDepartmentName(department.getDepartmentName().trim());
		department.setAddress(department.getAddress().trim());
		department.setDescription(department.getDescription().trim());
	}

	private void checkData(DepartmentBean department) {
		if (department.getDepartmentName() == null || department.getDepartmentName().length() == 0)
			throw new RuntimeException("�������Ʋ���Ϊ�ա�");
	}

	public List<DepartmentBean> search() {
		try {
			return dao.select();

		} catch (SQLException e) {
			throw new RuntimeException("��ȡ������Ϣʱ��������ԭ��" + e.getMessage());
		}

	}

	public boolean remove(int departmentId) {
		try {
			return dao.delete(departmentId) > 0;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("FK_Departments_Logins_DepartmentId") >= 0)
				throw new RuntimeException("��Ȼ�е�¼��Ϣ�����ڸò��ţ�������Ϣ���ڱ����ã�������ɾ��");
			else
				throw new RuntimeException("ɾ��������Ϣ��������ԭ��" + e.getMessage());
		}

	}

	public boolean modify(DepartmentBean department) {

		clearData(department);
		checkData(department);

		try {
			return dao.update(department) > 0;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("DepartmentName_UNIQUE") >= 0)
				throw new RuntimeException("ͬ���������Ѵ��ڣ��������");
			else
				throw new RuntimeException("�޸Ĳ�����Ϣ��������ԭ��" + e.getMessage());
		}

	}

	public DepartmentBean search(int departmentId) {
		try {
			return dao.select(departmentId);

		} catch (SQLException e) {
			throw new RuntimeException("��ȡ������Ϣʱ��������ԭ��" + e.getMessage());
		}
	}

	public List<DepartmentBean> search(String sortString) {
		try {
			return dao.select(sortString);

		} catch (SQLException e) {
			throw new RuntimeException("��ȡ������Ϣʱ��������ԭ��" + e.getMessage());
		}
	}

	public List<DepartmentBean> search(DepartmentSearcher searcher, String sortString) {
		try {
			return dao.select(searcher, sortString);

		} catch (SQLException e) {
			throw new RuntimeException("��ȡ������Ϣʱ��������ԭ��" + e.getMessage());
		}
	}
}
