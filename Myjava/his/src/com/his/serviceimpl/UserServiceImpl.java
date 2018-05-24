package com.his.serviceimpl;
import java.util.ArrayList;
import java.util.List;

import com.his.dao.UserDao;
import com.his.daoimpl.UserDaoImpl;
import com.his.service.UserService;
import com.his.vo.Page;
import com.his.vo.User;


public class UserServiceImpl implements UserService{
	UserDao ud=new UserDaoImpl();
	public int addUser(User user) {
		return ud.addUser(user);
	}

	public int deleteUser(int userid) {
		return ud.deleteUser(userid);
	}

	public int updateUser(User user) {
		return ud.updateUser(user);
	}

	public User findUserById(int id) {
		return ud.findUserById(id);
	}
	
	public User findUserByUserName(String username){
		return ud.findUserByUserName(username);
	}
	
	public Page findPageByUserName(String username,int pageNo, int pageSize, int totalCount){
		return ud.findPageByUserName(username,pageNo,pageSize,totalCount);
	}

	public int findUserNameCount(String username){
		return ud.findUserNameCount(username);
	}
	
}
