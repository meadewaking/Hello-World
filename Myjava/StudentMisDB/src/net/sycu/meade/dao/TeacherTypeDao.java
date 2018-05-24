package net.sycu.meade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.sycu.meade.common.MyConnection;
import net.sycu.meade.entity.TeacherTypeBean;

public class TeacherTypeDao {
	// ���һ������
	Connection conn = null;		//��������
	PreparedStatement st = null;		//�������
	ResultSet rs = null;		//���������
	
	public int insert(TeacherTypeBean TeacherType) throws SQLException {

		try {
			conn = MyConnection.getConnection();

			String strsql = "INSERT INTO TeacherTypes " + // Ҫִ�е�sql���
							"VALUES (null, ?, ?) ";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(1, TeacherType.getName());
			st.setString(2, TeacherType.getDescription()); // ʵ��ִ�����
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
	public int insert(ArrayList<TeacherTypeBean> TeacherTypes) throws SQLException {
		int count = 1;
		try {
			conn = MyConnection.getConnection();
			conn.setAutoCommit(false);
			int rowcount = 0;
			
			for (int j = 0; j < TeacherTypes.size(); j++) {
				
				String strsql = "INSERT INTO Students " + // Ҫִ�е�sql���
								"VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?) ";
				st = conn.prepareStatement(strsql); // ����ִ����
				st.setString(count++, TeacherTypes.get(j).getName());
				st.setString(count++, TeacherTypes.get(j).getDescription()); // ʵ��ִ�����
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
	public int delete(TeacherTypeBean TeacherType) throws SQLException {

		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM TeacherTypes " + // Ҫִ�е�sql���
							"WHERE Name = ��";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(1, TeacherType.getName()); // �ֶ���Ŵ�1��ʼ
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
	public int delete(int TeacherTypeId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM TeacherTypes " + // Ҫִ�е�sql���
							"WHERE TeacherTypeId = ? ";		//ע�⡰����
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setInt(1,TeacherTypeId); // �ֶ���Ŵ�1��ʼ	
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
			String strsql = "DELETE FROM TeacherTypes " + // Ҫִ�е�sql���
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
	public int delete(ArrayList<TeacherTypeBean> TeacherTypes) throws SQLException {
		
		int[] TeacherTypeIds = new int [TeacherTypes.size()];
		for (int i = 0; i < TeacherTypes.size(); i++) {
			TeacherTypeIds[i] = TeacherTypes.get(i).getTeacherTypeId();
			
		}
		return delete(TeacherTypeIds);
	}

	// ɾ����������,��������ɾ��
	public int delete(int[] TeacherTypeIds) throws SQLException {
		
		String str = " -1";
		for (int i = 0; i < TeacherTypeIds.length; i++) {
			str += ", " + TeacherTypeIds[i];
		}
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM TeacherTypes " + // Ҫִ�е�sql���
							"WHERE TeacherTypeId IN (" + str + ") ";		//ע�⡰����
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
	public int updata(TeacherTypeBean TeacherType) throws SQLException {

		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE TeacherTypes " + // Ҫִ�е�sql���
							"SET Name = ? , Description = ? " + 
							"WHERE TeacherTypeId = ?";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(1, TeacherType.getName());
			st.setString(2, TeacherType.getDescription());
			st.setInt(3, TeacherType.getTeacherTypeId());
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
	public int updata(TeacherTypeBean TeacherType, int TeacherTypeId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE TeacherTypes " + // Ҫִ�е�sql���
							"SET TeacherTypeId = ? ,Name = ? , Description = ? " + 
							"WHERE TeacherTypeId = ?";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(1, TeacherType.getName());
			st.setString(2, TeacherType.getDescription());
			st.setInt(3, TeacherType.getTeacherTypeId());
			st.setInt(4, TeacherTypeId);
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
	public TeacherTypeBean select(int TeacherTypeId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // Ҫִ�е�sql���
							"FROM TeacherTypes " +
							"WHERE TeacherTypeId = ? ";		//ע�⡰����
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setInt(1,TeacherTypeId); // �ֶ���Ŵ�1��ʼ	
			rs = st.executeQuery(); // ����������ѯ���
			
			if (rs.next()) {		//ֻ����һ����ֻ������
				TeacherTypeBean TeacherType = new TeacherTypeBean();
				TeacherType.setTeacherTypeId(rs.getInt(1));
				TeacherType.setName(rs.getString(2));
				TeacherType.setDescription(rs.getString(3));
				
				return TeacherType;
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
	public TeacherTypeBean select(String name) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // Ҫִ�е�sql���
							"FROM TeacherTypes " +
							"WHERE Name = ? ";		//ע�⡰����
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(1,name); // �ֶ���Ŵ�1��ʼ	
			rs = st.executeQuery(); // ����������ѯ���
			
			if (rs.next()) {		//ֻ����һ����ֻ������
				TeacherTypeBean TeacherType = new TeacherTypeBean();
				TeacherType.setTeacherTypeId(rs.getInt(1));
				TeacherType.setName(rs.getString(2));
				TeacherType.setDescription(rs.getString(3));
				
				return TeacherType;
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
	public ArrayList<TeacherTypeBean> select() throws SQLException {
		
		ArrayList<TeacherTypeBean> TeacherTypes = new ArrayList<TeacherTypeBean>();
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // Ҫִ�е�sql���
							"FROM TeacherTypes " ;
			st = conn.prepareStatement(strsql); // ����ִ����
			rs = st.executeQuery(); // ����������ѯ���
			
			while (rs.next()) {		//ֻ����һ����ֻ�������������ݷ�����
				TeacherTypeBean TeacherType = new TeacherTypeBean();
				TeacherType.setTeacherTypeId(rs.getInt(1));
				TeacherType.setName(rs.getString(2));
				TeacherType.setDescription(rs.getString(3));
				
				TeacherTypes.add(TeacherType);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		
		return TeacherTypes;
	}
}
