package com.his.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.his.dao.DrugDao;
import com.his.db.DBConnection;
import com.his.vo.Drug;
import com.his.vo.Page;

public class DrugDaoImpl implements DrugDao{
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private Connection conn=null;

	public int addDrug(Drug drug) {
		int flag=0;
		String sql="insert into drugvariety values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1,drug.getDID());
			ps.setString(2,drug.getDrugURL());
			ps.setDouble(3,drug.getPurchasing_price());
			ps.setDouble(4,drug.getSelling_price());
			ps.setString(5,drug.getDrugName());
			ps.setInt(6,drug.getDrugType());
			ps.setString(7,drug.getDescription());
			ps.setString(8,drug.getProduction_date());
			ps.setString(9,drug.getExpiration_date());
			ps.setInt(10,drug.getShelf_life());
			ps.setString(11,drug.getDetail());
			ps.setString(12,drug.getManufacturer());
			ps.setString(13,drug.getDirections());
			ps.setInt(14,drug.getCountpurchases());
			ps.setInt(15,drug.getInventory());
			ps.setInt(16,drug.getDrugflag());
			ps.setString(17,drug.getDrugcomment());
			flag=ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public int delDrug(String DID) {
		int flag=0;
		String sql="delete from drugvariety where DID=?";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1,DID);
			flag=ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public int updateDrug(Drug drug) {
		int flag=0;
		String sql="update drugvariety set drugURL=?,purchasing_price=?,selling_price=?,drugName=?,drugType=?,description=?,production_date=?,expiration_date=?,shelf_life=?,detail=?,manufacturer=?,directions=?,countpurchases=?,inventory=?,drugflag=?,drugcomment=? where DID=?";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1,drug.getDrugURL());
			ps.setDouble(2,drug.getPurchasing_price());
			ps.setDouble(3,drug.getSelling_price());
			ps.setString(4,drug.getDrugName());
			ps.setInt(5,drug.getDrugType());
			ps.setString(6,drug.getDescription());
			ps.setString(7,drug.getProduction_date());
			ps.setString(8,drug.getExpiration_date());
			ps.setInt(9,drug.getShelf_life());
			ps.setString(10,drug.getDetail());
			ps.setString(11,drug.getManufacturer());
			ps.setString(12,drug.getDirections());
			ps.setInt(13,drug.getCountpurchases());
			ps.setInt(14,drug.getInventory());
			ps.setInt(15,drug.getDrugflag());
			ps.setString(16,drug.getDrugcomment());
			ps.setString(17,drug.getDID());
			flag=ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public Drug findDrugById(String DID) {
		Drug drug=new Drug();
		String sql="select * from drugvariety where DID=?";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1,DID);
			rs=ps.executeQuery();
			while (rs.next()) {
				drug.setDID(rs.getString("DID"));
				drug.setDrugURL(rs.getString("drugURL"));
				drug.setPurchasing_price(rs.getDouble("purchasing_price"));
				drug.setSelling_price(rs.getDouble("selling_price"));
				drug.setDrugName(rs.getString("drugName"));
				drug.setDrugType(rs.getInt("drugType"));
				drug.setDescription(rs.getString("description"));
				drug.setProduction_date(rs.getString("production_date"));
				drug.setExpiration_date(rs.getString("expiration_date"));
				drug.setShelf_life(rs.getInt("shelf_life"));
				drug.setDetail(rs.getString("detail"));
				drug.setManufacturer(rs.getString("manufacturer"));
				drug.setDirections(rs.getString("directions"));
				drug.setCountpurchases(rs.getInt("countpurchases"));
				drug.setInventory(rs.getInt("inventory"));
				drug.setDrugflag(rs.getInt("drugflag"));
				drug.setDrugcomment(rs.getString("drugcomment"));
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return drug;
	}


	public Page findDrugPage(String drugName, int drugType, int pageNo,
			int pageSize, int totalCount) {
		Page page=new Page();
		String sql;
		if (drugName!=null) {
			if (drugType==0) {
				sql="select * from drugvariety where drugName like '%"+drugName+"%' limit "+page.getOffset(pageNo)+","+pageSize;
			}else if(drugType==4){
				sql="select * from drugvariety where drugName like '%"+drugName+"%' and (drugType=1 or drugType=2) limit "+page.getOffset(pageNo)+","+pageSize;
			}else{
				sql="select * from drugvariety where drugName like '%"+drugName+"%' and drugType="+drugType+" limit "+page.getOffset(pageNo)+","+pageSize;
			}
		}else{
			sql="select * from drugvariety limit "+page.getOffset(pageNo)+","+pageSize;
		}
		DBConnection db = new DBConnection();
		conn = db.getConn();
		List<Drug> drugList=new ArrayList<Drug>();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				Drug drug=new Drug();
				drug.setDID(rs.getString("DID"));
				drug.setDrugURL(rs.getString("drugURL"));
				drug.setPurchasing_price(rs.getDouble("purchasing_price"));
				drug.setSelling_price(rs.getDouble("selling_price"));
				drug.setDrugName(rs.getString("drugName"));
				drug.setDrugType(rs.getInt("drugType"));
				drug.setDescription(rs.getString("description"));
				drug.setProduction_date(rs.getString("production_date"));
				drug.setExpiration_date(rs.getString("expiration_date"));
				drug.setShelf_life(rs.getInt("shelf_life"));
				drug.setDetail(rs.getString("detail"));
				drug.setManufacturer(rs.getString("manufacturer"));
				drug.setDirections(rs.getString("directions"));
				drug.setCountpurchases(rs.getInt("countpurchases"));
				drug.setInventory(rs.getInt("inventory"));
				drug.setDrugflag(rs.getInt("drugflag"));
				drug.setDrugcomment(rs.getString("drugcomment"));
				drugList.add(drug);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		page.setPageItem(drugList);
		return page;
	}


	public int findDrugCount(String drugName, int drugType) {
		int count=0;
		String sql;
		if (drugName!=null) {
			if (drugType==0) {
				sql="select count(*) as num from drugvariety where drugName like '%"+drugName+"%'";
			}else if(drugType==4){
				sql="select count(*) as num from drugvariety where drugName like '%"+drugName+"%' and (drugType=1 or drugType=2)";
			}else{
				sql="select count(*) as num from drugvariety where drugName like '%"+drugName+"%' and drugType="+drugType;
			}
		}else{
			sql="select count(*) as num from drugvariety";
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
	public List<Drug> findAllDrug() {
		List<Drug> drugList=new ArrayList<Drug>();
		String sql="select * from drugvariety";
		DBConnection db = new DBConnection();
		conn = db.getConn();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				Drug drug=new Drug();
				drug.setDID(rs.getString("DID"));
				drug.setDrugURL(rs.getString("drugURL"));
				drug.setPurchasing_price(rs.getDouble("purchasing_price"));
				drug.setSelling_price(rs.getDouble("selling_price"));
				drug.setDrugName(rs.getString("drugName"));
				drug.setDrugType(rs.getInt("drugType"));
				drug.setDescription(rs.getString("description"));
				drug.setProduction_date(rs.getString("production_date"));
				drug.setExpiration_date(rs.getString("expiration_date"));
				drug.setShelf_life(rs.getInt("shelf_life"));
				drug.setDetail(rs.getString("detail"));
				drug.setManufacturer(rs.getString("manufacturer"));
				drug.setDirections(rs.getString("directions"));
				drug.setCountpurchases(rs.getInt("countpurchases"));
				drug.setInventory(rs.getInt("inventory"));
				drug.setDrugflag(rs.getInt("drugflag"));
				drug.setDrugcomment(rs.getString("drugcomment"));
				drugList.add(drug);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return drugList;
	}

	@Override
	public int updateDrugInventory(String DID,int inventory) {
		int flag=0;
		String sql="update drugvariety set inventory=? where DID=?";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1,inventory);
			ps.setString(2,DID);
			flag=ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

}
