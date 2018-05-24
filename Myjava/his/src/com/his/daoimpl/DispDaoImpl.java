package com.his.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.his.dao.DispDao;
import com.his.db.DBConnection;
import com.his.vo.Disp;
import com.his.vo.Drug;
import com.his.vo.Page;

public class DispDaoImpl implements DispDao{
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private Connection conn=null;

	@Override
	public int addDisp(Disp disp) {
		int flag=0;
		String sql="insert into dispensing values(?,?,?,?,?,?)";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1,disp.getMedicalNo());
			ps.setString(2,disp.getDrugId());
			ps.setInt(3,disp.getDispCount());
			ps.setInt(4,disp.getAldispCount());
			ps.setInt(5,disp.getNodispCount());
			ps.setString(6,disp.getDispTime());
			flag=ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public int findDispCount(String medicalNo) {
		int count=0;
		String sql="select count(*) as num from dispensing where medicalNo like '%"+medicalNo+"%'";
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

	@Override
	public Page findDispPage(String medicalNo, int pageNo, int pageSize) {
		Page page=new Page();
		String sql="select * from dispensing where medicalNo like '%"+medicalNo+"%' limit "+page.getOffset(pageNo)+","+pageSize;
		DBConnection db = new DBConnection();
		conn = db.getConn();
		List<Disp> dispList=new ArrayList<Disp>();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				Disp disp=new Disp();
				disp.setMedicalNo(rs.getString("medicalNo"));
				disp.setDrugId(rs.getString("drugId"));
				disp.setDispCount(rs.getInt("dispCount"));
				disp.setAldispCount(rs.getInt("aldispCount"));
				disp.setNodispCount(rs.getInt("nodispCount"));
				disp.setDispTime(rs.getString("dispTime"));
				dispList.add(disp);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		page.setPageItem(dispList);
		return page;
	}

	@Override
	public List<Disp> findDispListByNo(String medicalNo) {
		String sql="select * from dispensing where medicalNo="+medicalNo;
		List<Disp> dispList=new ArrayList<Disp>();
		DBConnection db = new DBConnection();
		conn = db.getConn();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				Disp disp=new Disp();
				disp.setMedicalNo(rs.getString("medicalNo"));
				disp.setDrugId(rs.getString("drugId"));
				disp.setDispCount(rs.getInt("dispCount"));
				disp.setAldispCount(rs.getInt("aldispCount"));
				disp.setNodispCount(rs.getInt("nodispCount"));
				disp.setDispTime(rs.getString("dispTime"));
				dispList.add(disp);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dispList;
	}

	@Override
	public Disp findDispByNoDID(String medicalNo, String drugId) {
		String sql="select * from dispensing where medicalNo="+medicalNo+" and drugId="+drugId;
		DBConnection db = new DBConnection();
		conn = db.getConn();
		Disp disp=new Disp();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				disp.setMedicalNo(rs.getString("medicalNo"));
				disp.setDrugId(rs.getString("drugId"));
				disp.setDispCount(rs.getInt("dispCount"));
				disp.setAldispCount(rs.getInt("aldispCount"));
				disp.setNodispCount(rs.getInt("nodispCount"));
				disp.setDispTime(rs.getString("dispTime"));
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return disp;
	}

	@Override
	public int updateDisp(Disp disp) {
		int flag=0;
		String sql="update dispensing set dispCount=?,aldispCount=?,nodispCount=? where medicalNo=? and drugId=?";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, disp.getDispCount());
			ps.setInt(2,disp.getAldispCount());
			ps.setInt(3,disp.getNodispCount());
			ps.setString(4,disp.getMedicalNo());
			ps.setString(5,disp.getDrugId());
			flag=ps.executeUpdate();
			ps.close();
			conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
}
