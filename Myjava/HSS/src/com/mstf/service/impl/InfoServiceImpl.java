package com.mstf.service.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import com.mstf.bean.Info;
import com.mstf.service.InfoService;

public class InfoServiceImpl implements InfoService{
	private SessionFactory sessionFactory;
	

	@Override
	public void save(Info info) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(info);
		
	}

	@Override
	public void update(Info info) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(info);
		
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Object obj=sessionFactory.getCurrentSession().get(Info.class, id);
		sessionFactory.getCurrentSession().delete(obj);
		
		
	}

	@Override
	public List<Info> getAll() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")//-------------���?��Ļ���
		List<Info>infolist=sessionFactory.getCurrentSession().createQuery(//
				"FROM Info").list();
		return infolist;
	}

	@Override
	public Info getById(int id) {
		// TODO Auto-generated method stub
		return (Info) sessionFactory.getCurrentSession().get(Info.class, id);
		
	}
//-------------Ϊprivate SessionFactory sessionFactory;�ṩgetter��setter������
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	

}
