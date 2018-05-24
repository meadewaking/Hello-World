package com.his.serviceimpl;

import java.util.List;

import com.his.dao.DepDao;
import com.his.daoimpl.DepDaoImpl;
import com.his.service.DepService;
import com.his.vo.Dep;

public class DepServiceImpl implements DepService{
	DepDao dd=new DepDaoImpl();
	@Override
	public List<Dep> findDepList() {
		return dd.findDepList();
	}
	@Override
	public Dep findDepById(int id) {
		return dd.findDepById(id);
	}
	
}
