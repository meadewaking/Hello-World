package cn.sycu.meade.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.sycu.meade.business.LoginManager;
import cn.sycu.meade.entity.LoginBean;

public class ResetPasswordServlet extends BaseServlet {
	LoginManager manager = new LoginManager();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String loginName = this.getStringParameter("txtLoginName", request);
		String password =this.getStringParameter("txtPassword", request);
		
		
		try {
			LoginBean login = manager.isLoginByPassword(loginName, password);
			
			if (login != null) {
				request.setAttribute("loginName",loginName);
				request.getRequestDispatcher("resetPassword.jsp").include(request, response);
			} else {
				request.getSession().setAttribute("Message", "alert('√‹¬Î÷ÿ÷√«Î«Û¥ÌŒÛ,≤ªƒ‹÷ÿ÷√√‹¬Î£°');");
			    response.sendRedirect("/YZY/FindPasswordServlet");
			}
		} catch (RuntimeException ex) {
			request.setAttribute("Message", "alert('" + ex.getMessage() + "');");
		}

	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String loginName = this.getStringParameter("txtLoginName", request);
		String password =this.getStringParameter("txtPassword", request);
		
		
		try {
			if (manager.resetPassword(loginName,password)) {
				request.getSession().setAttribute("Message", "alert('√‹¬Î“—æ≠÷ÿ÷√,«Îµ«¬º°£');");
				response.sendRedirect("/YZY/LoginServlet");
			} else {
				request.getSession().setAttribute("Message", "alert('√‹¬Î÷ÿ÷√ ß∞‹,≤ªƒ‹÷ÿ÷√√‹¬Î£°');");
			    response.sendRedirect("/YZY/FindPasswordServlet");
			}
		} catch (RuntimeException ex) {
			request.setAttribute("Message", "alert('" + ex.getMessage() + "');");
			 response.sendRedirect("/YZY/FindPasswordServlet");
		}
	}
}
