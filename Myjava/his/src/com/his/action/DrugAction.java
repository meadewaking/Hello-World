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
		if (action.equals("FindDrugPage")) {//------------------------------�鿴ҩƷ
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
				request.setAttribute("meesg","��ǰ��ɫ�ѽ���!");
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
					request.setAttribute("meesg","��ǰ��ɫ�����и�Ȩ��!");
					request.getRequestDispatcher("Error.jsp").forward(request, response);
				}else{
					ResourcesService res=new ResourcesServiceImpl();
					Resources resour=res.findResById(3);
					int sta=resour.getStatus();
					if (sta==0) {
						request.setAttribute("meesg","��Ȩ���ѽ���!");
						request.getRequestDispatcher("Error.jsp").forward(request, response);
					}else{
						String drugName=request.getParameter("drugName");
						String drugTy=request.getParameter("drugType");
						int drugType = 0;
						if (drugTy!=null) {
							drugType=Integer.valueOf(drugTy);
						}
						int totalCount=ds.findDrugCount(drugName,drugType);//��ȡҩƷ����
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
		}else if(action.equals("Look")){//------------------------------------------------ҩƷ����
			String DID=request.getParameter("DID");
			Drug drug=ds.findDrugById(DID);
			request.setAttribute("drug",drug);
			request.getRequestDispatcher("medicine/look.jsp").forward(request, response);
		}else if(action.equals("UpdateDrugA")){//--------------------------------------����ҩƷ
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
			//����ҩƷ
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
			//Ϊ�������ṩ������Ϣ 
			DiskFileItemFactory factory = new DiskFileItemFactory(); 
			//�����������ʵ�� 
			ServletFileUpload sfu = new ServletFileUpload(factory); 
			sfu.setHeaderEncoding("UTF-8");//���http��ͷ���룬�������ļ�������
			//��ʼ���� 
			sfu.setFileSizeMax(1024*400); 
			//ÿ�����������ݻ��װ��һ����Ӧ��FileItem������ 
			try { 
				List<FileItem> items = sfu.parseRequest(request); 
				//���ֱ��� 
				for (int i1 = 0; i1 < items.size(); i1++) { 
					FileItem item = items.get(i1); 
					//isFormFieldΪtrue����ʾ�ⲻ���ļ��ϴ����� 
					if(!item.isFormField()){ 
						ServletContext sctx = getServletContext(); 
						//��ô���ļ�������·�� 
						//upload�µ�ĳ���ļ��� �õ���ǰ���ߵ��û� �ҵ���Ӧ���ļ��� 
						String path = sctx.getRealPath("/medicine"); 
						//System.out.println("·��"+path); 
						//����ļ��� 
						String fileName = item.getName(); 
						drug.setDrugURL("medicine/"+fileName);
						//�÷�����ĳЩƽ̨(����ϵͳ),�᷵��·��+�ļ��� 
						fileName = fileName.substring(fileName.lastIndexOf("/")+1); 
						File file = new File(path+"\\"+fileName); 
						if(!file.exists()){ 
							item.write(file); 
						} 
					}else{
						//��ȡ������������  
						String name1 = item.getFieldName();  
						//�����ȡ�� ����Ϣ����ͨ�� �ı� ��Ϣ  
						//��ȡ�û�����������ַ��� ���������ͦ�ã���Ϊ���ύ�������� �ַ������͵�  
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
			//Ϊ�������ṩ������Ϣ 
			DiskFileItemFactory factory = new DiskFileItemFactory(); 
			//�����������ʵ�� 
			ServletFileUpload sfu = new ServletFileUpload(factory); 
			sfu.setHeaderEncoding("GBK");//���http��ͷ���룬�������ļ�������
			//��ʼ���� 
			sfu.setFileSizeMax(1024*400); 
			//ÿ�����������ݻ��װ��һ����Ӧ��FileItem������ 
			try { 
				List<FileItem> items = sfu.parseRequest(request); 
				//���ֱ��� 
				for (int i1 = 0; i1 < items.size(); i1++) { 
					FileItem item = items.get(i1); 
					//isFormFieldΪtrue����ʾ�ⲻ���ļ��ϴ����� 
					if(!item.isFormField()){ 
						ServletContext sctx = getServletContext(); 
						//��ô���ļ�������·�� 
						//upload�µ�ĳ���ļ��� �õ���ǰ���ߵ��û� �ҵ���Ӧ���ļ��� 
						String path = sctx.getRealPath("/medicine"); 
						//System.out.println("·��"+path); 
						//����ļ��� 
						String fileName = item.getName(); 
						drug.setDrugURL("medicine/"+fileName);
						//�÷�����ĳЩƽ̨(����ϵͳ),�᷵��·��+�ļ��� 
						fileName = fileName.substring(fileName.lastIndexOf("/")+1); 
						File file = new File(path+"\\"+fileName); 
						if(!file.exists()){ 
							item.write(file); 
						} 
					}else{
						//��ȡ������������  
						String name1 = item.getFieldName();  
						//�����ȡ�� ����Ϣ����ͨ�� �ı� ��Ϣ  
						//��ȡ�û�����������ַ��� ���������ͦ�ã���Ϊ���ύ�������� �ַ������͵�  
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
