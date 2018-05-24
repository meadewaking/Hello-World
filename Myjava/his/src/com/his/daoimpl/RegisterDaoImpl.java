package com.his.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.his.dao.RegisterDao;
import com.his.db.DBConnection;
import com.his.vo.Page;
import com.his.vo.Register;

public class RegisterDaoImpl implements RegisterDao{
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private Connection conn=null;

	@Override
	public int addRegister(Register reg) {
		int flag=0;
		String sql="insert into register values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1,reg.getMedicalNo());
			ps.setString(2, reg.getName());
			ps.setInt(3, reg.getIdentifierType());
			ps.setString(4, reg.getIdentifier());
			ps.setString(5, reg.getInsuranceNumber());
			ps.setString(6, reg.getPhoneNumber());
			ps.setInt(7, reg.getExpenseFlag());
			ps.setInt(8, reg.getGender());
			ps.setInt(9, reg.getAge());
			ps.setString(10, reg.getProfession());
			ps.setInt(11, reg.getCzflag());
			ps.setInt(12, reg.getDocid());
			ps.setInt(13, reg.getFlag());
			ps.setString(14, reg.getRemarks());
			ps.setString(15, reg.getRegtime());
			ps.setInt(16, reg.getDepid());
			ps.setDouble(17, reg.getRegfee());
			flag=ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return flag;
	}

	@Override
	public int delRegister(String medicalNo) {
		int flag=0;
		String sql="delete from register where medicalNo=?";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1,medicalNo);
			flag=ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return flag;
	}

	@Override
	public int updateRegister(Register reg) {
		int flag=0;
		String sql="update register set name=?,identifierType=?,identifier=?,insuranceNumber=?,phoneNumber=?,expenseFlag=?,gender=?,age=?,profession=?,czflag=?,docid=?,flag=?,remarks=?,regtime=?,depid=?,regfee=? where medicalNo=?";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, reg.getName());
			ps.setInt(2, reg.getIdentifierType());
			ps.setString(3, reg.getIdentifier());
			ps.setString(4, reg.getInsuranceNumber());
			ps.setString(5, reg.getPhoneNumber());
			ps.setInt(6, reg.getExpenseFlag());
			ps.setInt(7, reg.getGender());
			ps.setInt(8, reg.getAge());
			ps.setString(9, reg.getProfession());
			ps.setInt(10, reg.getCzflag());
			ps.setInt(11, reg.getDocid());
			ps.setInt(12, reg.getFlag());
			ps.setString(13, reg.getRemarks());
			ps.setString(14, reg.getRegtime());
			ps.setInt(15, reg.getDepid());
			ps.setDouble(16, reg.getRegfee());
			ps.setString(17, reg.getMedicalNo());
			flag=ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public Register findRegisterByNo(String medicalNo) {
		Register reg=new Register();
		try {
			String sql="select * from register where medicalNo=?";
			DBConnection db=new DBConnection();
			conn=db.getConn();
			ps=conn.prepareStatement(sql);
			ps.setString(1, medicalNo);
			rs=ps.executeQuery();
			while (rs.next()) {
				reg.setMedicalNo(rs.getString("medicalNo"));
				reg.setName(rs.getString("name"));
				reg.setIdentifierType(rs.getInt("identifierType"));
				reg.setIdentifier(rs.getString("identifier"));
				reg.setInsuranceNumber(rs.getString("insuranceNumber"));
				reg.setPhoneNumber(rs.getString("phoneNumber"));
				reg.setExpenseFlag(rs.getInt("expenseFlag"));
				reg.setGender(rs.getInt("gender"));
				reg.setAge(rs.getInt("age"));
				reg.setProfession(rs.getString("profession"));
				reg.setCzflag(rs.getInt("czflag"));			
				reg.setDocid(rs.getInt("docid"));
				reg.setFlag(rs.getInt("flag"));
				reg.setRemarks(rs.getString("remarks"));
				reg.setRegtime(rs.getString("regtime"));
				reg.setDepid(rs.getInt("depid"));
				reg.setRegfee(rs.getDouble("regfee"));
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reg;
	}

	@Override
	public Page findRegPage(String medicalNo,List<Integer> docid, int depid, String stime,
			String ftime, int pageNo, int pageSize, int totalCount) {
		Page page=new Page();
		String sql;
		String sql2=" limit "+page.getOffset(pageNo)+","+pageSize;
		if (depid==0) {
			sql="select * from register where medicalNo like '%"+medicalNo+"%' and regtime>'"+stime+" 00:00:00' and regtime<'"+ftime+" 23:59:59' and (";
			for (Integer id : docid) {
				sql=sql+"docid="+id+" or ";
			}
			sql=sql.substring(0, sql.length()-4)+")"+sql2;
		}else{
			sql="select * from register where medicalNo like '%"+medicalNo+"%' and depid="+depid+" and regtime>'"+stime+"  00:00:00' and regtime<'"+ftime+" 23:59:59' and (";
			for (Integer id : docid) {
				sql=sql+"docid="+id+" or ";
			}
			sql=sql.substring(0, sql.length()-4)+")"+sql2;
		}
		DBConnection db = new DBConnection();
		conn = db.getConn();
		List<Register> regList=new ArrayList<Register>();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				Register reg=new Register();
				reg.setMedicalNo(rs.getString("medicalNo"));
				reg.setName(rs.getString("name"));
				reg.setIdentifierType(rs.getInt("identifierType"));
				reg.setIdentifier(rs.getString("identifier"));
				reg.setInsuranceNumber(rs.getString("insuranceNumber"));
				reg.setPhoneNumber(rs.getString("phoneNumber"));
				reg.setExpenseFlag(rs.getInt("expenseFlag"));
				reg.setGender(rs.getInt("gender"));
				reg.setAge(rs.getInt("age"));
				reg.setProfession(rs.getString("profession"));
				reg.setCzflag(rs.getInt("czflag"));			
				reg.setDocid(rs.getInt("docid"));
				reg.setFlag(rs.getInt("flag"));
				reg.setRemarks(rs.getString("remarks"));
				reg.setRegtime(rs.getString("regtime"));
				reg.setDepid(rs.getInt("depid"));
				reg.setRegfee(rs.getDouble("regfee"));
				regList.add(reg);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		page.setPageItem(regList);
		return page;	
	}

	@Override
	public int findRegCount(String medicalNo,List<Integer> docid, int depid, String stime,
			String ftime) {
		int count=0;
		String sql;
		if (depid==0) {
			sql="select count(*) as num from register where medicalNo like '%"+medicalNo+"%' and regtime>='"+stime+" 00:00:00' and regtime<='"+ftime+" 23:59:59' and (";
			for (Integer id : docid) {
				sql=sql+"docid="+id+" or ";
			}
			sql=sql.substring(0, sql.length()-4)+")";	
		}else{
			sql="select count(*) as num from register where medicalNo like '%"+medicalNo+"%' and depid="+depid+" and regtime>='"+stime+" 00:00:00' and regtime<='"+ftime+" 23:59:59' and (";
			for (Integer id : docid) {
				sql=sql+"docid="+id+" or ";
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
	public List<String> findNoByName(String name,String medicalNo) {
		String sql="select medicalNo from register where name like '%"+name+"%' and medicalNo like '%"+medicalNo+"%'";
		DBConnection db = new DBConnection();
		conn = db.getConn();
		List<String> medicalNoList=new ArrayList<String>();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				String mno=rs.getString("medicalNo");
				medicalNoList.add(mno);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return medicalNoList;	
	}

	@Override
	public List<Register> findAllReg() {
		String sql="select * from register";
		DBConnection db = new DBConnection();
		conn = db.getConn();
		List<Register> regList=new ArrayList<Register>();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				Register reg=new Register();
				reg.setMedicalNo(rs.getString("medicalNo"));
				reg.setName(rs.getString("name"));
				reg.setIdentifierType(rs.getInt("identifierType"));
				reg.setIdentifier(rs.getString("identifier"));
				reg.setInsuranceNumber(rs.getString("insuranceNumber"));
				reg.setPhoneNumber(rs.getString("phoneNumber"));
				reg.setExpenseFlag(rs.getInt("expenseFlag"));
				reg.setGender(rs.getInt("gender"));
				reg.setAge(rs.getInt("age"));
				reg.setProfession(rs.getString("profession"));
				reg.setCzflag(rs.getInt("czflag"));			
				reg.setDocid(rs.getInt("docid"));
				reg.setFlag(rs.getInt("flag"));
				reg.setRemarks(rs.getString("remarks"));
				reg.setRegtime(rs.getString("regtime"));
				reg.setDepid(rs.getInt("depid"));
				reg.setRegfee(rs.getDouble("regfee"));
				regList.add(reg);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return regList;
	}
	
}
