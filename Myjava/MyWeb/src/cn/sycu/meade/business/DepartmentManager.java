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
				throw new RuntimeException("同名部门名已存在，不能添加");
			else
				throw new RuntimeException("添加部门信息出错，错误原因：" + e.getMessage());
		}

	}

	private void clearData(DepartmentBean department) {
		department.setDepartmentName(department.getDepartmentName().trim());
		department.setAddress(department.getAddress().trim());
		department.setDescription(department.getDescription().trim());
	}

	private void checkData(DepartmentBean department) {
		if (department.getDepartmentName() == null || department.getDepartmentName().length() == 0)
			throw new RuntimeException("部门名称不能为空。");
	}

	public List<DepartmentBean> search() {
		try {
			return dao.select();

		} catch (SQLException e) {
			throw new RuntimeException("获取部门信息时出错，错误原因：" + e.getMessage());
		}

	}

	public boolean remove(int departmentId) {
		try {
			return dao.delete(departmentId) > 0;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("FK_Departments_Logins_DepartmentId") >= 0)
				throw new RuntimeException("仍然有登录信息隶属于该部门（部门信息正在被引用），不能删除");
			else
				throw new RuntimeException("删除部门信息出错，错误原因：" + e.getMessage());
		}

	}

	public boolean modify(DepartmentBean department) {

		clearData(department);
		checkData(department);

		try {
			return dao.update(department) > 0;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("DepartmentName_UNIQUE") >= 0)
				throw new RuntimeException("同名部门名已存在，不能添加");
			else
				throw new RuntimeException("修改部门信息出错，错误原因：" + e.getMessage());
		}

	}

	public DepartmentBean search(int departmentId) {
		try {
			return dao.select(departmentId);

		} catch (SQLException e) {
			throw new RuntimeException("获取部门信息时出错，错误原因：" + e.getMessage());
		}
	}

	public List<DepartmentBean> search(String sortString) {
		try {
			return dao.select(sortString);

		} catch (SQLException e) {
			throw new RuntimeException("获取部门信息时出错，错误原因：" + e.getMessage());
		}
	}

	public List<DepartmentBean> search(DepartmentSearcher searcher, String sortString) {
		try {
			return dao.select(searcher, sortString);

		} catch (SQLException e) {
			throw new RuntimeException("获取部门信息时出错，错误原因：" + e.getMessage());
		}
	}
}
