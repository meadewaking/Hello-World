package cn.sycu.meade.business;

import java.sql.SQLException;
import java.util.List;

import cn.sycu.meade.dao.NoteDao;
import cn.sycu.meade.entity.*;

public class NoteManager {
	NoteDao dao = new NoteDao();
	public boolean add(NoteBean note){
		
		try{
			return dao.insert(note) > 0;
		} catch (SQLException e){
			if (e.getMessage().indexOf("FK_Departments_Notes_DepartmentId") >= 0)
				throw new RuntimeException("�ò����Ѵ���");
			else if (e.getMessage().indexOf("FK_Logins_Notes_PublisherId") >= 0)
				throw new RuntimeException("�õ�¼������");
			else
				throw new RuntimeException("�����Ϣ��������ԭ��" + e.getMessage());
		}
	}
	
	public List<NoteBean> search(NoteSearcher searcher, String sortString) {
		try {
			return dao.select(searcher, sortString);
		} catch (SQLException e) {
			throw new RuntimeException("��ȡ������Ϣʱ����,����ԭ��:" + e.getMessage());
		}
	}

	public boolean remove(int noteId) {
		try {
			return dao.delete(noteId) > 0;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("FK_Departments_Logins_DepartmentId") >= 0)
				throw new RuntimeException("������Ϣ���ڱ����ã�����ɾ����");
			else
				throw new RuntimeException("ɾ��������Ϣ��������ԭ��" + e.getMessage());
		}
	}
}

