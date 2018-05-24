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
import com.his.service.DispService;
import com.his.service.DoctorService;
import com.his.service.DrugService;
import com.his.service.RegisterService;
import com.his.service.ResourcesService;
import com.his.service.RoleService;
import com.his.service.Role_ResourcesService;
import com.his.service.UserService;
import com.his.serviceimpl.DispServiceImpl;
import com.his.serviceimpl.DoctorServiceImpl;
import com.his.serviceimpl.DrugServiceImpl;
import com.his.serviceimpl.RegisterServiceImpl;
import com.his.serviceimpl.ResourcesServiceImpl;
import com.his.serviceimpl.RoleServiceImpl;
import com.his.serviceimpl.Role_ResourcesServiceImpl;
import com.his.serviceimpl.UserServiceImpl;
import com.his.vo.Disp;
import com.his.vo.Doctor;
import com.his.vo.Drug;
import com.his.vo.Page;
import com.his.vo.Register;
import com.his.vo.Resources;
import com.his.vo.Role;
import com.his.vo.User;

public class DispAction extends HttpServlet {

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
		DrugService drugs=new DrugServiceImpl();
		DispService ds=new DispServiceImpl();
		if (action.equals("FindDispPage")) {
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
					if (reid0==6) {
						boo=true;
					}
				}
				if (!boo) {
					request.setAttribute("meesg","当前角色不具有该权限!");
					request.getRequestDispatcher("Error.jsp").forward(request, response);
				}else{
					ResourcesService res=new ResourcesServiceImpl();
					Resources resour=res.findResById(6);
					int sta=resour.getStatus();
					if (sta==0) {
						request.setAttribute("meesg","该权限已禁用!");
						request.getRequestDispatcher("Error.jsp").forward(request, response);
					}else{
						String medicalNo=request.getParameter("medicalNo");
						if (medicalNo==null) {
							medicalNo="";
						}
						int totalCount=ds.findDispCount(medicalNo);
						int pageSize=5;
						String pageNo=request.getParameter("pageNo");
						int pageNot=1;
						if(pageNo!=null&&pageNo!=""){
							pageNot = Integer.valueOf(pageNo);
						}else{
							pageNo=String.valueOf(pageNot);
						} 
						Page page =ds.findDispPage(medicalNo, pageNot, pageSize);
						List<Disp> dispList=page.getPageItem();
						request.setAttribute("medicalNo",medicalNo);
						request.setAttribute("dispList",dispList);
						RegisterService rs=new RegisterServiceImpl();
						List<Register> regList=rs.findAllReg();
						request.setAttribute("regList", regList);
						DoctorService docs=new DoctorServiceImpl();
						List<Doctor> docList=docs.findAllDoc();
						request.setAttribute("docList", docList);
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
						request.getRequestDispatcher("hospital/dispensing.jsp").forward(request, response);
					}
				}
			}
		}else if(action.equals("AddDispA")){
			List<Drug> drugList=drugs.findAllDrug();
			request.setAttribute("drugList", drugList);
			request.getRequestDispatcher("hospital/dispensing-gives.jsp").forward(request, response);
		}else if(action.equals("AddDispB")){
			String medicalNo=request.getParameter("medicalNo");
			String drugId=request.getParameter("drugId");
			String dispCoun=request.getParameter("dispCount");
			int dispCount=Integer.valueOf(dispCoun);
			int aldispCount=0;
			int nodispCount=dispCount;
			Date date=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dispTime=sdf.format(date);
			RegisterService regs=new RegisterServiceImpl();
			Register reg=regs.findRegisterByNo(medicalNo);
			reg.setFlag(2);
			regs.updateRegister(reg);
			Disp disp=new Disp();
			disp.setMedicalNo(medicalNo);
			disp.setDrugId(drugId);
			disp.setDispCount(dispCount);
			disp.setAldispCount(aldispCount);
			disp.setNodispCount(nodispCount);
			disp.setDispTime(dispTime);
			Disp di=ds.findDispByNoDID(medicalNo, drugId);
			if (medicalNo.equals(di.getMedicalNo())) {
				ds.updateDisp(disp);
			}else {
				ds.addDisp(disp);
			}
			response.sendRedirect("DispAction?action=FindDispPage");
		}else if(action.equals("AddDispC")){
			String medicalNo=request.getParameter("medicalNo");
			RegisterService rs=new RegisterServiceImpl();
			Register reg=rs.findRegisterByNo(medicalNo);
			String name=reg.getName();
			List<Drug> drugList=drugs.findAllDrug();
			request.setAttribute("medicalNo", medicalNo);
			request.setAttribute("name", name);
			request.setAttribute("drugList", drugList);
			request.getRequestDispatcher("hospital/dispensing-give.jsp").forward(request, response);
		}else if(action.equals("DrugAldispCount")){
			String n=request.getParameter("n");
			int aldispCount=Integer.valueOf(n);
			String drugid=request.getParameter("drid");
			String medicalNo=request.getParameter("mno");
			Drug drug=drugs.findDrugById(drugid);
			int msg;
			int inv=drug.getInventory();
			if (aldispCount>drug.getInventory()) {
				msg=inv;
			}else{
				drug.setInventory(inv-aldispCount);
				drugs.updateDrug(drug);
				Disp disp=ds.findDispByNoDID(medicalNo, drugid);
				disp.setAldispCount(aldispCount);
				int dc=disp.getDispCount();
				disp.setNodispCount(dc-aldispCount);
				int fg=ds.updateDisp(disp);
				if (fg==1) {
					msg=-1;
				}else {
					msg=0;
				}
			}
			Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String mesg=gson.toJson(msg);
			Writer out=response.getWriter();
			out.write(mesg);
			out.flush();
		}else if(action.equals("AddDispD")){
			String medicalNo=request.getParameter("medicalNo");
			String drugId=request.getParameter("drugId");
			String dispCoun=request.getParameter("dispCount");
			int dispCount=Integer.valueOf(dispCoun);
			int aldispCount=0;
			int nodispCount=dispCount;
			Date date=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dispTime=sdf.format(date);
			Disp disp=new Disp();
			disp.setMedicalNo(medicalNo);
			disp.setDrugId(drugId);
			disp.setDispCount(dispCount);
			disp.setAldispCount(aldispCount);
			disp.setNodispCount(nodispCount);
			disp.setDispTime(dispTime);
			ds.addDisp(disp);
			response.sendRedirect("DispAction?action=FindDispPage");
		}else if(action.equals("Look")){
			String medicalNo=request.getParameter("medicalNo");
			List<Disp> dispList=ds.findDispListByNo(medicalNo);
			RegisterService rs=new RegisterServiceImpl();
			Register reg=rs.findRegisterByNo(medicalNo);
			String name=reg.getName();
			List<Drug> drugList=drugs.findAllDrug();
			request.setAttribute("medicalNo", medicalNo);
			request.setAttribute("name", name);
			request.setAttribute("dispList", dispList);
			request.setAttribute("drugList", drugList);
			request.getRequestDispatcher("hospital/dispensing-look.jsp").forward(request, response);
		}
	}

}
