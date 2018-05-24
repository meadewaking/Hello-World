package cn.sycu.meade.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.sycu.meade.business.LoginManager;
import cn.sycu.meade.entity.LoginBean;

public class UserFilter implements Filter {
	LoginManager manager = new LoginManager();

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		if (arg0 instanceof HttpServletRequest) {
			HttpServletRequest request = (HttpServletRequest) arg0;
			HttpSession session = request.getSession();

			if (session.getAttribute("Login") != null) {
				Object obj = session.getAttribute("Login");
				if (obj instanceof LoginBean) {
					LoginBean login = (LoginBean) obj;
					if (manager.IsUser(login) || manager.IsAdmin(login)) {
						arg2.doFilter(arg0, arg1);
						return;
					}
				}
			}
			session.setAttribute("Message", "alert('无用户权限，不能访问，请使用用户账号登录。')");

		}
		if (arg1 instanceof HttpServletResponse) {
			HttpServletResponse response = (HttpServletResponse) arg1;
			response.sendRedirect("/YZY/LoginServlet");
		}

	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
