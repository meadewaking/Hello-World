package com.his.dao;

import java.util.List;

import com.his.vo.Role_Resources;

public interface Role_ResourcesDao {
	/**
	 * 添加
	 */
	public int addRole_Resources(Role_Resources rr);
	/**
	 * 根据角色表id删除
	 */
	public int delRole_ResourcesByRoleId(int roleId);
	/**
	 * 根据角色表id获取权限表id
	 */
	public List<Integer> finResIdByRoleId(int roleId);
	/**
	 * 根据权限表id删除
	 */
	public int delRole_ResourcesByResId(int resId);
}
