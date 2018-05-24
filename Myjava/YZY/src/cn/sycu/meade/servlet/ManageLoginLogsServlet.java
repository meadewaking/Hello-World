package cn.sycu.meade.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sycu.meade.business.LoginLogManager;
import cn.sycu.meade.entity.LoginLogBean;
import cn.sycu.meade.entity.LoginLogSearcher;
import cn.sycu.meade.entity.LoginSearcher;

public class ManageLoginLogsServlet extends BaseServlet {
	
	LoginLogManager manager = new LoginLogManager();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 查询、分页、排序在doGet中实现
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		// 排序
		String fieldName = "LoginLogId";
		String sortString = " LoginLogId ASC "; // 默认降序，保证首次访问是升序
		
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
		LoginLogSearcher searcher = new LoginLogSearcher();
		searcher.setLoginIp(this.getStringParameter("txtSearchLoginIp", request));
		if (request.getParameter("txtSearchLoginDateTimeBegin") != null
				&& request.getParameter("txtSearchLoginDateTimeBegin").length() != 0)
			searcher.setLoginDateTimeBegin(this.getTimestampParameter("txtSearchLoginDateTimeBegin", request));
		if (request.getParameter("txtSearchLoginDateTimeEnd") != null
			    && request.getParameter("txtSearchLoginDateTimeEnd").length() != 0)
			searcher.setLoginDateTimeEnd(this.getTimestampParameter("txtSearchLoginDateTimeEnd", request));
		
		searcher.setLogin(new LoginSearcher());
		searcher.getLogin().setLoginName(this.getStringParameter("txtSearchLoginName", request));
		try{
			List<LoginLogBean> loginLogs = manager.search(searcher, sortString);
			
			// 分页
			int pageIndex = 1;	// 代表当前页页码
			int pageSize = 20;	// 页尺寸，每页的数据条数
			int begin = 1;
			int end = 1;
			int pageCount = 1;// 页总数
			int rowCount = loginLogs.size();	// 记录总数
			
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
			request.setAttribute("loginLogs", loginLogs);
		}  catch(Exception ex) {
			request.setAttribute("Message", "alert('" + ex.getMessage() + "');");
		}
		// 请求转发
		request.getRequestDispatcher("/admin/manageLoginLogs.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);	// 显示网页,实现回传(callback)
	}

}
