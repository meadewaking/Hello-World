package com.his.dao;

import java.util.List;

import com.his.vo.Disp;
import com.his.vo.Page;

public interface DispDao {
	/**
	 * 添加发药
	 */
	public int addDisp(Disp disp);
	/**
	 * 获取总条数
	 */
	public int findDispCount(String medicalNo);
	/**
	 * 获取页对象(用于模糊查询和显示所有)
	 */
	public Page findDispPage(String medicalNo,int pageNo,int pageSize);
	/**
	 * 根据病历号获取对象集合
	 */
	public List<Disp> findDispListByNo(String medicalNo);
	/**
	 * 根据病历号和药品编号获取对象
	 */
	public Disp findDispByNoDID(String medicalNo,String drugId);
	/**
	 * 更新
	 */
	public int updateDisp(Disp disp);
	
}
