package com.his.service;

import java.util.List;

import com.his.vo.Doctor;
import com.his.vo.Page;

public interface DoctorService {
	/**
	 * ���
	 */
	public int addDoctor(Doctor doctor);
	/**
	 * ɾ��
	 */
	public int delDoctor(int id);
	/**
	 * �޸�
	 */
	public int updateDoctor(Doctor doctor);
	/**
	 * ����id��ѯ
	 */
	public Doctor findDoctorById(int id);
	/**
	 * ��ȡҳ����(����ģ����ѯ����ʾ����)
	 */
	public Page findDoctorPage(int id,String name,int depid,int pageNo,int pageSize ,int totalCount);
	/**
	 * ���ڻ�ȡģ����ѯ��ҳ��
	 */
	public int findDoctorCount(int id,String name,int depid);
	/**
	 * �������ֻ�ȡid�������ڲ�ѯ�Һű�
	 */
	public List<Integer> findIdByName(String name);
	/**
	 * ��ȡ����ҽ��id�ļ���
	 */
	public List<Integer> findAllId();
	/**
	 * ��ȡ����ҽ�����ϣ����ڹҺ���ҳ��ʾҽ������
	 */
	public List<Doctor> findAllDoc();
	/**
	 * ���ݿ��һ�ȡҽ��
	 */
	public List<Doctor> findDocByDepId(int depId);
}
