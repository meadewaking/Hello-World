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
import com.his.service.HospitalService;
import com.his.service.RegisterService;
import com.his.service.ResourcesService;
import com.his.service.RoleService;
import com.his.service.Role_ResourcesService;
import com.his.service.UserService;
import com.his.serviceimpl.DepServiceImpl;
import com.his.serviceimpl.DoctorServiceImpl;
import com.his.serviceimpl.HospitalServiceImpl;
import com.his.serviceimpl.RegisterServiceImpl;
import com.his.serviceimpl.ResourcesServiceImpl;
import com.his.serviceimpl.RoleServiceImpl;
import com.his.serviceimpl.Role_ResourcesServiceImpl;
import com.his.serviceimpl.UserServiceImpl;
import com.his.vo.Dep;
import com.his.vo.Doctor;
import com.his.vo.Drug;
import com.his.vo.Hospital;
import com.his.vo.Page;
import com.his.vo.Register;
import com.his.vo.Resources;
import com.his.vo.Role;
import com.his.vo.User;

public class HospitalAction extends HttpServlet {


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
		HospitalService hs=new HospitalServiceImpl();
		if (action.equals("ShowInformation")) {
			String medicalNo=request.getParameter("mno");
			RegisterService rs=new RegisterServiceImpl();
			Register reg=rs.findRegisterByNo(medicalNo);
			int depid=reg.getDepid();
			int docid=reg.getDocid();
			if (docid==0) {
				int m=0;
				Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				String s=gson.toJson(m);
				Writer out=response.getWriter();
				out.write(s);
				out.flush();
			}else{
				DepService deps=new DepServiceImpl();
				Dep dep=deps.findDepById(depid);
				String depName=dep.getDepName();
				DoctorService docs=new DoctorServiceImpl();
				Doctor doc=docs.findDoctorById(docid);
				String docName=doc.getName();
				reg.setDepName(depName);
				reg.setDocName(docName);
				Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				String s=gson.toJson(reg);
				Writer out=response.getWriter();
				out.write(s);
				out.flush();
			}
		}else if(action.equals("AddHos")){
			String medicalNo=request.getParameter("medicalNo");
			String nurse=request.getParameter("nurse");
			String bedNo=request.getParameter("bedNo");
			String payCas=request.getParameter("payCase");
			String pcondition=request.getParameter("pcondition");
			double payCase=Double.valueOf(payCas);
			Date date =new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String hosTime=sdf.format(date);
			Hospital hos=new Hospital();
			hos.setMedicalNo(medicalNo);
			hos.setNurse(nurse);
			hos.setBedNo(bedNo);
			hos.setPayCase(payCase);
			hos.setPcondition(pcondition);
			hos.setHosTime(hosTime);
			hs.addHos(hos);
			RegisterService rs=new RegisterServiceImpl();
			Register reg=rs.findRegisterByNo(medicalNo);
			reg.setFlag(5);
			rs.updateRegister(reg);
			response.sendRedirect("HospitalAction?action=FindHosPage");
		}else if(action.equals("FindHosPage")){
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
					if (reid0==4) {
						boo=true;
					}
				}
				if (!boo) {
					request.setAttribute("meesg","当前角色不具有该权限!");
					request.getRequestDispatcher("Error.jsp").forward(request, response);
				}else{
					ResourcesService res=new ResourcesServiceImpl();
					Resources resour=res.findResById(4);
					int sta=resour.getStatus();
					if (sta==0) {
						request.setAttribute("meesg","该权限已禁用!");
						request.getRequestDispatcher("Error.jsp").forward(request, response);
					}else{
						DepService ds=new DepServiceImpl();
						List<Dep> depList=ds.findDepList();
						List<Integer> lis=new ArrayList<Integer>();
						request.setAttribute("depList", depList);
						String medicalNo=request.getParameter("medicalNo");
						if (medicalNo==null) {
							medicalNo="";
						}
						String docName=request.getParameter("docName");
						List<Integer> docIdList=new ArrayList<Integer>();
						DoctorService docs=new DoctorServiceImpl();
						if (docName==null||docName.equals("")) {
							docIdList=docs.findAllId();
						}else{
							docIdList=docs.findIdByName(docName);
						}
						String dpid=request.getParameter("depId");
						if (dpid==null) {
							dpid="0";
						}
						int depId=Integer.valueOf(dpid);
						String stime=request.getParameter("stime");
						if (stime==null||stime.equals("")) {
							stime="2015-01-01";
						}
						String ftime=request.getParameter("ftime");
						if (ftime==null||ftime.equals("")) {
							Date date=new Date();
							SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
							ftime=sdf.format(date);
						}
						if (docIdList==null||docIdList.size()==0) {
							List<Hospital> hosList=new ArrayList<Hospital>();
							request.setAttribute("hosList", hosList);
							request.setAttribute("medicalNo", medicalNo);
							request.setAttribute("docName", docName);
							request.setAttribute("stime", stime);
							request.setAttribute("ftime", ftime);
							request.setAttribute("totalCount",0);
							request.setAttribute("pageNo",1);
							lis.add(1);
							request.setAttribute("lis", lis);
						}else{
							int totalCount=hs.findHosCount(medicalNo, docIdList, depId, stime, ftime);
							int pageSize=5;
							String pageNo=request.getParameter("pageNo");
							int pageNot=1;
							if(pageNo!=null&&pageNo!=""){
								pageNot = Integer.valueOf(pageNo);
							}else{
								pageNo=String.valueOf(pageNot);
							} 
							Page page=hs.findHosPage(medicalNo, docIdList, depId, stime, ftime, pageNot, pageSize);
							List<Hospital> hosList=page.getPageItem();
							RegisterService rs=new RegisterServiceImpl();
							List<Register> regList=rs.findAllReg();
							request.setAttribute("medicalNo", medicalNo);
							request.setAttribute("docName", docName);
							request.setAttribute("stime", stime);
							request.setAttribute("ftime", ftime);
							request.setAttribute("hosList", hosList);
							request.setAttribute("regList", regList);
							request.setAttribute("totalCount",totalCount);
							request.setAttribute("pageNo",pageNo);
							request.setAttribute("totalPage",page.getTotalPage(totalCount,pageSize));
							int t=page.getTotalPage(totalCount,pageSize);
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
						}
						request.setAttribute("depId", depId);
						List<Doctor> docList=docs.findAllDoc();
						request.setAttribute("docList", docList);
						request.setAttribute("depList", depList);
						request.getRequestDispatcher("hospital/index.jsp").forward(request, response);
					}
				}
			}
		}else if (action.equals("Look")) {
			String mNo=request.getParameter("mNo");
			RegisterService rs=new RegisterServiceImpl();
			Register reg=rs.findRegisterByNo(mNo);
			request.setAttribute("reg", reg);
			DepService deps=new DepServiceImpl();
			List<Dep> depList=deps.findDepList();
			request.setAttribute("depList", depList);
			DoctorService docs=new DoctorServiceImpl();
			List<Doctor> docList=docs.findAllDoc();
			request.setAttribute("docList", docList);
			request.getRequestDispatcher("hospital/look.jsp").forward(request, response);
		}else if(action.equals("EditHosA")){
			String mNo=request.getParameter("mNo");
			Hospital hos=hs.findHosById(mNo);
			request.setAttribute("hos", hos);
			RegisterService rs=new RegisterServiceImpl();
			Register reg=rs.findRegisterByNo(mNo);
			request.setAttribute("reg", reg);
			DepService deps=new DepServiceImpl();
			List<Dep> depList=deps.findDepList();
			request.setAttribute("depList", depList);
			DoctorService docs=new DoctorServiceImpl();
			List<Doctor> docList=docs.findAllDoc();
			request.setAttribute("docList", docList);
			request.getRequestDispatcher("hospital/edit.jsp").forward(request, response);
		}else if(action.equals("EditHosB")){
			String medicalNo=request.getParameter("medicalNo");
			String nurse=request.getParameter("nurse");
			String bedNo=request.getParameter("bedNo");
			String payCas=request.getParameter("payCase");
			String pcondition=request.getParameter("pcondition");
			double payCase=Double.valueOf(payCas);
			Date date =new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String hosTime=sdf.format(date);
			Hospital hos=new Hospital();
			hos.setMedicalNo(medicalNo);
			hos.setNurse(nurse);
			hos.setBedNo(bedNo);
			hos.setPayCase(payCase);
			hos.setPcondition(pcondition);
			hos.setHosTime(hosTime);
			hs.updateHos(hos);
			response.sendRedirect("HospitalAction?action=FindHosPage");
		}else if(action.equals("ExitHos")){
			String medicalNo=request.getParameter("mno");
			RegisterService rs=new RegisterServiceImpl();
			Register reg=rs.findRegisterByNo(medicalNo);
			int flag=reg.getFlag();
			int msg=0;
			if (flag==7) {
				reg.setFlag(6);
				rs.updateRegister(reg);
				msg=1;
			}
			Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String mesg=gson.toJson(msg);
			Writer out=response.getWriter();
			out.write(mesg);
			out.flush();
		}else if(action.equals("LeaveHos")){
			String medicalNo=request.getParameter("mno");
			RegisterService rs=new RegisterServiceImpl();
			Register reg=rs.findRegisterByNo(medicalNo);
			int flag=reg.getFlag();
			int msg=0;
			if (flag==7) {
				reg.setFlag(3);
				rs.updateRegister(reg);
				msg=1;
			}
			Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String mesg=gson.toJson(msg);
			Writer out=response.getWriter();
			out.write(mesg);
			out.flush();
		}else if(action.equals("ExportExcel")){
			String fileName=request.getParameter("fileName");
			List<Hospital> hosList=hs.findAllList();
			RegisterService rs =new RegisterServiceImpl();
			List<Register> regList=rs.findAllReg();
			DoctorService docs=new DoctorServiceImpl();
			List<Doctor> docList=docs.findAllDoc();
			DepService deps =new DepServiceImpl();
			List<Dep> depList=deps.findDepList();
			ExcleBook eb =new ExcleBook();
			int flag=eb.excleOutHos(hosList, regList, docList, depList, fileName);
			Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String r=gson.toJson(flag);
			Writer out=response.getWriter();
			out.write(r);
			out.flush();
		}else if (action.equals("Find")) {
			String medicalNo=request.getParameter("mno");
			Hospital hos=hs.findHosById(medicalNo);
			int msg=0;
			if (medicalNo.equals(hos.getMedicalNo())) {
				msg=1;
			}
			Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String r=gson.toJson(msg);
			Writer out=response.getWriter();
			out.write(r);
			out.flush();
		}

	}

}
