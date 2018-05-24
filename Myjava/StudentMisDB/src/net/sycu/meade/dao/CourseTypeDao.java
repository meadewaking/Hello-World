package net.sycu.meade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.sycu.meade.common.MyConnection;
import net.sycu.meade.entity.CourseTypeBean;

public class CourseTypeDao {
	// 添加一条数据
	Connection conn = null;		//声明连接
	PreparedStatement st = null;		//声明语句
	ResultSet rs = null;		//声明结果集
	
	public int insert(CourseTypeBean CourseType) throws SQLException {

		try {
			conn = MyConnection.getConnection();

			String strsql = "INSERT INTO CourseTypes " + // 要执行的sql语句
							"VALUES (null, ?, ?) ";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(1, CourseType.getName());
			st.setString(2, CourseType.getDescription()); // 实际执行语句
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
	public int insert(ArrayList<CourseTypeBean> CourseTypes) throws SQLException {
		int count = 1;
		try {
			conn = MyConnection.getConnection();
			conn.setAutoCommit(false);
			int rowcount = 0;
			
			for (int j = 0; j < CourseTypes.size(); j++) {
				
				String strsql = "INSERT INTO Students " + // 要执行的sql语句
								"VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?) ";
				st = conn.prepareStatement(strsql); // 定义执行器
				st.setString(count++, CourseTypes.get(j).getName());
				st.setString(count++, CourseTypes.get(j).getDescription()); // 实际执行语句
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
	public int delete(CourseTypeBean CourseType) throws SQLException {

		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM CourseTypes " + // 要执行的sql语句
							"WHERE Name = ？";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(1, CourseType.getName()); // 字段序号从1开始
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
	public int delete(int CourseTypeId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM CourseTypes " + // 要执行的sql语句
							"WHERE CourseTypeId = ? ";		//注意“？”
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setInt(1,CourseTypeId); // 字段序号从1开始	
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
			String strsql = "DELETE FROM CourseTypes " + // 要执行的sql语句
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
	public int delete(ArrayList<CourseTypeBean> CourseTypes) throws SQLException {
		
		int[] CourseTypeIds = new int [CourseTypes.size()];
		for (int i = 0; i < CourseTypes.size(); i++) {
			CourseTypeIds[i] = CourseTypes.get(i).getCourseTypeId();
			
		}
		return delete(CourseTypeIds);
	}

	// 删除多条数据,根据主键删除
	public int delete(int[] CourseTypeIds) throws SQLException {
		
		String str = " -1";
		for (int i = 0; i < CourseTypeIds.length; i++) {
			str += ", " + CourseTypeIds[i];
		}
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM CourseTypes " + // 要执行的sql语句
							"WHERE CourseTypeId IN (" + str + ") ";		//注意“？”
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
	public int updata(CourseTypeBean CourseType) throws SQLException {

		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE CourseTypes " + // 要执行的sql语句
							"SET Name = ? , Description = ? " + 
							"WHERE CourseTypeId = ?";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(1, CourseType.getName());
			st.setString(2, CourseType.getDescription());
			st.setInt(3, CourseType.getCourseTypeId());
			return st.executeUpdate(); // 实际执行语句
		} catch (SQLException e) {
			System.out.println("修改学生状态信息时出错。");
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
	}

	// 修改一条数据,可以修改主键
	public int updata(CourseTypeBean CourseType, int CourseTypeId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE CourseTypes " + // 要执行的sql语句
							"SET CourseTypeId = ? ,Name = ? , Description = ? " + 
							"WHERE CourseTypeId = ?";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(1, CourseType.getName());
			st.setString(2, CourseType.getDescription());
			st.setInt(3, CourseType.getCourseTypeId());
			st.setInt(4, CourseTypeId);
			st.executeUpdate(); // 实际执行语句
		} catch (SQLException e) {
			System.out.println("修改学生状态信息时出错。");
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		return 0;
	}

	// 根据主键查询
	public CourseTypeBean select(int CourseTypeId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // 要执行的sql语句
							"FROM CourseTypes " +
							"WHERE CourseTypeId = ? ";		//注意“？”
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setInt(1,CourseTypeId); // 字段序号从1开始	
			rs = st.executeQuery(); // 结果集缓存查询结果
			
			if (rs.next()) {		//只缓存一条，只读方法
				CourseTypeBean CourseType = new CourseTypeBean();
				CourseType.setCourseTypeId(rs.getInt(1));
				CourseType.setName(rs.getString(2));
				CourseType.setDescription(rs.getString(3));
				
				return CourseType;
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
	public CourseTypeBean select(String name) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // 要执行的sql语句
							"FROM CourseTypes " +
							"WHERE Name = ? ";		//注意“？”
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(1,name); // 字段序号从1开始	
			rs = st.executeQuery(); // 结果集缓存查询结果
			
			if (rs.next()) {		//只缓存一条，只读方法
				CourseTypeBean CourseType = new CourseTypeBean();
				CourseType.setCourseTypeId(rs.getInt(1));
				CourseType.setName(rs.getString(2));
				CourseType.setDescription(rs.getString(3));
				
				return CourseType;
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
	public ArrayList<CourseTypeBean> select() throws SQLException {
		
		ArrayList<CourseTypeBean> CourseTypes = new ArrayList<CourseTypeBean>();
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // 要执行的sql语句
							"FROM CourseTypes " ;
			st = conn.prepareStatement(strsql); // 定义执行器
			rs = st.executeQuery(); // 结果集缓存查询结果
			
			while (rs.next()) {		//只缓存一条，只读方法，有内容返回真
				CourseTypeBean CourseType = new CourseTypeBean();
				CourseType.setCourseTypeId(rs.getInt(1));
				CourseType.setName(rs.getString(2));
				CourseType.setDescription(rs.getString(3));
				
				CourseTypes.add(CourseType);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		
		return CourseTypes;
	}
}
