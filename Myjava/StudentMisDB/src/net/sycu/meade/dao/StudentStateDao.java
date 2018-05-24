package net.sycu.meade.dao;

import java.sql.*;
import java.util.ArrayList;

import net.sycu.meade.common.MyConnection;
import net.sycu.meade.entity.StudentStateBean;

//���ݷ��ʲ������������ݿ⣬ÿһ�����ݷ������Ӧһ��ʵ����
public class StudentStateDao {
	// ���һ������
	Connection conn = null;		//��������
	PreparedStatement st = null;		//�������
	ResultSet rs = null;		//���������
	
	public int insert(StudentStateBean StudentState) throws SQLException {

		try {
			conn = MyConnection.getConnection();

			String strsql = "INSERT INTO StudentStates " + // Ҫִ�е�sql���
							"VALUES (null, ?, ?, ?) ";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(1, StudentState.getName());
			st.setBoolean(2, StudentState.isInschool());
			st.setString(3, StudentState.getDescription()); // ʵ��ִ�����
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
	public int insert(ArrayList<StudentStateBean> StudentStates) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			conn.setAutoCommit(false);
			int rowcount = 0;
			
			for (int j = 0; j < StudentStates.size(); j++) {
				int count = 1;
				String strsql = "INSERT INTO Students " + // Ҫִ�е�sql���
								"VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?) ";
				st = conn.prepareStatement(strsql); // ����ִ����
				st.setString(count++, StudentStates.get(j).getName());
				st.setBoolean(count++, StudentStates.get(j).isInschool());
				st.setString(count++, StudentStates.get(j).getDescription()); // ʵ��ִ�����
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
	public int delete(StudentStateBean StudentState) throws SQLException {

		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM StudentStates " + // Ҫִ�е�sql���
							"WHERE Name = ��";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(1, StudentState.getName()); // �ֶ���Ŵ�1��ʼ
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
	public int delete(int StudentStateId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM StudentStates " + // Ҫִ�е�sql���
							"WHERE StudentStateId = ? ";		//ע�⡰����
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setInt(1,StudentStateId); // �ֶ���Ŵ�1��ʼ	
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
			String strsql = "DELETE FROM StudentStates " + // Ҫִ�е�sql���
							"WHERE Name = ��";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(1, name); // �ֶ���Ŵ�1��ʼ
			return st.executeUpdate(); // ʵ��ִ�����
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
	}

	// ɾ����������
	public int delete(ArrayList<StudentStateBean> StudentStates) throws SQLException {
		
		int[] StudentStateIds = new int [StudentStates.size()];
		for (int i = 0; i < StudentStates.size(); i++) {
			StudentStateIds[i] = StudentStates.get(i).getStudentStateId();
			
		}
		return delete(StudentStateIds);
	}

	// ɾ����������,��������ɾ��
	public int delete(int[] StudentStateIds) throws SQLException {
		
		String str = " -1";
		for (int i = 0; i < StudentStateIds.length; i++) {
			str += ", " + StudentStateIds[i];
		}
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM StudentStates " + // Ҫִ�е�sql���
							"WHERE StudentStateId IN (" + str + ") ";		//ע�⡰����
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
	public int updata(StudentStateBean StudentState) throws SQLException {

		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE StudentStates " + // Ҫִ�е�sql���
							"SET Name = ? ,Inschool = ?, Description = ? " + 
							"WHERE StudentStateId = ?";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(1, StudentState.getName());
			st.setBoolean(2, StudentState.isInschool());
			st.setString(3, StudentState.getDescription());
			st.setInt(4, StudentState.getStudentStateId());
			return st.executeUpdate(); // ʵ��ִ�����
		} catch (SQLException e) {
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
	}

	// �޸�һ������,�����޸�����
	public int updata(StudentStateBean StudentState, int StudentStateId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE StudentStates " + // Ҫִ�е�sql���
							"SET StudentStateId = ? ,Name = ? ,Inschool = ?, Description = ? " + 
							"WHERE StudentStateId = ?";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(1, StudentState.getName());
			st.setBoolean(2, StudentState.isInschool());
			st.setString(3, StudentState.getDescription());
			st.setInt(4, StudentState.getStudentStateId());
			st.setInt(5, StudentStateId);
			st.executeUpdate(); // ʵ��ִ�����
		} catch (SQLException e) {
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		return 0;
	}

	// ����������ѯ
	public StudentStateBean select(int StudentStateId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // Ҫִ�е�sql���
							"FROM StudentStates " +
							"WHERE StudentStateId = ? ";		//ע�⡰����
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setInt(1,StudentStateId); // �ֶ���Ŵ�1��ʼ	
			rs = st.executeQuery(); // ����������ѯ���
			
			if (rs.next()) {		//ֻ����һ����ֻ������
				StudentStateBean studentstate = new StudentStateBean();
				studentstate.setStudentStateId(rs.getInt(1));
				studentstate.setName(rs.getString(2));
				studentstate.setInschool(rs.getBoolean(3));
				studentstate.setDescription(rs.getString(4));
				
				return studentstate;
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		
		return null;
	}

	// ����Ψһ����ѯ
	public StudentStateBean select(String name) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // Ҫִ�е�sql���
							"FROM StudentStates " +
							"WHERE Name = ? ";		//ע�⡰����
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(1,name); // �ֶ���Ŵ�1��ʼ	
			rs = st.executeQuery(); // ����������ѯ���
			
			if (rs.next()) {		//ֻ����һ����ֻ������
				StudentStateBean studentstate = new StudentStateBean();
				studentstate.setStudentStateId(rs.getInt(1));
				studentstate.setName(rs.getString(2));
				studentstate.setInschool(rs.getBoolean(3));
				studentstate.setDescription(rs.getString(4));
				
				return studentstate;
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
	public ArrayList<StudentStateBean> select() throws SQLException {
		
		ArrayList<StudentStateBean> studentstates = new ArrayList<StudentStateBean>();
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // Ҫִ�е�sql���
							"FROM StudentStates " ;
			st = conn.prepareStatement(strsql); // ����ִ����
			rs = st.executeQuery(); // ����������ѯ���
			
			while (rs.next()) {		//ֻ����һ����ֻ�������������ݷ�����
				StudentStateBean studentstate = new StudentStateBean();
				studentstate.setStudentStateId(rs.getInt(1));
				studentstate.setName(rs.getString(2));
				studentstate.setInschool(rs.getBoolean(3));
				studentstate.setDescription(rs.getString(4));
				
				studentstates.add(studentstate);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		
		return studentstates;
	}
}
