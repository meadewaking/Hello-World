package com.his.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.his.dao.FeeManagerDao;
import com.his.db.DBConnection;
import com.his.vo.FeeManager;
import com.his.vo.Page;

public class FeeManagerDaoImpl implements FeeManagerDao {

	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private Connection conn=null;
	@Override
	public int addFeeManager(FeeManager fm) {
		int flag=0;
		String sql="insert into feemanager values(gid,?,?,?,?)";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1,fm.getMedicalNo());
			ps.setInt(2,fm.getFeeid());
			ps.setDouble(3, fm.getCharge_sum());
			ps.setString(4, fm.getFeeDate());
			flag=ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<Integer> findFeeIdByNo(String medicalNo) {
		String sql="select feeid from feemanager where medicalNo=?";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		List<Integer> feeidList=new ArrayList<Integer>();
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1,medicalNo);
			rs=ps.executeQuery();
			while (rs.next()) {
				feeidList.add(rs.getInt("feeid"));
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return feeidList;
	}

	@Override
	public int findFeeCount(List<String> medicalNoList) {
		int count=0;
		if (medicalNoList.size()!=0) {
			String sql="select count(*) as num from feemanager where ";
			for (String medicalNo : medicalNoList) {
				sql+="medicalNo="+medicalNo+" or ";
			}
			sql=sql.substring(0, sql.length()-4);
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
		}
		return count;
	}

	@Override
	public Page findFeePage(List<String> medicalNoList, int pageNo,
			int pageSize) {
		Page page=new Page();
		List<FeeManager> feeList=new ArrayList<FeeManager>();
		if (medicalNoList.size()!=0) {
			String sql="select * from feemanager where ";
			for (String medicalNo : medicalNoList) {
				sql+="medicalNo="+medicalNo+" or ";
			}
			sql=sql.substring(0, sql.length()-4)+" limit "+page.getOffset(pageNo)+","+pageSize;
			DBConnection db = new DBConnection();
			conn = db.getConn();
			try {
				ps=conn.prepareStatement(sql);
				rs=ps.executeQuery();
				while (rs.next()) {
					FeeManager fm=new FeeManager();
					fm.setGid(rs.getInt("gid"));
					fm.setMedicalNo(rs.getString("medicalNo"));
					fm.setFeeid(rs.getInt("feeid"));
					fm.setCharge_sum(rs.getDouble("charge_sum"));
					fm.setFeeDate(rs.getString("feeDate"));
					feeList.add(fm);
				}
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		page.setPageItem(feeList);
		return page;
	}

	@Override
	public FeeManager findFMById(int id) {
		String sql="select * from feemanager where gid=?";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		FeeManager fm=new FeeManager();
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1,id);
			rs=ps.executeQuery();
			while (rs.next()) {
				fm.setGid(rs.getInt("gid"));
				fm.setMedicalNo(rs.getString("medicalNo"));
				fm.setFeeid(rs.getInt("feeid"));
				fm.setCharge_sum(rs.getDouble("charge_sum"));
				fm.setFeeDate(rs.getString("feeDate"));
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fm;
	}


}
