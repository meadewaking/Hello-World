package net.sycu.meade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.sycu.meade.common.MyConnection;
import net.sycu.meade.entity.StudyCourseBean;

public class StudyCourseDao {
	// ���һ������
	Connection conn = null;		//��������
	PreparedStatement st = null;		//�������
	ResultSet rs = null;		//���������
	
	public int insert(StudyCourseBean StudyCourse) throws SQLException {

		try {
			conn = MyConnection.getConnection();

			String strsql = "INSERT INTO StudyCourses " + // Ҫִ�е�sql���
							"VALUES (null, ?, ?, ?, ?, ?) ";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setInt(1, StudyCourse.getStudentId());
			st.setInt(2, StudyCourse.getCourseId());
			st.setTimestamp(3, StudyCourse.getExamDateTime());
			st.setFloat(4, StudyCourse.getScore()); // ʵ��ִ�����
			st.setString(5, StudyCourse.getDescription());
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
	public int insert(ArrayList<StudyCourseBean> StudyCourses) throws SQLException {
		try {
			conn = MyConnection.getConnection();
			conn.setAutoCommit(false);
			int rowcount = 0;
			
			for (int j = 0; j < StudyCourses.size(); j++) {
				int count = 1;
				String strsql = "INSERT INTO StudyCourses " + // Ҫִ�е�sql���
								"VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
				st = conn.prepareStatement(strsql); // ����ִ����
				st.setInt(count++, StudyCourses.get(j).getStudentId());
				st.setInt(count++, StudyCourses.get(j).getCourseId());
				st.setTimestamp(count++, StudyCourses.get(j).getExamDateTime());
				st.setFloat(count++, StudyCourses.get(j).getScore());
				st.setString(count++, StudyCourses.get(j).getDescription());
				
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
	public int delete(int StudyCourseId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM StudyCourses " + // Ҫִ�е�sql���
							"WHERE StudyCourseId = ? ";		//ע�⡰����
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setInt(1,StudyCourseId); // �ֶ���Ŵ�1��ʼ	
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
	public int delete(ArrayList<StudyCourseBean> StudyCourses) throws SQLException {
		
		int[] StudyCourseIds = new int [StudyCourses.size()];
		for (int i = 0; i < StudyCourses.size(); i++) {
			StudyCourseIds[i] = StudyCourses.get(i).getStudyCourseId();
			
		}
		return delete(StudyCourseIds);
	}

	// ɾ����������,��������ɾ��
	public int delete(int[] StudyCourseIds) throws SQLException {
		
		String str = " -1";
		for (int i = 0; i < StudyCourseIds.length; i++) {
			str += ", " + StudyCourseIds[i];
		}
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM StudyCourses " + // Ҫִ�е�sql���
							"WHERE StudyCourseId IN (" + str + ") ";		//ע�⡰����
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
	public int updata(StudyCourseBean StudyCourse) throws SQLException {

		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE StudyCourses " + // Ҫִ�е�sql���
							"SET StudentId = ?, CourseId = ? ,ExamDateTime = ?, Score = ?, Description = ? " + 
							"WHERE StudyCourseId = ?";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setInt(1, StudyCourse.getStudentId());
			st.setInt(2, StudyCourse.getCourseId());
			st.setTimestamp(3, StudyCourse.getExamDateTime());
			st.setFloat(4, StudyCourse.getScore()); // ʵ��ִ�����
			st.setString(5, StudyCourse.getDescription());
			st.setInt(6, StudyCourse.getStudyCourseId());
			return st.executeUpdate(); // ʵ��ִ�����
		} catch (SQLException e) {
			System.out.println("�޸�ѡ����Ϣʱ����");
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
	}

	// �޸�һ������,�����޸�����
	public int updata(StudyCourseBean StudyCourse, int StudyCourseId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE StudyCourses " + // Ҫִ�е�sql���
							"SET StudyCourseId = ? ,StudentId = ?, CourseId = ? ,ExamDateTime = ?, Score = ? Description = ? " + 
							"WHERE StudyCourseId = ?";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setInt(1, StudyCourse.getStudyCourseId());
			st.setInt(2, StudyCourse.getStudentId());
			st.setInt(3, StudyCourse.getCourseId());
			st.setTimestamp(4, StudyCourse.getExamDateTime());
			st.setFloat(5, StudyCourse.getScore()); // ʵ��ִ�����
			st.setString(6, StudyCourse.getDescription());
			st.setInt(7, StudyCourse.getStudyCourseId());
			st.executeUpdate(); // ʵ��ִ�����
		} catch (SQLException e) {
			System.out.println("�޸�ѡ����Ϣʱ����");
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		return 0;
	}

	// ����������ѯ
	public StudyCourseBean select(int StudyCourseId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // Ҫִ�е�sql���
							"FROM StudyCourses " +
							"WHERE StudyCourseId = ? ";		//ע�⡰����
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setInt(1,StudyCourseId); // �ֶ���Ŵ�1��ʼ	
			rs = st.executeQuery(); // ����������ѯ���
			
			if (rs.next()) {		//ֻ����һ����ֻ������
				StudyCourseBean StudyCourse = new StudyCourseBean();
				StudyCourse.setStudyCourseId(rs.getInt(1));
				StudyCourse.setStudentId(rs.getInt(2));
				StudyCourse.setCourseId(rs.getInt(3));
				StudyCourse.setExamDateTime(rs.getTimestamp(4));
				StudyCourse.setScore(rs.getFloat(5));
				StudyCourse.setDescription(rs.getString(6));
				
				StudyCourse.setStudent(new StudentDao().select(StudyCourse.getStudentId()));
				StudyCourse.setCourse(new CourseDao().select(StudyCourse.getCourseId()));
				return StudyCourse;
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
//	public StudyCourseBean select(String name) throws SQLException {
//		
//		try {
//			conn = MyConnection.getConnection();
//			String strsql = "SELECT * " + // Ҫִ�е�sql���
//							"FROM StudyCourses " +
//							"WHERE Name = ? ";		//ע�⡰����
//			st = conn.prepareStatement(strsql); // ����ִ����
//			st.setString(1,name); // �ֶ���Ŵ�1��ʼ	
//			rs = st.executeQuery(); // ����������ѯ���
//			
//			if (rs.next()) {		//ֻ����һ����ֻ������
//				StudyCourseBean StudyCourse = new StudyCourseBean();
//				StudyCourse.setStudyCourseId(rs.getInt(1));
//				StudyCourse.setStudentId(rs.getInt(2));
//				StudyCourse.setCourseId(rs.getInt(3));
//				StudyCourse.setExamDateTime(rs.getTimestamp(4));
//				StudyCourse.setScore(rs.getFloat(5));
//				StudyCourse.setDescription(rs.getString(6));
//				
//				StudyCourse.setStudent(new StudentDao().select(StudyCourse.getStudentId()));
//				StudyCourse.setCourse(new CourseDao().select(StudyCourse.getCourseId()));
//				return StudyCourse;
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			throw e;
//		} finally {
//			MyConnection.closeStatement(st);
//			MyConnection.closeConnection(conn);
//		}
//		
//		return null;
//	}

	// ��ѯȫ������
	public ArrayList<StudyCourseBean> select() throws SQLException {
		
		ArrayList<StudyCourseBean> StudyCourses = new ArrayList<StudyCourseBean>();
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // Ҫִ�е�sql���
							"FROM StudyCourses " ;
			st = conn.prepareStatement(strsql); // ����ִ����
			rs = st.executeQuery(); // ����������ѯ���
			
			while (rs.next()) {		//ֻ����һ����ֻ�������������ݷ�����
				StudyCourseBean StudyCourse = new StudyCourseBean();
				StudyCourse.setStudyCourseId(rs.getInt(1));
				StudyCourse.setStudentId(rs.getInt(2));
				StudyCourse.setCourseId(rs.getInt(3));
				StudyCourse.setExamDateTime(rs.getTimestamp(4));
				StudyCourse.setScore(rs.getFloat(5));
				StudyCourse.setDescription(rs.getString(6));
				
				StudyCourse.setStudent(new StudentDao().select(StudyCourse.getStudentId()));
				StudyCourse.setCourse(new CourseDao().select(StudyCourse.getCourseId()));
				StudyCourses.add(StudyCourse);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		
		return StudyCourses;
	}
}
