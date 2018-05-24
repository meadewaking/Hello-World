package cn.sycu.meade.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Enumeration;

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
		request.setCharacterEncoding("UTF-8"); // 转编码

		Cookie cookie = getCookie("LoginName", request);
		if (cookie != null) {
			request.setAttribute("LoginName", cookie.getValue()); // 将获取到的cookie中的值赋给LoginName
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
		 * Enumeration names = request.getParameterNames(); // 获取所有元素name属性
		 * while (names.hasMoreElements()) { Object obj = names.nextElement();
		 * out.println(obj.toString() + ":" +
		 * request.getHeader(obj.toString())); } request.getMethod(); // 获得请求方法
		 * request.getRemotePort(); // 获得端口号
		 * 
		 * out.println(", using the GET method"); out.println("  </BODY>");
		 * out.println("</HTML>"); out.flush(); out.close();
		 */
	}

	// public Cookie getCookie(String name,HttpServletRequest request){
	// //获取cookie
	// Cookie [] cookis = request.getCookies();
	// for (int i = 0; cookis != null && i < cookis.length; i++) {
	// //在cookis列表中找到用户名并读取
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
		 * request.setCharacterEncoding("UTF-8"); // 转编码 PrintWriter out =
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
		String LoginName = request.getParameter("txtLoginName"); // 获取登录名
		String password = request.getParameter("txtPassword"); // 获取密码
		boolean selected = false;
		if (request.getParameter("chkSelected") != null) {
			selected = Boolean.parseBoolean(request.getParameter("chkSelected")); // 获取记住用户名的选项
		}
		if (selected) {
			Cookie cookie = new Cookie("LoginName", LoginName); //
			cookie.setMaxAge(60 * 60 * 24 * 365); // 设置cookie的保存时间单位以秒计
			response.addCookie(cookie); // 保存cookie
		}

		try {
			password = MD5Util.md5Encode(password);
			login = manager.isLogin(LoginName, password);
			if (login == null) {
				request.setAttribute("Message", "alert('登录失败');"); // 数据库中不存在输入数据，返回提示信息
			} else {
				HttpSession session = request.getSession(); // 声明session
				session.setAttribute("Login", login); // 将user输入的登录名保存到session中
				response.sendRedirect("index.jsp"); // 登录成功重定向至主页
				login.setLastLoginDateTime(getNowTimestamp());
				login.setLastLoginIp(request.getRemoteAddr());
				login.setLoginTime(login.getLoginTime() + 1);

				manager.update(login);
			}
		} catch (RuntimeException ex) {
			// TODO: handle exception
			request.setAttribute("Message", "alert('" + ex.getMessage() + "');"); // 出现其他异常返回异常消息
		} catch (Exception e) {
			throw new RuntimeException("修改密码时密码加密处理出错，错误原因：" + e.getMessage());
		}
		doGet(request, response);
	}

}
