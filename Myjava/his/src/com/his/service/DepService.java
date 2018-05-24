package com.his.service;

import java.util.List;

import com.his.vo.Dep;

public interface DepService {
	/**
	 * 获取科室表集合
	 */
	public List<Dep> findDepList();
	/**
	 * 根据id获取科室
	 */
	public Dep findDepById(int id);
}
