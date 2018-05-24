package com.his.action;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.his.excel.ExcleBook;
import com.his.service.DrugService;
import com.his.service.ResourcesService;
import com.his.service.RoleService;
import com.his.service.Role_ResourcesService;
import com.his.service.UserService;
import com.his.serviceimpl.DrugServiceImpl;
import com.his.serviceimpl.ResourcesServiceImpl;
import com.his.serviceimpl.RoleServiceImpl;
import com.his.serviceimpl.Role_ResourcesServiceImpl;
import com.his.serviceimpl.UserServiceImpl;
import com.his.txt.ExportTxt;
import com.his.vo.Drug;
import com.his.vo.Page;
import com.his.vo.Resources;
import com.his.vo.Role;
import com.his.vo.User;

public class DrugAction extends HttpServlet {

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
		DrugService ds=new DrugServiceImpl();
		if (action.equals("FindDrugPage")) {//------------------------------查看药品
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
					if (reid0==3) {
						boo=true;
					}
				}
				if (!boo) {
					request.setAttribute("meesg","当前角色不具有该权限!");
					request.getRequestDispatcher("Error.jsp").forward(request, response);
				}else{
					ResourcesService res=new ResourcesServiceImpl();
					Resources resour=res.findResById(3);
					int sta=resour.getStatus();
					if (sta==0) {
						request.setAttribute("meesg","该权限已禁用!");
						request.getRequestDispatcher("Error.jsp").forward(request, response);
					}else{
						String drugName=request.getParameter("drugName");
						String drugTy=request.getParameter("drugType");
						int drugType = 0;
						if (drugTy!=null) {
							drugType=Integer.valueOf(drugTy);
						}
						int totalCount=ds.findDrugCount(drugName,drugType);//获取药品条数
						int pageSize=5;
						String pageNo=request.getParameter("pageNo");
						int pageNot=1;
						if(pageNo!=null&&pageNo!=""){
							pageNot = Integer.valueOf(pageNo);
						}else{
							pageNo=String.valueOf(pageNot);
						} 
						Page page =ds.findDrugPage(drugName, drugType, pageNot, pageSize, totalCount);
						List<Drug> drugList=page.getPageItem();
						request.setAttribute("drugName",drugName);
						request.setAttribute("drugType",drugType);
						request.setAttribute("drugList",drugList);
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
						request.getRequestDispatcher("medicine/index.jsp").forward(request, response);
					}
				}
			}
		}else if(action.equals("Look")){//------------------------------------------------药品详情
			String DID=request.getParameter("DID");
			Drug drug=ds.findDrugById(DID);
			request.setAttribute("drug",drug);
			request.getRequestDispatcher("medicine/look.jsp").forward(request, response);
		}else if(action.equals("UpdateDrugA")){//--------------------------------------更新药品
			String DID=request.getParameter("DID");
			Drug drug=ds.findDrugById(DID);
			request.setAttribute("drug",drug);
			request.getRequestDispatcher("medicine/editDrug.jsp").forward(request, response);
		}else if(action.equals("UpdateDrugB")){
			String DID=request.getParameter("DID");
			String drugURL=request.getParameter("drugURL");
			String purchasing_price=request.getParameter("purchasing_price");
			String selling_price=request.getParameter("selling_price");
			String drugName=request.getParameter("drugName");
			String drugType=request.getParameter("drugType");
			String description=request.getParameter("description");
			String production_date=request.getParameter("production_date");
			String expiration_date=request.getParameter("expiration_date");
			String shelf_life=request.getParameter("shelf_life");
			String detail=request.getParameter("detail");
			String manufacturer=request.getParameter("manufacturer");
			String directions=request.getParameter("directions");
			String countpurchases=request.getParameter("countpurchases");
			String inventory=request.getParameter("inventory");
			String drugflag=request.getParameter("drugflag");
			String drugcomment=request.getParameter("drugcomment");

			Drug drug=new Drug();
			drug.setDID(DID);
			drug.setDrugURL(drugURL);
			double pp=Double.valueOf(purchasing_price);
			drug.setPurchasing_price(pp);
			double sp=Double.valueOf(selling_price);
			drug.setSelling_price(sp);
			drug.setDrugName(drugName);
			int dt=Integer.valueOf(drugType);
			drug.setDrugType(dt);
			drug.setDescription(description);
			drug.setProduction_date(production_date);
			drug.setExpiration_date(expiration_date);
			int sl=Integer.valueOf(shelf_life);
			drug.setShelf_life(sl);
			drug.setDetail(detail);
			drug.setManufacturer(manufacturer);
			drug.setDirections(directions);
			int cp=Integer.valueOf(countpurchases);
			drug.setCountpurchases(cp);
			int i=Integer.valueOf(inventory);
			drug.setInventory(i);
			int df=Integer.valueOf(drugflag);
			drug.setDrugflag(df);
			drug.setDrugcomment(drugcomment);
			//更新药品
			int flag=ds.updateDrug(drug);
			if (flag==1) {
				response.sendRedirect("DrugAction?action=FindDrugPage");
			}else{
				response.sendRedirect("Error.jsp");
			}
		}else if (action.equals("DrugToTxt")) {
			String fileName=request.getParameter("fileName");
			List<Drug> drugList=ds.findAllDrug();
			ExportTxt et=new ExportTxt();
			int flag=et.toTxtDoc(drugList, fileName);
			Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String r=gson.toJson(flag);
			Writer out=response.getWriter();
			out.write(r);
			out.flush();
		}else if(action.equals("ExportExcel")){
			String fileName=request.getParameter("fileName");
			List<Drug> drugList=ds.findAllDrug();
			ExcleBook eb =new ExcleBook();
			int flag=eb.excleOutDrug(drugList, fileName);
			Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String r=gson.toJson(flag);
			Writer out=response.getWriter();
			out.write(r);
			out.flush();
		}else if (action.equals("UPLD")) {
			Drug drug=new Drug();
			//为解析类提供配置信息 
			DiskFileItemFactory factory = new DiskFileItemFactory(); 
			//创建解析类的实例 
			ServletFileUpload sfu = new ServletFileUpload(factory); 
			sfu.setHeaderEncoding("UTF-8");//解决http报头乱码，即中文文件名乱码
			//开始解析 
			sfu.setFileSizeMax(1024*400); 
			//每个表单域中数据会封装到一个对应的FileItem对象上 
			try { 
				List<FileItem> items = sfu.parseRequest(request); 
				//区分表单域 
				for (int i1 = 0; i1 < items.size(); i1++) { 
					FileItem item = items.get(i1); 
					//isFormField为true，表示这不是文件上传表单域 
					if(!item.isFormField()){ 
						ServletContext sctx = getServletContext(); 
						//获得存放文件的物理路径 
						//upload下的某个文件夹 得到当前在线的用户 找到对应的文件夹 
						String path = sctx.getRealPath("/medicine"); 
						//System.out.println("路径"+path); 
						//获得文件名 
						String fileName = item.getName(); 
						drug.setDrugURL("medicine/"+fileName);
						//该方法在某些平台(操作系统),会返回路径+文件名 
						fileName = fileName.substring(fileName.lastIndexOf("/")+1); 
						File file = new File(path+"\\"+fileName); 
						if(!file.exists()){ 
							item.write(file); 
						} 
					}else{
						//获取表单的属性名字  
						String name1 = item.getFieldName();  
						//如果获取的 表单信息是普通的 文本 信息  
						//获取用户具体输入的字符串 ，名字起得挺好，因为表单提交过来的是 字符串类型的  
						String value = item.getString(); 
						System.out.println(name1+":"+value);
						if(name1.equals("druid")){
							drug.setDID(value);
						}else if(name1.equals("drugType")){
							if(value!=null&&value!=""){
								drug.setDrugType(Integer.parseInt(value));
							}
						}else if (name1.equals("pprice")) {
							if(value!=null&&value!=""){
								drug.setPurchasing_price(Double.valueOf(value));
							}
						}else if(name1.equals("sellingprice")){
							if(value!=null&&value!=""){
								drug.setSelling_price(Double.valueOf(value));
							}
						}else if (name1.equals("drugName")) {
							drug.setDrugName(value);
						}else if (name1.equals("description")) {
							drug.setDescription(value);
						}else if (name1.equals("production_date")) {
							drug.setProduction_date(value);
						}else if (name1.equals("expiration_date")) {
							drug.setExpiration_date(value);
						}else if (name1.equals("shelf_life")) {
							if(value!=null&&value!=""){
								drug.setShelf_life(Integer.valueOf(value));
							}
						}else if (name1.equals("detail")) {
							drug.setDetail(value);
						}else if (name1.equals("manufacturer")) {
							drug.setManufacturer(value);
						}else if (name1.equals("directions")) {
							drug.setDirections(value);
						}else if (name1.equals("countpurchases")) {
							if(value!=null&&value!=""){
								drug.setCountpurchases(Integer.valueOf(value));
							}
						}else if (name1.equals("inventory")) {
							if(value!=null&&value!=""){
								drug.setInventory(Integer.valueOf(value));
							}
						}else if (name1.equals("drugflag")) {
							if(value!=null&&value!=""){
								drug.setDrugflag(Integer.valueOf(value));
							}
						}else if (name1.equals("drugcomment")) {
							drug.setDrugcomment(value);
						}
					} 
				}
			} catch (Exception e) { 
				e.printStackTrace(); 
			}
			ds.addDrug(drug);
			response.sendRedirect("DrugAction?action=FindDrugPage");
		}else if (action.equals("UpdateU")) {
			Drug drug=new Drug();
			//为解析类提供配置信息 
			DiskFileItemFactory factory = new DiskFileItemFactory(); 
			//创建解析类的实例 
			ServletFileUpload sfu = new ServletFileUpload(factory); 
			sfu.setHeaderEncoding("GBK");//解决http报头乱码，即中文文件名乱码
			//开始解析 
			sfu.setFileSizeMax(1024*400); 
			//每个表单域中数据会封装到一个对应的FileItem对象上 
			try { 
				List<FileItem> items = sfu.parseRequest(request); 
				//区分表单域 
				for (int i1 = 0; i1 < items.size(); i1++) { 
					FileItem item = items.get(i1); 
					//isFormField为true，表示这不是文件上传表单域 
					if(!item.isFormField()){ 
						ServletContext sctx = getServletContext(); 
						//获得存放文件的物理路径 
						//upload下的某个文件夹 得到当前在线的用户 找到对应的文件夹 
						String path = sctx.getRealPath("/medicine"); 
						//System.out.println("路径"+path); 
						//获得文件名 
						String fileName = item.getName(); 
						drug.setDrugURL("medicine/"+fileName);
						//该方法在某些平台(操作系统),会返回路径+文件名 
						fileName = fileName.substring(fileName.lastIndexOf("/")+1); 
						File file = new File(path+"\\"+fileName); 
						if(!file.exists()){ 
							item.write(file); 
						} 
					}else{
						//获取表单的属性名字  
						String name1 = item.getFieldName();  
						//如果获取的 表单信息是普通的 文本 信息  
						//获取用户具体输入的字符串 ，名字起得挺好，因为表单提交过来的是 字符串类型的  
						String value = item.getString(); 
						System.out.println(name1+":"+value);
						if(name1.equals("DID")){
							drug.setDID(value);
						}else if(name1.equals("drugType")){
							if(value!=null&&value!=""){
								drug.setDrugType(Integer.parseInt(value));
							}
						}else if (name1.equals("purchasing_price")) {
							if(value!=null&&value!=""){
								drug.setPurchasing_price(Double.valueOf(value));
							}
						}else if(name1.equals("selling_price")){
							if(value!=null&&value!=""){
								drug.setSelling_price(Double.valueOf(value));
							}
						}else if (name1.equals("drugName")) {
							drug.setDrugName(value);
						}else if (name1.equals("description")) {
							drug.setDescription(value);
						}else if (name1.equals("production_date")) {
							drug.setProduction_date(value);
						}else if (name1.equals("expiration_date")) {
							drug.setExpiration_date(value);
						}else if (name1.equals("shelf_life")) {
							if(value!=null&&value!=""){
								drug.setShelf_life(Integer.valueOf(value));
							}
						}else if (name1.equals("detail")) {
							drug.setDetail(value);
						}else if (name1.equals("manufacturer")) {
							drug.setManufacturer(value);
						}else if (name1.equals("directions")) {
							drug.setDirections(value);
						}else if (name1.equals("countpurchases")) {
							if(value!=null&&value!=""){
								drug.setCountpurchases(Integer.valueOf(value));
							}
						}else if (name1.equals("inventory")) {
							if(value!=null&&value!=""){
								drug.setInventory(Integer.valueOf(value));
							}
						}else if (name1.equals("drugflag")) {
							if(value!=null&&value!=""){
								drug.setDrugflag(Integer.valueOf(value));
							}
						}else if (name1.equals("drugcomment")) {
							drug.setDrugcomment(value);
						}
					} 
				}
			} catch (Exception e) { 
				e.printStackTrace(); 
			}
			int fh=ds.updateDrug(drug);
			response.sendRedirect("DrugAction?action=FindDrugPage");
		}
	}
}
