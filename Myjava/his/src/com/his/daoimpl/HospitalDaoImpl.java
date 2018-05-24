package com.his.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.his.dao.HospitalDao;
import com.his.db.DBConnection;
import com.his.vo.Hospital;
import com.his.vo.Page;
import com.his.vo.Register;

public class HospitalDaoImpl implements HospitalDao{
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private Connection conn=null;

	@Override
	public int addHos(Hospital hospital) {
		int flag=0;
		String sql="insert into hospitalsheet values(?,?,?,?,?,?)";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1,hospital.getMedicalNo());
			ps.setString(2,hospital.getNurse());
			ps.setString(3,hospital.getBedNo());
			ps.setDouble(4,hospital.getPayCase());
			ps.setString(5,hospital.getPcondition());
			ps.setString(6, hospital.getHosTime());
			flag=ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public Page findHosPage(String medicalNo, List<Integer> docid, int depid,
			String stime, String ftime, int pageNo, int pageSize) {
		Page page=new Page();
		String sql;
		String sql2=" limit "+page.getOffset(pageNo)+","+pageSize;
		if (depid==0) {
			sql="select * from register,hospitalsheet where register.medicalNo=hospitalsheet.medicalNo and register.medicalNo like '%"+medicalNo+"%' and hospitalsheet.hosTime>='"+stime+" 00:00:00' and hospitalsheet.hosTime<='"+ftime+" 23:59:59' and (";
			for (Integer id : docid) {
				sql=sql+"register.docid="+id+" or ";
			}
			sql=sql.substring(0, sql.length()-4)+")"+sql2;
		}else{
			sql="select * from register,hospitalsheet where register.medicalNo=hospitalsheet.medicalNo and register.medicalNo like '%"+medicalNo+"%' and register.depid="+depid+" and hospitalsheet.hosTime>='"+stime+" 00:00:00' and hospitalsheet.hosTime<='"+ftime+" 23:59:59' and (";
			for (Integer id : docid) {
				sql=sql+"register.docid="+id+" or ";
			}
			sql=sql.substring(0, sql.length()-4)+")"+sql2;
		}
		DBConnection db = new DBConnection();
		conn = db.getConn();
		List<Hospital> hosList=new ArrayList<Hospital>();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				Hospital hos=new Hospital();
				hos.setMedicalNo(rs.getString("medicalNo"));
				hos.setNurse(rs.getString("nurse"));
				hos.setBedNo(rs.getString("bedNo"));
				hos.setPayCase(rs.getDouble("payCase"));
				hos.setPcondition(rs.getString("pcondition"));
				hos.setHosTime(rs.getString("hosTime"));
				hosList.add(hos);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		page.setPageItem(hosList);
		return page;	
	}

	@Override
	public int findHosCount(String medicalNo, List<Integer> docid, int depid,
			String stime, String ftime) {
		int count=0;
		String sql;
		if (depid==0) {
			sql="select count(*) as num from register,hospitalsheet where register.medicalNo=hospitalsheet.medicalNo and register.medicalNo like '%"+medicalNo+"%' and hospitalsheet.hosTime>='"+stime+" 00:00:00' and hospitalsheet.hosTime<='"+ftime+" 23:59:59' and (";
			for (Integer id : docid) {
				sql=sql+"register.docid="+id+" or ";
			}
			sql=sql.substring(0, sql.length()-4)+")";	
		}else{
			sql="select count(*) as num from register,hospitalsheet where register.medicalNo=hospitalsheet.medicalNo and register.medicalNo like '%"+medicalNo+"%' and register.depid="+depid+" and hospitalsheet.hosTime>='"+stime+" 00:00:00' and hospitalsheet.hosTime<='"+ftime+" 23:59:59' and (";
			for (Integer id : docid) {
				sql=sql+"register.docid="+id+" or ";
			}
			sql=sql.substring(0, sql.length()-4)+")";
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

	@Override
	public Hospital findHosById(String medicalNo) {
		Hospital hos=new Hospital();
		try {
			String sql="select * from hospitalsheet where medicalNo=?";
			DBConnection db=new DBConnection();
			conn=db.getConn();
			ps=conn.prepareStatement(sql);
			ps.setString(1, medicalNo);
			rs=ps.executeQuery();
			while (rs.next()) {
				hos.setMedicalNo(rs.getString("medicalNo"));
				hos.setBedNo(rs.getString("bedNo"));
				hos.setHosTime(rs.getString("hosTime"));
				hos.setNurse(rs.getString("nurse"));
				hos.setPayCase(rs.getDouble("payCase"));
				hos.setPcondition(rs.getString("pcondition"));
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hos;
	}

	@Override
	public int updateHos(Hospital hos) {
		int flag=0;
		String sql="update hospitalsheet set nurse=?,bedNo=?,payCase=?,pcondition=?,hosTime=? where medicalNo=?";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, hos.getNurse());
			ps.setString(2, hos.getBedNo());
			ps.setDouble(3, hos.getPayCase());
			ps.setString(4, hos.getPcondition());
			ps.setString(5, hos.getHosTime());
			ps.setString(6, hos.getMedicalNo());
			flag=ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<Hospital> findAllList() {
		List<Hospital> hosList=new ArrayList<Hospital>();
		String sql="select * from hospitalsheet";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				Hospital hos=new Hospital();
				hos.setMedicalNo(rs.getString("medicalNo"));
				hos.setBedNo(rs.getString("bedNo"));
				hos.setHosTime(rs.getString("hosTime"));
				hos.setNurse(rs.getString("nurse"));
				hos.setPayCase(rs.getDouble("payCase"));
				hos.setPcondition(rs.getString("pcondition"));
				hosList.add(hos);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hosList;
	}

}
