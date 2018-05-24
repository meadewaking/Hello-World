package cn.sycu.meade.servlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sycu.meade.business.LoginManager;
import cn.sycu.meade.entity.LoginBean;

public class RegisterServlet extends HttpServlet {

	LoginManager manager = new LoginManager();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		request.getRequestDispatcher("Register.jsp").include(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		LoginBean login = new LoginBean();
		login.setLoginName(request.getParameter("Name"));
		login.setPassword(request.getParameter("Password"));
		login.setEmail(request.getParameter("Email"));
		login.setNickname(request.getParameter("NickName"));
		login.setLoginTime(0);
		login.setGrade(0);
		login.setRegisterDateTime(new Timestamp(System.currentTimeMillis()));
		login.setRegisterIp(request.getRemoteAddr());
		login.setLastLoginDateTime(new Timestamp(System.currentTimeMillis()));
		login.setLastLoginIp(request.getRemoteAddr());
		login.setRemark("");

		login.setStateId(1);
		login.setTypeId(2);
		login.setDepartmentId(1);
		try {
			if (manager.add(login)) {
				request.setAttribute("Message", "alert('×¢²á³É¹¦');");
				response.sendRedirect("Login.jsp");

			} else {
				request.setAttribute("Message", "alert('×¢²áÊ§°Ü');");
			}
		} catch (RuntimeException ex) {
			request.setAttribute("Message", "alert('" + ex.getMessage() + "');");

		}
		doGet(request, response);
	}
}
