package cn.sycu.meade.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cn.sycu.meade.common.MyConnection;
import cn.sycu.meade.entity.NoteBean;
import cn.sycu.meade.entity.NoteSearcher;

public class NoteDao {
	public static final int FIELDCOUNT = 8;
	
	//���һ��
	public int insert(NoteBean note) throws SQLException {
		Connection conn = null;//�������Ӷ���
		PreparedStatement ps = null;//����������
		String strSql = " INSERT INTO Notes "+ //������
						" VALUES(null, ?, ?, ?, ?, ?, ?, ?) ";
		try {
			//�������������,�����������һ�����
			int count = 1;
			conn =MyConnection.getConnection();//��ȡ���Ӷ���
			ps = conn.prepareStatement(strSql);//? �˴�Ҫ�Ӳ���
			ps.setString(count++, note.getTitle());
			ps.setString(count++, note.getContent());
			ps.setTimestamp(count++, note.getSendDateTime());
			ps.setString(count++, note.getSendIp());
			ps.setInt(count++, note.getSenderId());
			ps.setInt(count++, note.getSendeeId());
			ps.setBoolean(count++, note.isRead());
			return ps.executeUpdate();//���ظ�������
		} catch (SQLException e) {//sql�쳣
			e.printStackTrace();//��ʾ�쳣
			throw e;
		}finally{//ֻҪ��try����û��catch������û��finally�������߲���ͬʱû��
			MyConnection.closeStatementAndConnection(ps, conn);//�ر����Ӷ����������
		}
	}
	//��Ӷ���
	public int insert(ArrayList<NoteBean> notes) throws SQLException {
		Connection conn = null;//�������Ӷ���
		PreparedStatement ps = null;//����������
		String strSql = " INSERT INTO Notes "+ //������
						" VALUES(null, ?, ?, ?, ?, ?, ?, ?) ";
		try {
			//�������������,�����������һ�����
			conn =MyConnection.getConnection();//��ȡ���Ӷ���
			conn.setAutoCommit(false);//�ֶ��ύ
			int rowCount = 1;
			for(NoteBean note : notes){
				int count = 1;
				ps = conn.prepareStatement(strSql);
				ps.setString(count++, note.getTitle());
				ps.setString(count++, note.getContent());
				ps.setTimestamp(count++, note.getSendDateTime());
				ps.setString(count++, note.getSendIp());
				ps.setInt(count++, note.getSenderId());
				ps.setInt(count++, note.getSendeeId());
				ps.setBoolean(count++, note.isRead());
				rowCount += ps.executeUpdate();
			}
			conn.commit();
			return rowCount;
		} catch (SQLException e) {//sql�쳣
			e.printStackTrace();//��ʾ�쳣
			conn.rollback();//��������쳣��ع�
			throw e;
		}finally{//ֻҪ��try����û��catch������û��finally�������߲���ͬʱû��
			MyConnection.closeStatementAndConnection(ps, conn);//�ر����Ӷ����������
		}
	}
	//ɾ��һ��
	public int delete(NoteBean note) throws SQLException{
		return delete(note.getNoteId());
	}
	//ɾ��,������ɾ��
	public int delete(int noteId) throws SQLException{
		Connection conn = null;//�������Ӷ���
		PreparedStatement ps = null;//����������
		String strSql = " DELETE FROM Notes "+ 
						" WHERE NoteId = ? ";
		try {
			int count=1;
			//�������������,�����������һ�����
			conn =MyConnection.getConnection();//��ȡ���Ӷ���
			ps = conn.prepareStatement(strSql);//? �˴�Ҫ�Ӳ���
			ps.setInt(count++, noteId);//
			
			return ps.executeUpdate();//���ظ�������
		} catch (SQLException e) {//sql�쳣
			e.printStackTrace();//��ʾ�쳣
			throw e;
		}finally{//ֻҪ��try����û��catch������û��finally�������߲���ͬʱû��
			MyConnection.closeStatementAndConnection(ps, conn);//�ر����Ӷ����������
		}
	}
	//ɾ������
	public int delete(ArrayList<NoteBean> noteIds) throws SQLException{
		return 0;
	}
	//ɾ������
	public int delete(int [] noteIds) throws SQLException{
		Connection conn = null;//�������Ӷ���
		PreparedStatement ps = null;//����������
		String strSql = " DELETE FROM Notes "+ 
						" WHERE NoteId IN ";
		//
		if(noteIds == null || noteIds.length == 0)
			return 0;
		String ids = "( -1";
		for(int i = 0; i < noteIds.length; i++)
			ids +=","+noteIds[i];
		ids +=")";
		
		try {
			//�������������,�����������һ�����
			conn =MyConnection.getConnection();//��ȡ���Ӷ���
			ps = conn.prepareStatement(strSql + ids);//? �˴�Ҫ�Ӳ���
			return ps.executeUpdate();//���ظ�������
		} catch (SQLException e) {//sql�쳣�����п���������û�ҵ�
			e.printStackTrace();//��ʾ�쳣
			throw e;//��ʹ�з���Ҳ�����ִ��finally
		}finally{//ֻҪ��try����û��catch������û��finally�������߲���ͬʱû��
			MyConnection.closeStatementAndConnection(ps, conn);//�ر����Ӷ����������
		}
	}
	
	public ArrayList<NoteBean> select(NoteSearcher searcher, String sortString) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		if (sortString.length() == 0)
			sortString = "NoteId";
		
		String strSql = " SELECT * " +
						" FROM Notes INNER JOIN Logins Sender " +
						" 		ON Notes.SenderId = Sender.LoginId " +
						" 			INNER JOIN Logins Sendee " +	
						" 		ON Notes.SendeeId = Sendee.LoginId " +
						" WHERE Title LIKE ? " +
						"	AND Content LIKE ? " +
						"	AND SendIp LIKE ? " +
						" 	AND SendDateTime BETWEEN ? AND ? ";
		
		if (searcher.getSenderId() != 0)
			strSql += " 	AND Notes.SenderId = ? ";
		if (searcher.getSendeeId() != 0)
			strSql += " 	AND Notes.SendeeId = ? ";
		
		strSql += " ORDER BY " + sortString;
		
		ArrayList<NoteBean> notes = new ArrayList<NoteBean>();
		
		try {
			conn =MyConnection.getConnection();
			ps = conn.prepareStatement(strSql);
			int count = 1;
			ps.setString(count++, "%" + searcher.getTitle() + "%");
			ps.setString(count++, "%" + searcher.getContent() + "%");
			ps.setString(count++, "%" + searcher.getSendIp() + "%");
			ps.setTimestamp(count++, searcher.getSendDateTimeBegin());
			ps.setTimestamp(count++, searcher.getSendDateTimeEnd());
				
			if (searcher.getSenderId() != 0)
				ps.setInt(count++, searcher.getSenderId());
			if (searcher.getSendeeId() != 0)
				ps.setInt(count++, searcher.getSendeeId());
			
			rs = ps.executeQuery();
			while (rs.next()) {
				count = 1;
				NoteBean note = null;
				note.setNoteId(rs.getInt(count));
				note.setTitle(rs.getString(count));
				note.setContent(rs.getString(count));
				note.setSendDateTime(rs.getTimestamp(count));
				note.setSendIp(rs.getString(count));
				note.setSenderId(rs.getInt(count));
				note.setSendeeId(rs.getInt(count));
				note.setRead(rs.getBoolean(count));
				note.setSender(new LoginDao().select(note.getSenderId()));
				note.setSendee(new LoginDao().select(note.getSendeeId()));
				notes.add(note);
			}
			return notes;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(ps, conn);
		}
	}

	
}
