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
import cn.sycu.meade.entity.LoginTypeBean;
import cn.sycu.meade.entity.LoginTypeSearcher;

public class LoginTypeDao {
	MyConnection myConnection = new MyConnection();

	public int insert(LoginTypeBean loginType) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String strSql = " INSERT INTO LoginTypes " + " VALUES(null, ?, ?, ?) ";
		try {
			int count = 1;
			conn = myConnection.getConnection();
			ps = conn.prepareStatement(strSql);
			ps.setString(count++, loginType.getLoginTypeName());
			ps.setString(count++, loginType.getDefaultPage());
			ps.setString(count++, loginType.getDescription());

			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			myConnection.closeStatementAndConnection(ps, conn);
		}
	}

	public int insert(ArrayList<LoginTypeBean> loginTypes) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String strSql = " INSERT INTO LoginTypes " + " VALUES(null, ?, ?, ?) ";
		try {
			conn = myConnection.getConnection();
			conn.setAutoCommit(false); 
			int rowCount = 0;
			for (LoginTypeBean loginType : loginTypes) {
				int count = 1;
				ps = conn.prepareStatement(strSql);
				ps.setString(count++, loginType.getLoginTypeName());
				ps.setString(count++, loginType.getDefaultPage());
				ps.setString(count++, loginType.getDescription());

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

	public int delete(LoginTypeBean loginType) throws SQLException {
		return delete(loginType.getLoginTypeId());
	}

	public int delete(int loginTypeId) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String strSql = " DELETE FROM LoginTypes " + " WHERE LoginTypeId = ? ";
		try {
			int count = 1;
			conn = myConnection.getConnection();
			ps = conn.prepareStatement(strSql);
			ps.setInt(count++, loginTypeId);

			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			myConnection.closeStatementAndConnection(ps, conn);
		}
	}

	public int delete(String loginTypeName) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String strSql = " DELETE FROM LoginTypes " + " WHERE LoginTypeName = ? ";
		try {
			int count = 1;
			conn = myConnection.getConnection();
			ps = conn.prepareStatement(strSql);
			ps.setString(count++, loginTypeName);

			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			myConnection.closeStatementAndConnection(ps, conn);
		}
	}

	public int update(LoginTypeBean loginType) throws SQLException {
		return update(loginType, loginType.getLoginTypeId());
	}

	public int update(LoginTypeBean loginType, int loginTypeId) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String strSql = " UPDATE LoginTypes "
				+ " SET LoginTypeId = ?, LoginTypeName = ?, DefaultPage = ?, Description = ? "
				+ " WHERE LoginTypeId = ? ";
		try {
			int count = 1;
			conn = myConnection.getConnection();
			ps = conn.prepareStatement(strSql);
			ps.setInt(count++, loginType.getLoginTypeId());
			ps.setString(count++, loginType.getLoginTypeName());
			ps.setString(count++, loginType.getDefaultPage());
			ps.setString(count++, loginType.getDescription());
			ps.setInt(count++, loginTypeId);

			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			myConnection.closeStatementAndConnection(ps, conn);
		}
	}

	public List<LoginTypeBean> select() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<LoginTypeBean> loginTypes = new ArrayList<LoginTypeBean>();
		
		try {

			String sql = " SELECT * FROM LoginTypes ";

			conn = MyConnection.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				LoginTypeBean loginType = new LoginTypeBean();
				int count = 1;

				loginType.setLoginTypeId(rs.getInt(count++));
				loginType.setLoginTypeName(rs.getString(count++));
				loginType.setDefaultPage(rs.getString(count++));
				loginType.setDescription(rs.getString(count++));
				
				loginTypes.add(loginType);
			}
			
			FileOutputStream fos = null; // 实例文件输出流
			OutputStreamWriter osw = null;
			try {
				fos = new FileOutputStream("D:/Program Files (x86)/Apache Software Foundation/Tomcat 8.0/webapps/YZY/file/loginTypes.csv"); // 打开保存文件
				osw = new OutputStreamWriter(fos,"GB2312");
				osw.write("登录类型编号,登录类型名称,所在地址,描述\r\n");
				for (int i = 0; i < loginTypes.size(); i++) {
					osw.write(String.valueOf(loginTypes.get(i).getLoginTypeId()));
					osw.write(",");
					osw.write(loginTypes.get(i).getLoginTypeName().length() > 0 ? loginTypes.get(i).getLoginTypeName() + "," : "NULL,");
					osw.write(loginTypes.get(i).getDefaultPage().length() > 0 ? loginTypes.get(i).getDefaultPage() + "," : "NULL,");
					osw.write(loginTypes.get(i).getDescription().length() > 0 ? loginTypes.get(i).getDescription() + "\r\n" : "NULL\r\n");
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
			
			return loginTypes;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			MyConnection.closeResultSetAndStatementAndConnection(rs, ps, conn);
		}
	}
	
	public LoginTypeBean select(int loginTypeId) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String strSql = " SELECT * FROM LoginTypes WHERE LoginTypeId = ? ";

		try {
			conn = myConnection.getConnection();
			ps = conn.prepareStatement(strSql);
			ps.setInt(1, loginTypeId);

			rs = ps.executeQuery();
			if (rs.next()) {
				int count = 1;
				LoginTypeBean LoginType = new LoginTypeBean();
				LoginType.setLoginTypeId(rs.getInt(count++));
				LoginType.setLoginTypeName(rs.getString(count++));
				LoginType.setDefaultPage(rs.getString(count++));
				LoginType.setDescription(rs.getString(count++));

				return LoginType;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			myConnection.closeStatementAndConnection(ps, conn);
		}
	}

	public List<LoginTypeBean> select(String sortString) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<LoginTypeBean> LoginTypes = new ArrayList<LoginTypeBean>();

		if (sortString == null || sortString.length() == 0)
			sortString = " LoginTypeId ASC ";
		try {
			String sql = " SELECT * FROM LoginTypes " + " ORDER BY " + sortString;
			conn = MyConnection.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				int count = 1;
				LoginTypeBean LoginType = new LoginTypeBean();
				LoginType.setLoginTypeId(rs.getInt(count++));
				LoginType.setLoginTypeName(rs.getString(count++));
				LoginType.setDefaultPage(rs.getString(count++));
				LoginType.setDescription(rs.getString(count++));

				LoginTypes.add(LoginType);
			}
			return LoginTypes;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(ps, conn);
			MyConnection.closeResultSet(rs);
		}
	}

	public List<LoginTypeBean> select(LoginTypeSearcher searcher, String sortString) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<LoginTypeBean> LoginTypes = new ArrayList<LoginTypeBean>();

		if (sortString == null || sortString.length() == 0)
			sortString = " LoginTypeId ASC ";
		try {
			String sql = " SELECT * FROM LoginTypes " + " WHERE LoginTypeName LIKE ? " + " AND DefaultPage LIKE ? "
					+ " AND Description LIKE ? " + " ORDER BY " + sortString;

			conn = MyConnection.getConnection();
			ps = conn.prepareStatement(sql);

			int count = 1;
			ps.setString(count++, "%" + searcher.getLoginTypeName() + "%");
			ps.setString(count++, "%" + searcher.getDefaultPage() + "%");
			ps.setString(count++, "%" + searcher.getDescription() + "%");

			rs = ps.executeQuery();
			while (rs.next()) {
				count = 1;
				LoginTypeBean LoginType = new LoginTypeBean();
				LoginType.setLoginTypeId(rs.getInt(count++));
				LoginType.setLoginTypeName(rs.getString(count++));
				LoginType.setDefaultPage(rs.getString(count++));
				LoginType.setDescription(rs.getString(count++));

				LoginTypes.add(LoginType);
			}
			return LoginTypes;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(ps, conn);
			MyConnection.closeResultSet(rs);
		}
	}


}
