package com.his.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.his.dao.RoleDao;
import com.his.db.DBConnection;
import com.his.vo.Page;
import com.his.vo.Role;

public class RoleDaoImpl implements RoleDao{
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private Connection conn=null;

	public int addRole(Role role) {
		int flag=0;
		String sql="insert into role values(roleId,?,?)";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, role.getRoleStatus());
			ps.setString(2, role.getRoleName());
			flag=ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	public int delRole(int roleId) {
		int flag=0;
		String sql="delete from role where roleid=?";
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
	public int updateRole(Role role) {
		int flag=0;
		String sql="update role set rolestatus=?,rolename=? where roleid=?";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, role.getRoleStatus());
			ps.setString(2, role.getRoleName());
			ps.setInt(3, role.getRoleId());
			flag=ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	public Role findRoleById(int roleId) {
		String sql="select * from role where roleid=?";
		DBConnection db=new DBConnection();
		conn=db.getConn();
		Role role=new Role();
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, roleId);
			rs=ps.executeQuery();
			while (rs.next()) {
				role.setRoleId(rs.getInt("roleid"));
				role.setRoleName(rs.getString("rolename"));
				role.setRoleStatus(rs.getInt("rolestatus"));
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return role;
	}
	public List<Role> findAllRole() {
		String sql="select * from role";
		DBConnection db= new DBConnection();
		conn=db.getConn();
		List<Role> roleList=new ArrayList<Role>();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				Role role=new Role();
				role.setRoleId(rs.getInt("roleid"));
				role.setRoleName(rs.getString("rolename"));
				role.setRoleStatus(rs.getInt("rolestatus"));
				roleList.add(role);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roleList;
	}
	
	public int findId(String roleName,int roleStatus){
		String sql="select roleid from role where rolename='"+roleName+"' and rolestatus="+roleStatus;
		int roleId=0;
		DBConnection db = new DBConnection();
		conn = db.getConn();
		try {
			ps =conn.prepareStatement(sql);
			rs =ps.executeQuery();
			while (rs.next()) {
				roleId=rs.getInt("roleid");
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roleId;
	}

	public Page findRolePage(String roleName, int pageNo, int pageSize,
			int totalCount) {
		Page page=new Page();
		String sql="select * from role where rolename like '%"+roleName+"%' limit "+page.getOffset(pageNo)+","+pageSize;
		DBConnection db = new DBConnection();
		conn = db.getConn();
		List<Role> roleList=new ArrayList<Role>();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				Role role=new Role();
				role.setRoleId(rs.getInt("roleid"));
				role.setRoleName(rs.getString("rolename"));
				role.setRoleStatus(rs.getInt("rolestatus"));
				roleList.add(role);	
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		page.setPageItem(roleList);
		return page;
	}
	public int findRoleCount(String roleName) {
		int count=0;
		String sql="select count(*) as num from role where rolename like '%"+roleName+"%'";
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
