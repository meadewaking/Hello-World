package com.his.serviceimpl;

import java.util.List;

import com.his.dao.Role_ResourcesDao;
import com.his.daoimpl.Role_ResourcesDaoImpl;
import com.his.service.Role_ResourcesService;
import com.his.vo.Role_Resources;

public class Role_ResourcesServiceImpl implements Role_ResourcesService{
	Role_ResourcesDao rrd=new Role_ResourcesDaoImpl();
	
	public int addRole_Resources(Role_Resources rr) {
		return rrd.addRole_Resources(rr);
	}

	public int delRole_ResourcesByRoleId(int roleId) {
		return rrd.delRole_ResourcesByRoleId(roleId);
	}

	public List<Integer> finResIdByRoleId(int roleId) {
		return rrd.finResIdByRoleId(roleId);
	}

	@Override
	public int delRole_ResourcesByResId(int resId) {
		return rrd.delRole_ResourcesByResId(resId);
	}
	
}
