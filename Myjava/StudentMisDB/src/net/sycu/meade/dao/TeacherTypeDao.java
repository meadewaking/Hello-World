package net.sycu.meade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.sycu.meade.common.MyConnection;
import net.sycu.meade.entity.TeacherTypeBean;

public class TeacherTypeDao {
	// 添加一条数据
	Connection conn = null;		//声明连接
	PreparedStatement st = null;		//声明语句
	ResultSet rs = null;		//声明结果集
	
	public int insert(TeacherTypeBean TeacherType) throws SQLException {

		try {
			conn = MyConnection.getConnection();

			String strsql = "INSERT INTO TeacherTypes " + // 要执行的sql语句
							"VALUES (null, ?, ?) ";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(1, TeacherType.getName());
			st.setString(2, TeacherType.getDescription()); // 实际执行语句
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
	public int insert(ArrayList<TeacherTypeBean> TeacherTypes) throws SQLException {
		int count = 1;
		try {
			conn = MyConnection.getConnection();
			conn.setAutoCommit(false);
			int rowcount = 0;
			
			for (int j = 0; j < TeacherTypes.size(); j++) {
				
				String strsql = "INSERT INTO Students " + // 要执行的sql语句
								"VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?) ";
				st = conn.prepareStatement(strsql); // 定义执行器
				st.setString(count++, TeacherTypes.get(j).getName());
				st.setString(count++, TeacherTypes.get(j).getDescription()); // 实际执行语句
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
	public int delete(TeacherTypeBean TeacherType) throws SQLException {

		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM TeacherTypes " + // 要执行的sql语句
							"WHERE Name = ？";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(1, TeacherType.getName()); // 字段序号从1开始
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
	public int delete(int TeacherTypeId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM TeacherTypes " + // 要执行的sql语句
							"WHERE TeacherTypeId = ? ";		//注意“？”
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setInt(1,TeacherTypeId); // 字段序号从1开始	
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
			String strsql = "DELETE FROM TeacherTypes " + // 要执行的sql语句
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
	public int delete(ArrayList<TeacherTypeBean> TeacherTypes) throws SQLException {
		
		int[] TeacherTypeIds = new int [TeacherTypes.size()];
		for (int i = 0; i < TeacherTypes.size(); i++) {
			TeacherTypeIds[i] = TeacherTypes.get(i).getTeacherTypeId();
			
		}
		return delete(TeacherTypeIds);
	}

	// 删除多条数据,根据主键删除
	public int delete(int[] TeacherTypeIds) throws SQLException {
		
		String str = " -1";
		for (int i = 0; i < TeacherTypeIds.length; i++) {
			str += ", " + TeacherTypeIds[i];
		}
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM TeacherTypes " + // 要执行的sql语句
							"WHERE TeacherTypeId IN (" + str + ") ";		//注意“？”
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
	public int updata(TeacherTypeBean TeacherType) throws SQLException {

		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE TeacherTypes " + // 要执行的sql语句
							"SET Name = ? , Description = ? " + 
							"WHERE TeacherTypeId = ?";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(1, TeacherType.getName());
			st.setString(2, TeacherType.getDescription());
			st.setInt(3, TeacherType.getTeacherTypeId());
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
	public int updata(TeacherTypeBean TeacherType, int TeacherTypeId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE TeacherTypes " + // 要执行的sql语句
							"SET TeacherTypeId = ? ,Name = ? , Description = ? " + 
							"WHERE TeacherTypeId = ?";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(1, TeacherType.getName());
			st.setString(2, TeacherType.getDescription());
			st.setInt(3, TeacherType.getTeacherTypeId());
			st.setInt(4, TeacherTypeId);
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
	public TeacherTypeBean select(int TeacherTypeId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // 要执行的sql语句
							"FROM TeacherTypes " +
							"WHERE TeacherTypeId = ? ";		//注意“？”
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setInt(1,TeacherTypeId); // 字段序号从1开始	
			rs = st.executeQuery(); // 结果集缓存查询结果
			
			if (rs.next()) {		//只缓存一条，只读方法
				TeacherTypeBean TeacherType = new TeacherTypeBean();
				TeacherType.setTeacherTypeId(rs.getInt(1));
				TeacherType.setName(rs.getString(2));
				TeacherType.setDescription(rs.getString(3));
				
				return TeacherType;
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
	public TeacherTypeBean select(String name) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // 要执行的sql语句
							"FROM TeacherTypes " +
							"WHERE Name = ? ";		//注意“？”
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(1,name); // 字段序号从1开始	
			rs = st.executeQuery(); // 结果集缓存查询结果
			
			if (rs.next()) {		//只缓存一条，只读方法
				TeacherTypeBean TeacherType = new TeacherTypeBean();
				TeacherType.setTeacherTypeId(rs.getInt(1));
				TeacherType.setName(rs.getString(2));
				TeacherType.setDescription(rs.getString(3));
				
				return TeacherType;
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
	public ArrayList<TeacherTypeBean> select() throws SQLException {
		
		ArrayList<TeacherTypeBean> TeacherTypes = new ArrayList<TeacherTypeBean>();
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // 要执行的sql语句
							"FROM TeacherTypes " ;
			st = conn.prepareStatement(strsql); // 定义执行器
			rs = st.executeQuery(); // 结果集缓存查询结果
			
			while (rs.next()) {		//只缓存一条，只读方法，有内容返回真
				TeacherTypeBean TeacherType = new TeacherTypeBean();
				TeacherType.setTeacherTypeId(rs.getInt(1));
				TeacherType.setName(rs.getString(2));
				TeacherType.setDescription(rs.getString(3));
				
				TeacherTypes.add(TeacherType);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		
		return TeacherTypes;
	}
}
