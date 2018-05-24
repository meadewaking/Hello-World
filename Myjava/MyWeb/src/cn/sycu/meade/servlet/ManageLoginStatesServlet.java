package cn.sycu.meade.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sycu.meade.business.LoginStateManager;
import cn.sycu.meade.entity.LoginStateBean;
import cn.sycu.meade.entity.LoginStateSearcher;


public class ManageLoginStatesServlet extends BaseServlet {
	
	LoginStateManager manager = new LoginStateManager();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 查询、分页、排序在doGet中实现
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		// 排序
		String fieldName = "LoginStateId";
		String sortString = " LoginStateId ASC "; // 默认降序，保证首次访问是升序
		
		// 获取排序字段
		if (request.getParameter("hidFieldName") != null
				&& request.getParameter("hidFieldName").length() > 0)
			fieldName = this.getStringParameter("hidFieldName", request);
		// 获取排序字符串
		if (request.getParameter("hidSortString") != null && request.getParameter("hidSortString").length() > 0){
			sortString = this.getStringParameter("hidSortString", request);
		}
		
		String operationType = this.getStringParameter("hidOperationType", request);
		if (operationType.compareTo("sorting") == 0) {
			if (sortString.indexOf(fieldName) >= 0) {
				if (sortString.indexOf(" ASC ") >= 0)
					sortString = sortString.replace(" ASC ", " DESC ");
				else if (sortString.indexOf(" DESC ") >= 0)
					sortString = sortString.replace(" DESC ", " ASC ");
				else
					sortString = fieldName + " ASC ";
			}
			else
				sortString = fieldName + " ASC ";
		}
		
		// 查询条件
		LoginStateSearcher searcher = new LoginStateSearcher();
		searcher.setLoginStateName(this.getStringParameter("txtSearchLoginStateName", request));
		searcher.setDescription(this.getStringParameter("txtSearchDescription", request));
		
		try{
			List<LoginStateBean> loginStates = manager.search(searcher, sortString);
			
			// 分页
			int pageIndex = 1;	// 代表当前页页码
			int pageSize= 20;	// 页尺寸，每页的数据条数
			int begin = 1;
			int end = 1;
			int pageCount = 1;// 页总数
			int rowCount = loginStates.size();	// 记录总数
			
			if (request.getParameter("hidPageSize") != null
					&& request.getParameter("hidPageSize").length() > 0)
				pageSize = this.getIntegerParameter("hidPageSize", request);
			
			//	计算页总数
			pageCount = rowCount / pageSize + (rowCount % pageSize  == 0 ? 0 : 1);
			// 修正页总数
			if (pageCount < 1)
				pageCount = 1;
			
			if (request.getParameter("hidPageIndex") != null
					&& request.getParameter("hidFieldName").length() > 0)
				pageIndex = this.getIntegerParameter("hidPageIndex", request);
			// 修正当前页
			if (pageIndex < 1)
				pageIndex = 1;
			if (pageIndex > pageCount)
				pageIndex = pageCount;
			
			begin = (pageIndex - 1)  * pageSize + 1; // 计算当前页第一条的数据的序号
			end = pageIndex * pageSize;	// 计算当前页最后一条数据的序号

			request.setAttribute("searcher", searcher);
			

			request.setAttribute("pageSize", pageSize);	// 保存页尺寸
			request.setAttribute("pageIndex", pageIndex); // 保存页码
			request.setAttribute("begin", begin); // 保存起始记录序号
			request.setAttribute("end", end); // 保存结尾记录序号
			request.setAttribute("pageCount", pageCount); // 保存页总数
			request.setAttribute("rowCount", rowCount);	// 保存记录总数
			
			request.setAttribute("fieldName", fieldName);
			request.setAttribute("sortString", sortString);
			request.setAttribute("loginStates", loginStates);
		}  catch(Exception ex) {
			request.setAttribute("Message", "alert('" + ex.getMessage() + "');");
		}
		// 请求转发
		request.getRequestDispatcher("/admin/manageLoginStates.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 增、删、改（保存）在doPost中实现
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String operationType = this.getStringParameter("hidOperationType", request);
		
		if (operationType.compareTo("add") == 0) {
			LoginStateBean loginState = new LoginStateBean();
			loginState.setLoginStateName(this.getStringParameter("txtLoginStateName", request));
			loginState.setCanLogin(this.getBooleanParameter("txtCanLogin", request));
			loginState.setDescription(this.getStringParameter("txtDescription", request));
			
			try{
			if (manager.add(loginState))
				request.setAttribute("Message", "alert('添加成功');");
			else
				request.setAttribute("Message", "alert('添加失败');");
			} catch(Exception ex) {
				request.setAttribute("Message", "alert('" + ex.getMessage() + "');");
			}
		}
		else if (operationType.compareTo("remove") == 0) {
			int loginStateId = this.getIntegerParameter("hidLoginStateId", request);
			
			try{
			if (manager.remove(loginStateId))
				request.setAttribute("Message", "alert('删除成功');");
			else
				request.setAttribute("Message", "alert('删除失败');");
			} catch(Exception ex) {
				request.setAttribute("Message", "alert('" + ex.getMessage() + "');");
			}
		}
		else if (operationType.compareTo("modify") == 0) {
			int loginStateId = this.getIntegerParameter("hidLoginStateId", request);
			
			try{
				LoginStateBean loginState = manager.search(loginStateId);
				if (loginState == null)
					request.setAttribute("Message", "alert('未找到该数据，不能修改。');");
				else
					request.setAttribute("ModifyLoginState", loginState); // 查到了，将所查数据显示在JSP中
			} catch(Exception ex) {
				request.setAttribute("Message", "alert('" + ex.getMessage() + "');");
			}
		}
		else if (operationType.compareTo("save") == 0) {
			int loginStateId = this.getIntegerParameter("hidLoginStateId", request);
			
			try{
				LoginStateBean loginState = manager.search(loginStateId);
				loginState.setLoginStateName(this.getStringParameter("txtLoginStateName", request));
				loginState.setCanLogin(this.getBooleanParameter("txtCanLogin", request));
				loginState.setDescription(this.getStringParameter("txtDescription", request));
				
				if (manager.modify(loginState))
					request.setAttribute("Message", "alert('保存成功');");
				else
					request.setAttribute("Message", "alert('保存失败');");
			} catch(Exception ex) {
				request.setAttribute("Message", "alert('" + ex.getMessage() + "');");
			}
		}
		else if (operationType.compareTo("cancelSave") == 0) {
			
		}
		
		doGet(request,response);	// 显示网页,实现回传(callback)
	}

}
