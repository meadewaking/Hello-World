package net.sycu.meade.dao;

import java.sql.*;
import java.util.ArrayList;

import net.sycu.meade.common.MyConnection;
import net.sycu.meade.entity.StudentStateBean;

//数据访问层用来访问数据库，每一个数据访问类对应一个实体类
public class StudentStateDao {
	// 添加一条数据
	Connection conn = null;		//声明连接
	PreparedStatement st = null;		//声明语句
	ResultSet rs = null;		//声明结果集
	
	public int insert(StudentStateBean StudentState) throws SQLException {

		try {
			conn = MyConnection.getConnection();

			String strsql = "INSERT INTO StudentStates " + // 要执行的sql语句
							"VALUES (null, ?, ?, ?) ";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(1, StudentState.getName());
			st.setBoolean(2, StudentState.isInschool());
			st.setString(3, StudentState.getDescription()); // 实际执行语句
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
	public int insert(ArrayList<StudentStateBean> StudentStates) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			conn.setAutoCommit(false);
			int rowcount = 0;
			
			for (int j = 0; j < StudentStates.size(); j++) {
				int count = 1;
				String strsql = "INSERT INTO Students " + // 要执行的sql语句
								"VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?) ";
				st = conn.prepareStatement(strsql); // 定义执行器
				st.setString(count++, StudentStates.get(j).getName());
				st.setBoolean(count++, StudentStates.get(j).isInschool());
				st.setString(count++, StudentStates.get(j).getDescription()); // 实际执行语句
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
	public int delete(StudentStateBean StudentState) throws SQLException {

		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM StudentStates " + // 要执行的sql语句
							"WHERE Name = ？";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(1, StudentState.getName()); // 字段序号从1开始
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
	public int delete(int StudentStateId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM StudentStates " + // 要执行的sql语句
							"WHERE StudentStateId = ? ";		//注意“？”
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setInt(1,StudentStateId); // 字段序号从1开始	
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
			String strsql = "DELETE FROM StudentStates " + // 要执行的sql语句
							"WHERE Name = ？";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(1, name); // 字段序号从1开始
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
	public int delete(ArrayList<StudentStateBean> StudentStates) throws SQLException {
		
		int[] StudentStateIds = new int [StudentStates.size()];
		for (int i = 0; i < StudentStates.size(); i++) {
			StudentStateIds[i] = StudentStates.get(i).getStudentStateId();
			
		}
		return delete(StudentStateIds);
	}

	// 删除多条数据,根据主键删除
	public int delete(int[] StudentStateIds) throws SQLException {
		
		String str = " -1";
		for (int i = 0; i < StudentStateIds.length; i++) {
			str += ", " + StudentStateIds[i];
		}
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM StudentStates " + // 要执行的sql语句
							"WHERE StudentStateId IN (" + str + ") ";		//注意“？”
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
	public int updata(StudentStateBean StudentState) throws SQLException {

		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE StudentStates " + // 要执行的sql语句
							"SET Name = ? ,Inschool = ?, Description = ? " + 
							"WHERE StudentStateId = ?";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(1, StudentState.getName());
			st.setBoolean(2, StudentState.isInschool());
			st.setString(3, StudentState.getDescription());
			st.setInt(4, StudentState.getStudentStateId());
			return st.executeUpdate(); // 实际执行语句
		} catch (SQLException e) {
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
	}

	// 修改一条数据,可以修改主键
	public int updata(StudentStateBean StudentState, int StudentStateId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE StudentStates " + // 要执行的sql语句
							"SET StudentStateId = ? ,Name = ? ,Inschool = ?, Description = ? " + 
							"WHERE StudentStateId = ?";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(1, StudentState.getName());
			st.setBoolean(2, StudentState.isInschool());
			st.setString(3, StudentState.getDescription());
			st.setInt(4, StudentState.getStudentStateId());
			st.setInt(5, StudentStateId);
			st.executeUpdate(); // 实际执行语句
		} catch (SQLException e) {
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		return 0;
	}

	// 根据主键查询
	public StudentStateBean select(int StudentStateId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // 要执行的sql语句
							"FROM StudentStates " +
							"WHERE StudentStateId = ? ";		//注意“？”
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setInt(1,StudentStateId); // 字段序号从1开始	
			rs = st.executeQuery(); // 结果集缓存查询结果
			
			if (rs.next()) {		//只缓存一条，只读方法
				StudentStateBean studentstate = new StudentStateBean();
				studentstate.setStudentStateId(rs.getInt(1));
				studentstate.setName(rs.getString(2));
				studentstate.setInschool(rs.getBoolean(3));
				studentstate.setDescription(rs.getString(4));
				
				return studentstate;
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		
		return null;
	}

	// 根据唯一键查询
	public StudentStateBean select(String name) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // 要执行的sql语句
							"FROM StudentStates " +
							"WHERE Name = ? ";		//注意“？”
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(1,name); // 字段序号从1开始	
			rs = st.executeQuery(); // 结果集缓存查询结果
			
			if (rs.next()) {		//只缓存一条，只读方法
				StudentStateBean studentstate = new StudentStateBean();
				studentstate.setStudentStateId(rs.getInt(1));
				studentstate.setName(rs.getString(2));
				studentstate.setInschool(rs.getBoolean(3));
				studentstate.setDescription(rs.getString(4));
				
				return studentstate;
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
	public ArrayList<StudentStateBean> select() throws SQLException {
		
		ArrayList<StudentStateBean> studentstates = new ArrayList<StudentStateBean>();
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // 要执行的sql语句
							"FROM StudentStates " ;
			st = conn.prepareStatement(strsql); // 定义执行器
			rs = st.executeQuery(); // 结果集缓存查询结果
			
			while (rs.next()) {		//只缓存一条，只读方法，有内容返回真
				StudentStateBean studentstate = new StudentStateBean();
				studentstate.setStudentStateId(rs.getInt(1));
				studentstate.setName(rs.getString(2));
				studentstate.setInschool(rs.getBoolean(3));
				studentstate.setDescription(rs.getString(4));
				
				studentstates.add(studentstate);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		
		return studentstates;
	}
}
