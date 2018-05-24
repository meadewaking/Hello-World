package net.sycu.meade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import net.sycu.meade.common.MyConnection;
import net.sycu.meade.entity.ClazzBean;
//dao类代码注释以Clazzdao为准,其余层同此
public class ClazzDao {
	// 添加一条数据
	Connection conn = null;		//声明连接
	PreparedStatement st = null;		//声明语句
	ResultSet rs = null;		//声明结果集
	
	public int insert(ClazzBean Clazz) throws SQLException {

		try {
			conn = MyConnection.getConnection();		//打开连接

			String strsql = "INSERT INTO Clazzes " + // 要执行的sql语句
							"VALUES (null, ?, ?, ?, ?, ?) ";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(1, Clazz.getName());	//插入的变量序号从1开始
			st.setDate(2, Clazz.getBeginDateTime());
			st.setDate(3, Clazz.getEndDateTime()); // 实际执行语句
			st.setString(4, Clazz.getDescription());
			if ( Clazz.getTeacherId() <= 0) {		//处理字段为空
				st.setNull(5, Types.INTEGER);		//如果为空插入int型空值
			} else {
				st.setInt(5, Clazz.getTeacherId());		//不为空则插值
			}
			
			return st.executeUpdate(); // 返回操作数,并执行sql语句
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;		//抛出sql异常
		} finally {
			MyConnection.closeStatement(st);		//关闭语句
			MyConnection.closeConnection(conn);		//关闭连接
		}
	}

	// 添加多条数据
	public int insert(ArrayList<ClazzBean> Clazzes) throws SQLException {
		try {
			conn = MyConnection.getConnection();	//打开连接
			conn.setAutoCommit(false);		//取消自动提交
			int rowcount = 0;		//统计执行条数
			
			for (int j = 0; j <Clazzes.size(); j++) {
				String strsql = "INSERT INTO Clazzs " + // 要执行的sql语句
								"VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?) ";
				st = conn.prepareStatement(strsql); // 定义执行器
				st.setString(1, Clazzes.get(j).getName());		//插入的变量序号从1开始
				st.setDate(2, Clazzes.get(j).getBeginDateTime());
				st.setDate(3, Clazzes.get(j).getEndDateTime()); // 实际执行语句
				st.setString(4, Clazzes.get(j).getDescription());
				if ( Clazzes.get(j).getTeacherId() <= 0) {	//处理字段为空
					st.setNull(5, Types.INTEGER);		//如果为空插入int型空值
				} else {
					st.setInt(5, Clazzes.get(j).getTeacherId());	//不为空则插值
				}
				rowcount += st.executeUpdate();
			}
			conn.commit();		//事物具有原子性,此时提交
			return rowcount; // 返回操作数
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();	//如果出现异常,则回滚
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
	}

	// 删除一条数据
	public int delete(ClazzBean Clazz) throws SQLException {

		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM Clazzes " + // 要执行的sql语句
							"WHERE Name = ？";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(1, Clazz.getName()); // 字段序号从1开始
			return st.executeUpdate(); // 实际执行语句
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
	}

	// 删除一条数据,根据主键删除
	public int delete(int ClazzId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM Clazzes " + // 要执行的sql语句
							"WHERE ClazzId = ? ";		//注意“？”
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setInt(1,ClazzId); // 字段序号从1开始	
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
			String strsql = "DELETE FROM Clazzes " + // 要执行的sql语句
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
	public int delete(ArrayList<ClazzBean> Clazzes) throws SQLException {
		
		int[] ClazzIds = new int [Clazzes.size()];		//声明数组用来存放主键
		for (int i = 0; i < Clazzes.size(); i++) {
			ClazzIds[i] = Clazzes.get(i).getClazzId();		//将主键存到数组中
			
		}
		return delete(ClazzIds);		//调用多条删除,并传出主键数组
	}

	// 删除多条数据,根据主键删除
	public int delete(int[] ClazzIds) throws SQLException {
		
		String str = " -1";			//声明sql语句初值为-1,主键取不到-1,避免为空出异常
		for (int i = 0; i < ClazzIds.length; i++) {
			str += ", " + ClazzIds[i];		//向sql语句中添加主键
		}
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM Clazzes " + // 要执行的sql语句
							"WHERE ClazzId IN (" + str + ") ";		//注意“？”
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
	public int updata(ClazzBean Clazz) throws SQLException {

		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE Clazzes " + // 要执行的sql语句
							"SET Name = ? ,BeginDateTime = ?, EndDateTime = ? ,Description = ?, TeacherId = ? " + 
							"WHERE ClazzId = ?";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(1, Clazz.getName());
			st.setDate(2, Clazz.getBeginDateTime());
			st.setDate(3, Clazz.getEndDateTime()); // 实际执行语句
			st.setString(4, Clazz.getDescription());
			if ( Clazz.getTeacherId() <= 0) {
				st.setNull(5, Types.INTEGER);//处理空值
			} else {
				st.setInt(5, Clazz.getTeacherId());
			}
			st.setInt(6, Clazz.getClazzId());
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
	public int updata(ClazzBean Clazz, int ClazzId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE Clazzes " + // 要执行的sql语句
							"SET ClazzId = ? ,Name = ? ,BeginDateTime = ?, EndDateTime = ? ,Description = ?, TeacherId = ? " + 
							"WHERE ClazzId = ?";
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setInt(1, Clazz.getClazzId());
			st.setString(2, Clazz.getName());
			st.setDate(3, Clazz.getBeginDateTime());
			st.setDate(4, Clazz.getEndDateTime()); // 实际执行语句
			st.setString(5, Clazz.getDescription());
			if ( Clazz.getTeacherId() <= 0) {
				st.setNull(6, Types.INTEGER);
			} else {
				st.setInt(6, Clazz.getTeacherId());
			}
			st.setInt(7, Clazz.getClazzId());
			return st.executeUpdate(); // 实际执行语句
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
	}

	// 根据主键查询
	public ClazzBean select(int ClazzId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // 要执行的sql语句
							"FROM Clazzes " +
							"WHERE ClazzId = ? ";		//注意“？”
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setInt(1,ClazzId); // 字段序号从1开始	
			rs = st.executeQuery(); // 结果集缓存查询结果
			
			if (rs.next()) {		//只缓存一条，只读方法,查询到结果会返回真值
				ClazzBean Clazz = new ClazzBean();
				Clazz.setClazzId(rs.getInt(1));
				Clazz.setName(rs.getString(2));
				Clazz.setBeginDateTime(rs.getDate(3));
				Clazz.setEndDateTime(rs.getDate(4));
				Clazz.setDescription(rs.getString(5));
				Clazz.setTeacherId(rs.wasNull() ? 0 : rs.getInt(6));//3位运算符?:用以处理空值
				Clazz.setTeacher(new TeacherDao().select(Clazz.getTeacherId()));//同时查询子表内容
				return Clazz;
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
	public ClazzBean select(String name) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // 要执行的sql语句
							"FROM Clazzes " +
							"WHERE Name = ? ";		//注意“？”
			st = conn.prepareStatement(strsql); // 定义执行器
			st.setString(1,name); // 字段序号从1开始	
			rs = st.executeQuery(); // 结果集缓存查询结果
			
			if (rs.next()) {		//只缓存一条，只读方法
				ClazzBean Clazz = new ClazzBean();
				Clazz.setClazzId(rs.getInt(1));
				Clazz.setName(rs.getString(2));
				Clazz.setBeginDateTime(rs.getDate(3));
				Clazz.setEndDateTime(rs.getDate(4));
				Clazz.setDescription(rs.getString(5));
				Clazz.setTeacherId(rs.wasNull() ? 0 : rs.getInt(6));
				Clazz.setTeacher(new TeacherDao().select(Clazz.getTeacherId()));
				return Clazz;
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
	public ArrayList<ClazzBean> select() throws SQLException {
		
		ArrayList<ClazzBean> Clazzes = new ArrayList<ClazzBean>();
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // 要执行的sql语句
							"FROM Clazzes " ;
			st = conn.prepareStatement(strsql); // 定义执行器
			rs = st.executeQuery(); // 结果集缓存查询结果
			
			while (rs.next()) {		//只缓存一条，只读方法，有内容返回真
				ClazzBean Clazz = new ClazzBean();
				Clazz.setClazzId(rs.getInt(1));
				Clazz.setName(rs.getString(2));
				Clazz.setBeginDateTime(rs.getDate(3));
				Clazz.setEndDateTime(rs.getDate(4));
				Clazz.setDescription(rs.getString(5));
				Clazz.setTeacherId(rs.wasNull() ? 0 : rs.getInt(6));
				Clazz.setTeacher(new TeacherDao().select(Clazz.getTeacherId()));
				Clazzes.add(Clazz);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		
		return Clazzes;
	}
}
