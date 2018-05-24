package net.sycu.meade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.sycu.meade.common.MyConnection;
import net.sycu.meade.entity.StudentBean;

public class StudentDao {
	// 添加一条数据
	Connection conn = null;		//声明连接
	PreparedStatement st = null;		//声明语句
	ResultSet rs = null;		//声明结果集
	
	public int insert(StudentBean Student) throws SQLException {
		int count = 1;
		try {
			conn = MyConnection.getConnection();

			String strsql = "INSERT INTO Students " + // 要执行的sql语句
							"VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(count++, Student.getName());
			st.setString(count++, Student.getNumber());
			st.setString(count++, Student.getGender()); // 实际执行语句
			st.setDate(count++, Student.getBirthday());
			st.setString(count++, Student.getPhoneNumber());
			st.setString(count++, Student.getAddress());
			st.setString(count++, Student.getRemark());
			st.setInt(count++, Student.getStateId());
			st.setInt(count++, Student.getClazzId());
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
	public int insert(ArrayList<StudentBean> Students) throws SQLException {
		try {
			conn = MyConnection.getConnection();
			conn.setAutoCommit(false);
			int rowcount = 0;
			
			for (int j = 0; j < Students.size(); j++) {
				int count = 1;
				String strsql = "INSERT INTO Students " + // 要执行的sql语句
								"VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
				st = conn.prepareStatement(strsql); // 定义执行器
				st.setString(count++, Students.get(j).getName());
				st.setString(count++, Students.get(j).getNumber());
				st.setString(count++, Students.get(j).getGender()); // 实际执行语句
				st.setDate(count++, Students.get(j).getBirthday());
				st.setString(count++, Students.get(j).getPhoneNumber());
				st.setString(count++, Students.get(j).getAddress());
				st.setString(count++, Students.get(j).getRemark());
				st.setInt(count++, Students.get(j).getStateId());
				st.setInt(count++, Students.get(j).getClazzId());
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
	public int delete(StudentBean Student) throws SQLException {
		int count = 1;
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM Students " + // 要执行的sql语句
							"WHERE Name = ？";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(count++, Student.getName()); // 字段序号从1开始
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
	public int delete(int StudentId) throws SQLException {
		int count = 1;
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM Students " + // 要执行的sql语句
							"WHERE StudentId = ? ";		//注意“？”
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setInt(count++,StudentId); // 字段序号从1开始	
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
		int count = 1;
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM Students " + // 要执行的sql语句
							"WHERE Name = ？";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(count++, name); // 字段序号从1开始
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
	public int delete(ArrayList<StudentBean> Students) throws SQLException {
		int[] StudentIds = new int [Students.size()];
		for (int i = 0; i < Students.size(); i++) {
			StudentIds[i] = Students.get(i).getStudentId();
			
		}
		return delete(StudentIds);
	}

	// 删除多条数据,根据主键删除
	public int delete(int[] StudentIds) throws SQLException {
		String str = " -1";
		for (int i = 0; i < StudentIds.length; i++) {
			str += ", " + StudentIds[i];
		}
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM Students " + // 要执行的sql语句
							"WHERE StudentId IN (" + str + ") ";		//注意“？”
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
	public int updata(StudentBean Student) throws SQLException {
		int count = 1;
		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE Students " + // 要执行的sql语句
							"SET Name = ? ,Number = ?, Gender = ? ,Birthday = ?, PhoneNumber = ?, Address = ?, Remark = ?, StateId = ?, ClazzId = ? " + 
							"WHERE StudentId = ?";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(count++, Student.getName());
			st.setString(count++, Student.getNumber());
			st.setString(count++, Student.getGender()); // 实际执行语句
			st.setDate(count++, Student.getBirthday());
			st.setString(count++, Student.getPhoneNumber());
			st.setString(count++, Student.getAddress());
			st.setString(count++, Student.getRemark());
			st.setInt(count++, Student.getStateId());
			st.setInt(count++, Student.getClazzId());
			st.setInt(count++, Student.getStudentId());
			return st.executeUpdate(); // 实际执行语句
		} catch (SQLException e) {
			System.out.println("修改学生信息时出错。");
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
	}

	// 修改一条数据,可以修改主键
	public int updata(StudentBean Student, int StudentId) throws SQLException {
		int count = 1;
		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE Students " + // 要执行的sql语句
							"SET StudentId = ? ,Name = ? ,Number = ?, Gender = ? ,Birthday = ?, PhoneNumber = ?, Address = ?, Remark = ?, StateId = ?, ClazzId = ? " + 
							"WHERE StudentId = ?";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setInt(count++, Student.getStudentId());
			st.setString(count++, Student.getName());
			st.setString(count++, Student.getNumber());
			st.setString(count++, Student.getGender()); // 实际执行语句
			st.setDate(count++, Student.getBirthday());
			st.setString(count++, Student.getPhoneNumber());
			st.setString(count++, Student.getAddress());
			st.setString(count++, Student.getRemark());
			st.setInt(count++, Student.getStateId());
			st.setInt(count++, Student.getClazzId());
			st.setInt(count++, Student.getStudentId());
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
	public StudentBean select(int StudentId) throws SQLException {
		int count = 1;
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // 要执行的sql语句
							"FROM Students " +
							"WHERE StudentId = ? ";		//注意“？”
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setInt(1,StudentId); // 字段序号从1开始	
			rs = st.executeQuery(); // 结果集缓存查询结果
			
			if (rs.next()) {		//只缓存一条，只读方法
				StudentBean Student = new StudentBean();
				Student.setStudentId(rs.getInt(count++));
				Student.setName(rs.getString(count++));
				Student.setNumber(rs.getString(count++));
				Student.setGender(rs.getString(count++));
				Student.setBirthday(rs.getDate(count++));
				Student.setPhoneNumber(rs.getString(count++));
				Student.setAddress(rs.getString(count++));
				Student.setRemark(rs.getString(count++));
				Student.setStateId(rs.getInt(count++));
				Student.setClazzId(rs.getInt(count++));
				Student.setStudentState(new StudentStateDao().select(Student.getStateId()));
				Student.setclazz(new ClazzDao().select(Student.getClazzId()));
				return Student;
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
	public StudentBean select(String name) throws SQLException {
		int count = 1;
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // 要执行的sql语句
							"FROM Students " +
							"WHERE Name = ? ";		//注意“？”
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(1,name); // 字段序号从1开始	
			rs = st.executeQuery(); // 结果集缓存查询结果
			
			if (rs.next()) {		//只缓存一条，只读方法
				StudentBean Student = new StudentBean();
				Student.setStudentId(rs.getInt(count++));
				Student.setName(rs.getString(count++));
				Student.setNumber(rs.getString(count++));
				Student.setGender(rs.getString(count++));
				Student.setBirthday(rs.getDate(count++));
				Student.setPhoneNumber(rs.getString(count++));
				Student.setAddress(rs.getString(count++));
				Student.setRemark(rs.getString(count++));
				Student.setStateId(rs.getInt(count++));
				Student.setClazzId(rs.getInt(count++));
				Student.setStudentState(new StudentStateDao().select(Student.getStateId()));
				Student.setclazz(new ClazzDao().select(Student.getClazzId()));
				return Student;
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
	public ArrayList<StudentBean> select() throws SQLException {
		
		ArrayList<StudentBean> Students = new ArrayList<StudentBean>();
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // 要执行的sql语句
							"FROM Students " ;
			st = conn.prepareStatement(strsql); // 定义执行器
			rs = st.executeQuery(); // 结果集缓存查询结果
			
			while (rs.next()) {		//只缓存一条，只读方法，有内容返回真
				int count = 1;
				StudentBean Student = new StudentBean();
				Student.setStudentId(rs.getInt(count++));
				Student.setName(rs.getString(count++));
				Student.setNumber(rs.getString(count++));
				Student.setGender(rs.getString(count++));
				Student.setBirthday(rs.getDate(count++));
				Student.setPhoneNumber(rs.getString(count++));
				Student.setAddress(rs.getString(count++));
				Student.setRemark(rs.getString(count++));
				Student.setStateId(rs.getInt(count++));
				Student.setClazzId(rs.getInt(count++));
				Student.setStudentState(new StudentStateDao().select(Student.getStateId()));
				Student.setclazz(new ClazzDao().select(Student.getClazzId()));
				Students.add(Student);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		
		return Students;
	}
}
