package com.his.service;

import java.util.List;

import com.his.vo.Dep;

public interface DepService {
	/**
	 * ��ȡ���ұ���
	 */
	public List<Dep> findDepList();
	/**
	 * ����id��ȡ����
	 */
	public Dep findDepById(int id);
}
