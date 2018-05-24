package com.his.serviceimpl;

import java.util.List;

import com.his.dao.DispDao;
import com.his.daoimpl.DispDaoImpl;
import com.his.service.DispService;
import com.his.vo.Disp;
import com.his.vo.Page;

public class DispServiceImpl implements DispService{
	DispDao dd=new DispDaoImpl();
	@Override
	public int addDisp(Disp disp) {
		return dd.addDisp(disp);
	}
	@Override
	public int findDispCount(String medicalNo) {
		return dd.findDispCount(medicalNo);
	}
	@Override
	public Page findDispPage(String medicalNo, int pageNo, int pageSize) {
		return dd.findDispPage(medicalNo, pageNo, pageSize);
	}
	@Override
	public List<Disp> findDispListByNo(String medicalNo) {
		return dd.findDispListByNo(medicalNo);
	}
	@Override
	public Disp findDispByNoDID(String medicalNo, String drugId) {
		return dd.findDispByNoDID(medicalNo, drugId);
	}
	@Override
	public int updateDisp(Disp disp) {
		return dd.updateDisp(disp);
	}
	
}
