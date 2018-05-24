package net.sycu.meade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.sycu.meade.common.MyConnection;
import net.sycu.meade.entity.TeacherStateBean;

public class TeacherStateDao {
	// 添加一条数据
	Connection conn = null;		//声明连接
	PreparedStatement st = null;		//声明语句
	ResultSet rs = null;		//声明结果集
	
	public int insert(TeacherStateBean TeacherState) throws SQLException {

		try {
			conn = MyConnection.getConnection();

			String strsql = "INSERT INTO TeacherStates " + // 要执行的sql语句
							"VALUES (null, ?, ?, ?) ";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(1, TeacherState.getName());
			st.setBoolean(2, TeacherState.isInschool());
			st.setString(3, TeacherState.getDescription()); // 实际执行语句
			return st.executeUpdate(); // 返回操作数
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("添加学生状态信息时出错。");
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
	}

	// 添加多条数据
	public int insert(ArrayList<TeacherStateBean> TeacherStates) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			conn.setAutoCommit(false);
			int rowcount = 0;
			
			for (int j = 0; j < TeacherStates.size(); j++) {
				int count = 1;
				String strsql = "INSERT INTO Students " + // 要执行的sql语句
								"VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?) ";
				st = conn.prepareStatement(strsql); // 定义执行器
				st.setString(count++, TeacherStates.get(j).getName());
				st.setBoolean(count++, TeacherStates.get(j).isInschool());
				st.setString(count++, TeacherStates.get(j).getDescription()); // 实际执行语句
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
	public int delete(TeacherStateBean TeacherState) throws SQLException {

		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM TeacherStates " + // 要执行的sql语句
							"WHERE Name = ？";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(1, TeacherState.getName()); // 字段序号从1开始
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
	public int delete(int TeacherStateId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM TeacherStates " + // 要执行的sql语句
							"WHERE TeacherStateId = ? ";		//注意“？”
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setInt(1,TeacherStateId); // 字段序号从1开始	
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
			String strsql = "DELETE FROM TeacherStates " + // 要执行的sql语句
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
	public int delete(ArrayList<TeacherStateBean> TeacherStates) throws SQLException {
		
		int[] TeacherStateIds = new int [TeacherStates.size()];
		for (int i = 0; i < TeacherStates.size(); i++) {
			TeacherStateIds[i] = TeacherStates.get(i).getTeacherStateId();
			
		}
		return delete(TeacherStateIds);
	}

	// 删除多条数据,根据主键删除
	public int delete(int[] TeacherStateIds) throws SQLException {
		
		String str = " -1";
		for (int i = 0; i < TeacherStateIds.length; i++) {
			str += ", " + TeacherStateIds[i];
		}
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM TeacherStates " + // 要执行的sql语句
							"WHERE TeacherStateId IN (" + str + ") ";		//注意“？”
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
	public int updata(TeacherStateBean TeacherState) throws SQLException {

		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE TeacherStates " + // 要执行的sql语句
							"SET Name = ? ,Inschool = ?, Description = ? " + 
							"WHERE TeacherStateId = ?";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(1, TeacherState.getName());
			st.setBoolean(2, TeacherState.isInschool());
			st.setString(3, TeacherState.getDescription());
			st.setInt(4, TeacherState.getTeacherStateId());
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
	public int updata(TeacherStateBean TeacherState, int TeacherStateId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE TeacherStates " + // 要执行的sql语句
							"SET TeacherStateId = ? ,Name = ? ,Inschool = ?, Description = ? " + 
							"WHERE TeacherStateId = ?";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(1, TeacherState.getName());
			st.setBoolean(2, TeacherState.isInschool());
			st.setString(3, TeacherState.getDescription());
			st.setInt(4, TeacherState.getTeacherStateId());
			st.setInt(5, TeacherStateId);
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
	public TeacherStateBean select(int TeacherStateId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // 要执行的sql语句
							"FROM TeacherStates " +
							"WHERE TeacherStateId = ? ";		//注意“？”
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setInt(1,TeacherStateId); // 字段序号从1开始	
			rs = st.executeQuery(); // 结果集缓存查询结果
			
			if (rs.next()) {		//只缓存一条，只读方法
				TeacherStateBean Teacherstate = new TeacherStateBean();
				Teacherstate.setTeacherStateId(rs.getInt(1));
				Teacherstate.setName(rs.getString(2));
				Teacherstate.setInschool(rs.getBoolean(3));
				Teacherstate.setDescription(rs.getString(4));
				
				return Teacherstate;
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
	public TeacherStateBean select(String name) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // 要执行的sql语句
							"FROM TeacherStates " +
							"WHERE Name = ? ";		//注意“？”
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(1,name); // 字段序号从1开始	
			rs = st.executeQuery(); // 结果集缓存查询结果
			
			if (rs.next()) {		//只缓存一条，只读方法
				TeacherStateBean Teacherstate = new TeacherStateBean();
				Teacherstate.setTeacherStateId(rs.getInt(1));
				Teacherstate.setName(rs.getString(2));
				Teacherstate.setInschool(rs.getBoolean(3));
				Teacherstate.setDescription(rs.getString(4));
				
				return Teacherstate;
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
	public ArrayList<TeacherStateBean> select() throws SQLException {
		
		ArrayList<TeacherStateBean> Teacherstates = new ArrayList<TeacherStateBean>();
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // 要执行的sql语句
							"FROM TeacherStates " ;
			st = conn.prepareStatement(strsql); // 定义执行器
			rs = st.executeQuery(); // 结果集缓存查询结果
			
			while (rs.next()) {		//只缓存一条，只读方法，有内容返回真
				TeacherStateBean Teacherstate = new TeacherStateBean();
				Teacherstate.setTeacherStateId(rs.getInt(1));
				Teacherstate.setName(rs.getString(2));
				Teacherstate.setInschool(rs.getBoolean(3));
				Teacherstate.setDescription(rs.getString(4));
				
				Teacherstates.add(Teacherstate);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		
		return Teacherstates;
	}
}
