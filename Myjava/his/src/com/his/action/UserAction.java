package com.his.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.his.service.ResourcesService;
import com.his.service.RoleService;
import com.his.service.Role_ResourcesService;
import com.his.service.UserService;
import com.his.serviceimpl.ResourcesServiceImpl;
import com.his.serviceimpl.RoleServiceImpl;
import com.his.serviceimpl.Role_ResourcesServiceImpl;
import com.his.serviceimpl.UserServiceImpl;
import com.his.vo.Page;
import com.his.vo.Resources;
import com.his.vo.Role;
import com.his.vo.User;

public class UserAction extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		UserService us=new UserServiceImpl();
		String action=request.getParameter("action");
		if (action.equals("FindUserPage")) {//------------查询并显示用户
			String ui=request.getParameter("uid");
			if (ui==null||ui=="") {
				ui="125";
			}
			int uid=Integer.valueOf(ui);
			UserService users=new UserServiceImpl();
			User user0=users.findUserById(uid);
			int roleid0=user0.getRoleid();
			RoleService roles=new RoleServiceImpl();
			Role role0=roles.findRoleById(roleid0);
			int rolesta=role0.getRoleStatus();
			if (rolesta==0) {
				request.setAttribute("meesg","当前角色已禁用!");
				request.getRequestDispatcher("Error.jsp").forward(request, response);
			}else{
				Role_ResourcesService rolers=new Role_ResourcesServiceImpl();
				List<Integer> residdList=rolers.finResIdByRoleId(roleid0);
				boolean boo=false;
				for (Integer reid0: residdList) {
					if (reid0==10) {
						boo=true;
					}
				}
				if (!boo) {
					request.setAttribute("meesg","当前角色不具有该权限!");
					request.getRequestDispatcher("Error.jsp").forward(request, response);
				}else{
					ResourcesService res=new ResourcesServiceImpl();
					Resources resour=res.findResById(10);
					int sta=resour.getStatus();
					if (sta==0) {
						request.setAttribute("meesg","该权限已禁用!");
						request.getRequestDispatcher("Error.jsp").forward(request, response);
					}else{
						String username=request.getParameter("username");
						int totalCount=us.findUserNameCount(username);//获取用户总条数
						int pageSize=5;
						String pageNo = request.getParameter("pageNo");
						int pageNot=1;
						if(pageNo!=null&&pageNo!=""){
							pageNot = Integer.valueOf(pageNo);
						}else{
							pageNo=String.valueOf(pageNot);
						}
						Page page=us.findPageByUserName(username, pageNot, pageSize, totalCount);

						List<User> userList = page.getPageItem();
						request.setAttribute("username", username);
						request.setAttribute("userList",userList);
						request.setAttribute("userCount",totalCount);
						request.setAttribute("pageNo",pageNo);
						request.setAttribute("totalPage",page.getTotalPage(totalCount,pageSize));
						RoleService rs=new RoleServiceImpl();
						List<Role> roleList=rs.findAllRole();
						request.setAttribute("roleList", roleList);
						int t=page.getTotalPage(totalCount,pageSize);
						List<Integer> lis=new ArrayList<Integer>();
						if (t==0) {
							lis.add(1);
						}else{
							for (int i = 1; i <= t; i++) {
								lis.add(i);
							}
						}
						if (t!=pageNot&&pageNot!=1) {
							request.setAttribute("pageMid", pageNot);
						}else if(pageNot==1){
							request.setAttribute("pageMid", 2);
						}else{
							request.setAttribute("pageMid", t-1);
						}
						request.setAttribute("lis", lis);
						request.getRequestDispatcher("User/index.jsp").forward(request, response);
					}
				}
			}
		}else if(action.equals("AddUser")){//----------------添加用户
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			String realname=request.getParameter("realname");
			String email=request.getParameter("email");
			String status=request.getParameter("status");
			String roleid=request.getParameter("roleid");
			User user=new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setRealname(realname);
			user.setEmail(email);
			int sta=Integer.valueOf(status);
			user.setStatus(sta);
			int rid=Integer.valueOf(roleid);
			user.setRoleid(rid);

			int flag=us.addUser(user);

			if (flag==1) {
				response.sendRedirect("UserAction?action=FindUserPage");
			}else{
				response.sendRedirect("Error.jsp");
			}
		}else if(action.equals("UpdateUser")){//-----------------更新用户
			String userid=request.getParameter("userid");
			int uid=Integer.valueOf(userid);
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			String realname=request.getParameter("realname");
			String email=request.getParameter("email");
			String status=request.getParameter("status");
			String roleid=request.getParameter("roleid");
			User user=new User();

			user.setUserid(uid);
			user.setUsername(username);
			user.setPassword(password);
			user.setRealname(realname);
			user.setEmail(email);
			int sta=Integer.valueOf(status);
			user.setStatus(sta);
			int rid=Integer.valueOf(roleid);
			user.setRoleid(rid);
			int flag=us.updateUser(user);

			if (flag==1) {
				response.sendRedirect("UserAction?action=FindUserPage");
			}else{
				response.sendRedirect("Error.jsp");
			}
		}else if(action.equals("DelUser")){//----------------删除用户
			//复选框删除
			String result = request.getParameter("ids"); 
			int flag=0;
			if (result!=null) {
				String[] ids = result.split(",");
				for (String s : ids) {
					int id=Integer.valueOf(s);
					flag=us.deleteUser(id);
				}
			}
			//点击删除按钮
			String userid=request.getParameter("userid");
			if (userid!=null) {
				int uid=Integer.valueOf(userid);
				flag=us.deleteUser(uid);
			}

			if (flag==1) {
				response.sendRedirect("UserAction?action=FindUserPage");
			}else{
				response.sendRedirect("Error.jsp");
			}
		}else if(action.equals("FindUserById")){
			String userid=request.getParameter("userid");
			int id=Integer.valueOf(userid);
			User user=us.findUserById(id);
			request.setAttribute("user", user);
			RoleService rs=new RoleServiceImpl();
			List<Role> roleList=new ArrayList<Role>();
			roleList=rs.findAllRole();
			request.setAttribute("roleList", roleList);
			request.getRequestDispatcher("User/EditUser.jsp").forward(request, response);
		}
	}

}
