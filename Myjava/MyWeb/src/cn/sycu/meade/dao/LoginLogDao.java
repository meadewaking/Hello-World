package cn.sycu.meade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cn.sycu.meade.common.MyConnection;
import cn.sycu.meade.entity.LoginLogBean;
import cn.sycu.meade.entity.LoginLogSearcher;



public class LoginLogDao {
	MyConnection myConnection = new MyConnection();
	// 添加一条
	public int insert(LoginLogBean loginLog) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String strSql = " INSERT INTO LoginLogs " +
						" VALUES(null, ?, ?, ?) ";
		try {
			int count = 1;
			conn = myConnection.getConnection();
			ps = conn.prepareStatement(strSql);
			ps.setTimestamp(count++, loginLog.getLoginDateTime());
			ps.setString(count++, loginLog.getLoginIp());
			ps.setInt(count++, loginLog.getLoginId());
			
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			myConnection.closeStatementAndConnection(ps, conn);
		}
	}
	// 添加多条
	public int insert(ArrayList<LoginLogBean> loginLogs) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String strSql = " INSERT INTO LoginLogs " +
						" VALUES(null, ?, ?, ?) ";
		try {
			conn = myConnection.getConnection();
			conn.setAutoCommit(false);	// 手动提交
			int rowCount = 0;
			for (LoginLogBean loginLog : loginLogs) {
				int count = 1;
				ps = conn.prepareStatement(strSql);
				ps.setTimestamp(count++, loginLog.getLoginDateTime());
				ps.setString(count++, loginLog.getLoginIp());
				ps.setInt(count++, loginLog.getLoginId());
				
				rowCount += ps.executeUpdate();
			}
			
			conn.commit();	// 添加多条后提交
			return rowCount;
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();	// 如果发生异常则回滚
			throw e;
		} finally {
			myConnection.closeStatementAndConnection(ps, conn);
		}
	}
	// 删除一条
	public int delete(LoginLogBean loginLog) throws SQLException {
		return delete(loginLog.getLoginLogId());
	}
	// 删除一条，按主键删除
	public int delete(int loginLogId) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String strSql = " DELETE FROM LoginLogs " +
						" WHERE LoginLogId = ? ";
		try {
			int count = 1;
			conn = myConnection.getConnection();
			ps = conn.prepareStatement(strSql);
			ps.setInt(count++, loginLogId);
			
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			myConnection.closeStatementAndConnection(ps, conn);
		}
	}
	// 删除一条，按唯一键删除
	public int delete(String loginLogName) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String strSql = " DELETE FROM LoginLogs " +
						" WHERE LoginDateTime = ? ";
		try {
			int count = 1;
			conn = myConnection.getConnection();
			ps = conn.prepareStatement(strSql);
			ps.setString(count++, loginLogName);
			
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			myConnection.closeStatementAndConnection(ps, conn);
		}
	}

	// 修改
	public int update(LoginLogBean loginLog) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String strSql = " UPDATE LoginLogs " +
						" SET LoginDateTime = ?, LoginIp = ?, LoginId = ? " +
						" WHERE LoginLogId = ? ";
		try {
			int count = 1;
			conn = myConnection.getConnection();
			ps = conn.prepareStatement(strSql);
			ps.setTimestamp(count++, loginLog.getLoginDateTime());
			ps.setString(count++, loginLog.getLoginIp());
			ps.setInt(count++, loginLog.getLoginId());
			ps.setInt(count++, loginLog.getLoginLogId());
			
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			myConnection.closeStatementAndConnection(ps, conn);
		}
	}
	// 修改，可修改主键
	public int update(LoginLogBean loginLog, int loginLogId) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String strSql = " UPDATE LoginLogs " +
						" SET LoginLogId = ?, LoginDateTime = ?, LoginIp = ?, LoginId = ? " +
						" WHERE LoginLogId = ? ";
		try {
			int count = 1;
			conn = myConnection.getConnection();
			ps = conn.prepareStatement(strSql);
			ps.setInt(count++, loginLog.getLoginLogId());
			ps.setTimestamp(count++, loginLog.getLoginDateTime());
			ps.setString(count++, loginLog.getLoginIp());
			ps.setInt(count++, loginLog.getLoginId());
			ps.setInt(count++, loginLogId);
			
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			myConnection.closeStatementAndConnection(ps, conn);
		}
	}

	// 查找一条，按主键
	public LoginLogBean select(int loginLogId) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = " SELECT * FROM LoginLogs " + 
				" WHERE LoginLogId = ? ";
		
		try {
			conn = myConnection.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, loginLogId);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				LoginLogBean loginLog = new LoginLogBean();
				int count = 1;
				loginLog.setLoginLogId(rs.getInt(count++));
				loginLog.setLoginDateTime(rs.getTimestamp(count++));
				loginLog.setLoginIp(rs.getString(count++));
				loginLog.setLoginId(rs.getInt(count++));
				
				loginLog.setLogin(new LoginDao().select(loginLog.getLoginId()));
				return loginLog;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			myConnection.closeStatementAndConnection(ps, conn);
		}
	}
	// 查找一条，按唯一键
	public ArrayList<LoginLogBean> select(String sortString) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<LoginLogBean> loginLogs = new ArrayList<LoginLogBean>();
		
		if (sortString == null || sortString.length() == 0)
			sortString = " LoginLogId ASC ";
		
		try {
			String sql = " SELECT * FROM LoginLogs " + 
					" ORDER BY " + sortString;

			conn = MyConnection.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				LoginLogBean loginLog = new LoginLogBean();
				int count = 1;
				loginLog.setLoginLogId(rs.getInt(count++));
				loginLog.setLoginDateTime(rs.getTimestamp(count++));
				loginLog.setLoginIp(rs.getString(count++));
				loginLog.setLoginId(rs.getInt(count++));

	
				loginLog.setLogin(new LoginDao().select(loginLog.getLoginId()));
				
				loginLogs.add(loginLog);
			}
			return loginLogs;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			MyConnection.closeResultSetAndStatementAndConnection(rs, ps, conn);
		}
	}
	// 查找多条，查全部
	public ArrayList<LoginLogBean> select() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String strSql = " SELECT * FROM LoginLogs ";
		
		ArrayList<LoginLogBean> loginLogs = new ArrayList<LoginLogBean>();
		
		try {
			conn = myConnection.getConnection();
			ps = conn.prepareStatement(strSql);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				LoginLogBean loginLog = new LoginLogBean();
				int count = 1;
				loginLog.setLoginLogId(rs.getInt(count++));
				loginLog.setLoginDateTime(rs.getTimestamp(count++));
				loginLog.setLoginIp(rs.getString(count++));
				loginLog.setLoginId(rs.getInt(count++));
				
				
				loginLog.setLogin(new LoginDao().select(loginLog.getLoginId()));
				
				loginLogs.add(loginLog);
			}
			return loginLogs;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			myConnection.closeStatementAndConnection(ps, conn);
		}
	}
	// 查找多条，按条件查找并排序
	public ArrayList<LoginLogBean> select(LoginLogSearcher searcher, String sortString) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<LoginLogBean> loginLogs = new ArrayList<LoginLogBean>();
		if (sortString == null || sortString.length() == 0)
			sortString = "LoginLogId ASC";
		
		String strSql = " SELECT LoginLogs.*, Logins.* " + 
				" FROM LoginLogs INNER JOIN Logins " + 
				" 		ON LoginLogs.LoginId = Logins.LoginId " + 
				" WHERE LoginIp LIKE ? " + 
				"   AND LoginDateTime BETWEEN ? AND ? " + 
				"   AND Logins.LoginName LIKE ? " + 
				" ORDER BY " + sortString;
		
		try {
			int count = 1;
			conn = MyConnection.getConnection();
			ps = conn.prepareStatement(strSql);
			ps.setString(count++ , "%" + searcher.getLoginIp() + "%");
			ps.setTimestamp(count++ , searcher.getLoginDateTimeBegin());
			ps.setTimestamp(count++ , searcher.getLoginDateTimeEnd());
			ps.setString(count++ , "%" + searcher.getLogin().getLoginName() + "%");
			
			rs = ps.executeQuery();
			while (rs.next()) {
				count = 1;
				LoginLogBean loginLog = new LoginLogBean();
				loginLog.setLoginLogId(rs.getInt(count++));
				loginLog.setLoginDateTime(rs.getTimestamp(count++));
				loginLog.setLoginIp(rs.getString(count++));
				loginLog.setLoginId(rs.getInt(count++));
				loginLog.setLogin(new LoginDao().select(loginLog.getLoginId()));
				loginLogs.add(loginLog);
			}
			return loginLogs;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			myConnection.closeStatementAndConnection(ps, conn);
		}
	}
	
}
