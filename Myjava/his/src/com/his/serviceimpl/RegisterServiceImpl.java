package com.his.serviceimpl;

import java.util.List;

import com.his.dao.RegisterDao;
import com.his.daoimpl.RegisterDaoImpl;
import com.his.service.RegisterService;
import com.his.vo.Page;
import com.his.vo.Register;

public class RegisterServiceImpl implements RegisterService{
	RegisterDao rd=new RegisterDaoImpl();

	@Override
	public int addRegister(Register reg) {
		return rd.addRegister(reg);
	}

	@Override
	public int delRegister(String medicalNo) {
		return rd.delRegister(medicalNo);
	}

	@Override
	public int updateRegister(Register register) {
		return rd.updateRegister(register);
	}

	@Override
	public Register findRegisterByNo(String medicalNo) {
		return rd.findRegisterByNo(medicalNo);
	}

	@Override
	public Page findRegPage(String medicalNo, List<Integer> docid, int depid,
			String stime, String ftime, int pageNo, int pageSize, int totalCount) {
		return rd.findRegPage(medicalNo, docid, depid, stime, ftime, pageNo, pageSize, totalCount);
	}

	@Override
	public int findRegCount(String medicalNo, List<Integer> docid, int depid,
			String stime, String ftime) {
		return rd.findRegCount(medicalNo, docid, depid, stime, ftime);
	}

	@Override
	public List<String> findNoByName(String name,String medicalNo) {
		return rd.findNoByName(name,medicalNo);
	}

	@Override
	public List<Register> findAllReg() {
		return rd.findAllReg();
	}
	

}
