package com.his.dao;

import java.util.List;

import com.his.vo.Disp;
import com.his.vo.Page;

public interface DispDao {
	/**
	 * ��ӷ�ҩ
	 */
	public int addDisp(Disp disp);
	/**
	 * ��ȡ������
	 */
	public int findDispCount(String medicalNo);
	/**
	 * ��ȡҳ����(����ģ����ѯ����ʾ����)
	 */
	public Page findDispPage(String medicalNo,int pageNo,int pageSize);
	/**
	 * ���ݲ����Ż�ȡ���󼯺�
	 */
	public List<Disp> findDispListByNo(String medicalNo);
	/**
	 * ���ݲ����ź�ҩƷ��Ż�ȡ����
	 */
	public Disp findDispByNoDID(String medicalNo,String drugId);
	/**
	 * ����
	 */
	public int updateDisp(Disp disp);
	
}
