package com.his.action;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.his.excel.ExcleBook;
import com.his.service.DepService;
import com.his.service.DoctorService;
import com.his.service.ResourcesService;
import com.his.service.RoleService;
import com.his.service.Role_ResourcesService;
import com.his.service.UserService;
import com.his.serviceimpl.DepServiceImpl;
import com.his.serviceimpl.DoctorServiceImpl;
import com.his.serviceimpl.ResourcesServiceImpl;
import com.his.serviceimpl.RoleServiceImpl;
import com.his.serviceimpl.Role_ResourcesServiceImpl;
import com.his.serviceimpl.UserServiceImpl;
import com.his.vo.Dep;
import com.his.vo.Doctor;
import com.his.vo.Page;
import com.his.vo.Register;
import com.his.vo.Resources;
import com.his.vo.Role;
import com.his.vo.User;

public class DoctorAction extends HttpServlet {


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
		DoctorService ds=new DoctorServiceImpl();
		if (action.equals("FindDoctorPage")) {//-------------------查询并显示所有
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
					if (reid0==2) {
						boo=true;
					}
				}
				if (!boo) {
					request.setAttribute("meesg","当前角色不具有该权限!");
					request.getRequestDispatcher("Error.jsp").forward(request, response);
				}else{
					ResourcesService res=new ResourcesServiceImpl();
					Resources resour=res.findResById(2);
					int sta=resour.getStatus();
					if (sta==0) {
						request.setAttribute("meesg","该权限已禁用!");
						request.getRequestDispatcher("Error.jsp").forward(request, response);
					}else{
						String docid=request.getParameter("docid");
						int id=0;
						if (docid!=null&&docid!="") {
							id=Integer.valueOf(docid);
						}else{
							docid="";
						}
						String name=request.getParameter("name");
						if (name==null) {
							name="";
						}
						String dpid=request.getParameter("depid");
						int did=0;
						if (dpid!=null&&dpid!="") {
							did=Integer.valueOf(dpid);
						}
						int totalCount=ds.findDoctorCount(id,name,did);
						int pageSize=5;
						String pageNo=request.getParameter("pageNo");
						int pageNot=1;
						if(pageNo!=null&&pageNo!=""){
							pageNot = Integer.valueOf(pageNo);
						}else{
							pageNo=String.valueOf(pageNot);
						} 
						Page page =ds.findDoctorPage(id,name,did, pageNot, pageSize, totalCount);
						List<Doctor> doctorList=page.getPageItem();
						request.setAttribute("docid",docid);
						request.setAttribute("name",name);
						request.setAttribute("did",did);
						request.setAttribute("doctorList",doctorList);
						request.setAttribute("totalCount",totalCount);
						request.setAttribute("pageNo",pageNo);
						request.setAttribute("totalPage",page.getTotalPage(totalCount,pageSize));
						//科室表
						DepService deps=new DepServiceImpl();
						List<Dep> depList=deps.findDepList();
						request.setAttribute("depList", depList);
						//三个页码部分
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
						request.getRequestDispatcher("doctor/index.jsp").forward(request, response);
					}
				}
			}
		}else if(action.equals("Look")){//---------------------------详情
			String id=request.getParameter("id");
			int docid=Integer.valueOf(id);
			Doctor doctor=ds.findDoctorById(docid);
			request.setAttribute("doc", doctor);
			DepService deps=new DepServiceImpl();
			Dep dep=deps.findDepById(doctor.getDepid());
			request.setAttribute("depName", dep.getDepName());
			request.getRequestDispatcher("doctor/look.jsp").forward(request, response);
		}else if(action.equals("FindDoctorById")){//-------------修改之前，获取用户返回编辑页面
			String id=request.getParameter("id");
			int docid=Integer.valueOf(id);
			Doctor doctor=ds.findDoctorById(docid);
			request.setAttribute("doc", doctor);
			DepService deps=new DepServiceImpl();
			List<Dep> depList=deps.findDepList();
			request.setAttribute("depList", depList);
			request.getRequestDispatcher("doctor/edit.jsp").forward(request, response);
		}else if(action.equals("EditDoctor")){//--------------编辑页面提交后执行修改
			String id=request.getParameter("docid");
			int docid=Integer.valueOf(id);
			String name=request.getParameter("docname");
			String identifierTy=request.getParameter("identifierType");
			int identifierType=Integer.valueOf(identifierTy);
			String identifier =request.getParameter("identifier");
			String telphone =request.getParameter("telphone");
			String phone =request.getParameter("phone");
			String gende=request.getParameter("gender");
			int gender=Integer.valueOf(gende);
			String birthDate=request.getParameter("birthDate");
			String ag=request.getParameter("age");
			int age=Integer.valueOf(ag);
			String email =request.getParameter("email");
			String depi=request.getParameter("depid");
			int depid=Integer.valueOf(depi);
			String degre=request.getParameter("degree");
			int degree=Integer.valueOf(degre);
			String remarks=request.getParameter("remarks");

			Doctor doc=new Doctor();
			doc.setId(docid);
			doc.setName(name);
			doc.setIdentifierType(identifierType);
			doc.setIdentifier(identifier);
			doc.setTelphone(telphone);
			doc.setPhone(phone);
			doc.setGender(gender);
			doc.setBirthDate(birthDate);
			doc.setAge(age);
			doc.setEmail(email);
			doc.setDepid(depid);
			doc.setDegree(degree);
			doc.setRemarks(remarks);
			ds.updateDoctor(doc);
			response.sendRedirect("DoctorAction?action=FindDoctorPage");
		}else if(action.equals("AddDoctor")){
			String name=request.getParameter("docname");
			String identifierTy=request.getParameter("identifierType");
			int identifierType=Integer.valueOf(identifierTy);
			String identifier =request.getParameter("identifier");
			String telphone =request.getParameter("telphone");
			String phone =request.getParameter("phone");
			String gende=request.getParameter("gender");
			int gender=Integer.valueOf(gende);
			String birthDate=request.getParameter("birthDate");
			String ag=request.getParameter("age");
			int age=Integer.valueOf(ag);
			String email =request.getParameter("email");
			String depi=request.getParameter("depid");
			int depid=Integer.valueOf(depi);
			String degre=request.getParameter("degree");
			int degree=Integer.valueOf(degre);
			String remarks=request.getParameter("remarks");
			Doctor doc=new Doctor();
			doc.setName(name);
			doc.setIdentifierType(identifierType);
			doc.setIdentifier(identifier);
			doc.setTelphone(telphone);
			doc.setPhone(phone);
			doc.setGender(gender);
			doc.setBirthDate(birthDate);
			doc.setAge(age);
			doc.setEmail(email);
			doc.setDepid(depid);
			doc.setDegree(degree);
			doc.setRemarks(remarks);
			Date date=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH：mm：ss");
			String joinTime=sdf.format(date);
			doc.setJoinTime(joinTime);
			ds.addDoctor(doc);
			response.sendRedirect("DoctorAction?action=FindDoctorPage");
		}else if(action.equals("ExportExcel")){
			String fileName=request.getParameter("fileName");
			DoctorService docs=new DoctorServiceImpl();
			List<Doctor> docList=docs.findAllDoc();
			DepService deps=new DepServiceImpl();
			List<Dep> depList=deps.findDepList();
			ExcleBook eb =new ExcleBook();
			int flag=eb.excleOutDoc(docList, depList,fileName);
			Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String r=gson.toJson(flag);
			Writer out=response.getWriter();
			out.write(r);
			out.flush();
		}else if (action.equals("AddDoctorA")) {
			DepService deps=new DepServiceImpl();
			List<Dep> depList=deps.findDepList();
			request.setAttribute("depList", depList);
			request.getRequestDispatcher("doctor/add.jsp").forward(request, response);
		}

	}

}
