package com.his.serviceimpl;

import java.util.List;

import com.his.dao.FeeManagerDao;
import com.his.daoimpl.FeeManagerDaoImpl;
import com.his.service.FeeManagerService;
import com.his.vo.FeeManager;
import com.his.vo.Page;

public class FeeManagerServiceImpl implements FeeManagerService {
	FeeManagerDao fmd=new FeeManagerDaoImpl();
	@Override
	public int addFeeManager(FeeManager fm) {
		return fmd.addFeeManager(fm);
	}
	@Override
	public List<Integer> findFeeIdByNo(String medicalNo) {
		return fmd.findFeeIdByNo(medicalNo);
	}
	@Override
	public int findFeeCount(List<String> medicalNoList) {
		return fmd.findFeeCount(medicalNoList);
	}
	@Override
	public Page findFeePage(List<String> medicalNoList, int pageNo, int pageSize) {
		return fmd.findFeePage(medicalNoList, pageNo, pageSize);
	}
	@Override
	public FeeManager findFMById(int id) {
		return fmd.findFMById(id);
	}
	
}
