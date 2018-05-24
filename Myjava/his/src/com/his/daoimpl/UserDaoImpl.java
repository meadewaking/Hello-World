package com.his.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.his.dao.UserDao;
import com.his.db.DBConnection;
import com.his.vo.Page;
import com.his.vo.User;

public class UserDaoImpl implements UserDao{

	private Connection conn=null;//数据库连接
	private PreparedStatement ps=null;//预处理语句
	private ResultSet rs=null;//结果集处理语句

	public int addUser(User user) {
		int flag=0;
		String sql="insert into user values(userid,?,?,?,?,?,?)";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getRealname());
			ps.setString(4, user.getEmail());
			ps.setInt(5, user.getStatus());
			ps.setInt(6, user.getRoleid());
			flag=ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public int deleteUser(int id) {
		int flag=0;
		String sql="delete from user where userid=?";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			flag=ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return flag;
	}

	public int updateUser(User user) {
		int flag=0;
		String sql ="update user set username=?,password=?,realname=?,email=?,status=?,roleid=? where userid=?";
		DBConnection db = new DBConnection();
		conn = db.getConn();
		try {
			ps =conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2,user.getPassword() );
			ps.setString(3,user.getRealname());
			ps.setString(4,user.getEmail());
			ps.setInt(5,user.getStatus());
			ps.setInt(6,user.getRoleid());
			ps.setInt(7,user.getUserid());
			flag =ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public User findUserById(int id) {
		String sql ="select * from user where userid="+id;
		DBConnection db = new DBConnection();
		conn = db.getConn();
		User user = new User();
		try {
			ps =conn.prepareStatement(sql);
			rs =ps.executeQuery();
			while (rs.next()) {
				user.setUserid(rs.getInt("userid")) ;  
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRealname(rs.getString("realname"));
				user.setEmail(rs.getString("email"));
				user.setStatus(rs.getInt("status"));
				user.setRoleid(rs.getInt("roleid"));
			} 
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public User findUserByUserName(String uname){
		User user = new User();
		String sql ="select * from user where username='"+uname+"'";
		DBConnection db = new DBConnection();
		conn = db.getConn();
		try {
			ps =conn.prepareStatement(sql);
			rs =ps.executeQuery();
			while (rs.next()) {
				user.setUserid(rs.getInt("userid")) ;  
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRealname(rs.getString("realname"));
				user.setEmail(rs.getString("email"));
				user.setStatus(rs.getInt("status"));
				user.setRoleid(rs.getInt("roleid"));
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	public Page findPageByUserName(String username,int pageNo, int pageSize, int totalCount){
		Page page = new Page();
		String sql;
		if (username!=null&&username!="") {
			sql ="select * from user where username like '%"+username+"%' limit "+page.getOffset(pageNo)+","+pageSize;
		}else{
			sql ="select * from user limit "+page.getOffset(pageNo)+","+pageSize;
		}
		DBConnection db = new DBConnection();
		conn = db.getConn();
		List<User> userList = new ArrayList<User>();	
		try {
			ps =conn.prepareStatement(sql);
			rs =ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserid(rs.getInt("userid")) ;  
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRealname(rs.getString("realname"));
				user.setEmail(rs.getString("email"));
				user.setStatus(rs.getInt("status"));
				user.setRoleid(rs.getInt("roleid"));
				userList.add(user);
			} 
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		page.setPageItem(userList);
		return page;
	}

	public int findUserNameCount(String username) {
		int count=0;
		String sql;
		if (username==null) {
			sql ="select count(*) as num from user";
		}else{
			sql ="select count(*) as num from user where username like '%"+username+"%'";
		}
		DBConnection db = new DBConnection();
		conn = db.getConn();
		try {
			ps =conn.prepareStatement(sql);
			rs =ps.executeQuery();
			while (rs.next()) {
				count=rs.getInt("num");
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

}
