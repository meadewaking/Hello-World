package com.his.service;

import java.util.List;

import com.his.vo.Page;
import com.his.vo.Role;

public interface RoleService {
	/**
	 * 添加
	 */
	public int addRole(Role role);
	/**
	 * 删除
	 */
	public int delRole(int roleId);
	/**
	 * 修改
	 */
	public int updateRole(Role role);
	/**
	 * 根据id查询
	 */
	public Role findRoleById(int roleId);
	/**
	 * 获取所有对象
	 */
	public List<Role> findAllRole();
	/**
	 * 获取页对象(用于模糊查询和显示所有)
	 */
	public Page findRolePage(String roleName,int pageNo,int pageSize ,int totalCount);
	/**
	 * 用于获取模糊查询总页数
	 */
	public int findRoleCount(String roleName);
	/**
	 * 根据角色名和角色状态得到id(用于添加角色权限中间表)
	 */
	public int findId(String roleName,int roleStatus);
}
