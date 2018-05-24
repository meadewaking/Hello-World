package cn.sycu.meade.dao;

import java.sql.*;

import cn.sycu.meade.common.*;
import cn.sycu.meade.entity.*;

public class LoginDao {

	public int insert(LoginBean login) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = " INSERT INTO Logins " + " VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		try {
			int count = 1;
			conn = MyConnection.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(count++, login.getLoginName());
			ps.setString(count++, login.getPassword());
			ps.setString(count++, login.getEmail());
			ps.setString(count++, login.getNickname());
			ps.setInt(count++, login.getLoginTime());
			ps.setInt(count++, login.getGrade());
			ps.setTimestamp(count++, login.getRegisterDateTime());
			ps.setString(count++, login.getRegisterIp());
			ps.setTimestamp(count++, login.getLastLoginDateTime());
			ps.setString(count++, login.getLastLoginIp());
			ps.setString(count++, login.getRemark());
			ps.setInt(count++, login.getStateId());
			ps.setInt(count++, login.getTypeId());
			if (login.getDepartmentId() == 0)
				ps.setNull(count++, Types.INTEGER);
			else
				ps.setInt(count++, login.getDepartmentId());

			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			// throw e;
		} finally {
			MyConnection.closeStatementAndConnection(ps, conn);
		}
		return 0;
	}

	public int update(LoginBean login) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String str = " UPDATE Logins "
					+ " SET LoginName = ?, Password = ?, Email = ?, Nickname = ?, LoginTime = ?, "
					+ " Grade = ?, RegisterDateTime = ?, RegisterIp = ?, LastLoginDateTime = ?, "
					+ " LastLoginIp = ?, Remark = ?, StateId = ?, TypeId = ?, DepartmentId = ? "
					+ " WHERE LoginId = ? ";
			int count = 1;
			conn = MyConnection.getConnection();
			ps = conn.prepareStatement(str);
			ps.setString(count++, login.getLoginName());
			ps.setString(count++, login.getPassword());
			ps.setString(count++, login.getEmail());
			ps.setString(count++, login.getNickname());
			ps.setInt(count++, login.getLoginTime());
			ps.setInt(count++, login.getGrade());
			ps.setTimestamp(count++, login.getRegisterDateTime());
			ps.setString(count++, login.getRegisterIp());
			ps.setTimestamp(count++, login.getLastLoginDateTime());
			ps.setString(count++, login.getLastLoginIp());
			ps.setString(count++, login.getRemark());
			ps.setInt(count++, login.getStateId());
			ps.setInt(count++, login.getTypeId());
			if (login.getDepartmentId() == 0)
				ps.setNull(count++, Types.INTEGER);
			else
				ps.setInt(count++, login.getDepartmentId());
			ps.setInt(count++, login.getLoginId());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			// throw e;
		} finally {
			MyConnection.closeStatementAndConnection(ps, conn);
		}
		return 0;
	}

	public LoginBean select(int loginId) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			String sql = " SELECT * FROM Logins " + " WHERE LoginId = ? ";
			conn = MyConnection.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, loginId);

			rs = ps.executeQuery();

			if (rs.next()) {
				LoginBean login = new LoginBean();
				int count = 1;
				login.setLoginId(rs.getInt(count++));
				login.setLoginName(rs.getString(count++));
				login.setPassword(rs.getString(count++));
				login.setEmail(rs.getString(count++));
				login.setNickname(rs.getString(count++));
				login.setLoginTime(rs.getInt(count++));
				login.setGrade(rs.getInt(count++));
				login.setRegisterDateTime(rs.getTimestamp(count++));
				login.setRegisterIp(rs.getString(count++));
				login.setLastLoginDateTime(rs.getTimestamp(count++));
				login.setLastLoginIp(rs.getString(count++));
				login.setRemark(rs.getString(count++));
				login.setStateId(rs.getInt(count++));
				login.setTypeId(rs.getInt(count++));

				int departmentId = rs.getInt(count++);
				login.setDepartmentId(rs.wasNull() ? 0 : departmentId);

				return login;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			MyConnection.closeStatement(ps);
			MyConnection.closeConnection(conn);
		}

		return null;
	}

	public LoginBean selectByLoginName(String loginName) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			String sql = " SELECT * FROM Logins " + " WHERE LoginName = ? ";
			conn = MyConnection.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginName);

			rs = ps.executeQuery();

			if (rs.next()) {
				LoginBean login = new LoginBean();
				int count = 1;
				login.setLoginId(rs.getInt(count++));
				login.setLoginName(rs.getString(count++));
				login.setPassword(rs.getString(count++));
				login.setEmail(rs.getString(count++));
				login.setNickname(rs.getString(count++));
				login.setLoginTime(rs.getInt(count++));
				login.setGrade(rs.getInt(count++));
				login.setRegisterDateTime(rs.getTimestamp(count++));
				login.setRegisterIp(rs.getString(count++));
				login.setLastLoginDateTime(rs.getTimestamp(count++));
				login.setLastLoginIp(rs.getString(count++));
				login.setRemark(rs.getString(count++));
				login.setStateId(rs.getInt(count++));
				login.setTypeId(rs.getInt(count++));

				int departmentId = rs.getInt(count++);
				login.setDepartmentId(rs.wasNull() ? 0 : departmentId);

				return login;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			MyConnection.closeStatement(ps);
			MyConnection.closeConnection(conn);
		}

		return null;
	}

	public LoginBean selectByEmail(String Email) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			String sql = " SELECT * FROM Logins " + " WHERE Email = ? ";
			conn = MyConnection.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, Email);

			rs = ps.executeQuery();

			if (rs.next()) {
				LoginBean login = new LoginBean();
				int count = 1;
				login.setLoginId(rs.getInt(count++));
				login.setLoginName(rs.getString(count++));
				login.setPassword(rs.getString(count++));
				login.setEmail(rs.getString(count++));
				login.setNickname(rs.getString(count++));
				login.setLoginTime(rs.getInt(count++));
				login.setGrade(rs.getInt(count++));
				login.setRegisterDateTime(rs.getTimestamp(count++));
				login.setRegisterIp(rs.getString(count++));
				login.setLastLoginDateTime(rs.getTimestamp(count++));
				login.setLastLoginIp(rs.getString(count++));
				login.setRemark(rs.getString(count++));
				login.setStateId(rs.getInt(count++));
				login.setTypeId(rs.getInt(count++));

				int departmentId = rs.getInt(count++);
				login.setDepartmentId(rs.wasNull() ? 0 : departmentId);

				return login;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			MyConnection.closeStatement(ps);
			MyConnection.closeConnection(conn);
		}

		return null;
	}
}
