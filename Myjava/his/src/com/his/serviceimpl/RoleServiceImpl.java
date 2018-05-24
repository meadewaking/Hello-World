package com.his.serviceimpl;

import java.util.List;

import com.his.dao.RoleDao;
import com.his.daoimpl.RoleDaoImpl;
import com.his.service.RoleService;
import com.his.vo.Page;
import com.his.vo.Role;

public class RoleServiceImpl implements RoleService{
	RoleDao rd=new RoleDaoImpl();
	public int addRole(Role role) {
		return rd.addRole(role);
	}

	public int delRole(int roleId) {
		return rd.delRole(roleId);
	}

	public int updateRole(Role role) {
		return rd.updateRole(role);
	}
	
	public Role findRoleById(int roleId) {
		return rd.findRoleById(roleId);
	}
	
	public List<Role> findAllRole() {
		return rd.findAllRole();
	}

	public Page findRolePage(String roleName, int pageNo, int pageSize,
			int totalCount) {
		return rd.findRolePage(roleName, pageNo, pageSize, totalCount);
	}

	public int findRoleCount(String roleName) {
		return rd.findRoleCount(roleName);
	}

	public int findId(String roleName, int roleStatus) {
		return rd.findId(roleName, roleStatus);
	}
	
}
