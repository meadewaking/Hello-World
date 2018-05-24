package com.his.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.his.dao.ResourcesDao;
import com.his.db.DBConnection;
import com.his.vo.Page;
import com.his.vo.Resources;

public class ResourcesDaoImpl implements ResourcesDao{
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private Connection conn=null;

	public int addResources(Resources res) {
		int flag=0;
		String sql="insert into resources values(resid,?,?,?)";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, res.getResName());
			ps.setString(2, res.getResURL());
			ps.setInt(3, res.getStatus());
			flag=ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return flag;
	}

	public int delResources(int resId) {
		int flag=0;
		String sql="delete from resources where resid=?";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		try{
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

	public int updateResources(Resources res) {
		int flag=0;
		String sql="update resources set resname=?,resurl=?,status=? where resid=?";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, res.getResName());
			ps.setString(2, res.getResURL());
			ps.setInt(3, res.getStatus());
			ps.setInt(4, res.getResId());
			flag=ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public List<Resources> findAllResources() {
		List<Resources> resList=new ArrayList<Resources>();
		String sql="select * from resources";
		DBConnection db= new DBConnection();
		conn=db.getConn();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				Resources res=new Resources();
				res.setResId(rs.getInt("resid"));
				res.setResName(rs.getString("resname"));
				res.setResURL(rs.getString("resurl"));
				res.setStatus(rs.getInt("status"));
				resList.add(res);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return resList;
	}

	public Resources findResById(int resId) {
		Resources res=new Resources();
		String sql="select * from resources where resid=?";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		try{
			ps=conn.prepareStatement(sql);
			ps.setInt(1, resId);
			rs=ps.executeQuery();
			while (rs.next()) {
				res.setResId(rs.getInt("resid"));
				res.setResName(rs.getString("resname"));
				res.setResURL(rs.getString("resurl"));
				res.setStatus(rs.getInt("status"));
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return res;
	}
	
	public Page findResPage(String resName, int pageNo, int pageSize,
			int totalCount) {
		Page page=new Page();
		String sql="select * from resources where resname like '%"+resName+"%' limit "+page.getOffset(pageNo)+","+pageSize;
		DBConnection db = new DBConnection();
		conn = db.getConn();
		List<Resources> resList=new ArrayList<Resources>();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				Resources res=new Resources();
				res.setResId(rs.getInt("resid"));
				res.setResName(rs.getString("resname"));
				res.setResURL(rs.getString ("resurl"));
				res.setStatus(rs.getInt("status"));
				resList.add(res);	
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		page.setPageItem(resList);
		return page;
	}
	
	public int findResCount(String resName) {
		int count=0;
		String sql="select count(*) as num from resources where resname like '%"+resName+"%'";
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
