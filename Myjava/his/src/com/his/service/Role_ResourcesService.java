package com.his.service;

import java.util.List;

import com.his.vo.Role_Resources;

public interface Role_ResourcesService {
	/**
	 * ���
	 */
	public int addRole_Resources(Role_Resources rr);
	/**
	 * ���ݽ�ɫ��idɾ��
	 */
	public int delRole_ResourcesByRoleId(int roleId);
	/**
	 * ���ݽ�ɫ��id��ȡȨ�ޱ�id
	 */
	public List<Integer> finResIdByRoleId(int roleId);
	/**
	 * ����Ȩ�ޱ�idɾ��
	 */
	public int delRole_ResourcesByResId(int resId);
}
