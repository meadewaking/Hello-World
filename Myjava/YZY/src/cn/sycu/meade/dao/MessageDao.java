package cn.sycu.meade.dao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import cn.sycu.meade.common.MyConnection;
import cn.sycu.meade.entity.DepartmentBean;
import cn.sycu.meade.entity.MessageBean;
import cn.sycu.meade.entity.MessageSearcher;

public class MessageDao {
	LoginDao  loginDao = new LoginDao();
	DepartmentDao  departmentDao = new DepartmentDao();
	
	public int insert(MessageBean message) throws SQLException {
	Connection conn = null;
	PreparedStatement ps = null;
	try {
		String sql = " INSERT INTO Messages " + 
					" VALUES (null, ?, ?, ?, ?, ?, ?) ";
		int count = 1;
		conn = MyConnection.getConnection();
		ps = conn.prepareStatement(sql);
		ps.setString(count++, message.getTitle());
		ps.setString(count++, message.getContent());
		ps.setTimestamp(count++, message.getPublishDateTime());
		ps.setString(count++, message.getPublishIP());
		ps.setInt(count++, message.getPublisherId());
		if (message.getDepartmentId() == 0)
			ps.setNull(count++, Types.INTEGER);
		else
			ps.setInt(count++, message.getDepartmentId());
		
		return ps.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
		throw e;
	} finally {
		MyConnection.closeStatementAndConnection(ps, conn);
	}		
  }

	public int update(MessageBean message) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String sql = " UPDATE Messages " + 
						" SET Title = ?, Content = ?, PublishDateTime = ?, PublishIp = ?, " +
						" PublisherId = ?, DepartmentId = ? " +
						" WHERE MessageId = ? ";
			int count = 1;
			conn = MyConnection.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(count++, message.getTitle());
			ps.setString(count++, message.getContent());
			ps.setTimestamp(count++, message.getPublishDateTime());
			ps.setString(count++, message.getPublishIP());
			ps.setInt(count++, message.getPublisherId());
			if (message.getDepartmentId() == 0)
				ps.setNull(count++, Types.INTEGER);
			else
				ps.setInt(count++, message.getDepartmentId());
			
			ps.setInt(count++, message.getMessageId());
			
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(ps, conn);
		}		
	  }
	
	public MessageBean selectBytitle(String title) throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = " SELECT * FROM Messages " + 
						" WHERE Title = ? ";
			conn = MyConnection.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, title);
			
			rs = ps.executeQuery();			
			if (rs.next()) {
				MessageBean message = new MessageBean();
				int count = 1;
				message.setMessageId(rs.getInt(count++));
				message.setTitle(rs.getString(count++));
				message.setContent(rs.getString(count++));
				message.setPublishDateTime(rs.getTimestamp(count++));
				message.setPublishIP(rs.getString(count++));
				message.setPublisherId(rs.getInt(count++));
				
				int departmentId = rs.getInt(count++);
				message.setDepartmentId(rs.wasNull() ? 0 : departmentId);
				if (rs.wasNull())
					message.setDepartmentId(0);
				else
					message.setDepartmentId(departmentId);
				
				message.setPublisher(loginDao.select(message.getPublisherId()));
				message.setDepartment(departmentDao.select(message.getDepartmentId()));
				
				return message;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			MyConnection.closeResultSetAndStatementAndConnection(rs, ps, conn);
		}		
	}	
	
	public MessageBean select(int messageId) throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = " SELECT * FROM Messages " + 
						" WHERE MessageId = ? ";
			conn = MyConnection.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, messageId);
			
			rs = ps.executeQuery();			
			if (rs.next()) {
				MessageBean message = new MessageBean();
				int count = 1;
				message.setMessageId(rs.getInt(count++));
				message.setTitle(rs.getString(count++));
				message.setContent(rs.getString(count++));
				message.setPublishDateTime(rs.getTimestamp(count++));
				message.setPublishIP(rs.getString(count++));
				message.setPublisherId(rs.getInt(count++));
				
				int departmentId = rs.getInt(count++);
				message.setDepartmentId(rs.wasNull() ? 0 : departmentId);
				message.setPublisher(loginDao.select(message.getPublisherId()));
				message.setDepartment(departmentDao.select(message.getDepartmentId()));
				
				return message;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			MyConnection.closeResultSetAndStatementAndConnection(rs, ps, conn);
		}		
	}

	public List<MessageBean> select(MessageSearcher searcher, String sortString) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<MessageBean> messages = new ArrayList<MessageBean>();
		
		if (sortString == null || sortString.length() == 0)
			sortString = " MessageId ASC ";
		
		try {
			String sql = " SELECT * FROM Messages " + 
					" WHERE Title LIKE ? " +
					"  AND Content LIKE ? " +
					"  AND PublishIp LIKE ? ";
			
			if (searcher.getDepartmentId() != 0)			//�û�ֻ�ܿ������ŵ���Ϣ
				sql += "	AND DepartmentId = ? ";
			
			sql += " ORDER BY " + sortString;
			
			int count = 1;//����Ϊ�Ӳ�ѯ����  
			conn = MyConnection.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(count++, "%" + searcher.getTitle() + "%");//ģ����ѯ
			ps.setString(count++, "%" + searcher.getContent() + "%");
			ps.setString(count++, "%" + searcher.getPublishIP() + "%");//�Ӳ�ѯ����
			
			if (searcher.getDepartmentId() != 0)
				ps.setInt(count++, searcher.getDepartmentId());
			
			rs = ps.executeQuery();			
			while (rs.next()) {
				count = 1;
				MessageBean message = new MessageBean();
				message.setMessageId(rs.getInt(count++));
				message.setTitle(rs.getString(count++));
				message.setContent(rs.getString(count++));
				message.setPublishDateTime(rs.getTimestamp(count++));
				message.setPublishIP(rs.getString(count++));
				message.setPublisherId(rs.getInt(count++));
				
				int departmentId = rs.getInt(count++);
				message.setDepartmentId(rs.wasNull() ? 0 : departmentId);
			
				message.setPublisher(loginDao.select(message.getPublisherId()));
				message.setDepartment(departmentDao.select(message.getDepartmentId()));
			
				messages.add(message);
			}
			
			FileOutputStream fos = null; // ʵ���ļ������
			OutputStreamWriter osw = null;
			try {
				fos = new FileOutputStream("D:/Program Files (x86)/Apache Software Foundation/Tomcat 8.0/webapps/YZY/file/messages.csv"); // �򿪱����ļ�
				osw = new OutputStreamWriter(fos,"GB2312");
				osw.write("���ű��,����,����ʱ��,����IP,������,��������\r\n");
				for (int i = 0; i < messages.size(); i++) {
					osw.write(String.valueOf(messages.get(i).getDepartmentId()));
					osw.write(",");
					osw.write(messages.get(i).getTitle().length() > 0 ? messages.get(i).getTitle() + "," : "NULL,");
					osw.write(messages.get(i).getPublishDateTime().toString().length() > 0 ? messages.get(i).getPublishDateTime().toString() + "," : "NULL,");
					osw.write(messages.get(i).getPublishIP().length() > 0 ? messages.get(i).getPublishIP() + "," : "NULL,");
					osw.write(messages.get(i).getPublisher().getLoginName().length() > 0 ? messages.get(i).getPublisher().getLoginName() + "," : "NULL,");
					osw.write(messages.get(i).getDepartment().getDepartmentName().length() > 0 ? messages.get(i).getDepartment().getDepartmentName() + "\r\n" : "NULL\r\n");
				}
			} catch (IOException e) { // ������������쳣
				// TODO: handle exception
				e.printStackTrace(); // ��ӡ�쳣
			} finally { // ���մ���(finally�ؼ���һ���ᱻִ��)
				try {
					if (osw != null) {
						osw.close();
					}
				} catch (IOException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				try { // �ر�������
					if (fos != null) {
						fos.close();
					}
				} catch (IOException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			
			return messages;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			MyConnection.closeResultSetAndStatementAndConnection(rs, ps, conn);
		}
	}

	public int delete(int messageId) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String sql = " DELETE FROM Messages " + 
						" WHERE MessageId = ? ";
			int count = 1;
			conn = MyConnection.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(count++, messageId);
			
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(ps, conn);
		}	
	}
}

