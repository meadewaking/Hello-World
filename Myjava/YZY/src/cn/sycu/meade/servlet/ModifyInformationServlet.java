package cn.sycu.meade.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.sycu.meade.entity.*;
import cn.sycu.meade.business.*;

public class ModifyInformationServlet extends BaseServlet {
	LoginManager loginManager = new LoginManager();
	UserManager userManager = new UserManager();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		UserBean user = null;
		LoginBean login = null;
		HttpSession session = request.getSession();
		if (session != null && session.getAttribute("Login") != null
				&& session.getAttribute("Login") instanceof LoginBean)
			login = (LoginBean) session.getAttribute("Login");
		if (login != null)
			user = userManager.search(login.getLoginId());
		// 在界面上显示用户信息
		request.setAttribute("User", user);
		request.getRequestDispatcher("modifyInformation.jsp").include(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		UserBean user = null;
		LoginBean login = null;

		login = loginManager.search(this.getStringParameter("txtLoginName", request));
		user = userManager.search(login.getLoginId());

		login.setEmail(this.getStringParameter("txtEmail", request));
		login.setNickname(this.getStringParameter("txtNickname", request));

		if (user != null) {
			user.setUserName(this.getStringParameter("txtUserName", request));
			user.setGender(this.getStringParameter("radGender", request));
			user.setBirthday(this.getDateParameter("txtBirthday", request));
			user.setCardId(this.getStringParameter("txtCardId", request));
			user.setRemark(this.getStringParameter("txtRemark", request));

			try {
				if (loginManager.modify(login) && userManager.modify(user))
					request.setAttribute("Message", "alert('修改成功');");
				else
					request.setAttribute("Message", "alert('修改失败');");
			} catch (Exception ex) {
				request.setAttribute("Message", "alert('" + ex.getMessage() + "');");
			}
		} else {
			user = new UserBean();
			user.setLoginId(login.getLoginId());
			user.setUserName(this.getStringParameter("txtUserName", request));
			user.setGender(this.getStringParameter("radGender", request));
			user.setBirthday(this.getDateParameter("txtBirthday", request));
			user.setCardId(this.getStringParameter("txtCardId", request));
			user.setRemark(this.getStringParameter("txtRemark", request));

			try {
				if (loginManager.modify(login) && userManager.add(user))
					request.setAttribute("Message", "alert('修改成功');");
				else
					request.setAttribute("Message", "alert('修改失败');");
			} catch (Exception ex) {
				request.setAttribute("Message", "alert('" + ex.getMessage() + "');");
			}

		}
		doGet(request, response);
	}
}
