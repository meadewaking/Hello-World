package net.sycu.meade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.sycu.meade.common.MyConnection;
import net.sycu.meade.entity.TeachCourseBean;

public class TeachCourseDao {
	// ���һ������
	Connection conn = null;		//��������
	PreparedStatement st = null;		//�������
	ResultSet rs = null;		//���������
	
	public int insert(TeachCourseBean TeachCourse) throws SQLException {

		try {
			conn = MyConnection.getConnection();

			String strsql = "INSERT INTO TeachCourses " + // Ҫִ�е�sql���
							"VALUES (null, ?, ?, ?, ?, ?) ";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setInt(1, TeachCourse.getTeacherId());
			st.setInt(2, TeachCourse.getCourseId());
			st.setTimestamp(3, TeachCourse.getBeginDateTime());
			st.setTimestamp(4, TeachCourse.getEndDateTime()); // ʵ��ִ�����
			st.setString(5, TeachCourse.getDescription());
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
	public int insert(ArrayList<TeachCourseBean> TeachCourses) throws SQLException {
		try {
			conn = MyConnection.getConnection();
			conn.setAutoCommit(false);
			int rowcount = 0;
			
			for (int j = 0; j < TeachCourses.size(); j++) {
				int count = 1;
				String strsql = "INSERT INTO TeachCourses " + // Ҫִ�е�sql���
								"VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
				st = conn.prepareStatement(strsql); // ����ִ����
				st.setInt(count++, TeachCourses.get(j).getTeacherId());
				st.setInt(count++, TeachCourses.get(j).getCourseId());
				st.setTimestamp(count++, TeachCourses.get(j).getBeginDateTime());
				st.setTimestamp(count++, TeachCourses.get(j).getEndDateTime());
				st.setString(count++, TeachCourses.get(j).getDescription());
				
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

	// ɾ��һ������,��������ɾ��
	public int delete(int TeachCourseId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM TeachCourses " + // Ҫִ�е�sql���
							"WHERE TeachCourseId = ? ";		//ע�⡰����
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setInt(1,TeachCourseId); // �ֶ���Ŵ�1��ʼ	
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
	public int delete(ArrayList<TeachCourseBean> TeachCourses) throws SQLException {
		
		int[] TeachCourseIds = new int [TeachCourses.size()];
		for (int i = 0; i < TeachCourses.size(); i++) {
			TeachCourseIds[i] = TeachCourses.get(i).getTeachCourseId();
			
		}
		return delete(TeachCourseIds);
	}

	// ɾ����������,��������ɾ��
	public int delete(int[] TeachCourseIds) throws SQLException {
		
		String str = " -1";
		for (int i = 0; i < TeachCourseIds.length; i++) {
			str += ", " + TeachCourseIds[i];
		}
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM TeachCourses " + // Ҫִ�е�sql���
							"WHERE TeachCourseId IN (" + str + ") ";		//ע�⡰����
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
	public int updata(TeachCourseBean TeachCourse) throws SQLException {

		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE TeachCourses " + // Ҫִ�е�sql���
							"SET TeacherId = ?, CourseId = ? ,BeginDateTime = ?, EndDateTime = ?, Description = ? " + 
							"WHERE TeachCourseId = ?";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setInt(1, TeachCourse.getTeacherId());
			st.setInt(2, TeachCourse.getCourseId());
			st.setTimestamp(3, TeachCourse.getBeginDateTime());
			st.setTimestamp(4, TeachCourse.getEndDateTime()); // ʵ��ִ�����
			st.setString(5, TeachCourse.getDescription());
			st.setInt(6, TeachCourse.getTeachCourseId());
			return st.executeUpdate(); // ʵ��ִ�����
		} catch (SQLException e) {
			System.out.println("�޸��ڿ���Ϣʱ����");
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
	}

	// �޸�һ������,�����޸�����
	public int updata(TeachCourseBean TeachCourse, int TeachCourseId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE TeachCourses " + // Ҫִ�е�sql���
							"SET TeachCourseId = ? ,TeacherId = ?, CourseId = ? ,BeginDateTime = ?, EndDateTime = ?, Description = ? " + 
							"WHERE TeachCourseId = ?";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setInt(1, TeachCourse.getTeachCourseId());
			st.setInt(2, TeachCourse.getTeacherId());
			st.setInt(3, TeachCourse.getCourseId());
			st.setTimestamp(4, TeachCourse.getBeginDateTime());
			st.setTimestamp(5, TeachCourse.getEndDateTime()); // ʵ��ִ�����
			st.setString(6, TeachCourse.getDescription());
			st.setInt(7, TeachCourse.getTeachCourseId());
			return st.executeUpdate(); // ʵ��ִ�����
		} catch (SQLException e) {
			System.out.println("�޸��ڿ���Ϣʱ����");
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
	}

	// ����������ѯ
	public TeachCourseBean select(int TeachCourseId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // Ҫִ�е�sql���
							"FROM TeachCourses " +
							"WHERE TeachCourseId = ? ";		//ע�⡰����
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setInt(1,TeachCourseId); // �ֶ���Ŵ�1��ʼ	
			rs = st.executeQuery(); // ����������ѯ���
			
			if (rs.next()) {		//ֻ����һ����ֻ������
				TeachCourseBean TeachCourse = new TeachCourseBean();
				TeachCourse.setTeachCourseId(rs.getInt(1));
				TeachCourse.setTeacherId(rs.getInt(2));
				TeachCourse.setCourseId(rs.getInt(3));
				TeachCourse.setBeginDateTime(rs.getTimestamp(4));
				TeachCourse.setEndDateTime(rs.getTimestamp(5));
				TeachCourse.setDescription(rs.getString(6));
				
				TeachCourse.setTeacher(new TeacherDao().select(TeachCourse.getTeacherId()));
				TeachCourse.setCourse(new CourseDao().select(TeachCourse.getCourseId()));
				return TeachCourse;
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
	public ArrayList<TeachCourseBean> select() throws SQLException {
		
		ArrayList<TeachCourseBean> TeachCourses = new ArrayList<TeachCourseBean>();
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // Ҫִ�е�sql���
							"FROM TeachCourses " ;
			st = conn.prepareStatement(strsql); // ����ִ����
			rs = st.executeQuery(); // ����������ѯ���
			
			while (rs.next()) {		//ֻ����һ����ֻ�������������ݷ�����
				TeachCourseBean TeachCourse = new TeachCourseBean();
				TeachCourse.setTeachCourseId(rs.getInt(1));
				TeachCourse.setTeacherId(rs.getInt(2));
				TeachCourse.setCourseId(rs.getInt(3));
				TeachCourse.setBeginDateTime(rs.getTimestamp(4));
				TeachCourse.setEndDateTime(rs.getTimestamp(5));
				TeachCourse.setDescription(rs.getString(6));
				
				TeachCourse.setTeacher(new TeacherDao().select(TeachCourse.getTeacherId()));
				TeachCourse.setCourse(new CourseDao().select(TeachCourse.getCourseId()));
				TeachCourses.add(TeachCourse);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		
		return TeachCourses;
	}
}
