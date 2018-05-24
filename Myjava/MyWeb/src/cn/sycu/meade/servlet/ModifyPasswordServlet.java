package cn.sycu.meade.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sycu.meade.business.LoginManager;

public class ModifyPasswordServlet extends BaseServlet {
	LoginManager manager = new LoginManager();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		request.getRequestDispatcher("modifyPassword.jsp").include(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String loginName = this.getStringParameter("txtLoginName", request);
		String oldPassword = this.getStringParameter("txtOldPassword", request);
		String newPassword = this.getStringParameter("txtNewPassword", request);
		
		try {
			if (manager.modifyPassword(loginName, oldPassword, newPassword))
				request.setAttribute("Message", "alert('修改成功');");
			else
				request.setAttribute("Message", "alert('修改失败');");
			} catch (Exception ex) {
				request.setAttribute("Message", "alert('" + ex.getMessage() + "');");
			}
		
		doGet(request, response);
	}
}
