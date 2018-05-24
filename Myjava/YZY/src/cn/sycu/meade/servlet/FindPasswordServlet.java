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
				EmailUtil.sendEmail("��ҵ��Ϣƽ̨�����һ�", 
						"�뽫�������Ӹ��Ƶ���ַ����http://127.0.0.1:8080/YZY/ResetPasswordServlet?loginName=" +
						loginName + "&password=" + login.getPassword(), 
						"test@163.com", 
						email, 
						"meade", 
						"151006123");
				request.setAttribute("Message", "alert('���������ʼ��ѷ��ͣ�����ա�');");
			} else {
				request.setAttribute("Message", "alert('�û�����������󣬲����һء�');");
			}
		} catch (RuntimeException ex) {
			request.setAttribute("Message", "alert('" + ex.getMessage() + "');");
		}
		doGet(request, response);// ��ʾ��ҳ��ʵ�ֻش�
	}
}
