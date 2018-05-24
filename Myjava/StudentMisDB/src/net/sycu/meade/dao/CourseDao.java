package net.sycu.meade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.sycu.meade.common.MyConnection;
import net.sycu.meade.entity.CourseBean;

public class CourseDao {
	// ���һ������
	Connection conn = null;		//��������
	PreparedStatement st = null;		//�������
	ResultSet rs = null;		//���������
	
	public int insert(CourseBean Course) throws SQLException {

		try {
			conn = MyConnection.getConnection();

			String strsql = "INSERT INTO Courses " + // Ҫִ�е�sql���
							"VALUES (null, ?, ?, ?, ?, ?, ?, ?) ";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(1, Course.getName());
			st.setString(2, Course.getNumber());
			st.setInt(3, Course.getTerm()); // ʵ��ִ�����
			st.setFloat(4, Course.getCredit());
			st.setFloat(5, Course.getHours());
			st.setString(6, Course.getDescription());
			st.setInt(7, Course.getCourseTypeId());
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
	public int insert(ArrayList<CourseBean> Courses) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			conn.setAutoCommit(false);
			int rowcount = 0;
			
			for (int j = 0; j < Courses.size(); j++) {
				int count = 1;
				String strsql = "INSERT INTO Students " + // Ҫִ�е�sql���
								"VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
				st = conn.prepareStatement(strsql); // ����ִ����
				st.setString(1, Courses.get(j).getName());
				st.setString(2, Courses.get(j).getNumber());
				st.setInt(3, Courses.get(j).getTerm()); // ʵ��ִ�����
				st.setFloat(4, Courses.get(j).getCredit());
				st.setFloat(5, Courses.get(j).getHours());
				st.setString(6, Courses.get(j).getDescription());
				st.setInt(7, Courses.get(j).getCourseTypeId());
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
	public int delete(CourseBean Course) throws SQLException {

		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM Courses " + // Ҫִ�е�sql���
							"WHERE Name = ��";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(1, Course.getName()); // �ֶ���Ŵ�1��ʼ
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
	public int delete(int CourseId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM Courses " + // Ҫִ�е�sql���
							"WHERE CourseId = ? ";		//ע�⡰����
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setInt(1,CourseId); // �ֶ���Ŵ�1��ʼ	
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
			String strsql = "DELETE FROM Courses " + // Ҫִ�е�sql���
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
	public int delete(ArrayList<CourseBean> Courses) throws SQLException {
		
		int[] CourseIds = new int [Courses.size()];
		for (int i = 0; i < Courses.size(); i++) {
			CourseIds[i] = Courses.get(i).getCourseId();
			
		}
		return delete(CourseIds);
	}

	// ɾ����������,��������ɾ��
	public int delete(int[] CourseIds) throws SQLException {
		
		String str = " -1";
		for (int i = 0; i < CourseIds.length; i++) {
			str += ", " + CourseIds[i];
		}
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM Courses " + // Ҫִ�е�sql���
							"WHERE CourseId IN (" + str + ") ";		//ע�⡰����
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
	public int updata(CourseBean Course) throws SQLException {

		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE Courses " + // Ҫִ�е�sql���
							"SET Name = ? ,Number = ?, Term = ? ,Credit = ?, Hours = ?, Description = ?, CourseTypeId = ? " + 
							"WHERE CourseId = ?";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(1, Course.getName());
			st.setString(2, Course.getNumber());
			st.setInt(3, Course.getTerm()); // ʵ��ִ�����
			st.setFloat(4, Course.getCredit());
			st.setFloat(5, Course.getHours());
			st.setString(6, Course.getDescription());
			st.setInt(7, Course.getCourseTypeId());
			st.setInt(8, Course.getCourseId());
			return st.executeUpdate(); // ʵ��ִ�����
		} catch (SQLException e) {
			System.out.println("�޸Ŀγ���Ϣʱ����");
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
	}

	// �޸�һ������,�����޸�����
	public int updata(CourseBean Course, int CourseId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE Courses " + // Ҫִ�е�sql���
							"SET CourseId = ? ,Name = ? ,Number = ?, Gender = ? ,Birthday = ?, PhoneNumber = ? Address = ? Remark = ? StateId = ? " + 
							"WHERE CourseId = ?";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setInt(1, Course.getCourseId());
			st.setString(2, Course.getName());
			st.setString(3, Course.getNumber());
			st.setInt(4, Course.getTerm()); // ʵ��ִ�����
			st.setFloat(5, Course.getCredit());
			st.setFloat(6, Course.getHours());
			st.setString(7, Course.getDescription());
			st.setInt(8, Course.getCourseTypeId());
			st.setInt(9, Course.getCourseId());
			st.executeUpdate(); // ʵ��ִ�����
		} catch (SQLException e) {
			System.out.println("�޸Ŀγ���Ϣʱ����");
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		return 0;
	}

	// ����������ѯ
	public CourseBean select(int CourseId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // Ҫִ�е�sql���
							"FROM Courses " +
							"WHERE CourseId = ? ";		//ע�⡰����
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setInt(1,CourseId); // �ֶ���Ŵ�1��ʼ	
			rs = st.executeQuery(); // ����������ѯ���
			
			if (rs.next()) {		//ֻ����һ����ֻ������
				CourseBean Course = new CourseBean();
				Course.setCourseId(rs.getInt(1));
				Course.setName(rs.getString(2));
				Course.setNumber(rs.getString(3));
				Course.setTerm(rs.getInt(4));
				Course.setCredit(rs.getFloat(5));
				Course.setHours(rs.getFloat(6));
				Course.setDescription(rs.getString(7));
				Course.setCourseTypeId(rs.getInt(8));
				Course.setCourseType(new CourseTypeDao().select(Course.getCourseTypeId()));
				return Course;
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
	public CourseBean select(String name) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // Ҫִ�е�sql���
							"FROM Courses " +
							"WHERE Name = ? ";		//ע�⡰����
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(1,name); // �ֶ���Ŵ�1��ʼ	
			rs = st.executeQuery(); // ����������ѯ���
			
			if (rs.next()) {		//ֻ����һ����ֻ������
				CourseBean Course = new CourseBean();
				Course.setCourseId(rs.getInt(1));
				Course.setName(rs.getString(2));
				Course.setNumber(rs.getString(3));
				Course.setTerm(rs.getInt(4));
				Course.setCredit(rs.getFloat(5));
				Course.setHours(rs.getFloat(6));
				Course.setDescription(rs.getString(7));
				Course.setCourseTypeId(rs.getInt(8));
				Course.setCourseType(new CourseTypeDao().select(Course.getCourseTypeId()));
				return Course;
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
	public ArrayList<CourseBean> select() throws SQLException {
		
		ArrayList<CourseBean> Courses = new ArrayList<CourseBean>();
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // Ҫִ�е�sql���
							"FROM Courses " ;
			st = conn.prepareStatement(strsql); // ����ִ����
			rs = st.executeQuery(); // ����������ѯ���
			
			while (rs.next()) {		//ֻ����һ����ֻ�������������ݷ�����
				CourseBean Course = new CourseBean();
				Course.setCourseId(rs.getInt(1));
				Course.setName(rs.getString(2));
				Course.setNumber(rs.getString(3));
				Course.setTerm(rs.getInt(4));
				Course.setCredit(rs.getFloat(5));
				Course.setHours(rs.getFloat(6));
				Course.setDescription(rs.getString(7));
				Course.setCourseTypeId(rs.getInt(8));
				Course.setCourseType(new CourseTypeDao().select(Course.getCourseTypeId()));
				Courses.add(Course);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		
		return Courses;
	}
}
