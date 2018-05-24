package cn.sycu.meade.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyConnection {
	// ���ݿ����ӻ�����Ϣ
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

	// ��������
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			System.out.println("����Jar��δ�ҵ�");
			ex.printStackTrace();
		}
	}

	// ��������
	public static Connection getConnection() throws SQLException {
		try {
			return DriverManager.getConnection(url, "root", "123456");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	// �رս����
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	// �ر����
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	// �ر�����
	public static void closeConnection(Connection conn) {
		try {
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	// �ر���������
	public static void closeStatementAndConnection(Statement st, Connection conn) {
		closeStatement(st);
		closeConnection(conn);
	}

	// �رս��������������
	public static void closeResultSetAndStatementAndConnection(ResultSet rs, Statement st, Connection conn) {
		closeResultSet(rs);
		closeStatement(st);
		closeConnection(conn);
	}

}
