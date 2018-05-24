package com.his.dao;

import java.util.List;

import com.his.vo.FeeManager;
import com.his.vo.Page;

public interface FeeManagerDao {
	/**
	 * ���
	 */
	public int addFeeManager(FeeManager fm);
	/**
	 * ���ݲ����Ż�ȡ�շѱ�id����
	 */
	public List<Integer> findFeeIdByNo(String medicalNo);
	/**
	 * ��ȡ������
	 */
	public int findFeeCount(List<String> medicalNoList);
	/**
	 * ��ȡ�շ���Ŀ
	 */
	public Page findFeePage(List<String> medicalNoList,int pageNo,int pageSize);
	/**
	 * ����id��ȡ�շ���Ŀ
	 */
	public FeeManager findFMById(int id);
}
