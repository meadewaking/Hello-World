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
import com.his.service.FeeManagerService;
import com.his.service.FeeProjectService;
import com.his.service.RegisterService;
import com.his.service.ResourcesService;
import com.his.service.RoleService;
import com.his.service.Role_ResourcesService;
import com.his.service.UserService;
import com.his.serviceimpl.FeeManagerServiceImpl;
import com.his.serviceimpl.FeeProjectServiceImpl;
import com.his.serviceimpl.RegisterServiceImpl;
import com.his.serviceimpl.ResourcesServiceImpl;
import com.his.serviceimpl.RoleServiceImpl;
import com.his.serviceimpl.Role_ResourcesServiceImpl;
import com.his.serviceimpl.UserServiceImpl;
import com.his.vo.FeeManager;
import com.his.vo.FeeProject;
import com.his.vo.Page;
import com.his.vo.Register;
import com.his.vo.Resources;
import com.his.vo.Role;
import com.his.vo.User;

public class FeeAction extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		FeeProjectService fps=new FeeProjectServiceImpl();
		FeeManagerService fms=new FeeManagerServiceImpl();
		String action= request.getParameter("action");
		if (action.equals("AddFee")){//--------------添加收费项目
			String medicalNo=request.getParameter("medicalNo");
			String feeName=request.getParameter("feeName");
			String amoun=request.getParameter("amount");
			double amount=Double.valueOf(amoun);
			List<FeeProject> fprList=fps.findAllFeePro();
			int flag=0;
			for (FeeProject fpro : fprList) {
				if (feeName.equals(fpro.getFeeName())) {
					flag++;
				}
			}
			if (flag==0) {
				FeeProject fp=new FeeProject();
				fp.setFeeName(feeName);
				fp.setAmount(amount);
				fps.addFeeProject(fp);
			}
			FeeManager fm=new FeeManager();
			fm.setMedicalNo(medicalNo);
			fm.setFeeid(fps.findIdByFeeName(feeName));
			Date date=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String feeDate=sdf.format(date);
			fm.setFeeDate(feeDate);
			double charge_sum=amount;
			List<Integer> feeidList=fms.findFeeIdByNo(medicalNo);
			if (feeidList!=null) {
				for (Integer feeid : feeidList) {
					FeeProject fpr=fps.findFeeProById(feeid);
					charge_sum+=fpr.getAmount();
				}
			}
			fm.setCharge_sum(charge_sum);
			fms.addFeeManager(fm);
			response.sendRedirect("FeeAction?action=FindFeePage");
		}else if(action.equals("FindFeePage")){//---------------查看所有
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
					if (reid0==5) {
						boo=true;
					}
				}
				if (!boo) {
					request.setAttribute("meesg","当前角色不具有该权限!");
					request.getRequestDispatcher("Error.jsp").forward(request, response);
				}else{
					ResourcesService res=new ResourcesServiceImpl();
					Resources resour=res.findResById(5);
					int sta=resour.getStatus();
					if (sta==0) {
						request.setAttribute("meesg","该权限已禁用!");
						request.getRequestDispatcher("Error.jsp").forward(request, response);
					}else{
						String medicalNo=request.getParameter("medicalNo");
						if (medicalNo==null) {
							medicalNo="";
						}
						String name=request.getParameter("name");
						if (name==null) {
							name="";
						}
						RegisterService rs=new RegisterServiceImpl();
						List<String> medicalNoList=rs.findNoByName(name,medicalNo);
						int totalCount=fms.findFeeCount(medicalNoList);
						int pageSize=5;
						String pageNo=request.getParameter("pageNo");
						int pageNot=1;
						if(pageNo!=null&&pageNo!=""){
							pageNot = Integer.valueOf(pageNo);
						}else{
							pageNo=String.valueOf(pageNot);
						} 
						Page page=fms.findFeePage(medicalNoList, pageNot, pageSize);
						List<FeeManager> feeList=page.getPageItem();
						List<Register> regList=rs.findAllReg();
						List<FeeProject> feeProList=fps.findAllFeePro();
						request.setAttribute("feeProList", feeProList);
						request.setAttribute("regList", regList);
						request.setAttribute("medicalNo", medicalNo);
						request.setAttribute("name", name);
						request.setAttribute("feeList", feeList);
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
						request.getRequestDispatcher("hospital/charge.jsp").forward(request, response);
					}
				}
			}
		}else if(action.equals("ShowName")){
			RegisterService rs=new RegisterServiceImpl();
			String medicalNo=request.getParameter("mno");
			Register reg=rs.findRegisterByNo(medicalNo);
			Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String r=gson.toJson(reg);
			Writer out=response.getWriter();
			out.write(r);
			out.flush();
		}else if(action.equals("FindFeePro")){
			List<FeeProject> fpList=fps.findAllFeePro();
			request.setAttribute("fpList", fpList);
			request.getRequestDispatcher("hospital/charge-new.jsp").forward(request, response);
		}else if(action.equals("ShowAmount")){
			String fpi=request.getParameter("fpid");
			int fpid=Integer.valueOf(fpi);
			FeeProject fp=fps.findFeeProById(fpid);
			Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String ff=gson.toJson(fp);
			Writer out=response.getWriter();
			out.write(ff);
			out.flush();
		}else if(action.equals("AddProA")){
			String gi=request.getParameter("gid");
			int gid=Integer.valueOf(gi);
			FeeManager fm=fms.findFMById(gid);
			String medicalNo=fm.getMedicalNo();
			RegisterService rs=new RegisterServiceImpl();
			Register reg=rs.findRegisterByNo(medicalNo);
			String name=reg.getName();
			request.setAttribute("medicalNo", medicalNo);
			request.setAttribute("name", name);
			List<FeeProject> fpList=fps.findAllFeePro();
			request.setAttribute("fpList", fpList);
			request.getRequestDispatcher("hospital/charge-one.jsp").forward(request, response);
		}else if(action.equals("AddProB")){
			String medicalNo=request.getParameter("medicalNo");
			String feei=request.getParameter("feeName");
			int feeid=Integer.valueOf(feei);
			FeeManager fm=new FeeManager();
			fm.setMedicalNo(medicalNo);
			fm.setFeeid(feeid);
			Date date=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String feeDate=sdf.format(date);
			fm.setFeeDate(feeDate);
			FeeProject fp=fps.findFeeProById(feeid);
			double amount =fp.getAmount();
			double charge_sum=amount;
			List<Integer> feeidList=fms.findFeeIdByNo(medicalNo);
			if (feeidList!=null) {
				for (Integer fid : feeidList) {
					FeeProject fpr=fps.findFeeProById(fid);
					charge_sum+=fpr.getAmount();
				}
			}
			fm.setCharge_sum(charge_sum);
			fms.addFeeManager(fm);
			response.sendRedirect("FeeAction?action=FindFeePage");
		}

	}

}
