package net.sycu.meade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.sycu.meade.common.MyConnection;
import net.sycu.meade.entity.StudentBean;

public class StudentDao {
	// ���һ������
	Connection conn = null;		//��������
	PreparedStatement st = null;		//�������
	ResultSet rs = null;		//���������
	
	public int insert(StudentBean Student) throws SQLException {
		int count = 1;
		try {
			conn = MyConnection.getConnection();

			String strsql = "INSERT INTO Students " + // Ҫִ�е�sql���
							"VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(count++, Student.getName());
			st.setString(count++, Student.getNumber());
			st.setString(count++, Student.getGender()); // ʵ��ִ�����
			st.setDate(count++, Student.getBirthday());
			st.setString(count++, Student.getPhoneNumber());
			st.setString(count++, Student.getAddress());
			st.setString(count++, Student.getRemark());
			st.setInt(count++, Student.getStateId());
			st.setInt(count++, Student.getClazzId());
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
	public int insert(ArrayList<StudentBean> Students) throws SQLException {
		try {
			conn = MyConnection.getConnection();
			conn.setAutoCommit(false);
			int rowcount = 0;
			
			for (int j = 0; j < Students.size(); j++) {
				int count = 1;
				String strsql = "INSERT INTO Students " + // Ҫִ�е�sql���
								"VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
				st = conn.prepareStatement(strsql); // ����ִ����
				st.setString(count++, Students.get(j).getName());
				st.setString(count++, Students.get(j).getNumber());
				st.setString(count++, Students.get(j).getGender()); // ʵ��ִ�����
				st.setDate(count++, Students.get(j).getBirthday());
				st.setString(count++, Students.get(j).getPhoneNumber());
				st.setString(count++, Students.get(j).getAddress());
				st.setString(count++, Students.get(j).getRemark());
				st.setInt(count++, Students.get(j).getStateId());
				st.setInt(count++, Students.get(j).getClazzId());
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
	public int delete(StudentBean Student) throws SQLException {
		int count = 1;
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM Students " + // Ҫִ�е�sql���
							"WHERE Name = ��";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(count++, Student.getName()); // �ֶ���Ŵ�1��ʼ
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
	public int delete(int StudentId) throws SQLException {
		int count = 1;
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM Students " + // Ҫִ�е�sql���
							"WHERE StudentId = ? ";		//ע�⡰����
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setInt(count++,StudentId); // �ֶ���Ŵ�1��ʼ	
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
		int count = 1;
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM Students " + // Ҫִ�е�sql���
							"WHERE Name = ��";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(count++, name); // �ֶ���Ŵ�1��ʼ
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
	public int delete(ArrayList<StudentBean> Students) throws SQLException {
		int[] StudentIds = new int [Students.size()];
		for (int i = 0; i < Students.size(); i++) {
			StudentIds[i] = Students.get(i).getStudentId();
			
		}
		return delete(StudentIds);
	}

	// ɾ����������,��������ɾ��
	public int delete(int[] StudentIds) throws SQLException {
		String str = " -1";
		for (int i = 0; i < StudentIds.length; i++) {
			str += ", " + StudentIds[i];
		}
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM Students " + // Ҫִ�е�sql���
							"WHERE StudentId IN (" + str + ") ";		//ע�⡰����
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
	public int updata(StudentBean Student) throws SQLException {
		int count = 1;
		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE Students " + // Ҫִ�е�sql���
							"SET Name = ? ,Number = ?, Gender = ? ,Birthday = ?, PhoneNumber = ?, Address = ?, Remark = ?, StateId = ?, ClazzId = ? " + 
							"WHERE StudentId = ?";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(count++, Student.getName());
			st.setString(count++, Student.getNumber());
			st.setString(count++, Student.getGender()); // ʵ��ִ�����
			st.setDate(count++, Student.getBirthday());
			st.setString(count++, Student.getPhoneNumber());
			st.setString(count++, Student.getAddress());
			st.setString(count++, Student.getRemark());
			st.setInt(count++, Student.getStateId());
			st.setInt(count++, Student.getClazzId());
			st.setInt(count++, Student.getStudentId());
			return st.executeUpdate(); // ʵ��ִ�����
		} catch (SQLException e) {
			System.out.println("�޸�ѧ����Ϣʱ����");
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
	}

	// �޸�һ������,�����޸�����
	public int updata(StudentBean Student, int StudentId) throws SQLException {
		int count = 1;
		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE Students " + // Ҫִ�е�sql���
							"SET StudentId = ? ,Name = ? ,Number = ?, Gender = ? ,Birthday = ?, PhoneNumber = ?, Address = ?, Remark = ?, StateId = ?, ClazzId = ? " + 
							"WHERE StudentId = ?";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setInt(count++, Student.getStudentId());
			st.setString(count++, Student.getName());
			st.setString(count++, Student.getNumber());
			st.setString(count++, Student.getGender()); // ʵ��ִ�����
			st.setDate(count++, Student.getBirthday());
			st.setString(count++, Student.getPhoneNumber());
			st.setString(count++, Student.getAddress());
			st.setString(count++, Student.getRemark());
			st.setInt(count++, Student.getStateId());
			st.setInt(count++, Student.getClazzId());
			st.setInt(count++, Student.getStudentId());
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

	// ����������ѯ
	public StudentBean select(int StudentId) throws SQLException {
		int count = 1;
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // Ҫִ�е�sql���
							"FROM Students " +
							"WHERE StudentId = ? ";		//ע�⡰����
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setInt(1,StudentId); // �ֶ���Ŵ�1��ʼ	
			rs = st.executeQuery(); // ����������ѯ���
			
			if (rs.next()) {		//ֻ����һ����ֻ������
				StudentBean Student = new StudentBean();
				Student.setStudentId(rs.getInt(count++));
				Student.setName(rs.getString(count++));
				Student.setNumber(rs.getString(count++));
				Student.setGender(rs.getString(count++));
				Student.setBirthday(rs.getDate(count++));
				Student.setPhoneNumber(rs.getString(count++));
				Student.setAddress(rs.getString(count++));
				Student.setRemark(rs.getString(count++));
				Student.setStateId(rs.getInt(count++));
				Student.setClazzId(rs.getInt(count++));
				Student.setStudentState(new StudentStateDao().select(Student.getStateId()));
				Student.setclazz(new ClazzDao().select(Student.getClazzId()));
				return Student;
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
	public StudentBean select(String name) throws SQLException {
		int count = 1;
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // Ҫִ�е�sql���
							"FROM Students " +
							"WHERE Name = ? ";		//ע�⡰����
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(1,name); // �ֶ���Ŵ�1��ʼ	
			rs = st.executeQuery(); // ����������ѯ���
			
			if (rs.next()) {		//ֻ����һ����ֻ������
				StudentBean Student = new StudentBean();
				Student.setStudentId(rs.getInt(count++));
				Student.setName(rs.getString(count++));
				Student.setNumber(rs.getString(count++));
				Student.setGender(rs.getString(count++));
				Student.setBirthday(rs.getDate(count++));
				Student.setPhoneNumber(rs.getString(count++));
				Student.setAddress(rs.getString(count++));
				Student.setRemark(rs.getString(count++));
				Student.setStateId(rs.getInt(count++));
				Student.setClazzId(rs.getInt(count++));
				Student.setStudentState(new StudentStateDao().select(Student.getStateId()));
				Student.setclazz(new ClazzDao().select(Student.getClazzId()));
				return Student;
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
	public ArrayList<StudentBean> select() throws SQLException {
		
		ArrayList<StudentBean> Students = new ArrayList<StudentBean>();
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // Ҫִ�е�sql���
							"FROM Students " ;
			st = conn.prepareStatement(strsql); // ����ִ����
			rs = st.executeQuery(); // ����������ѯ���
			
			while (rs.next()) {		//ֻ����һ����ֻ�������������ݷ�����
				int count = 1;
				StudentBean Student = new StudentBean();
				Student.setStudentId(rs.getInt(count++));
				Student.setName(rs.getString(count++));
				Student.setNumber(rs.getString(count++));
				Student.setGender(rs.getString(count++));
				Student.setBirthday(rs.getDate(count++));
				Student.setPhoneNumber(rs.getString(count++));
				Student.setAddress(rs.getString(count++));
				Student.setRemark(rs.getString(count++));
				Student.setStateId(rs.getInt(count++));
				Student.setClazzId(rs.getInt(count++));
				Student.setStudentState(new StudentStateDao().select(Student.getStateId()));
				Student.setclazz(new ClazzDao().select(Student.getClazzId()));
				Students.add(Student);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		
		return Students;
	}
}
