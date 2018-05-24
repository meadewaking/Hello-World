package com.his.serviceimpl;

import java.util.List;

import com.his.dao.ResourcesDao;
import com.his.daoimpl.ResourcesDaoImpl;
import com.his.service.ResourcesService;
import com.his.vo.Page;
import com.his.vo.Resources;

public class ResourcesServiceImpl implements ResourcesService{
	ResourcesDao rd=new ResourcesDaoImpl();
	@Override
	public int addResources(Resources resources) {
		return rd.addResources(resources);
	}

	@Override
	public int delResources(int resId) {
		return rd.delResources(resId);
	}

	@Override
	public int updateResources(Resources resources) {
		return rd.updateResources(resources);
	}

	@Override
	public List<Resources> findAllResources() {
		return rd.findAllResources();
	}

	@Override
	public Resources findResById(int resId) {
		return rd.findResById(resId);
	}

	@Override
	public Page findResPage(String resName, int pageNo, int pageSize,
			int totalCount) {
		return rd.findResPage(resName, pageNo, pageSize, totalCount);
	}

	@Override
	public int findResCount(String resName) {
		return rd.findResCount(resName);
	}

}
