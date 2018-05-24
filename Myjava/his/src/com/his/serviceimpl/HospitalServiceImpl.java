package com.his.serviceimpl;

import java.util.List;

import com.his.dao.HospitalDao;
import com.his.daoimpl.HospitalDaoImpl;
import com.his.service.HospitalService;
import com.his.vo.Hospital;
import com.his.vo.Page;

public class HospitalServiceImpl implements HospitalService{
	HospitalDao hd=new HospitalDaoImpl();

	@Override
	public int addHos(Hospital hospital) {
		return hd.addHos(hospital);
	}

	@Override
	public Page findHosPage(String medicalNo, List<Integer> docid, int depid,
			String stime, String ftime, int pageNo, int pageSize) {
		return hd.findHosPage(medicalNo, docid, depid, stime, ftime, pageNo, pageSize);
	}

	@Override
	public int findHosCount(String medicalNo, List<Integer> docid, int depid,
			String stime, String ftime) {
		return hd.findHosCount(medicalNo, docid, depid, stime, ftime);
	}

	@Override
	public Hospital findHosById(String medicalNo) {
		return hd.findHosById(medicalNo);
	}

	@Override
	public int updateHos(Hospital hos) {
		return hd.updateHos(hos);
	}

	@Override
	public List<Hospital> findAllList() {
		return hd.findAllList();
	}
	
	
}
