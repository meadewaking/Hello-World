package cn.sycu.meade.dao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
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
			
			FileOutputStream fos = null; // 实例文件输出流
			OutputStreamWriter osw = null;
			try {
				fos = new FileOutputStream("D:/Program Files (x86)/Apache Software Foundation/Tomcat 8.0/webapps/YZY/file/modifyPasswordLogs.csv"); // 打开保存文件
				osw = new OutputStreamWriter(fos,"GB2312");
				osw.write("密码修改日志编号,修改时间,登录IP\r\n");
				for (int i = 0; i < modifyPasswordLogs.size(); i++) {
					osw.write(String.valueOf(modifyPasswordLogs.get(i).getLoginId()));
					osw.write(",");
					osw.write(modifyPasswordLogs.get(i).getModifyDateTime().toString().length() > 0 ? modifyPasswordLogs.get(i).getModifyDateTime() + "," : "NULL,");
					osw.write(modifyPasswordLogs.get(i).getModifyIp().length() > 0 ? modifyPasswordLogs.get(i).getModifyIp() + "\r\n" : "NULL\r\n");
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
			
			return modifyPasswordLogs;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			MyConnection.closeResultSetAndStatementAndConnection(rs, ps, conn);
		}
	}
}
