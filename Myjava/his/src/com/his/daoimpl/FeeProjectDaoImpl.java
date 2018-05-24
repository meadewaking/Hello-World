package com.his.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.his.dao.FeeProjectDao;
import com.his.db.DBConnection;
import com.his.vo.FeeProject;

public class FeeProjectDaoImpl implements FeeProjectDao{
	
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private Connection conn=null;
	
	@Override
	public int addFeeProject(FeeProject fp) {
		int flag=0;
		String sql="insert into feeproject values(id,?,?)";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1,fp.getFeeName());
			ps.setDouble(2,fp.getAmount());
			flag=ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	public int findIdByFeeName(String feeName) {
		String sql="select id from feeproject where feeName=?";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		int id=0;
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1,feeName);
			rs=ps.executeQuery();
			while (rs.next()) {
			id=rs.getInt("id");
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	@Override
	public FeeProject findFeeProById(int id) {
		String sql="select * from feeproject where id=?";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		FeeProject fp=new FeeProject();
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1,id);
			rs=ps.executeQuery();
			while (rs.next()) {
			fp.setId(rs.getInt("id"));
			fp.setFeeName(rs.getString("feeName"));
			fp.setAmount(rs.getDouble("amount"));
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fp;
	}

	@Override
	public List<FeeProject> findAllFeePro() {
		String sql="select * from feeproject";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		List<FeeProject> feeProList=new ArrayList<FeeProject>();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
			FeeProject fp=new FeeProject();	
			fp.setId(rs.getInt("id"));
			fp.setFeeName(rs.getString("feeName"));
			fp.setAmount(rs.getDouble("amount"));
			feeProList.add(fp);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return feeProList;
	}
	
}
