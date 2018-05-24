package com.his.service;

import java.util.List;

import com.his.vo.Page;
import com.his.vo.Register;

public interface RegisterService {
	/**
	 * 添加
	 */
	public int addRegister(Register reg);
	/**
	 * 删除
	 */
	public int delRegister(String medicalNo);
	/**
	 * 修改
	 */
	public int updateRegister(Register register);
	/**
	 * 根据id查询
	 */
	public Register findRegisterByNo(String medicalNo);
	/**
	 * 获取页对象(用于模糊查询和显示所有)
	 */
	public Page findRegPage(String medicalNo,List<Integer> docid,int depid,String stime,String ftime,int pageNo,int pageSize ,int totalCount);
	/**
	 * 用于获取模糊查询总页数
	 */
	public int findRegCount(String medicalNo,List<Integer> docid,int depid,String stime,String ftime);
	/**
	 * 通过姓名获取病历号(用于收费管理的查询)
	 */
	public List<String> findNoByName(String name,String medicalNo);
	/**
	 * 获取所有挂号者
	 */
	public List<Register> findAllReg();
}
