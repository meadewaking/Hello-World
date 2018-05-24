package cn.sycu.meade.dao;

import java.sql.*;

import cn.sycu.meade.common.*;
import cn.sycu.meade.entity.*;

public class LoginDao {

	// 添加一条
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

			return ps.executeUpdate();// 返回值类型
		} catch (SQLException e) {// 异常的显式处理方式,使用try...catch...块
			e.printStackTrace();// 异常处理
			// throw e;
		} finally {
			MyConnection.closeStatementAndConnection(ps, conn);// 调用状态
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
			return ps.executeUpdate();// 返回值类型
		} catch (SQLException e) {// 异常的显式处理方式,使用try...catch...块
			e.printStackTrace();// 异常处理
			// throw e;
		} finally {
			MyConnection.closeStatementAndConnection(ps, conn);// 调用状态
		}
		return 0;
	}

	public LoginBean select(int loginId) throws SQLException {// 抛出异常
		Connection conn = null;// 定义变量
		PreparedStatement ps = null;// 定义变量
		ResultSet rs = null;
		try {// try块

			String sql = " SELECT * FROM Logins " + " WHERE LoginId = ? ";// 字段设置，sql语句
			conn = MyConnection.getConnection();// 与数据库连接，调用一个类
			ps = conn.prepareStatement(sql);
			ps.setInt(1, loginId);// 序号

			rs = ps.executeQuery();

			if (rs.next()) {
				LoginBean login = new LoginBean();
				int count = 1;// 计数器
				login.setLoginId(rs.getInt(count++));// 主键
				login.setLoginName(rs.getString(count++));// 编号为第几个字段，字段不同，值不同
				login.setPassword(rs.getString(count++));// rs.get根据不同数据类型取值
				login.setEmail(rs.getString(count++));// rs只读，不能给rs赋值，只进，只缓存一条数据
				login.setNickname(rs.getString(count++));// 结果集只读，只进，只缓存一条
				login.setLoginTime(rs.getInt(count++));
				login.setGrade(rs.getInt(count++));
				login.setRegisterDateTime(rs.getTimestamp(count++));
				login.setRegisterIp(rs.getString(count++));
				login.setLastLoginDateTime(rs.getTimestamp(count++));
				login.setLastLoginIp(rs.getString(count++));
				login.setRemark(rs.getString(count++));
				login.setStateId(rs.getInt(count++));
				login.setTypeId(rs.getInt(count++));

				int departmentId = rs.getInt(count++);// 外键
				login.setDepartmentId(rs.wasNull() ? 0 : departmentId);

				return login;
			}
		} catch (SQLException e) {// 异常的显式处理方式,使用try...catch...块
			e.printStackTrace();// 异常处理
			throw e;// 抛出异常
		} finally {
			MyConnection.closeStatement(ps);// 调用状态
			MyConnection.closeConnection(conn);// 调用连接
		}

		return null;// 返回值类型
	}

	public LoginBean selectByLoginName(String loginName) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			

			String sql = " SELECT * FROM Logins "
					+ " WHERE LoginName = ? ";
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
}
