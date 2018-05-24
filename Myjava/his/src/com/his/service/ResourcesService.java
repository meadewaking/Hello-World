package com.his.service;

import java.util.List;

import com.his.vo.Page;
import com.his.vo.Resources;

public interface ResourcesService {
	/**
	 * 添加权限
	 */
	public int addResources(Resources resources);
	/**
	 * 删除权限
	 */	
	public int delResources(int resId);
	/**
	 * 修改权限
	 */	
	public int updateResources(Resources resources);
	/**
	 * 获取所有Resources的List集合
	 */	
	public List<Resources> findAllResources();
	/**
	 * 通过id获取Resources，用于修改
	 */	
	public Resources findResById(int resId);
	/**
	 * 获取页对象(用于模糊查询和显示所有)
	 */
	public Page findResPage(String resName,int pageNo,int pageSize ,int totalCount);
	/**
	 * 用于获取模糊查询总页数
	 */
	public int findResCount(String resName);
}
