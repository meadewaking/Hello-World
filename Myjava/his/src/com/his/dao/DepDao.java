package com.his.dao;

import java.util.List;

import com.his.vo.Dep;

public interface DepDao {
	/**
	 * ��ȡ���ұ���
	 */
	public List<Dep> findDepList();
	/**
	 * ����id��ȡ����
	 */
	public Dep findDepById(int id);
}
