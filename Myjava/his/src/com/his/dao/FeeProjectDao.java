package com.his.dao;

import java.util.List;

import com.his.vo.FeeProject;

public interface FeeProjectDao {
	/**
	 * 添加
	 */
	public int addFeeProject(FeeProject fp);
	/**
	 * 根据收费项目来获取收费项目表id
	 */
	public int findIdByFeeName(String feeName);
	/**
	 * 根据id获取收费项目
	 */
	public FeeProject findFeeProById(int id);
	/**
	 * 获取所有收费项目
	 */
	public List<FeeProject> findAllFeePro();
}
