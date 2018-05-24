package com.his.serviceimpl;

import java.util.List;

import com.his.dao.FeeProjectDao;
import com.his.daoimpl.FeeProjectDaoImpl;
import com.his.service.FeeProjectService;
import com.his.vo.FeeProject;

public class FeeProjectServiceImpl implements FeeProjectService{
	FeeProjectDao fpd=new FeeProjectDaoImpl();
	
	@Override
	public int addFeeProject(FeeProject fp) {
		return fpd.addFeeProject(fp);
	}
	
	@Override
	public int findIdByFeeName(String feeName) {
		return fpd.findIdByFeeName(feeName);
	}

	@Override
	public FeeProject findFeeProById(int id) {
		return fpd.findFeeProById(id);
	}

	@Override
	public List<FeeProject> findAllFeePro() {
		return fpd.findAllFeePro();
	}
	
}
