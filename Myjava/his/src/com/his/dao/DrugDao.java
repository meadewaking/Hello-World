package com.his.dao;

import java.util.List;

import com.his.vo.Drug;
import com.his.vo.Page;

public interface DrugDao {
	/**
	 * ���
	 */
	public int addDrug(Drug drug);
	/**
	 * ɾ��
	 */
	public int delDrug(String DID);
	/**
	 * �޸�
	 */
	public int updateDrug(Drug drug);
	/**
	 * ����id��ѯ
	 */
	public Drug findDrugById(String DID);
	/**
	 * ��ȡҳ����(����ģ����ѯ����ʾ����)
	 */
	public Page findDrugPage(String drugName,int drugType,int pageNo,int pageSize ,int totalCount);
	/**
	 * ���ڻ�ȡģ����ѯ��ҳ��
	 */
	public int findDrugCount(String drugName,int drugType);
	/**
	 * ��ȡ����ҩƷ(���ڷ�ҩʱ��ʾ)
	 */
	public List<Drug> findAllDrug();
	/**
	 * ����ҩƷ����
	 */
	public int updateDrugInventory(String DID,int inventory);
}
