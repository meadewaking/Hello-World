package cn.sycu.meade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.sycu.meade.common.MyConnection;
import cn.sycu.meade.entity.DepartmentBean;
import cn.sycu.meade.entity.DepartmentSearcher;

public class DepartmentDao {
	MyConnection myConnection = new MyConnection();
	// 添加一条

	public int insert(DepartmentBean department) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String strSql = " INSERT INTO Departments " + " VALUES(null, ?, ?, ?) ";
		try {
			int count = 1;
			conn = MyConnection.getConnection();
			ps = conn.prepareStatement(strSql);
			ps.setString(count++, department.getDepartmentName());
			ps.setString(count++, department.getAddress());
			ps.setString(count++, department.getDescription());

			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(ps, conn);
		}
	}

	// 添加多条
	public int insert(ArrayList<DepartmentBean> departments) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String strSql = " INSERT INTO Departments " + " VALUES(null, ?, ?, ?) ";
		try {
			conn = myConnection.getConnection();
			conn.setAutoCommit(false); // 手动提交
			int rowCount = 0;
			for (DepartmentBean department : departments) {
				int count = 1;
				ps = conn.prepareStatement(strSql);
				ps.setString(count++, department.getDepartmentName());
				ps.setString(count++, department.getAddress());
				ps.setString(count++, department.getDescription());

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
	public int delete(DepartmentBean department) throws SQLException {
		return delete(department.getDepartmentId());
	}

	// 删除一条，按主键删除
	public int delete(int departmentId) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String strSql = " DELETE FROM Departments " + " WHERE DepartmentId = ? ";
		try {
			int count = 1;
			conn = myConnection.getConnection();
			ps = conn.prepareStatement(strSql);
			ps.setInt(count++, departmentId);

			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			myConnection.closeStatementAndConnection(ps, conn);
		}
	}

	// 删除一条，按唯一键删除
	public int delete(String departmentName) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String strSql = " DELETE FROM Departments " + " WHERE DepartmentName = ? ";
		try {
			int count = 1;
			conn = myConnection.getConnection();
			ps = conn.prepareStatement(strSql);
			ps.setString(count++, departmentName);

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
	public int delete(ArrayList<DepartmentBean> departments) {
		return 0;
	}

	// 删除多条
	public int delete(int[] departmentIds) {
		return 0;
	}

	// 修改
	public int update(DepartmentBean department) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String strSql = " UPDATE Departments " + " SET DepartmentName = ?, Address = ?, Description = ? "
				+ " WHERE DepartmentId = ? ";
		try {
			int count = 1;
			conn = myConnection.getConnection();
			ps = conn.prepareStatement(strSql);
			ps.setString(count++, department.getDepartmentName());
			ps.setString(count++, department.getAddress());
			ps.setString(count++, department.getDescription());
			ps.setInt(count++, department.getDepartmentId());

			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			myConnection.closeStatementAndConnection(ps, conn);
		}
	}

	/**
	 * 修改，可修改主键
	 * 
	 * @param department
	 *            可以包含新主键值
	 * @param departmentId
	 *            原主键值
	 * @return
	 * @throws SQLException
	 */
	public int update(DepartmentBean department, int departmentId) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String strSql = " UPDATE Departments "
				+ " SET DepartmentId = ?， DepartmentName = ?, Address = ?, Description = ? "
				+ " WHERE DepartmentId = ? ";
		try {
			int count = 1;
			conn = myConnection.getConnection();
			ps = conn.prepareStatement(strSql);
			ps.setInt(count++, department.getDepartmentId());
			ps.setString(count++, department.getDepartmentName());
			ps.setString(count++, department.getAddress());
			ps.setString(count++, department.getDescription());
			ps.setInt(count++, departmentId);

			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			myConnection.closeStatementAndConnection(ps, conn);
		}
	}

	// 查找一条，按主键
	public DepartmentBean select(int departmentId) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String strSql = " SELECT * FROM Departments WHERE DepartmentId = ? ";

		try {
			conn = myConnection.getConnection();
			ps = conn.prepareStatement(strSql);
			ps.setInt(1, departmentId);

			rs = ps.executeQuery();
			if (rs.next()) {
				int count = 1;
				DepartmentBean department = new DepartmentBean();
				department.setDepartmentId(rs.getInt(count++));
				department.setDepartmentName(rs.getString(count++));
				department.setAddress(rs.getString(count++));
				department.setDescription(rs.getString(count++));

				return department;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			myConnection.closeStatementAndConnection(ps, conn);
		}
	}

	public List<DepartmentBean> select(String sortString) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<DepartmentBean> departments = new ArrayList<DepartmentBean>();

		if (sortString == null || sortString.length() == 0)
			sortString = " DepartmentId ASC ";
		try {
			String sql = " SELECT * FROM Departments " + " ORDER BY " + sortString;
			conn = MyConnection.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				int count = 1;
				DepartmentBean department = new DepartmentBean();
				department.setDepartmentId(rs.getInt(count++));
				department.setDepartmentName(rs.getString(count++));
				department.setAddress(rs.getString(count++));
				department.setDescription(rs.getString(count++));

				departments.add(department);
			}
			return departments;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(ps, conn);
			MyConnection.closeResultSet(rs);
		}
	}

	public List<DepartmentBean> select(DepartmentSearcher searcher, String sortString) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<DepartmentBean> departments = new ArrayList<DepartmentBean>();

		if (sortString == null || sortString.length() == 0)
			sortString = " DepartmentId ASC ";
		try {
			String sql = " SELECT * FROM Departments " + " WHERE DepartmentName LIKE ? " + " AND Address LIKE ? "
					+ " AND Description LIKE ? " + " ORDER BY " + sortString;

			conn = MyConnection.getConnection();
			ps = conn.prepareStatement(sql);

			int count = 1;
			ps.setString(count++, "%" + searcher.getDepartmentName() + "%");
			ps.setString(count++, "%" + searcher.getAddress() + "%");
			ps.setString(count++, "%" + searcher.getDescription() + "%");

			rs = ps.executeQuery();
			while (rs.next()) {
				count = 1;
				DepartmentBean department = new DepartmentBean();
				department.setDepartmentId(rs.getInt(count++));
				department.setDepartmentName(rs.getString(count++));
				department.setAddress(rs.getString(count++));
				department.setDescription(rs.getString(count++));

				departments.add(department);
			}
			return departments;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(ps, conn);
			MyConnection.closeResultSet(rs);
		}
	}

	// 查找多条，查全部
	public ArrayList<DepartmentBean> select() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String strSql = " SELECT * FROM Departments ";
		ArrayList<DepartmentBean> departments = new ArrayList<DepartmentBean>();

		try {
			conn = myConnection.getConnection();
			ps = conn.prepareStatement(strSql);

			rs = ps.executeQuery();
			while (rs.next()) {
				int count = 1;
				DepartmentBean department = new DepartmentBean();
				department.setDepartmentId(rs.getInt(count++));
				department.setDepartmentName(rs.getString(count++));
				department.setAddress(rs.getString(count++));
				department.setDescription(rs.getString(count++));

				departments.add(department);
			}
			return departments;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			myConnection.closeStatementAndConnection(ps, conn);
		}
	}
	// 查找多条，按条件查找并排序
	/*
	 * public ArrayList<DepartmentBean> select(DepartmentSearcher searcher,
	 * String sortString) throws SQLException { Connection conn = null;
	 * PreparedStatement ps = null; ResultSet rs = null;
	 * 
	 * if (sortString.length() == 0) sortString = "DepartmentId";
	 * 
	 * String strSql = " SELECT * FROM Departments " +
	 * " WHERE DepartmentName LIKE ? " + "	AND Address LIKE ? " +
	 * "	AND Description LIKE ? " + " ORDER BY " + sortString;
	 * ArrayList<DepartmentBean> departments = new ArrayList<DepartmentBean>();
	 * 
	 * try { conn = myConnection.getConnection(); ps =
	 * conn.prepareStatement(strSql); int count = 1; ps.setString(count++, "%" +
	 * searcher.getDepartmentName() + "%"); ps.setString(count++, "%" +
	 * searcher.getAddress() + "%"); ps.setString(count++, "%" +
	 * searcher.getDescription() + "%");
	 * 
	 * rs = ps.executeQuery(); while (rs.next()) {
	 * departments.add(getDepartment(rs)); } return departments; } catch
	 * (SQLException e) { e.printStackTrace(); throw e; } finally {
	 * myConnection.closeStatementAndConnection(ps, conn); } }
	 * 
	 * DepartmentBean getDepartment(ResultSet rs) throws SQLException { return
	 * getDepartment(rs, 1); } DepartmentBean getDepartment(ResultSet rs, int
	 * count) throws SQLException { DepartmentBean department = new
	 * DepartmentBean(); department.setDepartmentId(rs.getInt(count++));
	 * department.setDepartmentName(rs.getString(count++));
	 * department.setAddress(rs.getString(count++));
	 * department.setDescription(rs.getString(count++)); return department; }
	 */
}
