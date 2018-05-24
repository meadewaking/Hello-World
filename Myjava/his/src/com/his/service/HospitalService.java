package com.his.service;

import java.util.List;

import com.his.vo.Hospital;
import com.his.vo.Page;

public interface HospitalService {
	/**
	 * ���
	 */
	public int addHos(Hospital hospital);
	/**
	 * ��ȡҳ����(����ģ����ѯ����ʾ����)
	 */
	public Page findHosPage(String medicalNo,List<Integer> docid,int depid,String stime,String ftime,int pageNo,int pageSize);
	/**
	 * ���ڻ�ȡģ����ѯ������
	 */
	public int findHosCount(String medicalNo,List<Integer> docid,int depid,String stime,String ftime);
	/**
	 * ����id��ȡ����
	 */
	public Hospital findHosById(String medicalNo);
	/**
	 * ����
	 */
	public int updateHos(Hospital hos);
	/**
	 * ��ȡ����סԺ��Ϣ
	 */
	public List<Hospital> findAllList();
}
