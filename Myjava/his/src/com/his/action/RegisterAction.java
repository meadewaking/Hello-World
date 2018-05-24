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
import com.his.service.RegisterService;
import com.his.service.ResourcesService;
import com.his.service.RoleService;
import com.his.service.Role_ResourcesService;
import com.his.service.UserService;
import com.his.serviceimpl.DepServiceImpl;
import com.his.serviceimpl.DoctorServiceImpl;
import com.his.serviceimpl.RegisterServiceImpl;
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

public class RegisterAction extends HttpServlet {

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
		RegisterService rs=new RegisterServiceImpl();

		if (action.equals("FindRegPage")) {//-------------------------显示查询结果
			String ui=request.getParameter("uid");
			int uid=125;
			if (ui==null||ui=="") {
				ui="125";
			}else{
			uid=Integer.valueOf(ui);
			}
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
					if (reid0==1) {
						boo=true;
					}
				}
				if (!boo) {
					request.setAttribute("meesg","当前角色不具有该权限!");
					request.getRequestDispatcher("Error.jsp").forward(request, response);
				}else{
					ResourcesService res=new ResourcesServiceImpl();
					Resources resour=res.findResById(1);
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
							List<Register> regList=new ArrayList<Register>();
							request.setAttribute("regList", regList);
							request.setAttribute("medicalNo", medicalNo);
							request.setAttribute("docName", docName);
							request.setAttribute("stime", stime);
							request.setAttribute("ftime", ftime);
							request.setAttribute("totalCount",0);
							request.setAttribute("pageNo",1);
							lis.add(1);
							request.setAttribute("lis", lis);
						}else{
							int totalCount=rs.findRegCount(medicalNo, docIdList, depId, stime, ftime);
							int pageSize=5;
							String pageNo=request.getParameter("pageNo");
							int pageNot=1;
							if(pageNo!=null&&pageNo!=""){
								pageNot = Integer.valueOf(pageNo);
							}else{
								pageNo=String.valueOf(pageNot);
							} 
							Page page=rs.findRegPage(medicalNo, docIdList, depId, stime, ftime, pageNot, pageSize, totalCount);
							List<Register> regList=page.getPageItem();
							request.setAttribute("medicalNo", medicalNo);
							request.setAttribute("docName", docName);
							request.setAttribute("stime", stime);
							request.setAttribute("ftime", ftime);
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
						request.getRequestDispatcher("register/index.jsp").forward(request, response);
					}
				}
			}
		}else if(action.equals("Look")){//-------------------详情
			String mNo=request.getParameter("mNo");
			Register reg=rs.findRegisterByNo(mNo);
			request.setAttribute("reg", reg);
			DepService deps=new DepServiceImpl();
			List<Dep> depList=deps.findDepList();
			request.setAttribute("depList", depList);
			DoctorService docs=new DoctorServiceImpl();
			List<Doctor> docList=docs.findAllDoc();
			request.setAttribute("docList", docList);
			request.getRequestDispatcher("register/look.jsp").forward(request, response);
		}else if(action.equals("AddRegA")){//-------------------------获取医生科室列表发送给添加网页
			DepService deps=new DepServiceImpl();
			List<Dep> depList=deps.findDepList();
			request.setAttribute("depList", depList);
			DoctorService docs=new DoctorServiceImpl();
			List<Doctor> docList=docs.findAllDoc();
			request.setAttribute("docList", docList);
			request.getRequestDispatcher("register/add.jsp").forward(request, response);
		}else if(action.equals("AddRegB")){//-----------------------网页返回数据进行添加保存
			Register reg=new Register();
			String name=request.getParameter("name");
			String identifierTyp=request.getParameter("identifierType");
			int identifierType=Integer.valueOf(identifierTyp);
			String identifier=request.getParameter("identifier");
			String insuranceNumber=request.getParameter("insuranceNumber");
			String regfe=request.getParameter("regfee");
			double regfee=0;
			if (regfe!="") {
				regfee=Double.valueOf(regfe);
			}				
			String phoneNumber=request.getParameter("phoneNumber");
			String expenseFla=request.getParameter("expenseFlag");
			int expenseFlag=Integer.valueOf(expenseFla);
			String gende=request.getParameter("gender");
			int gender=Integer.valueOf(gende);
			String ag=request.getParameter("age");
			int age=0;
			if (ag!="") {
				age=Integer.valueOf(ag);
			}
			String profession=request.getParameter("profession");
			String czfla=request.getParameter("czflag");
			int czflag=Integer.valueOf(czfla);
			String depI=request.getParameter("depId");
			int depId=Integer.valueOf(depI);
			String docI=request.getParameter("docId");
			int docId=Integer.valueOf(docI);
			String remarks=request.getParameter("remarks");
			Date date =new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSS");
			SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String regtime=sdf2.format(date);
			String medicalNo=sdf.format(date);
			reg.setMedicalNo(medicalNo);
			reg.setName(name);
			reg.setIdentifierType(identifierType);
			reg.setIdentifier(identifier);
			reg.setInsuranceNumber(insuranceNumber);
			reg.setRegfee(regfee);
			reg.setPhoneNumber(phoneNumber);
			reg.setExpenseFlag(expenseFlag);
			reg.setGender(gender);
			reg.setAge(age);
			reg.setProfession(profession);
			reg.setCzflag(czflag);
			reg.setDepid(depId);
			reg.setDocid(docId);
			reg.setRemarks(remarks);
			reg.setRegtime(regtime);
			reg.setFlag(1);
			rs.addRegister(reg);
			response.sendRedirect("RegisterAction?action=FindRegPage");
		}else if(action.equals("UpdateA")){//-------------------获取要修改的对象
			String medicalNo=request.getParameter("medicalNo");
			Register reg=rs.findRegisterByNo(medicalNo);
			request.setAttribute("reg", reg);
			DepService deps=new DepServiceImpl();
			List<Dep> depList=deps.findDepList();
			request.setAttribute("depList", depList);
			DoctorService docs=new DoctorServiceImpl();
			List<Doctor> docList=docs.findAllDoc();
			request.setAttribute("docList", docList);
			request.getRequestDispatcher("register/edit.jsp").forward(request, response);
		}else if(action.equals("UpdateB")){
			String medicalNo=request.getParameter("medicalNo");
			Register reg=new Register();
			String name=request.getParameter("name");
			String identifierTyp=request.getParameter("identifierType");
			int identifierType=Integer.valueOf(identifierTyp);
			String identifier=request.getParameter("identifier");
			String insuranceNumber=request.getParameter("insuranceNumber");
			String regfe=request.getParameter("regfee");
			double regfee=0;
			if (regfe!="") {
				regfee=Double.valueOf(regfe);
			}				
			String phoneNumber=request.getParameter("phoneNumber");
			String expenseFla=request.getParameter("expenseFlag");
			int expenseFlag=Integer.valueOf(expenseFla);
			String gende=request.getParameter("gender");
			int gender=Integer.valueOf(gende);
			String ag=request.getParameter("age");
			int age=0;
			if (ag!="") {
				age=Integer.valueOf(ag);
			}
			String profession=request.getParameter("profession");
			String czfla=request.getParameter("czflag");
			int czflag=Integer.valueOf(czfla);
			String depI=request.getParameter("depId");
			int depId=Integer.valueOf(depI);
			String docI=request.getParameter("docId");
			int docId=Integer.valueOf(docI);
			String remarks=request.getParameter("remarks");
			String regtime=request.getParameter("regtime");
			reg.setMedicalNo(medicalNo);
			reg.setName(name);
			reg.setIdentifierType(identifierType);
			reg.setIdentifier(identifier);
			reg.setInsuranceNumber(insuranceNumber);
			reg.setRegfee(regfee);
			reg.setPhoneNumber(phoneNumber);
			reg.setExpenseFlag(expenseFlag);
			reg.setGender(gender);
			reg.setAge(age);
			reg.setProfession(profession);
			reg.setCzflag(czflag);
			reg.setDepid(depId);
			reg.setDocid(docId);
			reg.setRemarks(remarks);
			reg.setRegtime(regtime);
			reg.setFlag(1);
			rs.updateRegister(reg);
			response.sendRedirect("RegisterAction?action=FindRegPage");
		}else if(action.equals("UpdateC")){//------------------------------退号
			String medicalNo=request.getParameter("medi");
			Register reg=rs.findRegisterByNo(medicalNo);
			reg.setFlag(4);
			rs.updateRegister(reg);
			response.sendRedirect("RegisterAction?action=FindRegPage");
		}else if(action.equals("exiChecked")){//-------------------------复选框退号
			String result = request.getParameter("ids"); 
			String[] ids = result.split(",");
			int alt=0;//检查是否含有已退号的
			int atime=0;//检查是否含有挂号时间短于一小时的
			for (String s : ids) {
				Register reg=rs.findRegisterByNo(s);
				if (reg.getFlag()==4) {
					alt++;
				}
			}
			if (alt==0) {
				for (String s : ids) {
					Register reg=rs.findRegisterByNo(s);
					reg.setFlag(4);
					rs.updateRegister(reg);
				}
			}
			Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String r=gson.toJson(alt);
			Writer out=response.getWriter();
			out.write(r);
			out.flush();
		}else if(action.equals("findDocList")){
			DoctorService ds=new DoctorServiceImpl();
			String depI=request.getParameter("depid");
			int depId=Integer.valueOf(depI);
			List<Doctor> docList=ds.findDocByDepId(depId);
			Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String s=gson.toJson(docList);
			Writer out=response.getWriter();
			out.write(s);
			out.flush();
		}else if(action.equals("ExportExcel")){
			String fileName=request.getParameter("fileName");
			List<Register> regList=rs.findAllReg();
			DoctorService docs=new DoctorServiceImpl();
			List<Doctor> docList=docs.findAllDoc();
			DepService deps=new DepServiceImpl();
			List<Dep> depList=deps.findDepList();
			ExcleBook eb =new ExcleBook();
			int flag=eb.excleOutReg(regList, docList, depList,fileName);
			Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String r=gson.toJson(flag);
			Writer out=response.getWriter();
			out.write(r);
			out.flush();
		}

	}

}
