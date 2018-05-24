package cn.sycu.meade.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.sycu.meade.entity.*;
import cn.sycu.meade.business.*;

public class PublishDepartmentMessageServlet extends BaseServlet {
	MessageManager manager = new MessageManager();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("publishDepartmentMessage.jsp").include(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MessageBean message = new MessageBean();

		message.setTitle(this.getStringParameter("txtTitle", request));
		message.setContent(this.getStringParameter("txtContent", request));
		message.setPublishDateTime(this.getNowTimestamp());
		message.setPublishIP(request.getRemoteAddr());
		if (this.getLogin(request) != null){
			message.setPublisherId(this.getLogin(request).getLoginId());
			message.setDepartmentId(this.getLogin(request).getDepartmentId());
		}
		try {
			if (manager.add(message))
				request.setAttribute("Message", "alert('发布成功');");
			else
				request.setAttribute("Message", "alert('发布失败');");
		} catch (Exception ex) {
			request.setAttribute("Message", "alert('" + ex.getMessage() + "');");
		}
		doGet(request, response);
	}
}
