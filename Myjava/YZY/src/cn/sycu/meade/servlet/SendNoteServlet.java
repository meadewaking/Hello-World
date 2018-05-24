package cn.sycu.meade.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.sycu.meade.entity.*;
import cn.sycu.meade.business.*;

public class SendNoteServlet extends BaseServlet {
	NoteManager manager = new NoteManager();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("sendNote.jsp").include(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoteBean note = new NoteBean();

		note.setTitle(this.getStringParameter("txtTitle", request));
		note.setContent(this.getStringParameter("txtContent", request));
		note.setSendDateTime(this.getNowTimestamp());
		note.setSendIp(request.getRemoteAddr());
		if (this.getLogin(request) != null){
			note.setSenderId(this.getLogin(request).getLoginId());
			note.setSendeeId(this.getLogin(request).getLoginId());
			note.setRead(true);
		}
		try {
			if (manager.add(note))
				request.setAttribute("Note", "alert('发布成功');");
			else
				request.setAttribute("Note", "alert('发布失败');");
		} catch (Exception ex) {
			request.setAttribute("Note", "alert('" + ex.getMessage() + "');");
		}
		doGet(request, response);
	}
}
