package cn.sycu.meade.servlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.sycu.meade.business.LoginManager;
import cn.sycu.meade.common.EmailUtil;
import cn.sycu.meade.entity.LoginBean;
import java.util.*;

public class FindPasswordServlet extends BaseServlet {
	LoginManager manager = new LoginManager();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		request.getRequestDispatcher("findPassword.jsp").include(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		
		String loginName = this.getStringParameter("txtLoginName", request);
		String email =this.getStringParameter("txtEmail", request);
		
		
		try {
			LoginBean login = manager.isLoginByEmail(loginName, email);
			if (login != null) {
				EmailUtil.sendEmail("企业消息平台密码找回", 
						"请将以下链接复制到地址栏：http://127.0.0.1:8080/YZY/ResetPasswordServlet?loginName=" +
						loginName + "&password=" + login.getPassword(), 
						"test@163.com", 
						email, 
						"meade", 
						"151006123");
				request.setAttribute("Message", "alert('重置密码邮件已发送，请查收。');");
			} else {
				request.setAttribute("Message", "alert('用户名或邮箱错误，不能找回。');");
			}
		} catch (RuntimeException ex) {
			request.setAttribute("Message", "alert('" + ex.getMessage() + "');");
		}
		doGet(request, response);// 显示网页，实现回传
	}
}
