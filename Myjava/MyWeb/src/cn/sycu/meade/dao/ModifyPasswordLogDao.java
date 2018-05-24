package cn.sycu.meade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.sycu.meade.common.MyConnection;
import cn.sycu.meade.entity.ModifyPasswordLogBean;
import cn.sycu.meade.entity.ModifyPasswordLogSearcher;

public class ModifyPasswordLogDao {
	
	public int insert(ModifyPasswordLogBean modifyPasswordLog) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String sql = " INSERT INTO ModifyPasswordLogs " + 
						" VALUES (null, ?, ?, ?) ";
			int count = 1;
			conn = MyConnection.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setTimestamp(count++, modifyPasswordLog.getModifyDateTime());
			ps.setString(count++, modifyPasswordLog.getModifyIp());
			ps.setInt(count++, modifyPasswordLog.getLoginId());
			
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(ps, conn);
		}		
	  }
	
	public int delete(int modifyPasswordLogId) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String sql = " DELETE FROM ModifyPasswordLogs " + 
						" WHERE ModifyPasswordLogId = ? ";
			int count = 1;
			conn = MyConnection.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(count++, modifyPasswordLogId);
			
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(ps, conn);
		}		
	  }
	
	public int update(ModifyPasswordLogBean modifyPasswordLog) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String sql = " UPDATE ModifyPasswordLogs " + 
						" SET ModifyDateTime = ?, ModifyIp = ?, LoginId = ? " + 
					    " WHERE ModifyPasswordLogId = ? ";
			int count = 1;
			conn = MyConnection.getConnection();
			ps = conn.prepareStatement(sql);

			ps.setTimestamp(count++, modifyPasswordLog.getModifyDateTime());
			ps.setString(count++, modifyPasswordLog.getModifyIp());
			ps.setInt(count++, modifyPasswordLog.getLoginId());
			ps.setInt(count++, modifyPasswordLog.getModifyPasswordLogId());
			
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(ps, conn);
		}		
	}
	
	public List<ModifyPasswordLogBean> select() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ModifyPasswordLogBean> modifyPasswordLogs = new ArrayList<ModifyPasswordLogBean>();
		
		try {
			String sql = " SELECT * FROM ModifyPasswordLogs ";
			
			conn = MyConnection.getConnection();
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();			
			while (rs.next()) {
				ModifyPasswordLogBean modifyPasswordLog = new ModifyPasswordLogBean();
				int count = 1;	
				modifyPasswordLog.setModifyPasswordLogId(rs.getInt(count++));
				modifyPasswordLog.setModifyDateTime(rs.getTimestamp(count++));
				modifyPasswordLog.setModifyIp(rs.getString(count++));
				modifyPasswordLog.setLoginId(rs.getInt(count++));

				modifyPasswordLog.setLogin(new LoginDao().select(modifyPasswordLog.getLoginId()));

				modifyPasswordLogs.add(modifyPasswordLog);
			}
			return modifyPasswordLogs;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			MyConnection.closeResultSetAndStatementAndConnection(rs, ps, conn);
		}		
	}

	public ModifyPasswordLogBean select(int modifyPasswordLogId) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String sql = " SELECT * FROM ModifyPasswordLogs " + 
						" WHERE ModifyPasswordLogId = ? ";
			
			int count = 1;
			conn = MyConnection.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(count++, modifyPasswordLogId);
			
			rs = ps.executeQuery();			
			if (rs.next()) {
				ModifyPasswordLogBean modifyPasswordLog = new ModifyPasswordLogBean();
				count = 1;						
				modifyPasswordLog.setModifyPasswordLogId(rs.getInt(count++));
				modifyPasswordLog.setModifyDateTime(rs.getTimestamp(count++));
				modifyPasswordLog.setModifyIp(rs.getString(count++));
				modifyPasswordLog.setLoginId(rs.getInt(count++));
				
				//获得外键对象
				modifyPasswordLog.setLogin(new LoginDao().select(modifyPasswordLog.getLoginId()));
				
				return modifyPasswordLog;
			}
			else 
				return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			MyConnection.closeResultSetAndStatementAndConnection(rs, ps, conn);
		}		
	}

	public List<ModifyPasswordLogBean> select(String sortString) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ModifyPasswordLogBean> modifyPasswordLogs = new ArrayList<ModifyPasswordLogBean>();
		
		if (sortString == null || sortString.length() == 0)
			sortString = " ModifyPasswordLogId ASC ";
		
		try {
			String sql = " SELECT * FROM ModifyPasswordLogs " + 
					" ORDER BY " + sortString;
		
			conn = MyConnection.getConnection();
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();			
			while (rs.next()) {
				ModifyPasswordLogBean modifyPasswordLog = new ModifyPasswordLogBean();
				int count = 1;						
				modifyPasswordLog.setModifyPasswordLogId(rs.getInt(count++));
				modifyPasswordLog.setModifyDateTime(rs.getTimestamp(count++));
				modifyPasswordLog.setModifyIp(rs.getString(count++));
				modifyPasswordLog.setLoginId(rs.getInt(count++));
				//获取外键对象
				modifyPasswordLog.setLogin(new LoginDao().select(modifyPasswordLog.getLoginId()));

				modifyPasswordLogs.add(modifyPasswordLog);
			}
			return modifyPasswordLogs;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			MyConnection.closeResultSetAndStatementAndConnection(rs, ps, conn);
		}	
	}

	public List<ModifyPasswordLogBean> select(ModifyPasswordLogSearcher searcher,String sortString) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ModifyPasswordLogBean> modifyPasswordLogs = new ArrayList<ModifyPasswordLogBean>();
		
		if (sortString == null || sortString.length() == 0)
			sortString = " ModifyPasswordLogId ASC ";
		
		try {
			String sql = " SELECT ModifyPasswordLogs.*, Logins.* " + 
					" FROM ModifyPasswordLogs INNER JOIN  Logins " +
					"		ON ModifyPasswordLogs.LoginId =  Logins.LoginId " +
					" WHERE ModifyIp LIKE ? " +
					"   AND ModifyDateTime BETWEEN ? AND ? " +
					"   AND Logins.LoginName LIKE ? " +
					" ORDER BY " + sortString;
		
			int count = 1;
			conn = MyConnection.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(count++ , "%" + searcher.getModifyIp() + "%");
			ps.setTimestamp(count++ , searcher.getModifyDateTimeBegin());
			ps.setTimestamp(count++ , searcher.getModifyDateTimeEnd());
			ps.setString(count++ , "%" + searcher.getLogin().getLoginName() + "%");
						
			rs = ps.executeQuery();			
			while (rs.next()) {
				count = 1;
				ModifyPasswordLogBean modifyPasswordLog = new ModifyPasswordLogBean();
				modifyPasswordLog.setModifyPasswordLogId(rs.getInt(count++));
				modifyPasswordLog.setModifyDateTime(rs.getTimestamp(count++));
				modifyPasswordLog.setOldPassword(rs.getString(count++));
				modifyPasswordLog.setModifyIp(rs.getString(count++));
				modifyPasswordLog.setLoginId(rs.getInt(count++));
			
				modifyPasswordLog.setLogin(new LoginDao().select(modifyPasswordLog.getLoginId()));

				modifyPasswordLogs.add(modifyPasswordLog);
			}
			return modifyPasswordLogs;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			MyConnection.closeResultSetAndStatementAndConnection(rs, ps, conn);
		}
	}
}
