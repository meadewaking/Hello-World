package com.his.dao;

import java.util.List;

import com.his.vo.Page;
import com.his.vo.Role;

public interface RoleDao {
	/**
	 * ���
	 */
	public int addRole(Role role);
	/**
	 * ɾ��
	 */
	public int delRole(int roleId);
	/**
	 * �޸�
	 */
	public int updateRole(Role role);
	/**
	 * ����id��ѯ
	 */
	public Role findRoleById(int roleId);
	/**
	 * ��ȡ���ж���
	 */
	public List<Role> findAllRole();
	/**
	 * ��ȡҳ����(����ģ����ѯ����ʾ����)
	 */
	public Page findRolePage(String roleName,int pageNo,int pageSize ,int totalCount);
	/**
	 * ���ڻ�ȡģ����ѯ��ҳ��
	 */
	public int findRoleCount(String roleName);
	/**
	 * ���ݽ�ɫ���ͽ�ɫ״̬�õ�id(������ӽ�ɫȨ���м��)
	 */
	public int findId(String roleName,int roleStatus);
}
