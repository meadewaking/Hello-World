package com.his.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.his.dao.DepDao;
import com.his.db.DBConnection;
import com.his.vo.Dep;

public class DepDaoImpl implements DepDao{
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private Connection conn=null;

	@Override
	public List<Dep> findDepList() {
		List<Dep> depList=new ArrayList<Dep>();
		String sql="select * from dep";
		DBConnection db= new DBConnection();
		conn=db.getConn();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				Dep dep=new Dep();
				dep.setId(rs.getInt("id"));
				dep.setDepCode(rs.getString("DepCode"));
				dep.setDepName(rs.getString("DepName"));
				depList.add(dep);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return depList;
	}

	@Override
	public Dep findDepById(int id) {
		String sql="select * from dep where id=?";
		DBConnection db= new DBConnection();
		conn=db.getConn();
		Dep dep=new Dep();
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			while (rs.next()) {
				dep.setId(rs.getInt("id"));
				dep.setDepCode(rs.getString("DepCode"));
				dep.setDepName(rs.getString("DepName"));
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dep;
	}

}
