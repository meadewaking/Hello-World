package com.his.serviceimpl;

import java.util.List;

import com.his.dao.DoctorDao;
import com.his.daoimpl.DoctorDaoImpl;
import com.his.service.DoctorService;
import com.his.vo.Doctor;
import com.his.vo.Page;

public class DoctorServiceImpl implements DoctorService{
	DoctorDao dd=new DoctorDaoImpl();
	@Override
	public int addDoctor(Doctor doctor) {
		return dd.addDoctor(doctor);
	}

	@Override
	public int delDoctor(int id) {
		return dd.delDoctor(id);
	}

	@Override
	public int updateDoctor(Doctor doctor) {
		return dd.updateDoctor(doctor);
	}

	@Override
	public Doctor findDoctorById(int id) {
		return dd.findDoctorById(id);
	}

	@Override
	public Page findDoctorPage(int id, String name, int depid, int pageNo,
			int pageSize, int totalCount) {
		return dd.findDoctorPage(id, name, depid, pageNo, pageSize, totalCount);
	}

	@Override
	public int findDoctorCount(int id, String name, int depid) {
		return dd.findDoctorCount(id, name, depid);
	}

	@Override
	public List<Integer> findIdByName(String name) {
		return dd.findIdByName(name);
	}

	@Override
	public List<Integer> findAllId() {
		return dd.findAllId();
	}

	@Override
	public List<Doctor> findAllDoc() {
		return dd.findAllDoc();
	}

	public List<Doctor> findDocByDepId(int depId){
		return dd.findDocByDepId(depId);
	}
}
