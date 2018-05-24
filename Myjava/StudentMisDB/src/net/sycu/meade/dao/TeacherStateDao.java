package net.sycu.meade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.sycu.meade.common.MyConnection;
import net.sycu.meade.entity.TeacherStateBean;

public class TeacherStateDao {
	// ���һ������
	Connection conn = null;		//��������
	PreparedStatement st = null;		//�������
	ResultSet rs = null;		//���������
	
	public int insert(TeacherStateBean TeacherState) throws SQLException {

		try {
			conn = MyConnection.getConnection();

			String strsql = "INSERT INTO TeacherStates " + // Ҫִ�е�sql���
							"VALUES (null, ?, ?, ?) ";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(1, TeacherState.getName());
			st.setBoolean(2, TeacherState.isInschool());
			st.setString(3, TeacherState.getDescription()); // ʵ��ִ�����
			return st.executeUpdate(); // ���ز�����
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("���ѧ��״̬��Ϣʱ����");
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
	}

	// ��Ӷ�������
	public int insert(ArrayList<TeacherStateBean> TeacherStates) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			conn.setAutoCommit(false);
			int rowcount = 0;
			
			for (int j = 0; j < TeacherStates.size(); j++) {
				int count = 1;
				String strsql = "INSERT INTO Students " + // Ҫִ�е�sql���
								"VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?) ";
				st = conn.prepareStatement(strsql); // ����ִ����
				st.setString(count++, TeacherStates.get(j).getName());
				st.setBoolean(count++, TeacherStates.get(j).isInschool());
				st.setString(count++, TeacherStates.get(j).getDescription()); // ʵ��ִ�����
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
	public int delete(TeacherStateBean TeacherState) throws SQLException {

		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM TeacherStates " + // Ҫִ�е�sql���
							"WHERE Name = ��";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(1, TeacherState.getName()); // �ֶ���Ŵ�1��ʼ
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
	public int delete(int TeacherStateId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM TeacherStates " + // Ҫִ�е�sql���
							"WHERE TeacherStateId = ? ";		//ע�⡰����
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setInt(1,TeacherStateId); // �ֶ���Ŵ�1��ʼ	
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
			String strsql = "DELETE FROM TeacherStates " + // Ҫִ�е�sql���
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
	public int delete(ArrayList<TeacherStateBean> TeacherStates) throws SQLException {
		
		int[] TeacherStateIds = new int [TeacherStates.size()];
		for (int i = 0; i < TeacherStates.size(); i++) {
			TeacherStateIds[i] = TeacherStates.get(i).getTeacherStateId();
			
		}
		return delete(TeacherStateIds);
	}

	// ɾ����������,��������ɾ��
	public int delete(int[] TeacherStateIds) throws SQLException {
		
		String str = " -1";
		for (int i = 0; i < TeacherStateIds.length; i++) {
			str += ", " + TeacherStateIds[i];
		}
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM TeacherStates " + // Ҫִ�е�sql���
							"WHERE TeacherStateId IN (" + str + ") ";		//ע�⡰����
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
	public int updata(TeacherStateBean TeacherState) throws SQLException {

		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE TeacherStates " + // Ҫִ�е�sql���
							"SET Name = ? ,Inschool = ?, Description = ? " + 
							"WHERE TeacherStateId = ?";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(1, TeacherState.getName());
			st.setBoolean(2, TeacherState.isInschool());
			st.setString(3, TeacherState.getDescription());
			st.setInt(4, TeacherState.getTeacherStateId());
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
	public int updata(TeacherStateBean TeacherState, int TeacherStateId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE TeacherStates " + // Ҫִ�е�sql���
							"SET TeacherStateId = ? ,Name = ? ,Inschool = ?, Description = ? " + 
							"WHERE TeacherStateId = ?";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(1, TeacherState.getName());
			st.setBoolean(2, TeacherState.isInschool());
			st.setString(3, TeacherState.getDescription());
			st.setInt(4, TeacherState.getTeacherStateId());
			st.setInt(5, TeacherStateId);
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
	public TeacherStateBean select(int TeacherStateId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // Ҫִ�е�sql���
							"FROM TeacherStates " +
							"WHERE TeacherStateId = ? ";		//ע�⡰����
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setInt(1,TeacherStateId); // �ֶ���Ŵ�1��ʼ	
			rs = st.executeQuery(); // ����������ѯ���
			
			if (rs.next()) {		//ֻ����һ����ֻ������
				TeacherStateBean Teacherstate = new TeacherStateBean();
				Teacherstate.setTeacherStateId(rs.getInt(1));
				Teacherstate.setName(rs.getString(2));
				Teacherstate.setInschool(rs.getBoolean(3));
				Teacherstate.setDescription(rs.getString(4));
				
				return Teacherstate;
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
	public TeacherStateBean select(String name) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // Ҫִ�е�sql���
							"FROM TeacherStates " +
							"WHERE Name = ? ";		//ע�⡰����
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(1,name); // �ֶ���Ŵ�1��ʼ	
			rs = st.executeQuery(); // ����������ѯ���
			
			if (rs.next()) {		//ֻ����һ����ֻ������
				TeacherStateBean Teacherstate = new TeacherStateBean();
				Teacherstate.setTeacherStateId(rs.getInt(1));
				Teacherstate.setName(rs.getString(2));
				Teacherstate.setInschool(rs.getBoolean(3));
				Teacherstate.setDescription(rs.getString(4));
				
				return Teacherstate;
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
	public ArrayList<TeacherStateBean> select() throws SQLException {
		
		ArrayList<TeacherStateBean> Teacherstates = new ArrayList<TeacherStateBean>();
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // Ҫִ�е�sql���
							"FROM TeacherStates " ;
			st = conn.prepareStatement(strsql); // ����ִ����
			rs = st.executeQuery(); // ����������ѯ���
			
			while (rs.next()) {		//ֻ����һ����ֻ�������������ݷ�����
				TeacherStateBean Teacherstate = new TeacherStateBean();
				Teacherstate.setTeacherStateId(rs.getInt(1));
				Teacherstate.setName(rs.getString(2));
				Teacherstate.setInschool(rs.getBoolean(3));
				Teacherstate.setDescription(rs.getString(4));
				
				Teacherstates.add(Teacherstate);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		
		return Teacherstates;
	}
}
