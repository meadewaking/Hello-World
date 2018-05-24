package cn.sycu.meade.dao;

import java.sql.*;

import cn.sycu.meade.common.MyConnection;
import cn.sycu.meade.entity.*;

public class UserDao {
	public int insert(UserBean user) throws SQLException {
	Connection conn = null;
	PreparedStatement ps = null;
	try {
		String sql = " INSERT INTO Users " + 
					" VALUES (null, ?, ?, ?, ?, ?, ?) ";
		int count = 1;
		conn = MyConnection.getConnection();
		ps = conn.prepareStatement(sql);
		ps.setInt(count++, user.getLoginId());
		ps.setString(count++, user.getUserName());
		ps.setString(count++, user.getGender());
		ps.setDate(count++, user.getBirthday());
		ps.setString(count++, user.getCardId());
		ps.setString(count++, user.getRemark());
		
		return ps.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
		throw e;
	} finally {
		MyConnection.closeStatementAndConnection(ps, conn);
	}		
  }

	public int update(UserBean user) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String sql = " UPDATE Users " + 
						" SET LoginId = ?, UserName = ?, Gender = ?, Birthday = ?, " +
						" CardId = ?, Remark = ? " +
						" WHERE UserId = ? ";
			int count = 1;
			conn = MyConnection.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(count++, user.getLoginId());
			ps.setString(count++, user.getUserName());
			ps.setString(count++, user.getGender());
			ps.setDate(count++, user.getBirthday());
			ps.setString(count++, user.getCardId());
			ps.setString(count++, user.getRemark());
			ps.setInt(count++, user.getUserId());
			
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(ps, conn);
		}		
	  }
	
	public UserBean selectUserByLoginId(int loginId) throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = " SELECT * FROM Users " + 
						" WHERE LoginId = ? ";
			conn = MyConnection.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, loginId);
			
			rs = ps.executeQuery();			
			if (rs.next()) {
				UserBean user = new UserBean();
				int count = 1;
				user.setUserId(rs.getInt(count++));
				user.setLoginId(rs.getInt(count++));
				user.setUserName(rs.getString(count++));
				user.setGender(rs.getString(count++));
				user.setBirthday(rs.getDate(count++));
				user.setCardId(rs.getString(count++));
				user.setRemark(rs.getString(count++));
				
				return user;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			MyConnection.closeResultSetAndStatementAndConnection(rs, ps, conn);
		}		
	}	
	
	public UserBean selectUserByCardId(String CardId) throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = " SELECT * FROM Users " + 
						" WHERE CardId = ? ";
			conn = MyConnection.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, CardId);
			
			rs = ps.executeQuery();			
			if (rs.next()) {
				UserBean user = new UserBean();
				int count = 1;
				user.setUserId(rs.getInt(count++));
				user.setLoginId(rs.getInt(count++));
				user.setUserName(rs.getString(count++));
				user.setGender(rs.getString(count++));
				user.setBirthday(rs.getDate(count++));
				user.setCardId(rs.getString(count++));
				user.setRemark(rs.getString(count++));
				
				return user;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			MyConnection.closeResultSetAndStatementAndConnection(rs, ps, conn);
		}		
	}	
	
	public UserBean select(int userId) throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = " SELECT * FROM Users " + 
						" WHERE UserId = ? ";
			conn = MyConnection.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			
			rs = ps.executeQuery();			
			if (rs.next()) {
				UserBean user = new UserBean();
				int count = 1;
				user.setUserId(rs.getInt(count++));
				user.setLoginId(rs.getInt(count++));
				user.setUserName(rs.getString(count++));
				user.setGender(rs.getString(count++));
				user.setBirthday(rs.getDate(count++));
				user.setCardId(rs.getString(count++));
				user.setRemark(rs.getString(count++));
				
				//Íâ¼ü´¦Àí
				
				return user;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			MyConnection.closeResultSetAndStatementAndConnection(rs, ps, conn);
		}		
	}	
}
