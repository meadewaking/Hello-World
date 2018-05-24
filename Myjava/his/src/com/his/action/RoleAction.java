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
import com.his.vo.Role_Resources;
import com.his.vo.User;

public class RoleAction extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String action= request.getParameter("action");
		RoleService rs=new RoleServiceImpl();
		Role_ResourcesService rrs=new Role_ResourcesServiceImpl();

		if (action.equals("FindAllRole")) {//---------------------------添加及编辑user时获取role
			List<Role> roleList=rs.findAllRole();
			request.setAttribute("roleList", roleList);
			request.getRequestDispatcher("User/AddUser.jsp").forward(request, response);
		}else if(action.equals("FindRolePage")){//-----------------------显示角色
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
				for (int reid0: residdList) {
					if (reid0==11) {
						boo=true;
					}
				}
				if (!boo) {
					request.setAttribute("meesg","当前角色不具有该权限!");
					request.getRequestDispatcher("Error.jsp").forward(request, response);
				}else{
					ResourcesService res=new ResourcesServiceImpl();
					Resources resour=res.findResById(11);
					int sta=resour.getStatus();
					if (sta==0) {
						request.setAttribute("meesg","该权限已禁用!");
						request.getRequestDispatcher("Error.jsp").forward(request, response);
					}else{
						String roleName=request.getParameter("roleName");
						if(roleName==null){
							roleName="";
						}
						int totalCount=rs.findRoleCount(roleName);//获取总条数
						int pageSize=5;
						String pageNo=request.getParameter("pageNo");
						int pageNot=1;
						if(pageNo!=null&&pageNo!=""){
							pageNot = Integer.valueOf(pageNo);
						}else{
							pageNo=String.valueOf(pageNot);
						} 
						Page page =rs.findRolePage(roleName, pageNot, pageSize, totalCount);
						List<Role> roleList=page.getPageItem();
						request.setAttribute("roleName",roleName);
						request.setAttribute("roleList",roleList);
						request.setAttribute("totalCount",totalCount);
						request.setAttribute("pageNo",pageNo);
						request.setAttribute("totalPage",page.getTotalPage(totalCount,pageSize));
						int t=page.getTotalPage(totalCount,pageSize);
						List<Integer> lis=new ArrayList<Integer>();
						for (int i = 1; i <= t; i++) {
							lis.add(i);
						}
						if (t!=pageNot&&pageNot!=1) {
							request.setAttribute("pageMid", pageNot);
						}else if(pageNot==1){
							request.setAttribute("pageMid", 2);
						}else{
							request.setAttribute("pageMid", t-1);
						}
						request.setAttribute("lis", lis);
						request.getRequestDispatcher("Role/index.jsp").forward(request, response);
					}
				}
			}
		}else if(action.equals("addRole")){//------------------添加角色，相应的添加角色权限中间表相关记录
			String roleName=request.getParameter("roleName");
			String roleSta=request.getParameter("roleStatus");
			int roleStatus=Integer.valueOf(roleSta);
			Role role=new Role();
			role.setRoleName(roleName);
			role.setRoleStatus(roleStatus);
			rs.addRole(role);

			int roleId=rs.findId(roleName,roleStatus);
			String group[]=request.getParameterValues("group[]");
			for (String s : group) {
				int resId=Integer.valueOf(s);
				Role_Resources rr=new Role_Resources();
				rr.setRoleId(roleId);
				rr.setResId(resId);
				rrs.addRole_Resources(rr);
			}
			response.sendRedirect("RoleAction?action=FindRolePage");
		}else if(action.equals("delRole")){//-------------------删除角色，相应的删除角色权限中间表相关记录
			String rid=request.getParameter("roleId");
			int roleId=Integer.valueOf(rid);
			rs.delRole(roleId);
			rrs.delRole_ResourcesByRoleId(roleId);
			response.sendRedirect("RoleAction?action=FindRolePage");
		}else if(action.equals("findRoleById")){//----------------获取角色id及权限id用以跳转到修改页面
			String rid=request.getParameter("roleId");
			int roleId=Integer.valueOf(rid);
			Role role=new Role();
			role=rs.findRoleById(roleId);
			request.setAttribute("role",role);
			List<Integer> resIdList=rrs.finResIdByRoleId(roleId);
			request.setAttribute("resIdList", resIdList);
			request.getRequestDispatcher("Role/editRole.jsp").forward(request, response);
		}else if(action.equals("updateRole")){//------------------修改角色表和角色权限中间表
			String roleName=request.getParameter("roleName");
			String roleSta=request.getParameter("roleStatus");
			int roleStatus=Integer.valueOf(roleSta);
			String rid=request.getParameter("roleId");
			int roleId=Integer.valueOf(rid);
			Role role=new Role();
			role.setRoleName(roleName);
			role.setRoleStatus(roleStatus);
			role.setRoleId(roleId);
			rs.updateRole(role);
			rrs.delRole_ResourcesByRoleId(roleId);
			String group[]=request.getParameterValues("group[]");
			for (String s : group) {
				int resId=Integer.valueOf(s);
				Role_Resources rr=new Role_Resources();
				rr.setRoleId(roleId);
				rr.setResId(resId);
				rrs.addRole_Resources(rr);
			}
			response.sendRedirect("RoleAction?action=FindRolePage");
		}else if(action.equals("delChecked")){//-------------------------复选框删除
			String result = request.getParameter("ids"); 
			String[] ids = result.split(",");
			for (String s : ids) {
				int roleId=Integer.valueOf(s);
				rs.delRole(roleId);
				rrs.delRole_ResourcesByRoleId(roleId);
			}
			response.sendRedirect("RoleAction?action=FindRolePage");
		}else if(action.equals("getRes")){
			ResourcesService ress=new ResourcesServiceImpl();
			List<Resources> resList=ress.findAllResources();
			request.setAttribute("resList", resList);
			request.getRequestDispatcher("Role/addRole.jsp").forward(request, response);
		}else{
			//未知方法
		}

	}

}
