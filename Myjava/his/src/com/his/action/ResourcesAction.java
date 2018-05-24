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

public class ResourcesAction extends HttpServlet {

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
		ResourcesService rs=new ResourcesServiceImpl();
		Role_ResourcesService rrs=new Role_ResourcesServiceImpl();

		if (action.equals("FindResPage")) {//-------------------------------显示所有权限
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
					if (reid0==12) {
						boo=true;
					}
				}
				if (!boo) {
					request.setAttribute("meesg","当前角色不具有该权限!");
					request.getRequestDispatcher("Error.jsp").forward(request, response);
				}else{

					ResourcesService res=new ResourcesServiceImpl();
					Resources resour=res.findResById(12);
					int sta=resour.getStatus();
					if (sta==0) {
						request.setAttribute("meesg","该权限已禁用!");
						request.getRequestDispatcher("Error.jsp").forward(request, response);
					}else{
						String resName=request.getParameter("resName");
						if (resName==null) {
							resName="";
						}
						int totalCount=rs.findResCount(resName);//获取总条数
						int pageSize=5;
						String pageNo=request.getParameter("pageNo");
						int pageNot=1;
						if(pageNo!=null&&pageNo!=""){
							pageNot = Integer.valueOf(pageNo);
						}else{
							pageNo=String.valueOf(pageNot);
						} 
						Page page =rs.findResPage(resName, pageNot, pageSize, totalCount);
						List<Resources> resList=page.getPageItem();
						request.setAttribute("resName",resName);
						request.setAttribute("resList",resList);
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
						request.getRequestDispatcher("Resource/index.jsp").forward(request, response);
					}
				}
			}
		}else if(action.equals("AddRes")){//---------------------------------添加权限
			String resName=request.getParameter("resName");
			if (resName==null) {
				resName="";
			}
			String resURL=request.getParameter("resURL");
			if (resURL==null) {
				resURL="";
			}
			String sta=request.getParameter("status");
			int status=1;
			if (sta!=null) {
				status=	Integer.valueOf(sta);
			}
			Resources res=new Resources();
			res.setResName(resName);
			res.setResURL(resURL);
			res.setStatus(status);
			rs.addResources(res);
			response.sendRedirect("ResourcesAction?action=FindResPage");
		}else if(action.equals("DelRes")){//-----------------------------按钮删除权限
			String rid=request.getParameter("resId");
			int resId=Integer.valueOf(rid);
			rs.delResources(resId);
			rrs.delRole_ResourcesByResId(resId);
			response.sendRedirect("ResourcesAction?action=FindResPage");
		}else if(action.equals("DelChecked")){//---------------------------复选框删除
			String result = request.getParameter("ids"); 
			String[] ids = result.split(",");
			for (String s : ids) {
				int resId=Integer.valueOf(s);
				rs.delResources(resId);
				System.err.println("rrs err");
				rrs.delRole_ResourcesByResId(resId);
			}
			response.sendRedirect("ResourcesAction?action=FindResPage");
		}else if(action.equals("FindResById")){//------------------------根据id查找，用于修改	
			String rid=request.getParameter("resId");
			int resId=Integer.valueOf(rid);
			Resources res=rs.findResById(resId);
			request.setAttribute("res", res);
			request.getRequestDispatcher("Resource/edit.jsp").forward(request, response);
		}else if(action.equals("UpdateRes")){//--------------------------更新权限
			String resI=request.getParameter("resid");
			int resId=Integer.valueOf(resI);
			String resName=request.getParameter("resName");
			String resURL=request.getParameter("resURL");
			String statu=request.getParameter("statu");
			int status=Integer.valueOf(statu);
			Resources res=new Resources();
			res.setResId(resId);
			res.setResName(resName);
			res.setResURL(resURL);
			res.setStatus(status);
			rs.updateResources(res);
			response.sendRedirect("ResourcesAction?action=FindResPage");
		}


	}

}
