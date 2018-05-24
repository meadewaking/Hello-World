package net.sycu.meade.common;

import java.sql.*;


public class MyConnection {
	static {		//类的静态块，可以理解为类的构造方法，将类加载到jvm时调用一次
		try {
			Class.forName("com.mysql.jdbc.Driver"); // 识别并加载指定的类(加载驱动)
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		try {
			return DriverManager.getConnection( // 连接数据库
					"jdbc:mysql://127.0.0.1:3306/studentmisdb?" + // 找到指定的数据库by ip+端口号+数据库名
					"user=root&password=123456&" + // 数据库账户和密码
					"use Unicode=true&characterEncoding=UTF-8"); // 连接使用的编码
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if(e.getMessage().indexOf("No suitable driver found for") >= 0)
				System.out.println("所要求的驱动和所加载的的驱动不一致");
			else if(e.getMessage().indexOf("Communications Link failure") >=0)
				System.out.println("对应IP的服务器不存在，或对应Port的服务未启动！");
			else if(e.getMessage().indexOf("Unknown datebase") >=0)
				System.out.println("对应名称的数据库不存在，或数据库名称错误！");
			else if(e.getMessage().indexOf("Access denied for user") >=0)
				System.out.println("连接数据库的用户名或密码错误！");
			e.printStackTrace();
		}
		return null;
	}
	
	public static void closeStatement(Statement st){		//关闭语句
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void closeConnection(Connection conn){		//关闭数据库
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
