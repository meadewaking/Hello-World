package com.his.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.his.dao.Role_ResourcesDao;
import com.his.db.DBConnection;
import com.his.vo.Role_Resources;

public class Role_ResourcesDaoImpl implements Role_ResourcesDao{
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private Connection conn=null;
	
	public int addRole_Resources(Role_Resources rr) {
		int flag=0;
		String sql="insert into role_resources values(id,?,?)";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, rr.getRoleId());
			ps.setInt(2, rr.getResId());
			flag=ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public int delRole_ResourcesByRoleId(int roleId) {
		int flag=0;
		String sql="delete from role_resources where roleid=?";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, roleId);
			flag=ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public List<Integer> finResIdByRoleId(int roleId){
		String sql="select resid from role_resources where roleid=?";
		DBConnection db = new DBConnection();
		conn = db.getConn();
		List<Integer> resIdList=new ArrayList<Integer>();
		try {
			ps =conn.prepareStatement(sql);
			ps.setInt(1, roleId);
			rs =ps.executeQuery();
			while (rs.next()) {
				int resId=rs.getInt("resid");
				resIdList.add(resId);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resIdList;
	}

	@Override
	public int delRole_ResourcesByResId(int resId) {
		int flag=0;
		String sql="delete from role_resources where resid=?";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, resId);
			flag=ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	

}
