package com.mstf.service;

import java.util.List;

import com.mstf.bean.Info;

public interface InfoService {
	public void save(Info info);
	public void update(Info info);
	public void delete(int id);
	
	public List<Info>getAll();
	public Info getById(int id);

}
