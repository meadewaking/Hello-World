package com.his.service;

import java.util.List;

import com.his.vo.FeeProject;

public interface FeeProjectService {
	/**
	 * ���
	 */
	public int addFeeProject(FeeProject fp);
	/**
	 * �����շ���Ŀ����ȡ�շ���Ŀ��id
	 */
	public int findIdByFeeName(String feeName);
	/**
	 * ����id��ȡ�շ���Ŀ
	 */
	public FeeProject findFeeProById(int id);
	/**
	 * ��ȡ�����շ���Ŀ
	 */
	public List<FeeProject> findAllFeePro();
}
