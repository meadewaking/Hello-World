package net.sycu.meade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.sycu.meade.common.MyConnection;
import net.sycu.meade.entity.CourseBean;

public class CourseDao {
	// 添加一条数据
	Connection conn = null;		//声明连接
	PreparedStatement st = null;		//声明语句
	ResultSet rs = null;		//声明结果集
	
	public int insert(CourseBean Course) throws SQLException {

		try {
			conn = MyConnection.getConnection();

			String strsql = "INSERT INTO Courses " + // 要执行的sql语句
							"VALUES (null, ?, ?, ?, ?, ?, ?, ?) ";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(1, Course.getName());
			st.setString(2, Course.getNumber());
			st.setInt(3, Course.getTerm()); // 实际执行语句
			st.setFloat(4, Course.getCredit());
			st.setFloat(5, Course.getHours());
			st.setString(6, Course.getDescription());
			st.setInt(7, Course.getCourseTypeId());
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
	public int insert(ArrayList<CourseBean> Courses) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			conn.setAutoCommit(false);
			int rowcount = 0;
			
			for (int j = 0; j < Courses.size(); j++) {
				int count = 1;
				String strsql = "INSERT INTO Students " + // 要执行的sql语句
								"VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
				st = conn.prepareStatement(strsql); // 定义执行器
				st.setString(1, Courses.get(j).getName());
				st.setString(2, Courses.get(j).getNumber());
				st.setInt(3, Courses.get(j).getTerm()); // 实际执行语句
				st.setFloat(4, Courses.get(j).getCredit());
				st.setFloat(5, Courses.get(j).getHours());
				st.setString(6, Courses.get(j).getDescription());
				st.setInt(7, Courses.get(j).getCourseTypeId());
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

	// 删除一条数据
	public int delete(CourseBean Course) throws SQLException {

		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM Courses " + // 要执行的sql语句
							"WHERE Name = ？";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(1, Course.getName()); // 字段序号从1开始
			st.executeUpdate(); // 实际执行语句
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		return 0;
	}

	// 删除一条数据,根据主键删除
	public int delete(int CourseId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM Courses " + // 要执行的sql语句
							"WHERE CourseId = ? ";		//注意“？”
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setInt(1,CourseId); // 字段序号从1开始	
			return st.executeUpdate(); // 实际执行语句
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		
	}

	// 根据唯一键删除
	public int delete(String name) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM Courses " + // 要执行的sql语句
							"WHERE Name = ？";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(1, name); // 字段序号从1开始
			st.executeUpdate(); // 实际执行语句
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		return 0;
	}

	// 删除多条数据
	public int delete(ArrayList<CourseBean> Courses) throws SQLException {
		
		int[] CourseIds = new int [Courses.size()];
		for (int i = 0; i < Courses.size(); i++) {
			CourseIds[i] = Courses.get(i).getCourseId();
			
		}
		return delete(CourseIds);
	}

	// 删除多条数据,根据主键删除
	public int delete(int[] CourseIds) throws SQLException {
		
		String str = " -1";
		for (int i = 0; i < CourseIds.length; i++) {
			str += ", " + CourseIds[i];
		}
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM Courses " + // 要执行的sql语句
							"WHERE CourseId IN (" + str + ") ";		//注意“？”
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
	public int updata(CourseBean Course) throws SQLException {

		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE Courses " + // 要执行的sql语句
							"SET Name = ? ,Number = ?, Term = ? ,Credit = ?, Hours = ?, Description = ?, CourseTypeId = ? " + 
							"WHERE CourseId = ?";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(1, Course.getName());
			st.setString(2, Course.getNumber());
			st.setInt(3, Course.getTerm()); // 实际执行语句
			st.setFloat(4, Course.getCredit());
			st.setFloat(5, Course.getHours());
			st.setString(6, Course.getDescription());
			st.setInt(7, Course.getCourseTypeId());
			st.setInt(8, Course.getCourseId());
			return st.executeUpdate(); // 实际执行语句
		} catch (SQLException e) {
			System.out.println("修改课程信息时出错。");
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
	}

	// 修改一条数据,可以修改主键
	public int updata(CourseBean Course, int CourseId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE Courses " + // 要执行的sql语句
							"SET CourseId = ? ,Name = ? ,Number = ?, Gender = ? ,Birthday = ?, PhoneNumber = ? Address = ? Remark = ? StateId = ? " + 
							"WHERE CourseId = ?";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setInt(1, Course.getCourseId());
			st.setString(2, Course.getName());
			st.setString(3, Course.getNumber());
			st.setInt(4, Course.getTerm()); // 实际执行语句
			st.setFloat(5, Course.getCredit());
			st.setFloat(6, Course.getHours());
			st.setString(7, Course.getDescription());
			st.setInt(8, Course.getCourseTypeId());
			st.setInt(9, Course.getCourseId());
			st.executeUpdate(); // 实际执行语句
		} catch (SQLException e) {
			System.out.println("修改课程信息时出错。");
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		return 0;
	}

	// 根据主键查询
	public CourseBean select(int CourseId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // 要执行的sql语句
							"FROM Courses " +
							"WHERE CourseId = ? ";		//注意“？”
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setInt(1,CourseId); // 字段序号从1开始	
			rs = st.executeQuery(); // 结果集缓存查询结果
			
			if (rs.next()) {		//只缓存一条，只读方法
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

	// 根据唯一键查询
	public CourseBean select(String name) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // 要执行的sql语句
							"FROM Courses " +
							"WHERE Name = ? ";		//注意“？”
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(1,name); // 字段序号从1开始	
			rs = st.executeQuery(); // 结果集缓存查询结果
			
			if (rs.next()) {		//只缓存一条，只读方法
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

	// 查询全部数据
	public ArrayList<CourseBean> select() throws SQLException {
		
		ArrayList<CourseBean> Courses = new ArrayList<CourseBean>();
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // 要执行的sql语句
							"FROM Courses " ;
			st = conn.prepareStatement(strsql); // 定义执行器
			rs = st.executeQuery(); // 结果集缓存查询结果
			
			while (rs.next()) {		//只缓存一条，只读方法，有内容返回真
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
