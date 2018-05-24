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
	
	//添加一条
	public int insert(NoteBean note) throws SQLException {
		Connection conn = null;//声明连接对象
		PreparedStatement ps = null;//声明语句对象
		String strSql = " INSERT INTO Notes "+ //添加语句
						" VALUES(null, ?, ?, ?, ?, ?, ?, ?) ";
		try {
			//驱动程序管理器,驱动程序就是一个类库
			int count = 1;
			conn =MyConnection.getConnection();//获取连接对象
			ps = conn.prepareStatement(strSql);//? 此处要加参数
			ps.setString(count++, note.getTitle());
			ps.setString(count++, note.getContent());
			ps.setTimestamp(count++, note.getSendDateTime());
			ps.setString(count++, note.getSendIp());
			ps.setInt(count++, note.getSenderId());
			ps.setInt(count++, note.getSendeeId());
			ps.setBoolean(count++, note.isRead());
			return ps.executeUpdate();//返回更新数据
		} catch (SQLException e) {//sql异常
			e.printStackTrace();//显示异常
			throw e;
		}finally{//只要有try可以没有catch，可以没有finally，但两者不能同时没有
			MyConnection.closeStatementAndConnection(ps, conn);//关闭连接对象和语句对象
		}
	}
	//添加多条
	public int insert(ArrayList<NoteBean> notes) throws SQLException {
		Connection conn = null;//声明连接对象
		PreparedStatement ps = null;//声明语句对象
		String strSql = " INSERT INTO Notes "+ //添加语句
						" VALUES(null, ?, ?, ?, ?, ?, ?, ?) ";
		try {
			//驱动程序管理器,驱动程序就是一个类库
			conn =MyConnection.getConnection();//获取连接对象
			conn.setAutoCommit(false);//手动提交
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
		} catch (SQLException e) {//sql异常
			e.printStackTrace();//显示异常
			conn.rollback();//如果发生异常则回滚
			throw e;
		}finally{//只要有try可以没有catch，可以没有finally，但两者不能同时没有
			MyConnection.closeStatementAndConnection(ps, conn);//关闭连接对象和语句对象
		}
	}
	//删除一条
	public int delete(NoteBean note) throws SQLException{
		return delete(note.getNoteId());
	}
	//删除,按主键删除
	public int delete(int noteId) throws SQLException{
		Connection conn = null;//声明连接对象
		PreparedStatement ps = null;//声明语句对象
		String strSql = " DELETE FROM Notes "+ 
						" WHERE NoteId = ? ";
		try {
			int count=1;
			//驱动程序管理器,驱动程序就是一个类库
			conn =MyConnection.getConnection();//获取连接对象
			ps = conn.prepareStatement(strSql);//? 此处要加参数
			ps.setInt(count++, noteId);//
			
			return ps.executeUpdate();//返回更新数据
		} catch (SQLException e) {//sql异常
			e.printStackTrace();//显示异常
			throw e;
		}finally{//只要有try可以没有catch，可以没有finally，但两者不能同时没有
			MyConnection.closeStatementAndConnection(ps, conn);//关闭连接对象和语句对象
		}
	}
	//删除多条
	public int delete(ArrayList<NoteBean> noteIds) throws SQLException{
		return 0;
	}
	//删除多条
	public int delete(int [] noteIds) throws SQLException{
		Connection conn = null;//声明连接对象
		PreparedStatement ps = null;//声明语句对象
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
			//驱动程序管理器,驱动程序就是一个类库
			conn =MyConnection.getConnection();//获取连接对象
			ps = conn.prepareStatement(strSql + ids);//? 此处要加参数
			return ps.executeUpdate();//返回更新数据
		} catch (SQLException e) {//sql异常：很有可能是驱动没找到
			e.printStackTrace();//显示异常
			throw e;//即使有返回也会继续执行finally
		}finally{//只要有try可以没有catch，可以没有finally，但两者不能同时没有
			MyConnection.closeStatementAndConnection(ps, conn);//关闭连接对象和语句对象
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
