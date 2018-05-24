package com.his.service;

import java.util.List;

import com.his.vo.Page;
import com.his.vo.Resources;

public interface ResourcesService {
	/**
	 * ���Ȩ��
	 */
	public int addResources(Resources resources);
	/**
	 * ɾ��Ȩ��
	 */	
	public int delResources(int resId);
	/**
	 * �޸�Ȩ��
	 */	
	public int updateResources(Resources resources);
	/**
	 * ��ȡ����Resources��List����
	 */	
	public List<Resources> findAllResources();
	/**
	 * ͨ��id��ȡResources�������޸�
	 */	
	public Resources findResById(int resId);
	/**
	 * ��ȡҳ����(����ģ����ѯ����ʾ����)
	 */
	public Page findResPage(String resName,int pageNo,int pageSize ,int totalCount);
	/**
	 * ���ڻ�ȡģ����ѯ��ҳ��
	 */
	public int findResCount(String resName);
}
