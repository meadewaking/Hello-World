package com.his.dao;

import java.util.List;

import com.his.vo.Page;
import com.his.vo.User;


public interface UserDao {
	/**
	 * ���
	 */
	public int addUser(User user);
	/**
	 * ɾ��
	 */
	public int deleteUser(int userid);
	/**
	 * �޸�
	 */
	public int updateUser(User user);
	/**
	 * ����id��ѯ
	 */
	public User findUserById(int userid);
	/**
	 * ��ȡ�û�(���ڵ�¼)
	 */
	public User findUserByUserName(String username);
	/**
	 * ��ȡҳ����(����ģ����ѯ����ʾ����)
	 */
	public Page findPageByUserName(String username,int pageNo, int pageSize, int totalCount);
	/**
	 * ���ڻ�ȡģ����ѯ��ҳ��
	 */
	public int findUserNameCount(String username);
}
