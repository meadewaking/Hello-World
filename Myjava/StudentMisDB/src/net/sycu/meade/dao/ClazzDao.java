package net.sycu.meade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import net.sycu.meade.common.MyConnection;
import net.sycu.meade.entity.ClazzBean;
//dao�����ע����ClazzdaoΪ׼,�����ͬ��
public class ClazzDao {
	// ���һ������
	Connection conn = null;		//��������
	PreparedStatement st = null;		//�������
	ResultSet rs = null;		//���������
	
	public int insert(ClazzBean Clazz) throws SQLException {

		try {
			conn = MyConnection.getConnection();		//������

			String strsql = "INSERT INTO Clazzes " + // Ҫִ�е�sql���
							"VALUES (null, ?, ?, ?, ?, ?) ";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(1, Clazz.getName());	//����ı�����Ŵ�1��ʼ
			st.setDate(2, Clazz.getBeginDateTime());
			st.setDate(3, Clazz.getEndDateTime()); // ʵ��ִ�����
			st.setString(4, Clazz.getDescription());
			if ( Clazz.getTeacherId() <= 0) {		//�����ֶ�Ϊ��
				st.setNull(5, Types.INTEGER);		//���Ϊ�ղ���int�Ϳ�ֵ
			} else {
				st.setInt(5, Clazz.getTeacherId());		//��Ϊ�����ֵ
			}
			
			return st.executeUpdate(); // ���ز�����,��ִ��sql���
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;		//�׳�sql�쳣
		} finally {
			MyConnection.closeStatement(st);		//�ر����
			MyConnection.closeConnection(conn);		//�ر�����
		}
	}

	// ��Ӷ�������
	public int insert(ArrayList<ClazzBean> Clazzes) throws SQLException {
		try {
			conn = MyConnection.getConnection();	//������
			conn.setAutoCommit(false);		//ȡ���Զ��ύ
			int rowcount = 0;		//ͳ��ִ������
			
			for (int j = 0; j <Clazzes.size(); j++) {
				String strsql = "INSERT INTO Clazzs " + // Ҫִ�е�sql���
								"VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?) ";
				st = conn.prepareStatement(strsql); // ����ִ����
				st.setString(1, Clazzes.get(j).getName());		//����ı�����Ŵ�1��ʼ
				st.setDate(2, Clazzes.get(j).getBeginDateTime());
				st.setDate(3, Clazzes.get(j).getEndDateTime()); // ʵ��ִ�����
				st.setString(4, Clazzes.get(j).getDescription());
				if ( Clazzes.get(j).getTeacherId() <= 0) {	//�����ֶ�Ϊ��
					st.setNull(5, Types.INTEGER);		//���Ϊ�ղ���int�Ϳ�ֵ
				} else {
					st.setInt(5, Clazzes.get(j).getTeacherId());	//��Ϊ�����ֵ
				}
				rowcount += st.executeUpdate();
			}
			conn.commit();		//�������ԭ����,��ʱ�ύ
			return rowcount; // ���ز�����
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();	//��������쳣,��ع�
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
	}

	// ɾ��һ������
	public int delete(ClazzBean Clazz) throws SQLException {

		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM Clazzes " + // Ҫִ�е�sql���
							"WHERE Name = ��";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(1, Clazz.getName()); // �ֶ���Ŵ�1��ʼ
			return st.executeUpdate(); // ʵ��ִ�����
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
	}

	// ɾ��һ������,��������ɾ��
	public int delete(int ClazzId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM Clazzes " + // Ҫִ�е�sql���
							"WHERE ClazzId = ? ";		//ע�⡰����
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setInt(1,ClazzId); // �ֶ���Ŵ�1��ʼ	
			return st.executeUpdate(); // ʵ��ִ�����
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		
	}

	// ����Ψһ��ɾ��
	public int delete(String name) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM Clazzes " + // Ҫִ�е�sql���
							"WHERE Name = ��";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(1, name); // �ֶ���Ŵ�1��ʼ
			return st.executeUpdate(); // ʵ��ִ�����
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
	}

	// ɾ����������
	public int delete(ArrayList<ClazzBean> Clazzes) throws SQLException {
		
		int[] ClazzIds = new int [Clazzes.size()];		//�������������������
		for (int i = 0; i < Clazzes.size(); i++) {
			ClazzIds[i] = Clazzes.get(i).getClazzId();		//�������浽������
			
		}
		return delete(ClazzIds);		//���ö���ɾ��,��������������
	}

	// ɾ����������,��������ɾ��
	public int delete(int[] ClazzIds) throws SQLException {
		
		String str = " -1";			//����sql����ֵΪ-1,����ȡ����-1,����Ϊ�ճ��쳣
		for (int i = 0; i < ClazzIds.length; i++) {
			str += ", " + ClazzIds[i];		//��sql������������
		}
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "DELETE FROM Clazzes " + // Ҫִ�е�sql���
							"WHERE ClazzId IN (" + str + ") ";		//ע�⡰����
			st = conn.prepareStatement(strsql); // ����ִ����
			return st.executeUpdate(); // ʵ��ִ�����
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
		
	}

	// �޸�һ������,�����޸�����
	public int updata(ClazzBean Clazz) throws SQLException {

		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE Clazzes " + // Ҫִ�е�sql���
							"SET Name = ? ,BeginDateTime = ?, EndDateTime = ? ,Description = ?, TeacherId = ? " + 
							"WHERE ClazzId = ?";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(1, Clazz.getName());
			st.setDate(2, Clazz.getBeginDateTime());
			st.setDate(3, Clazz.getEndDateTime()); // ʵ��ִ�����
			st.setString(4, Clazz.getDescription());
			if ( Clazz.getTeacherId() <= 0) {
				st.setNull(5, Types.INTEGER);//�����ֵ
			} else {
				st.setInt(5, Clazz.getTeacherId());
			}
			st.setInt(6, Clazz.getClazzId());
			return st.executeUpdate(); // ʵ��ִ�����
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
	}

	// �޸�һ������,�����޸�����
	public int updata(ClazzBean Clazz, int ClazzId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "UPDATE Clazzes " + // Ҫִ�е�sql���
							"SET ClazzId = ? ,Name = ? ,BeginDateTime = ?, EndDateTime = ? ,Description = ?, TeacherId = ? " + 
							"WHERE ClazzId = ?";
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setInt(1, Clazz.getClazzId());
			st.setString(2, Clazz.getName());
			st.setDate(3, Clazz.getBeginDateTime());
			st.setDate(4, Clazz.getEndDateTime()); // ʵ��ִ�����
			st.setString(5, Clazz.getDescription());
			if ( Clazz.getTeacherId() <= 0) {
				st.setNull(6, Types.INTEGER);
			} else {
				st.setInt(6, Clazz.getTeacherId());
			}
			st.setInt(7, Clazz.getClazzId());
			return st.executeUpdate(); // ʵ��ִ�����
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			MyConnection.closeStatement(st);
			MyConnection.closeConnection(conn);
		}
	}

	// ����������ѯ
	public ClazzBean select(int ClazzId) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // Ҫִ�е�sql���
							"FROM Clazzes " +
							"WHERE ClazzId = ? ";		//ע�⡰����
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setInt(1,ClazzId); // �ֶ���Ŵ�1��ʼ	
			rs = st.executeQuery(); // ����������ѯ���
			
			if (rs.next()) {		//ֻ����һ����ֻ������,��ѯ������᷵����ֵ
				ClazzBean Clazz = new ClazzBean();
				Clazz.setClazzId(rs.getInt(1));
				Clazz.setName(rs.getString(2));
				Clazz.setBeginDateTime(rs.getDate(3));
				Clazz.setEndDateTime(rs.getDate(4));
				Clazz.setDescription(rs.getString(5));
				Clazz.setTeacherId(rs.wasNull() ? 0 : rs.getInt(6));//3λ�����?:���Դ����ֵ
				Clazz.setTeacher(new TeacherDao().select(Clazz.getTeacherId()));//ͬʱ��ѯ�ӱ�����
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

	// ����Ψһ����ѯ
	public ClazzBean select(String name) throws SQLException {
		
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // Ҫִ�е�sql���
							"FROM Clazzes " +
							"WHERE Name = ? ";		//ע�⡰����
			st = conn.prepareStatement(strsql); // ����ִ����
			st.setString(1,name); // �ֶ���Ŵ�1��ʼ	
			rs = st.executeQuery(); // ����������ѯ���
			
			if (rs.next()) {		//ֻ����һ����ֻ������
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

	// ��ѯȫ������
	public ArrayList<ClazzBean> select() throws SQLException {
		
		ArrayList<ClazzBean> Clazzes = new ArrayList<ClazzBean>();
		try {
			conn = MyConnection.getConnection();
			String strsql = "SELECT * " + // Ҫִ�е�sql���
							"FROM Clazzes " ;
			st = conn.prepareStatement(strsql); // ����ִ����
			rs = st.executeQuery(); // ����������ѯ���
			
			while (rs.next()) {		//ֻ����һ����ֻ�������������ݷ�����
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
