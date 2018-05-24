package net.sycu.meade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.sycu.meade.common.MyConnection;
import net.sycu.meade.entity.CourseTypeBean;

public class CourseTypeDao {
	// ���һ������
	Connection conn = null;		//��������
	PreparedStatement st = null;		//�������
	ResultSet rs = null;		//���������
	
	public int insert(CourseTypeBean CourseType) throws SQLException {

		try {
			conn = MyConnection.getConnection();

			String strsql = "INSERT INTO CourseTypes " + // Ҫִ�е�sql���
							"VALUES (null, ?, ?) ";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(1, CourseType.getName());
			st.setString(2, CourseType.getDescription()); // ʵ��ִ�����
			return st.executeUpdate(); // ���ز�����
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
	}

	// ��Ӷ�������
	public int insert(ArrayList<CourseTypeBean> CourseTypes) throws SQLException {
		int count = 1;
		try {
			conn = MyConnection.getConnection();
			conn.setAutoCommit(false);
			int rowcount = 0;
			
			for (int j = 0; j < CourseTypes.size(); j++) {
				
				String strsql = "INSERT INTO Students " + // Ҫִ�е�sql���
								"VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?) ";
				st = conn.prepareStatement(strsql); // ����ִ����
				st.setString(count++, CourseTypes.get(j).getName());
				st.setString(count++, CourseTypes.get(j).getDescription()); // ʵ��ִ�����
				rowcount += st.executeUpdate();
			}
			conn.commit();
			return rowcount; // ���ز�����
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
	}

	// ɾ��һ������
	public int delete(CourseTypeBean CourseType) throws SQLException {

		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM CourseTypes " + // Ҫִ�е�sql���
							"WHERE Name = ��";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(1, CourseType.getName()); // �ֶ���Ŵ�1��ʼ
			st.executeUpdate(); // ʵ��ִ�����
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		return 0;
	}

	// ɾ��һ������,��������ɾ��
	public int delete(int CourseTypeId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM CourseTypes " + // Ҫִ�е�sql���
							"WHERE CourseTypeId = ? ";		//ע�⡰����
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setInt(1,CourseTypeId); // �ֶ���Ŵ�1��ʼ	
			return st.executeUpdate(); // ʵ��ִ�����
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		
	}

	// ����Ψһ��ɾ��
	public int delete(String name) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM CourseTypes " + // Ҫִ�е�sql���
							"WHERE Name = ��";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(1, name); // �ֶ���Ŵ�1��ʼ
			st.executeUpdate(); // ʵ��ִ�����
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		return 0;
	}

	// ɾ����������
	public int delete(ArrayList<CourseTypeBean> CourseTypes) throws SQLException {
		
		int[] CourseTypeIds = new int [CourseTypes.size()];
		for (int i = 0; i < CourseTypes.size(); i++) {
			CourseTypeIds[i] = CourseTypes.get(i).getCourseTypeId();
			
		}
		return delete(CourseTypeIds);
	}

	// ɾ����������,��������ɾ��
	public int delete(int[] CourseTypeIds) throws SQLException {
		
		String str = " -1";
		for (int i = 0; i < CourseTypeIds.length; i++) {
			str += ", " + CourseTypeIds[i];
		}
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM CourseTypes " + // Ҫִ�е�sql���
							"WHERE CourseTypeId IN (" + str + ") ";		//ע�⡰����
			st = conn.prepareStatement(strsql); // ����ִ����
			return st.executeUpdate(); // ʵ��ִ�����
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		
	}

	// �޸�һ������,�����޸�����
	public int updata(CourseTypeBean CourseType) throws SQLException {

		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE CourseTypes " + // Ҫִ�е�sql���
							"SET Name = ? , Description = ? " + 
							"WHERE CourseTypeId = ?";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(1, CourseType.getName());
			st.setString(2, CourseType.getDescription());
			st.setInt(3, CourseType.getCourseTypeId());
			return st.executeUpdate(); // ʵ��ִ�����
		} catch (SQLException e) {
			System.out.println("�޸�ѧ��״̬��Ϣʱ����");
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
	}

	// �޸�һ������,�����޸�����
	public int updata(CourseTypeBean CourseType, int CourseTypeId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE CourseTypes " + // Ҫִ�е�sql���
							"SET CourseTypeId = ? ,Name = ? , Description = ? " + 
							"WHERE CourseTypeId = ?";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(1, CourseType.getName());
			st.setString(2, CourseType.getDescription());
			st.setInt(3, CourseType.getCourseTypeId());
			st.setInt(4, CourseTypeId);
			st.executeUpdate(); // ʵ��ִ�����
		} catch (SQLException e) {
			System.out.println("�޸�ѧ��״̬��Ϣʱ����");
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		return 0;
	}

	// ����������ѯ
	public CourseTypeBean select(int CourseTypeId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // Ҫִ�е�sql���
							"FROM CourseTypes " +
							"WHERE CourseTypeId = ? ";		//ע�⡰����
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setInt(1,CourseTypeId); // �ֶ���Ŵ�1��ʼ	
			rs = st.executeQuery(); // ����������ѯ���
			
			if (rs.next()) {		//ֻ����һ����ֻ������
				CourseTypeBean CourseType = new CourseTypeBean();
				CourseType.setCourseTypeId(rs.getInt(1));
				CourseType.setName(rs.getString(2));
				CourseType.setDescription(rs.getString(3));
				
				return CourseType;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		
		return null;
	}

	// ����Ψһ����ѯ
	public CourseTypeBean select(String name) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // Ҫִ�е�sql���
							"FROM CourseTypes " +
							"WHERE Name = ? ";		//ע�⡰����
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(1,name); // �ֶ���Ŵ�1��ʼ	
			rs = st.executeQuery(); // ����������ѯ���
			
			if (rs.next()) {		//ֻ����һ����ֻ������
				CourseTypeBean CourseType = new CourseTypeBean();
				CourseType.setCourseTypeId(rs.getInt(1));
				CourseType.setName(rs.getString(2));
				CourseType.setDescription(rs.getString(3));
				
				return CourseType;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		
		return null;
	}

	// ��ѯȫ������
	public ArrayList<CourseTypeBean> select() throws SQLException {
		
		ArrayList<CourseTypeBean> CourseTypes = new ArrayList<CourseTypeBean>();
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // Ҫִ�е�sql���
							"FROM CourseTypes " ;
			st = conn.prepareStatement(strsql); // ����ִ����
			rs = st.executeQuery(); // ����������ѯ���
			
			while (rs.next()) {		//ֻ����һ����ֻ�������������ݷ�����
				CourseTypeBean CourseType = new CourseTypeBean();
				CourseType.setCourseTypeId(rs.getInt(1));
				CourseType.setName(rs.getString(2));
				CourseType.setDescription(rs.getString(3));
				
				CourseTypes.add(CourseType);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		
		return CourseTypes;
	}
}
