package com.his.dao;

import java.util.List;

import com.his.vo.Drug;
import com.his.vo.Page;

public interface DrugDao {
	/**
	 * 添加
	 */
	public int addDrug(Drug drug);
	/**
	 * 删除
	 */
	public int delDrug(String DID);
	/**
	 * 修改
	 */
	public int updateDrug(Drug drug);
	/**
	 * 根据id查询
	 */
	public Drug findDrugById(String DID);
	/**
	 * 获取页对象(用于模糊查询和显示所有)
	 */
	public Page findDrugPage(String drugName,int drugType,int pageNo,int pageSize ,int totalCount);
	/**
	 * 用于获取模糊查询总页数
	 */
	public int findDrugCount(String drugName,int drugType);
	/**
	 * 获取所有药品(用于发药时显示)
	 */
	public List<Drug> findAllDrug();
	/**
	 * 更新药品余量
	 */
	public int updateDrugInventory(String DID,int inventory);
}
