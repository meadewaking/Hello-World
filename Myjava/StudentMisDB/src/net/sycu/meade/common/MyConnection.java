package net.sycu.meade.common;

import java.sql.*;


public class MyConnection {
	static {		//��ľ�̬�飬�������Ϊ��Ĺ��췽����������ص�jvmʱ����һ��
		try {
			Class.forName("com.mysql.jdbc.Driver"); // ʶ�𲢼���ָ������(��������)
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		try {
			return DriverManager.getConnection( // �������ݿ�
					"jdbc:mysql://127.0.0.1:3306/studentmisdb?" + // �ҵ�ָ�������ݿ�by ip+�˿ں�+���ݿ���
					"user=root&password=123456&" + // ���ݿ��˻�������
					"use Unicode=true&characterEncoding=UTF-8"); // ����ʹ�õı���
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if(e.getMessage().indexOf("No suitable driver found for") >= 0)
				System.out.println("��Ҫ��������������صĵ�������һ��");
			else if(e.getMessage().indexOf("Communications Link failure") >=0)
				System.out.println("��ӦIP�ķ����������ڣ����ӦPort�ķ���δ������");
			else if(e.getMessage().indexOf("Unknown datebase") >=0)
				System.out.println("��Ӧ���Ƶ����ݿⲻ���ڣ������ݿ����ƴ���");
			else if(e.getMessage().indexOf("Access denied for user") >=0)
				System.out.println("�������ݿ���û������������");
			e.printStackTrace();
		}
		return null;
	}
	
	public static void closeStatement(Statement st){		//�ر����
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void closeConnection(Connection conn){		//�ر����ݿ�
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
