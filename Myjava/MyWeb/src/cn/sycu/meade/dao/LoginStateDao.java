package cn.sycu.meade.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.sycu.meade.common.MyConnection;
import cn.sycu.meade.entity.LoginStateBean;
import cn.sycu.meade.entity.LoginStateSearcher;


public class LoginStateDao {
	MyConnection myConnection = new MyConnection();
	// ���һ��
	public int insert(LoginStateBean loginState) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String strSql = " INSERT INTO LoginStates " +
						" VALUES(null, ?, ?, ?) ";
		try {
			int count = 1;
			conn = myConnection.getConnection();
			ps = conn.prepareStatement(strSql);
			ps.setString(count++, loginState.getLoginStateName());
			ps.setBoolean(count++, loginState.getCanLogin());
			ps.setString(count++, loginState.getDescription());
			
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			myConnection.closeStatementAndConnection(ps, conn);
		}
	}
	// ��Ӷ���
	public int insert(ArrayList<LoginStateBean> loginStates) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String strSql = " INSERT INTO LoginStates " +
						" VALUES(null, ?, ?, ?) ";
		try {
			conn = myConnection.getConnection();
			conn.setAutoCommit(false);	// �ֶ��ύ
			int rowCount = 0;
			for (LoginStateBean loginState : loginStates) {
				int count = 1;
				ps = conn.prepareStatement(strSql);
				ps.setString(count++, loginState.getLoginStateName());
				ps.setBoolean(count++, loginState.getCanLogin());
				ps.setString(count++, loginState.getDescription());
				
				rowCount += ps.executeUpdate();
			}
			
			conn.commit();	// ��Ӷ������ύ
			return rowCount;
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();	// ��������쳣��ع�
			throw e;
		} finally {
			myConnection.closeStatementAndConnection(ps, conn);
		}
	}
	// ɾ��һ��
	public int delete(LoginStateBean loginState) throws SQLException {
		return delete(loginState.getLoginStateId());
	}
	// ɾ��һ����������ɾ��
	public int delete(int loginStateId) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String strSql = " DELETE FROM LoginStates " +
						" WHERE LoginStateId = ? ";
		try {
			int count = 1;
			conn = myConnection.getConnection();
			ps = conn.prepareStatement(strSql);
			ps.setInt(count++, loginStateId);
			
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			myConnection.closeStatementAndConnection(ps, conn);
		}
	}
	// ɾ��һ������Ψһ��ɾ��
	public int delete(String loginStateName) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String strSql = " DELETE FROM LoginStates " +
						" WHERE LoginStateName = ? ";
		try {
			int count = 1;
			conn = myConnection.getConnection();
			ps = conn.prepareStatement(strSql);
			ps.setString(count++, loginStateName);
			
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			myConnection.closeStatementAndConnection(ps, conn);
		}
	}
	// ɾ�������������ɾ��
	// ...
	// ɾ������
	public int delete(ArrayList<LoginStateBean> loginStates) {
		return 0;
	}
	// ɾ������
	public int delete(int [] loginStateIds) {
		return 0;
	}
	// �޸�
	public int update(LoginStateBean loginState) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String strSql = " UPDATE LoginStates " +
						" SET LoginStateName = ?, CanLogin = ?, Description = ? " +
						" WHERE LoginStateId = ? ";
		try {
			int count = 1;
			conn = myConnection.getConnection();
			ps = conn.prepareStatement(strSql);
			ps.setString(count++, loginState.getLoginStateName());
			ps.setBoolean(count++, loginState.getCanLogin());
			ps.setString(count++, loginState.getDescription());
			ps.setInt(count++, loginState.getLoginStateId());
			
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			myConnection.closeStatementAndConnection(ps, conn);
		}
	}
	// �޸ģ����޸�����
	public int update(LoginStateBean loginState, int loginStateId) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String strSql = " UPDATE LoginStates " +
						" SET LoginStateId = ?, LoginStateName = ?, CanLogin = ?, Description = ? " +
						" WHERE LoginStateId = ? ";
		try {
			int count = 1;
			conn = myConnection.getConnection();
			ps = conn.prepareStatement(strSql);
			ps.setInt(count++, loginState.getLoginStateId());
			ps.setString(count++, loginState.getLoginStateName());
			ps.setBoolean(count++, loginState.getCanLogin());
			ps.setString(count++, loginState.getDescription());
			ps.setInt(count++, loginStateId);
			
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			myConnection.closeStatementAndConnection(ps, conn);
		}
	}

	// ����һ����������
	public LoginStateBean select(int loginStateId) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String strSql = " SELECT * FROM LoginStates WHERE LoginStateId = ? ";
		
		try {
			conn = myConnection.getConnection();
			ps = conn.prepareStatement(strSql);
			ps.setInt(1, loginStateId);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				return getLoginState(rs);
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			myConnection.closeStatementAndConnection(ps, conn);
		}
	}

	// ���Ҷ�������ȫ��
	public ArrayList<LoginStateBean> select() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String strSql = " SELECT * FROM LoginStates ";
		ArrayList<LoginStateBean> loginStates = new ArrayList<LoginStateBean>();
		
		try {
			conn = myConnection.getConnection();
			ps = conn.prepareStatement(strSql);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				loginStates.add(getLoginState(rs));
			}
			return loginStates;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			myConnection.closeStatementAndConnection(ps, conn);
		}
	}
	
	public List<LoginStateBean> select(String sortString) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		if (sortString == null || sortString.length() == 0)
			sortString = " LoginStateId ASC ";
		try {
			String sql = " SELECT * FROM LoginStates " + " ORDER BY " + sortString;
			conn = MyConnection.getConnection();
			ps = conn.prepareStatement(sql);
			ArrayList<LoginStateBean> loginStates = new ArrayList<LoginStateBean>();
			rs = ps.executeQuery();
			while (rs.next()) {
				int count = 1;
				LoginStateBean loginState = new LoginStateBean();
				loginState.setLoginStateId(rs.getInt(count++));
				loginState.setLoginStateName(rs.getString(count++));
				loginState.setCanLogin(rs.getBoolean(count++));
				loginState.setDescription(rs.getString(count++));

				loginStates.add(loginState);
			}
			return loginStates;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(ps, conn);
			MyConnection.closeResultSet(rs);
		}
	}
	
	// ���Ҷ��������������Ҳ�����
	public ArrayList<LoginStateBean> select(LoginStateSearcher searcher, String sortString) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		if (sortString.length() == 0)
			sortString = "LoginStateId";
		
		String strSql = " SELECT * FROM LoginStates " +
						" WHERE LoginStateName LIKE ? " +
						"	AND Description LIKE ? " +
						" ORDER BY " + sortString;
		ArrayList<LoginStateBean> loginStates = new ArrayList<LoginStateBean>();
		
		try {
			conn = myConnection.getConnection();
			ps = conn.prepareStatement(strSql);
			int count = 1;
			ps.setString(count++, "%" + searcher.getLoginStateName() + "%");
			ps.setString(count++, "%" + searcher.getDescription() + "%");
			
			rs = ps.executeQuery();
			while (rs.next()) {
				loginStates.add(getLoginState(rs));
			}
			return loginStates;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			myConnection.closeStatementAndConnection(ps, conn);
		}
	}

	LoginStateBean getLoginState(ResultSet rs) throws SQLException {
		return getLoginState(rs, 1);
	}
	LoginStateBean getLoginState(ResultSet rs, int count) throws SQLException {
		LoginStateBean loginState = new LoginStateBean();
		loginState.setLoginStateId(rs.getInt(count++));
		loginState.setLoginStateName(rs.getString(count++));
		loginState.setCanLogin(rs.getBoolean(count++));
		loginState.setDescription(rs.getString(count++));
		return loginState;
	}
}

