package net.sycu.meade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.sycu.meade.common.MyConnection;
import net.sycu.meade.entity.StudyCourseBean;

public class StudyCourseDao {
	// 添加一条数据
	Connection conn = null;		//声明连接
	PreparedStatement st = null;		//声明语句
	ResultSet rs = null;		//声明结果集
	
	public int insert(StudyCourseBean StudyCourse) throws SQLException {

		try {
			conn = MyConnection.getConnection();

			String strsql = "INSERT INTO StudyCourses " + // 要执行的sql语句
							"VALUES (null, ?, ?, ?, ?, ?) ";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setInt(1, StudyCourse.getStudentId());
			st.setInt(2, StudyCourse.getCourseId());
			st.setTimestamp(3, StudyCourse.getExamDateTime());
			st.setFloat(4, StudyCourse.getScore()); // 实际执行语句
			st.setString(5, StudyCourse.getDescription());
			return st.executeUpdate(); // 返回操作数
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
	}

	// 添加多条数据
	public int insert(ArrayList<StudyCourseBean> StudyCourses) throws SQLException {
		try {
			conn = MyConnection.getConnection();
			conn.setAutoCommit(false);
			int rowcount = 0;
			
			for (int j = 0; j < StudyCourses.size(); j++) {
				int count = 1;
				String strsql = "INSERT INTO StudyCourses " + // 要执行的sql语句
								"VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
				st = conn.prepareStatement(strsql); // 定义执行器
				st.setInt(count++, StudyCourses.get(j).getStudentId());
				st.setInt(count++, StudyCourses.get(j).getCourseId());
				st.setTimestamp(count++, StudyCourses.get(j).getExamDateTime());
				st.setFloat(count++, StudyCourses.get(j).getScore());
				st.setString(count++, StudyCourses.get(j).getDescription());
				
				rowcount += st.executeUpdate();
			}
			conn.commit();
			return rowcount; // 返回操作数
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

	// 删除一条数据,根据主键删除
	public int delete(int StudyCourseId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM StudyCourses " + // 要执行的sql语句
							"WHERE StudyCourseId = ? ";		//注意“？”
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setInt(1,StudyCourseId); // 字段序号从1开始	
			return st.executeUpdate(); // 实际执行语句
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		
	}

	// 删除多条数据
	public int delete(ArrayList<StudyCourseBean> StudyCourses) throws SQLException {
		
		int[] StudyCourseIds = new int [StudyCourses.size()];
		for (int i = 0; i < StudyCourses.size(); i++) {
			StudyCourseIds[i] = StudyCourses.get(i).getStudyCourseId();
			
		}
		return delete(StudyCourseIds);
	}

	// 删除多条数据,根据主键删除
	public int delete(int[] StudyCourseIds) throws SQLException {
		
		String str = " -1";
		for (int i = 0; i < StudyCourseIds.length; i++) {
			str += ", " + StudyCourseIds[i];
		}
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM StudyCourses " + // 要执行的sql语句
							"WHERE StudyCourseId IN (" + str + ") ";		//注意“？”
			st = conn.prepareStatement(strsql); // 定义执行器
			return st.executeUpdate(); // 实际执行语句
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		
	}

	// 修改一条数据,不能修改主键
	public int updata(StudyCourseBean StudyCourse) throws SQLException {

		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE StudyCourses " + // 要执行的sql语句
							"SET StudentId = ?, CourseId = ? ,ExamDateTime = ?, Score = ?, Description = ? " + 
							"WHERE StudyCourseId = ?";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setInt(1, StudyCourse.getStudentId());
			st.setInt(2, StudyCourse.getCourseId());
			st.setTimestamp(3, StudyCourse.getExamDateTime());
			st.setFloat(4, StudyCourse.getScore()); // 实际执行语句
			st.setString(5, StudyCourse.getDescription());
			st.setInt(6, StudyCourse.getStudyCourseId());
			return st.executeUpdate(); // 实际执行语句
		} catch (SQLException e) {
			System.out.println("修改选课信息时出错。");
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
	}

	// 修改一条数据,可以修改主键
	public int updata(StudyCourseBean StudyCourse, int StudyCourseId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE StudyCourses " + // 要执行的sql语句
							"SET StudyCourseId = ? ,StudentId = ?, CourseId = ? ,ExamDateTime = ?, Score = ? Description = ? " + 
							"WHERE StudyCourseId = ?";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setInt(1, StudyCourse.getStudyCourseId());
			st.setInt(2, StudyCourse.getStudentId());
			st.setInt(3, StudyCourse.getCourseId());
			st.setTimestamp(4, StudyCourse.getExamDateTime());
			st.setFloat(5, StudyCourse.getScore()); // 实际执行语句
			st.setString(6, StudyCourse.getDescription());
			st.setInt(7, StudyCourse.getStudyCourseId());
			st.executeUpdate(); // 实际执行语句
		} catch (SQLException e) {
			System.out.println("修改选课信息时出错。");
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		return 0;
	}

	// 根据主键查询
	public StudyCourseBean select(int StudyCourseId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // 要执行的sql语句
							"FROM StudyCourses " +
							"WHERE StudyCourseId = ? ";		//注意“？”
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setInt(1,StudyCourseId); // 字段序号从1开始	
			rs = st.executeQuery(); // 结果集缓存查询结果
			
			if (rs.next()) {		//只缓存一条，只读方法
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

	// 根据唯一键查询
//	public StudyCourseBean select(String name) throws SQLException {
//		
//		try {
//			conn = MyConnection.getConnection();
//			String strsql = "SELECT * " + // 要执行的sql语句
//							"FROM StudyCourses " +
//							"WHERE Name = ? ";		//注意“？”
//			st = conn.prepareStatement(strsql); // 定义执行器
//			st.setString(1,name); // 字段序号从1开始	
//			rs = st.executeQuery(); // 结果集缓存查询结果
//			
//			if (rs.next()) {		//只缓存一条，只读方法
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

	// 查询全部数据
	public ArrayList<StudyCourseBean> select() throws SQLException {
		
		ArrayList<StudyCourseBean> StudyCourses = new ArrayList<StudyCourseBean>();
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // 要执行的sql语句
							"FROM StudyCourses " ;
			st = conn.prepareStatement(strsql); // 定义执行器
			rs = st.executeQuery(); // 结果集缓存查询结果
			
			while (rs.next()) {		//只缓存一条，只读方法，有内容返回真
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
