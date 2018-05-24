package cn.sycu.meade.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.sycu.meade.business.LoginManager;
import cn.sycu.meade.common.MD5Util;
import cn.sycu.meade.entity.LoginBean;

public class LoginServlet extends BaseServlet {
	LoginManager manager = new LoginManager();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8"); // ת����

		Cookie cookie = getCookie("LoginName", request);
		if (cookie != null) {
			request.setAttribute("LoginName", cookie.getValue()); // ����ȡ����cookie�е�ֵ����LoginName
			request.getRequestDispatcher("Login.jsp").include(request, response);
			return;
		}
		request.getRequestDispatcher("Login.jsp").include(request, response);
		/*
		 * PrintWriter out = response.getWriter(); out.println(
		 * "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		 * out.println("<HTML>"); out.println(
		 * "  <HEAD><TITLE>A Servlet</TITLE></HEAD>"); out.println("  <BODY>");
		 * out.print("    This is "); out.print(this.getClass());
		 * 
		 * Enumeration names = request.getParameterNames(); // ��ȡ����Ԫ��name����
		 * while (names.hasMoreElements()) { Object obj = names.nextElement();
		 * out.println(obj.toString() + ":" +
		 * request.getHeader(obj.toString())); } request.getMethod(); // ������󷽷�
		 * request.getRemotePort(); // ��ö˿ں�
		 * 
		 * out.println(", using the GET method"); out.println("  </BODY>");
		 * out.println("</HTML>"); out.flush(); out.close();
		 */
	}

	// public Cookie getCookie(String name,HttpServletRequest request){
	// //��ȡcookie
	// Cookie [] cookis = request.getCookies();
	// for (int i = 0; cookis != null && i < cookis.length; i++) {
	// //��cookis�б����ҵ��û�������ȡ
	// if (cookis[i].getName().compareTo("LoginName") == 0) {
	// //request.setAttribute("LoginName", cookis[i].getValue());
	// return cookis[i];
	// }
	// }
	// return null;
	// }
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*
		 * response.setContentType("text/html");
		 * response.setCharacterEncoding("UTF-8");
		 * request.setCharacterEncoding("UTF-8"); // ת���� PrintWriter out =
		 * response.getWriter(); out.println(
		 * "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		 * out.println("<HTML>"); out.println(
		 * "  <HEAD><TITLE>A Servlet</TITLE></HEAD>"); out.println("  <BODY>");
		 * out.println(request.getParameter("txtLoginName"));
		 * out.println(request.getParameter("txtPassword")); out.println(
		 * "  </BODY>"); out.println("</HTML>"); out.flush(); out.close();
		 */
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		LoginBean login = null;
		String LoginName = request.getParameter("txtLoginName"); // ��ȡ��¼��
		String password = request.getParameter("txtPassword"); // ��ȡ����
		boolean selected = false;
		if (request.getParameter("chkSelected") != null) {
			selected = Boolean.parseBoolean(request.getParameter("chkSelected")); // ��ȡ��ס�û�����ѡ��
		}
		if (selected) {
			Cookie cookie = new Cookie("LoginName", LoginName); //
			cookie.setMaxAge(60 * 60 * 24 * 365); // ����cookie�ı���ʱ�䵥λ�����
			response.addCookie(cookie); // ����cookie
		}

		try {
			password = MD5Util.md5Encode(password);
			login = manager.isLogin(LoginName, password);
			if (login == null) {
				request.setAttribute("Message", "alert('��¼ʧ��');"); // ���ݿ��в������������ݣ�������ʾ��Ϣ
			} else {
				HttpSession session = request.getSession(); // ����session
				session.setAttribute("Login", login); // ��user����ĵ�¼�����浽session��

				login.setLastLoginDateTime(getNowTimestamp());
				login.setLastLoginIp(request.getRemoteAddr());
				login.setLoginTime(login.getLoginTime() + 1);

				manager.update(login);

				if (session.getAttribute("Login") == null) {
					// ��¼����ͳ�Ƽ�1
					ServletContext application = session.getServletContext();
					int loginCount = 0;
					if (application.getAttribute("LoginCount") != null)
						loginCount = (Integer) application.getAttribute("LoginCount");
					loginCount++;
					application.setAttribute("LoginCount", loginCount);
				}
				session.removeAttribute("Message");
				if (login.getTypeId() == 1)
					response.sendRedirect("/YZY/admin/AdminIndexServlet");
				else if (login.getTypeId() == 2)
					response.sendRedirect("/YZY/user/UserIndexServlet");
				return;
			}
		} catch (RuntimeException ex) {
			// TODO: handle exception
			request.setAttribute("Message", "alert('" + ex.getMessage() + "');"); // ���������쳣�����쳣��Ϣ
		} catch (Exception e) {
			throw new RuntimeException("��¼��������ԭ��" + e.getMessage());
		}
		doGet(request, response);
	}

}
