package com.mstf.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.internal.matchers.Each;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import com.mstf.bean.User;

public class TestUser {
	//获取Spring资源文件
	ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
	SessionFactory sessionFactory = (SessionFactory) ac.getBean("sessionFactory");;
	Session session = sessionFactory.openSession();
	Transaction transaction = session.beginTransaction();

	@Test
	public void testInsert() throws Exception{
	
		User user = new User("Alin", "123456", 1, 0);
		session.save(user);
		transaction.commit();
		session.close();
	}
	
	@Test
	public void testDelete() throws Exception{
		User user = new User();
		user.setUser_id(3);
		session.delete(user);
		transaction.commit();
		session.close();
	}
	
	@Test
	public void testUpdate() throws Exception{
		User user = new User("rose", "666666", 1, 0);
		user.setUser_id(2);
		session.update(user);
		transaction.commit();
		session.close();
	}
	
	@Test
	public void testSelect1() throws Exception{
		//当查询全部字段时，可省略select *
		Query query = session.createQuery("from User");
		List<User> users = query.list();
		for (User user : users) {
			System.out.println(user.toString());
		}
	}
	
	@Test
	public void testSelect2() throws Exception{
		//查询部分字段
		Query query = session.createQuery("select new User(username,age,priority) from User");
		List<User> users = query.list();
		for (User users2 : users) {
			System.out.println(users2.toString());
		}
	}
}
