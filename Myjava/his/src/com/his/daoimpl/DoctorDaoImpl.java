package com.his.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.his.dao.DoctorDao;
import com.his.db.DBConnection;
import com.his.vo.Doctor;
import com.his.vo.Drug;
import com.his.vo.Page;

public class DoctorDaoImpl implements DoctorDao{
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private Connection conn=null;

	@Override
	public int addDoctor(Doctor doctor) {
		int flag=0;
		String sql="insert into staff values(id,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1,doctor.getName());
			ps.setInt(2,doctor.getIdentifierType());
			ps.setString(3,doctor.getIdentifier());
			ps.setString(4,doctor.getTelphone());
			ps.setString(5,doctor.getPhone());
			ps.setInt(6,doctor.getGender());
			ps.setInt(7,doctor.getAge());
			ps.setString(8,doctor.getBirthDate());
			ps.setString(9,doctor.getEmail());
			ps.setInt(10,doctor.getDepid());
			ps.setInt(11,doctor.getDegree());
			ps.setString(12,doctor.getRemarks());
			ps.setString(13,doctor.getJoinTime());
			flag=ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public int delDoctor(int id) {
		int flag=0;
		String sql="delete from staff where id=?";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1,id);
			flag=ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public int updateDoctor(Doctor doctor) {
		int flag=0;
		String sql="update staff set name=?,identifierType=?,identifier=?,telphone=?,phone=?,gender=?,age=?,birthDate=?,email=?,depid=?,degree=?,remarks=? where id=?";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1,doctor.getName());
			ps.setInt(2,doctor.getIdentifierType());
			ps.setString(3,doctor.getIdentifier());
			ps.setString(4,doctor.getTelphone());
			ps.setString(5,doctor.getPhone());
			ps.setInt(6,doctor.getGender());
			ps.setInt(7,doctor.getAge());
			ps.setString(8,doctor.getBirthDate());
			ps.setString(9,doctor.getEmail());
			ps.setInt(10,doctor.getDepid());
			ps.setInt(11,doctor.getDegree());
			ps.setString(12,doctor.getRemarks());
			ps.setInt(13,doctor.getId());
			flag=ps.executeUpdate();
			ps.close();
			conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public Doctor findDoctorById(int id) {
		Doctor doctor=new Doctor();
		String sql="select * from staff where id=?";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1,id);
			rs=ps.executeQuery();
			while (rs.next()) {
				doctor.setId(rs.getInt("id"));
				doctor.setName(rs.getString("name"));
				doctor.setIdentifierType(rs.getInt("identifierType"));
				doctor.setIdentifier(rs.getString("identifier"));
				doctor.setTelphone(rs.getString("telphone"));
				doctor.setPhone(rs.getString("phone"));
				doctor.setGender(rs.getInt("gender"));
				doctor.setAge(rs.getInt("age"));
				doctor.setBirthDate(rs.getString("birthDate"));
				doctor.setEmail(rs.getString("email"));
				doctor.setDepid(rs.getInt("depid"));
				doctor.setDegree(rs.getInt("degree"));
				doctor.setRemarks(rs.getString("remarks"));
				doctor.setJoinTime(rs.getString("joinTime"));
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return doctor;
	}

	@Override
	public Page findDoctorPage(int id, String name, int dpid, int pageNo,
			int pageSize, int totalCount) {
		Page page=new Page();
		String sql;
		if (id==0&&dpid==0) {
			sql="select * from staff where name like '%"+name+"%' limit "+page.getOffset(pageNo)+","+pageSize;
		}else if(id==0&&dpid!=0){
			sql="select * from staff where name like '%"+name+"%' and depid="+dpid+" limit "+page.getOffset(pageNo)+","+pageSize;
		}else if(id!=0&&dpid==0){
			sql="select * from staff where name like '%"+name+"%' and id="+id+" limit "+page.getOffset(pageNo)+","+pageSize;
		}else{
			sql="select * from staff where name like '%"+name+"%' and id="+id+" and depid="+dpid+" limit "+page.getOffset(pageNo)+","+pageSize;
		}
		DBConnection db = new DBConnection();
		conn = db.getConn();
		List<Doctor> doctorList=new ArrayList<Doctor>();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				Doctor doctor=new Doctor();
				doctor.setId(rs.getInt("id"));
				doctor.setName(rs.getString("name"));
				doctor.setIdentifierType(rs.getInt("identifierType"));
				doctor.setIdentifier(rs.getString("identifier"));
				doctor.setTelphone(rs.getString("telphone"));
				doctor.setPhone(rs.getString("phone"));
				doctor.setGender(rs.getInt("gender"));
				doctor.setAge(rs.getInt("age"));
				doctor.setBirthDate(rs.getString("birthDate"));
				doctor.setEmail(rs.getString("email"));
				doctor.setDepid(rs.getInt("depid"));
				doctor.setDegree(rs.getInt("degree"));
				doctor.setRemarks(rs.getString("remarks"));
				doctor.setJoinTime(rs.getString("joinTime"));
				doctorList.add(doctor);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		page.setPageItem(doctorList);
		return page;
	}

	@Override
	public int findDoctorCount(int id, String name, int depid) {
		int count=0;
		String sql;
		if (id==0&&depid==0) {
			sql="select count(*) as num from staff where name like '%"+name+"%'";
		}else if(id==0&&depid!=0){
			sql="select count(*) as num from staff where name like '%"+name+"%' and depid="+depid;
		}else if(id!=0&&depid==0){
			sql="select count(*) as num from staff where name like '%"+name+"%' and id="+id;
		}else{//��������0
			sql="select count(*) as num from staff where name like '%"+name+"%' and id="+id+" and depid="+depid;
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
	public List<Integer> findIdByName(String name) {
		String sql="select * from staff where name like '%"+name+"%'";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		List<Integer> docIdList=new ArrayList<Integer>();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				docIdList.add(rs.getInt("id"));
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return docIdList;
	}
	public List<Integer> findAllId(){
		String sql="select id from staff";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		List<Integer> idList=new ArrayList<Integer>();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				idList.add(rs.getInt("id"));
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idList;
	}
	public List<Doctor> findAllDoc(){
		String sql="select * from staff";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		List<Doctor> docList=new ArrayList<Doctor>();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				Doctor doctor=new Doctor();
				doctor.setId(rs.getInt("id"));
				doctor.setName(rs.getString("name"));
				doctor.setIdentifierType(rs.getInt("identifierType"));
				doctor.setIdentifier(rs.getString("identifier"));
				doctor.setTelphone(rs.getString("telphone"));
				doctor.setPhone(rs.getString("phone"));
				doctor.setGender(rs.getInt("gender"));
				doctor.setAge(rs.getInt("age"));
				doctor.setBirthDate(rs.getString("birthDate"));
				doctor.setEmail(rs.getString("email"));
				doctor.setDepid(rs.getInt("depid"));
				doctor.setDegree(rs.getInt("degree"));
				doctor.setRemarks(rs.getString("remarks"));
				doctor.setJoinTime(rs.getString("joinTime"));
				docList.add(doctor);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return docList;
	}
	
	public List<Doctor> findDocByDepId(int depId){
		String sql="select * from staff where depid=?";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		List<Doctor> docList=new ArrayList<Doctor>();
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, depId);
			rs=ps.executeQuery();
			while (rs.next()) {
				Doctor doctor=new Doctor();
				doctor.setId(rs.getInt("id"));
				doctor.setName(rs.getString("name"));
				doctor.setIdentifierType(rs.getInt("identifierType"));
				doctor.setIdentifier(rs.getString("identifier"));
				doctor.setTelphone(rs.getString("telphone"));
				doctor.setPhone(rs.getString("phone"));
				doctor.setGender(rs.getInt("gender"));
				doctor.setAge(rs.getInt("age"));
				doctor.setBirthDate(rs.getString("birthDate"));
				doctor.setEmail(rs.getString("email"));
				doctor.setDepid(rs.getInt("depid"));
				doctor.setDegree(rs.getInt("degree"));
				doctor.setRemarks(rs.getString("remarks"));
				doctor.setJoinTime(rs.getString("joinTime"));
				docList.add(doctor);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return docList;
	} 
	
	
}
