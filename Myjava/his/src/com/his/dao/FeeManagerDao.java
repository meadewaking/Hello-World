package com.his.dao;

import java.util.List;

import com.his.vo.FeeManager;
import com.his.vo.Page;

public interface FeeManagerDao {
	/**
	 * 添加
	 */
	public int addFeeManager(FeeManager fm);
	/**
	 * 根据病历号获取收费表id集合
	 */
	public List<Integer> findFeeIdByNo(String medicalNo);
	/**
	 * 获取总条数
	 */
	public int findFeeCount(List<String> medicalNoList);
	/**
	 * 获取收费项目
	 */
	public Page findFeePage(List<String> medicalNoList,int pageNo,int pageSize);
	/**
	 * 根据id获取收费项目
	 */
	public FeeManager findFMById(int id);
}
