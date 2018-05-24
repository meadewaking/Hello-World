package com.his.service;

import com.his.vo.Page;
import com.his.vo.User;


public interface UserService {
	/**
	 * 添加
	 */
	public int addUser(User user);
	/**
	 * 删除
	 */
	public int deleteUser(int userid);
	/**
	 * 修改
	 */
	public int updateUser(User user);
	/**
	 * 根据id查询
	 */
	public User findUserById(int id);
	/**
	 * 获取用户(用于登录)
	 */
	public User findUserByUserName(String username);
	/**
	 * 获取用户组(用于模糊查询)
	 */
	public Page findPageByUserName(String username,int pageNo, int pageSize, int totalCount);
	/**
	 * 用于获取模糊查询总页数
	 */
	public int findUserNameCount(String username);
}
