package net.sycu.meade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.sycu.meade.common.MyConnection;
import net.sycu.meade.entity.TeachCourseBean;

public class TeachCourseDao {
	// 添加一条数据
	Connection conn = null;		//声明连接
	PreparedStatement st = null;		//声明语句
	ResultSet rs = null;		//声明结果集
	
	public int insert(TeachCourseBean TeachCourse) throws SQLException {

		try {
			conn = MyConnection.getConnection();

			String strsql = "INSERT INTO TeachCourses " + // 要执行的sql语句
							"VALUES (null, ?, ?, ?, ?, ?) ";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setInt(1, TeachCourse.getTeacherId());
			st.setInt(2, TeachCourse.getCourseId());
			st.setTimestamp(3, TeachCourse.getBeginDateTime());
			st.setTimestamp(4, TeachCourse.getEndDateTime()); // 实际执行语句
			st.setString(5, TeachCourse.getDescription());
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
	public int insert(ArrayList<TeachCourseBean> TeachCourses) throws SQLException {
		try {
			conn = MyConnection.getConnection();
			conn.setAutoCommit(false);
			int rowcount = 0;
			
			for (int j = 0; j < TeachCourses.size(); j++) {
				int count = 1;
				String strsql = "INSERT INTO TeachCourses " + // 要执行的sql语句
								"VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
				st = conn.prepareStatement(strsql); // 定义执行器
				st.setInt(count++, TeachCourses.get(j).getTeacherId());
				st.setInt(count++, TeachCourses.get(j).getCourseId());
				st.setTimestamp(count++, TeachCourses.get(j).getBeginDateTime());
				st.setTimestamp(count++, TeachCourses.get(j).getEndDateTime());
				st.setString(count++, TeachCourses.get(j).getDescription());
				
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
	public int delete(int TeachCourseId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM TeachCourses " + // 要执行的sql语句
							"WHERE TeachCourseId = ? ";		//注意“？”
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setInt(1,TeachCourseId); // 字段序号从1开始	
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
	public int delete(ArrayList<TeachCourseBean> TeachCourses) throws SQLException {
		
		int[] TeachCourseIds = new int [TeachCourses.size()];
		for (int i = 0; i < TeachCourses.size(); i++) {
			TeachCourseIds[i] = TeachCourses.get(i).getTeachCourseId();
			
		}
		return delete(TeachCourseIds);
	}

	// 删除多条数据,根据主键删除
	public int delete(int[] TeachCourseIds) throws SQLException {
		
		String str = " -1";
		for (int i = 0; i < TeachCourseIds.length; i++) {
			str += ", " + TeachCourseIds[i];
		}
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM TeachCourses " + // 要执行的sql语句
							"WHERE TeachCourseId IN (" + str + ") ";		//注意“？”
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
	public int updata(TeachCourseBean TeachCourse) throws SQLException {

		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE TeachCourses " + // 要执行的sql语句
							"SET TeacherId = ?, CourseId = ? ,BeginDateTime = ?, EndDateTime = ?, Description = ? " + 
							"WHERE TeachCourseId = ?";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setInt(1, TeachCourse.getTeacherId());
			st.setInt(2, TeachCourse.getCourseId());
			st.setTimestamp(3, TeachCourse.getBeginDateTime());
			st.setTimestamp(4, TeachCourse.getEndDateTime()); // 实际执行语句
			st.setString(5, TeachCourse.getDescription());
			st.setInt(6, TeachCourse.getTeachCourseId());
			return st.executeUpdate(); // 实际执行语句
		} catch (SQLException e) {
			System.out.println("修改授课信息时出错。");
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
	}

	// 修改一条数据,可以修改主键
	public int updata(TeachCourseBean TeachCourse, int TeachCourseId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE TeachCourses " + // 要执行的sql语句
							"SET TeachCourseId = ? ,TeacherId = ?, CourseId = ? ,BeginDateTime = ?, EndDateTime = ?, Description = ? " + 
							"WHERE TeachCourseId = ?";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setInt(1, TeachCourse.getTeachCourseId());
			st.setInt(2, TeachCourse.getTeacherId());
			st.setInt(3, TeachCourse.getCourseId());
			st.setTimestamp(4, TeachCourse.getBeginDateTime());
			st.setTimestamp(5, TeachCourse.getEndDateTime()); // 实际执行语句
			st.setString(6, TeachCourse.getDescription());
			st.setInt(7, TeachCourse.getTeachCourseId());
			return st.executeUpdate(); // 实际执行语句
		} catch (SQLException e) {
			System.out.println("修改授课信息时出错。");
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
	}

	// 根据主键查询
	public TeachCourseBean select(int TeachCourseId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // 要执行的sql语句
							"FROM TeachCourses " +
							"WHERE TeachCourseId = ? ";		//注意“？”
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setInt(1,TeachCourseId); // 字段序号从1开始	
			rs = st.executeQuery(); // 结果集缓存查询结果
			
			if (rs.next()) {		//只缓存一条，只读方法
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

	// 查询全部数据
	public ArrayList<TeachCourseBean> select() throws SQLException {
		
		ArrayList<TeachCourseBean> TeachCourses = new ArrayList<TeachCourseBean>();
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // 要执行的sql语句
							"FROM TeachCourses " ;
			st = conn.prepareStatement(strsql); // 定义执行器
			rs = st.executeQuery(); // 结果集缓存查询结果
			
			while (rs.next()) {		//只缓存一条，只读方法，有内容返回真
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
