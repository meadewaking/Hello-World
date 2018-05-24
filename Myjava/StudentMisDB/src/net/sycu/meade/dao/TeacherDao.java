package net.sycu.meade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.sycu.meade.common.MyConnection;
import net.sycu.meade.entity.TeacherBean;

public class TeacherDao {
	// 添加一条数据
	Connection conn = null;		//声明连接
	PreparedStatement st = null;		//声明语句
	ResultSet rs = null;		//声明结果集
	
	public int insert(TeacherBean Teacher) throws SQLException {
		int count = 1;
		try {
			conn = MyConnection.getConnection();

			String strsql = "INSERT INTO Teachers " + // 要执行的sql语句
							"VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(count++, Teacher.getName());
			st.setString(count++, Teacher.getNumber());
			st.setString(count++, Teacher.getGender()); // 实际执行语句
			st.setDate(count++, Teacher.getBirthday());
			st.setString(count++, Teacher.getPhoneNumber());
			st.setString(count++, Teacher.getAddress());
			st.setFloat(count++, Teacher.getSalary());
			st.setString(count++, Teacher.getRemark());
			st.setInt(count++, Teacher.getStateId());
			st.setInt(count++, Teacher.getTypeId());
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
	public int insert(ArrayList<TeacherBean> Teachers) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			conn.setAutoCommit(false);
			int rowcount = 0;
			
			for (int j = 0; j < Teachers.size(); j++) {
				int count = 1;
				String strsql = "INSERT INTO Teachers " + // 要执行的sql语句
								"VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
				st = conn.prepareStatement(strsql); // 定义执行器
				st.setString(count++, Teachers.get(j).getName());
				st.setString(count++, Teachers.get(j).getNumber());
				st.setString(count++, Teachers.get(j).getGender()); // 实际执行语句
				st.setDate(count++, Teachers.get(j).getBirthday());
				st.setString(count++, Teachers.get(j).getPhoneNumber());
				st.setString(count++, Teachers.get(j).getAddress());
				st.setFloat(count++, Teachers.get(j).getSalary());
				st.setString(count++, Teachers.get(j).getRemark());
				st.setInt(count++, Teachers.get(j).getStateId());
				st.setInt(count++, Teachers.get(j).getTypeId());
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
	public int delete(TeacherBean Teacher) throws SQLException {

		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM Teachers " + // 要执行的sql语句
							"WHERE Name = ？";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(1, Teacher.getName()); // 字段序号从1开始
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
	public int delete(int TeacherId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM Teachers " + // 要执行的sql语句
							"WHERE TeacherId = ? ";		//注意“？”
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setInt(1,TeacherId); // 字段序号从1开始	
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
	public int delete(String Number) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM Teachers " + // 要执行的sql语句
							"WHERE Number = ？";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(1, Number); // 字段序号从1开始
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
	public int delete(ArrayList<TeacherBean> Teachers) throws SQLException {
		
		int[] TeacherIds = new int [Teachers.size()];
		for (int i = 0; i < Teachers.size(); i++) {
			TeacherIds[i] = Teachers.get(i).getTeacherId();
			
		}
		return delete(TeacherIds);
	}

	// 删除多条数据,根据主键删除
	public int delete(int[] TeacherIds) throws SQLException {
		
		String str = " -1";
		for (int i = 0; i < TeacherIds.length; i++) {
			str += ", " + TeacherIds[i];
		}
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM Teachers " + // 要执行的sql语句
							"WHERE TeacherId IN (" + str + ") ";		//注意“？”
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
	public int updata(TeacherBean Teacher) throws SQLException {
		int count = 1;
		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE Teachers " + // 要执行的sql语句
							"SET Name = ? ,Number = ?, Gender = ? ,Birthday = ?, PhoneNumber = ?, Address = ?, Salary = ?, Remark = ?, StateId = ?, TypeId = ? " + 
							"WHERE TeacherId = ?";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(count++, Teacher.getName());
			st.setString(count++, Teacher.getNumber());
			st.setString(count++, Teacher.getGender()); // 实际执行语句
			st.setDate(count++, Teacher.getBirthday());
			st.setString(count++, Teacher.getPhoneNumber());
			st.setString(count++, Teacher.getAddress());
			st.setFloat(count++, Teacher.getSalary());
			st.setString(count++, Teacher.getRemark());
			st.setInt(count++, Teacher.getStateId());
			st.setInt(count++, Teacher.getTypeId());
			st.setInt(count++, Teacher.getTeacherId());
			return st.executeUpdate(); // 实际执行语句
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
	}

	// 修改一条数据,可以修改主键
	public int updata(TeacherBean Teacher, int TeacherId) throws SQLException {
		int count = 1;
		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE Teachers " + // 要执行的sql语句
							"SET Name = ? ,Number = ?, Gender = ? ,Birthday = ?, PhoneNumber = ?, Address = ?, Salary = ?, Remark = ?, StateId = ?, TypeId = ? " + 
							"WHERE TeacherId = ?";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(count++, Teacher.getName());
			st.setString(count++, Teacher.getNumber());
			st.setString(count++, Teacher.getGender()); // 实际执行语句
			st.setDate(count++, Teacher.getBirthday());
			st.setString(count++, Teacher.getPhoneNumber());
			st.setString(count++, Teacher.getAddress());
			st.setFloat(count++, Teacher.getSalary());
			st.setString(count++, Teacher.getRemark());
			st.setInt(count++, Teacher.getStateId());
			st.setInt(count++, Teacher.getTypeId());
			st.setInt(count++, Teacher.getTeacherId());
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

	// 根据主键查询
	public TeacherBean select(int TeacherId) throws SQLException {
		int count = 1;
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // 要执行的sql语句
							"FROM Teachers " +
							"WHERE TeacherId = ? ";		//注意“？”
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setInt(1,TeacherId); // 字段序号从1开始	
			rs = st.executeQuery(); // 结果集缓存查询结果
			
			if (rs.next()) {		//只缓存一条，只读方法
				TeacherBean Teacher = new TeacherBean();
				Teacher.setTeacherId(rs.getInt(count++));				
				Teacher.setNumber(rs.getString(count++));
				Teacher.setName(rs.getString(count++));
				Teacher.setGender(rs.getString(count++));
				Teacher.setBirthday(rs.getDate(count++));
				Teacher.setPhoneNumber(rs.getString(count++));
				Teacher.setAddress(rs.getString(count++));
				Teacher.setSalary(rs.getFloat(count++));
				Teacher.setRemark(rs.getString(count++));
				Teacher.setStateId(rs.getInt(count++));
				Teacher.setTypeId(rs.getInt(count++));
				Teacher.setTeacherState(new TeacherStateDao().select(Teacher.getStateId()));
				Teacher.setTeacherType(new TeacherTypeDao().select(Teacher.getTypeId()));
				return Teacher;
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
	public TeacherBean select(String Number) throws SQLException {
		int count = 1;
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // 要执行的sql语句
							"FROM Teachers " +
							"WHERE Number = ? ";		//注意“？”
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(1,Number); // 字段序号从1开始	
			rs = st.executeQuery(); // 结果集缓存查询结果
			
			if (rs.next()) {		//只缓存一条，只读方法
				TeacherBean Teacher = new TeacherBean();
				Teacher.setTeacherId(rs.getInt(count++));				
				Teacher.setNumber(rs.getString(count++));
				Teacher.setName(rs.getString(count++));
				Teacher.setGender(rs.getString(count++));
				Teacher.setBirthday(rs.getDate(count++));
				Teacher.setPhoneNumber(rs.getString(count++));
				Teacher.setAddress(rs.getString(count++));
				Teacher.setSalary(rs.getFloat(count++));
				Teacher.setRemark(rs.getString(count++));
				Teacher.setStateId(rs.getInt(count++));
				Teacher.setTypeId(rs.getInt(count++));
				Teacher.setTeacherState(new TeacherStateDao().select(Teacher.getStateId()));
				Teacher.setTeacherType(new TeacherTypeDao().select(Teacher.getTypeId()));
				return Teacher;
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
	public ArrayList<TeacherBean> select() throws SQLException {
		
		ArrayList<TeacherBean> Teachers = new ArrayList<TeacherBean>();
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // 要执行的sql语句
							"FROM Teachers " ;
			st = conn.prepareStatement(strsql); // 定义执行器
			rs = st.executeQuery(); // 结果集缓存查询结果
			
			while (rs.next()) {		//只缓存一条，只读方法，有内容返回真
				int count = 1;
				TeacherBean Teacher = new TeacherBean();
				Teacher.setTeacherId(rs.getInt(count++));				
				Teacher.setNumber(rs.getString(count++));
				Teacher.setName(rs.getString(count++));
				Teacher.setGender(rs.getString(count++));
				Teacher.setBirthday(rs.getDate(count++));
				Teacher.setPhoneNumber(rs.getString(count++));
				Teacher.setAddress(rs.getString(count++));
				Teacher.setSalary(rs.getFloat(count++));
				Teacher.setRemark(rs.getString(count++));
				Teacher.setStateId(rs.getInt(count++));
				Teacher.setTypeId(rs.getInt(count++));
				Teacher.setTeacherState(new TeacherStateDao().select(Teacher.getStateId()));
				Teacher.setTeacherType(new TeacherTypeDao().select(Teacher.getTypeId()));
				Teachers.add(Teacher);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		
		return Teachers;
	}
}
