package net.sycu.meade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.sycu.meade.common.MyConnection;
import net.sycu.meade.entity.TeacherBean;

public class TeacherDao {
	// ���һ������
	Connection conn = null;		//��������
	PreparedStatement st = null;		//�������
	ResultSet rs = null;		//���������
	
	public int insert(TeacherBean Teacher) throws SQLException {
		int count = 1;
		try {
			conn = MyConnection.getConnection();

			String strsql = "INSERT INTO Teachers " + // Ҫִ�е�sql���
							"VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(count++, Teacher.getName());
			st.setString(count++, Teacher.getNumber());
			st.setString(count++, Teacher.getGender()); // ʵ��ִ�����
			st.setDate(count++, Teacher.getBirthday());
			st.setString(count++, Teacher.getPhoneNumber());
			st.setString(count++, Teacher.getAddress());
			st.setFloat(count++, Teacher.getSalary());
			st.setString(count++, Teacher.getRemark());
			st.setInt(count++, Teacher.getStateId());
			st.setInt(count++, Teacher.getTypeId());
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
	public int insert(ArrayList<TeacherBean> Teachers) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			conn.setAutoCommit(false);
			int rowcount = 0;
			
			for (int j = 0; j < Teachers.size(); j++) {
				int count = 1;
				String strsql = "INSERT INTO Teachers " + // Ҫִ�е�sql���
								"VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
				st = conn.prepareStatement(strsql); // ����ִ����
				st.setString(count++, Teachers.get(j).getName());
				st.setString(count++, Teachers.get(j).getNumber());
				st.setString(count++, Teachers.get(j).getGender()); // ʵ��ִ�����
				st.setDate(count++, Teachers.get(j).getBirthday());
				st.setString(count++, Teachers.get(j).getPhoneNumber());
				st.setString(count++, Teachers.get(j).getAddress());
				st.setFloat(count++, Teachers.get(j).getSalary());
				st.setString(count++, Teachers.get(j).getRemark());
				st.setInt(count++, Teachers.get(j).getStateId());
				st.setInt(count++, Teachers.get(j).getTypeId());
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
	public int delete(TeacherBean Teacher) throws SQLException {

		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM Teachers " + // Ҫִ�е�sql���
							"WHERE Name = ��";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(1, Teacher.getName()); // �ֶ���Ŵ�1��ʼ
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
	public int delete(int TeacherId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM Teachers " + // Ҫִ�е�sql���
							"WHERE TeacherId = ? ";		//ע�⡰����
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setInt(1,TeacherId); // �ֶ���Ŵ�1��ʼ	
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
	public int delete(String Number) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM Teachers " + // Ҫִ�е�sql���
							"WHERE Number = ��";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(1, Number); // �ֶ���Ŵ�1��ʼ
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
	public int delete(ArrayList<TeacherBean> Teachers) throws SQLException {
		
		int[] TeacherIds = new int [Teachers.size()];
		for (int i = 0; i < Teachers.size(); i++) {
			TeacherIds[i] = Teachers.get(i).getTeacherId();
			
		}
		return delete(TeacherIds);
	}

	// ɾ����������,��������ɾ��
	public int delete(int[] TeacherIds) throws SQLException {
		
		String str = " -1";
		for (int i = 0; i < TeacherIds.length; i++) {
			str += ", " + TeacherIds[i];
		}
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM Teachers " + // Ҫִ�е�sql���
							"WHERE TeacherId IN (" + str + ") ";		//ע�⡰����
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
	public int updata(TeacherBean Teacher) throws SQLException {
		int count = 1;
		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE Teachers " + // Ҫִ�е�sql���
							"SET Name = ? ,Number = ?, Gender = ? ,Birthday = ?, PhoneNumber = ?, Address = ?, Salary = ?, Remark = ?, StateId = ?, TypeId = ? " + 
							"WHERE TeacherId = ?";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(count++, Teacher.getName());
			st.setString(count++, Teacher.getNumber());
			st.setString(count++, Teacher.getGender()); // ʵ��ִ�����
			st.setDate(count++, Teacher.getBirthday());
			st.setString(count++, Teacher.getPhoneNumber());
			st.setString(count++, Teacher.getAddress());
			st.setFloat(count++, Teacher.getSalary());
			st.setString(count++, Teacher.getRemark());
			st.setInt(count++, Teacher.getStateId());
			st.setInt(count++, Teacher.getTypeId());
			st.setInt(count++, Teacher.getTeacherId());
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
	public int updata(TeacherBean Teacher, int TeacherId) throws SQLException {
		int count = 1;
		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE Teachers " + // Ҫִ�е�sql���
							"SET Name = ? ,Number = ?, Gender = ? ,Birthday = ?, PhoneNumber = ?, Address = ?, Salary = ?, Remark = ?, StateId = ?, TypeId = ? " + 
							"WHERE TeacherId = ?";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(count++, Teacher.getName());
			st.setString(count++, Teacher.getNumber());
			st.setString(count++, Teacher.getGender()); // ʵ��ִ�����
			st.setDate(count++, Teacher.getBirthday());
			st.setString(count++, Teacher.getPhoneNumber());
			st.setString(count++, Teacher.getAddress());
			st.setFloat(count++, Teacher.getSalary());
			st.setString(count++, Teacher.getRemark());
			st.setInt(count++, Teacher.getStateId());
			st.setInt(count++, Teacher.getTypeId());
			st.setInt(count++, Teacher.getTeacherId());
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
	public TeacherBean select(int TeacherId) throws SQLException {
		int count = 1;
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // Ҫִ�е�sql���
							"FROM Teachers " +
							"WHERE TeacherId = ? ";		//ע�⡰����
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setInt(1,TeacherId); // �ֶ���Ŵ�1��ʼ	
			rs = st.executeQuery(); // ����������ѯ���
			
			if (rs.next()) {		//ֻ����һ����ֻ������
				TeacherBean Teacher = new TeacherBean();
				Teacher.setTeacherId(rs.getInt(count++));				
				Teacher.setNumber(rs.getString(count++));
				Teacher.setName(rs.getString(count++));
				Teacher.setGender(rs.getString(count++));
				Teacher.setBirthday(rs.getDate(count++));
				Teacher.setPhoneNumber(rs.getString(count++));
				Teacher.setAddress(rs.getString(count++));
				Teacher.setSalary(rs.getFloat(count++));
				Teacher.setRemark(rs.getString(count++));
				Teacher.setStateId(rs.getInt(count++));
				Teacher.setTypeId(rs.getInt(count++));
				Teacher.setTeacherState(new TeacherStateDao().select(Teacher.getStateId()));
				Teacher.setTeacherType(new TeacherTypeDao().select(Teacher.getTypeId()));
				return Teacher;
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
	public TeacherBean select(String Number) throws SQLException {
		int count = 1;
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // Ҫִ�е�sql���
							"FROM Teachers " +
							"WHERE Number = ? ";		//ע�⡰����
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(1,Number); // �ֶ���Ŵ�1��ʼ	
			rs = st.executeQuery(); // ����������ѯ���
			
			if (rs.next()) {		//ֻ����һ����ֻ������
				TeacherBean Teacher = new TeacherBean();
				Teacher.setTeacherId(rs.getInt(count++));				
				Teacher.setNumber(rs.getString(count++));
				Teacher.setName(rs.getString(count++));
				Teacher.setGender(rs.getString(count++));
				Teacher.setBirthday(rs.getDate(count++));
				Teacher.setPhoneNumber(rs.getString(count++));
				Teacher.setAddress(rs.getString(count++));
				Teacher.setSalary(rs.getFloat(count++));
				Teacher.setRemark(rs.getString(count++));
				Teacher.setStateId(rs.getInt(count++));
				Teacher.setTypeId(rs.getInt(count++));
				Teacher.setTeacherState(new TeacherStateDao().select(Teacher.getStateId()));
				Teacher.setTeacherType(new TeacherTypeDao().select(Teacher.getTypeId()));
				return Teacher;
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
	public ArrayList<TeacherBean> select() throws SQLException {
		
		ArrayList<TeacherBean> Teachers = new ArrayList<TeacherBean>();
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // Ҫִ�е�sql���
							"FROM Teachers " ;
			st = conn.prepareStatement(strsql); // ����ִ����
			rs = st.executeQuery(); // ����������ѯ���
			
			while (rs.next()) {		//ֻ����һ����ֻ�������������ݷ�����
				int count = 1;
				TeacherBean Teacher = new TeacherBean();
				Teacher.setTeacherId(rs.getInt(count++));				
				Teacher.setNumber(rs.getString(count++));
				Teacher.setName(rs.getString(count++));
				Teacher.setGender(rs.getString(count++));
				Teacher.setBirthday(rs.getDate(count++));
				Teacher.setPhoneNumber(rs.getString(count++));
				Teacher.setAddress(rs.getString(count++));
				Teacher.setSalary(rs.getFloat(count++));
				Teacher.setRemark(rs.getString(count++));
				Teacher.setStateId(rs.getInt(count++));
				Teacher.setTypeId(rs.getInt(count++));
				Teacher.setTeacherState(new TeacherStateDao().select(Teacher.getStateId()));
				Teacher.setTeacherType(new TeacherTypeDao().select(Teacher.getTypeId()));
				Teachers.add(Teacher);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		
		return Teachers;
	}
}
