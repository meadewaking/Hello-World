package cn.sycu.meade.dao;

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

	// 添加一条
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

	// 添加多条
	public int insert(ArrayList<LoginTypeBean> loginTypes) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String strSql = " INSERT INTO LoginTypes " + " VALUES(null, ?, ?, ?) ";
		try {
			conn = myConnection.getConnection();
			conn.setAutoCommit(false); // 手动提交
			int rowCount = 0;
			for (LoginTypeBean loginType : loginTypes) {
				int count = 1;
				ps = conn.prepareStatement(strSql);
				ps.setString(count++, loginType.getLoginTypeName());
				ps.setString(count++, loginType.getDefaultPage());
				ps.setString(count++, loginType.getDescription());

				rowCount += ps.executeUpdate();
			}

			conn.commit(); // 添加多条后提交
			return rowCount;
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback(); // 如果发生异常则回滚
			throw e;
		} finally {
			myConnection.closeStatementAndConnection(ps, conn);
		}
	}

	// 删除一条
	public int delete(LoginTypeBean loginType) throws SQLException {
		return delete(loginType.getLoginTypeId());
	}

	// 删除一条，按主键删除
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

	// 删除一条，按唯一键删除
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

	// 删除多条，按外键删除
	// ...
	// 删除多条
	public int delete(ArrayList<LoginTypeBean> loginTypes) {
		return 0;
	}

	// 删除多条
	public int delete(int[] loginTypeIds) {
		return 0;
	}

	// 修改
	public int update(LoginTypeBean loginType) throws SQLException {
		return update(loginType, loginType.getLoginTypeId());
	}

	// 修改，可修改主键
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
			return loginTypes;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			MyConnection.closeResultSetAndStatementAndConnection(rs, ps, conn);
		}
	}
	
	// 查找一条，按主键
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
	// 查找一条，按唯一键

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

	// 查找多条，查全部

}
