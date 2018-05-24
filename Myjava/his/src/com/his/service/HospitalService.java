package com.his.service;

import java.util.List;

import com.his.vo.Hospital;
import com.his.vo.Page;

public interface HospitalService {
	/**
	 * 添加
	 */
	public int addHos(Hospital hospital);
	/**
	 * 获取页对象(用于模糊查询和显示所有)
	 */
	public Page findHosPage(String medicalNo,List<Integer> docid,int depid,String stime,String ftime,int pageNo,int pageSize);
	/**
	 * 用于获取模糊查询总条数
	 */
	public int findHosCount(String medicalNo,List<Integer> docid,int depid,String stime,String ftime);
	/**
	 * 根据id获取对象
	 */
	public Hospital findHosById(String medicalNo);
	/**
	 * 更改
	 */
	public int updateHos(Hospital hos);
	/**
	 * 获取所有住院信息
	 */
	public List<Hospital> findAllList();
}
