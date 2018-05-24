package com.his.action;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.his.service.UserService;
import com.his.serviceimpl.UserServiceImpl;
import com.his.vo.User;

public class Verify extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String action=request.getParameter("action");
		if (action==null) {
			action="";
		}
		if (action.equals("yzma")) {
			String ma=request.getParameter("verify");
			String key = (String)request.getSession().getAttribute("KAPTCHA_SESSION_KEY"); 
			int i=0;
			if (key.equals(ma)) {
				i=1;
			}
			Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String r=gson.toJson(i);
			Writer out=response.getWriter();
			out.write(r);
			out.flush();
		}else{
			String username=request.getParameter("username");
			String password=request.getParameter("password");

			UserService us=new UserServiceImpl();
			User user=us.findUserByUserName(username);
			String pwd=user.getPassword();
			if (password.equals(pwd)) {
				//response.sendRedirect("index.jsp?name="+user.getRealname()+"#1/11");
				if (user.getStatus()==0) {
					request.setAttribute("meesg", "当前用户已禁用!");
					request.setAttribute("retu", "返回登录");
					request.getRequestDispatcher("Error.jsp").forward(request, response);
				}else{
					request.setAttribute("rname", user.getRealname());
					request.setAttribute("userid", user.getUserid());
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
			}else{
				request.setAttribute("meesg","用户名或密码错误!");
				request.setAttribute("retu", "返回登录");
				request.getRequestDispatcher("Error.jsp").forward(request, response);
			}

		}
	}

}
