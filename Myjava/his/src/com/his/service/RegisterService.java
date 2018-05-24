package com.his.service;

import java.util.List;

import com.his.vo.Page;
import com.his.vo.Register;

public interface RegisterService {
	/**
	 * ���
	 */
	public int addRegister(Register reg);
	/**
	 * ɾ��
	 */
	public int delRegister(String medicalNo);
	/**
	 * �޸�
	 */
	public int updateRegister(Register register);
	/**
	 * ����id��ѯ
	 */
	public Register findRegisterByNo(String medicalNo);
	/**
	 * ��ȡҳ����(����ģ����ѯ����ʾ����)
	 */
	public Page findRegPage(String medicalNo,List<Integer> docid,int depid,String stime,String ftime,int pageNo,int pageSize ,int totalCount);
	/**
	 * ���ڻ�ȡģ����ѯ��ҳ��
	 */
	public int findRegCount(String medicalNo,List<Integer> docid,int depid,String stime,String ftime);
	/**
	 * ͨ��������ȡ������(�����շѹ���Ĳ�ѯ)
	 */
	public List<String> findNoByName(String name,String medicalNo);
	/**
	 * ��ȡ���йҺ���
	 */
	public List<Register> findAllReg();
}
