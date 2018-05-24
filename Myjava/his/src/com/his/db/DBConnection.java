package com.his.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String USERNAME="root";//�������ݿ���û���
	private static final String PASSWORD="123456";//�������ݿ������
	private static final String URL="jdbc:mysql://localhost:3306/his";//���ݿ��ַ
	private static final String DRIVER="com.mysql.jdbc.Driver";//���ݿ�������
	private Connection conn=null;//����
	
	public DBConnection(){//�޲ι�����,��������ʱ�����ؽ�������
		try {
			Class.forName(DRIVER);//ע������
			conn=DriverManager.getConnection(URL,USERNAME,PASSWORD);//��������
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
	}
	/**
	 * ��ȡ����
	 */
	public Connection getConn(){
		return conn;
	}
}
