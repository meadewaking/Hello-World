package com.his.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String USERNAME="root";//连接数据库的用户名
	private static final String PASSWORD="123456";//连接数据库的密码
	private static final String URL="jdbc:mysql://localhost:3306/his";//数据库地址
	private static final String DRIVER="com.mysql.jdbc.Driver";//数据库驱动类
	private Connection conn=null;//连接
	
	public DBConnection(){//无参构造器,创建对象时即加载建立连接
		try {
			Class.forName(DRIVER);//注册驱动
			conn=DriverManager.getConnection(URL,USERNAME,PASSWORD);//建立连接
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
	}
	/**
	 * 获取连接
	 */
	public Connection getConn(){
		return conn;
	}
}
