package com.his.service;

import java.util.List;

import com.his.vo.FeeManager;
import com.his.vo.Page;

public interface FeeManagerService {
	/**
	 * ���
	 */
	public int addFeeManager(FeeManager fm);
	/**
	 * ���ݲ����Ż�ȡ�շѱ�id
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
