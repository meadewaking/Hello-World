package cn.sycu.meade.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyConnection {
	// 数据库连接基本信息
	// @SuppressWarnings("unused")
	// private String databaseServiceName = "";
	// private String databaseName = "";
	// private static String databaseLoginName = "root";
	// private static String databasePassword = "123456";
	// private static String driverType = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/emsdb?unicode=true&characterEncoding=UTF-8";
	// private String baseURL = "";
	// private String projectName = "";
	// private String tomcatPath = "";

	// 加载驱动
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			System.out.println("驱动Jar包未找到");
			ex.printStackTrace();
		}
	}

	// 创建连接
	public static Connection getConnection() throws SQLException {
		try {
			return DriverManager.getConnection(url, "root", "123456");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	// 关闭结果集
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	// 关闭语句
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	// 关闭连接
	public static void closeConnection(Connection conn) {
		try {
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	// 关闭语句和连接
	public static void closeStatementAndConnection(Statement st, Connection conn) {
		closeStatement(st);
		closeConnection(conn);
	}

	// 关闭结果集、语句和连接
	public static void closeResultSetAndStatementAndConnection(ResultSet rs, Statement st, Connection conn) {
		closeResultSet(rs);
		closeStatement(st);
		closeConnection(conn);
	}

}
