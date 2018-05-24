package com.his.service;

import com.his.vo.Page;
import com.his.vo.User;


public interface UserService {
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
	public User findUserById(int id);
	/**
	 * ��ȡ�û�(���ڵ�¼)
	 */
	public User findUserByUserName(String username);
	/**
	 * ��ȡ�û���(����ģ����ѯ)
	 */
	public Page findPageByUserName(String username,int pageNo, int pageSize, int totalCount);
	/**
	 * ���ڻ�ȡģ����ѯ��ҳ��
	 */
	public int findUserNameCount(String username);
}
