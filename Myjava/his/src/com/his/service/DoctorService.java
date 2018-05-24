package com.his.service;

import java.util.List;

import com.his.vo.Doctor;
import com.his.vo.Page;

public interface DoctorService {
	/**
	 * 添加
	 */
	public int addDoctor(Doctor doctor);
	/**
	 * 删除
	 */
	public int delDoctor(int id);
	/**
	 * 修改
	 */
	public int updateDoctor(Doctor doctor);
	/**
	 * 根据id查询
	 */
	public Doctor findDoctorById(int id);
	/**
	 * 获取页对象(用于模糊查询和显示所有)
	 */
	public Page findDoctorPage(int id,String name,int depid,int pageNo,int pageSize ,int totalCount);
	/**
	 * 用于获取模糊查询总页数
	 */
	public int findDoctorCount(int id,String name,int depid);
	/**
	 * 根据名字获取id集合用于查询挂号表
	 */
	public List<Integer> findIdByName(String name);
	/**
	 * 获取所有医生id的集合
	 */
	public List<Integer> findAllId();
	/**
	 * 获取所有医生集合，用于挂号首页显示医生名字
	 */
	public List<Doctor> findAllDoc();
	/**
	 * 根据科室获取医生
	 */
	public List<Doctor> findDocByDepId(int depId);
}
