package cn.sycu.meade.dao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
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
	// 娣诲姞澶氭潯
	public int insert(ArrayList<LoginLogBean> loginLogs) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String strSql = " INSERT INTO LoginLogs " +
						" VALUES(null, ?, ?, ?) ";
		try {
			conn = myConnection.getConnection();
			conn.setAutoCommit(false);	
			int rowCount = 0;
			for (LoginLogBean loginLog : loginLogs) {
				int count = 1;
				ps = conn.prepareStatement(strSql);
				ps.setTimestamp(count++, loginLog.getLoginDateTime());
				ps.setString(count++, loginLog.getLoginIp());
				ps.setInt(count++, loginLog.getLoginId());
				
				rowCount += ps.executeUpdate();
			}
			
			conn.commit();	
			return rowCount;
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();	
			throw e;
		} finally {
			myConnection.closeStatementAndConnection(ps, conn);
		}
	}
	
	public int delete(LoginLogBean loginLog) throws SQLException {
		return delete(loginLog.getLoginLogId());
	}
	
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
			
			FileOutputStream fos = null; // 实例文件输出流
			OutputStreamWriter osw = null;
			try {
				fos = new FileOutputStream("D:/Program Files (x86)/Apache Software Foundation/Tomcat 8.0/webapps/YZY/file/loginLogs.csv"); // 打开保存文件
				osw = new OutputStreamWriter(fos,"GB2312");
				osw.write("登录日志编号,登录时间,登录IP,登录名\r\n");
				for (int i = 0; i < loginLogs.size(); i++) {
					osw.write(String.valueOf(loginLogs.get(i).getLoginLogId()));
					osw.write(",");
					osw.write(loginLogs.get(i).getLoginDateTime().toString().length() > 0 ? loginLogs.get(i).getLoginDateTime().toString() + "," : "NULL,");
					osw.write(loginLogs.get(i).getLoginIp().length() > 0 ? loginLogs.get(i).getLoginIp() + "," : "NULL,");
					osw.write(loginLogs.get(i).getLogin().getLoginName().length() > 0 ? loginLogs.get(i).getLogin().getLoginName() + "\r\n" : "NULL\r\n");
				}
			} catch (IOException e) { // 处理输入输出异常
				// TODO: handle exception
				e.printStackTrace(); // 打印异常
			} finally { // 最终处理(finally关键字一定会被执行)
				try {
					if (osw != null) {
						osw.close();
					}
				} catch (IOException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				try { // 关闭两个流
					if (fos != null) {
						fos.close();
					}
				} catch (IOException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
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
