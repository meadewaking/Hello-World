package com.his.serviceimpl;

import java.util.List;

import com.his.dao.DrugDao;
import com.his.daoimpl.DrugDaoImpl;
import com.his.service.DrugService;
import com.his.vo.Drug;
import com.his.vo.Page;

public class DrugServiceImpl implements DrugService{
	DrugDao dd=new DrugDaoImpl();
	public int addDrug(Drug drug) {
		return dd.addDrug(drug);
	}

	public int delDrug(String DID) {
		return dd.delDrug(DID);
	}

	public int updateDrug(Drug drug) {
		return dd.updateDrug(drug);
	}

	public Drug findDrugById(String DID) {
		return dd.findDrugById(DID);
	}

	public Page findDrugPage(String drugName, int drugType, int pageNo,
			int pageSize, int totalCount) {
		return dd.findDrugPage(drugName, drugType, pageNo, pageSize, totalCount);
	}

	public int findDrugCount(String drugName, int drugType) {
		
		return dd.findDrugCount(drugName, drugType);
	}

	@Override
	public List<Drug> findAllDrug() {
		return dd.findAllDrug();
	}

	@Override
	public int updateDrugInventory(String DID, int inventory) {
		return dd.updateDrugInventory(DID, inventory);
	}

}
