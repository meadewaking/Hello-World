package cn.sycu.meade.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sycu.meade.business.MessageManager;
import cn.sycu.meade.entity.*;

public class ShowDepartmentMessageListServlet extends BaseServlet {
	
	MessageManager manager = new MessageManager();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//查询 分页 排序在doGet方法中实现
		//排序///////////////////////
		String fieldName = "MessageId";//默认按部门编号排序
		String sortString = " MessageId ASC ";
		
		//获取排序字段
		if (request.getParameter("hidFieldName") != null
				&& request.getParameter("hidFieldName").length() > 0)
			fieldName = this.getStringParameter("hidFieldName", request);
		//获取排序字符串	
		if (request.getParameter("hidSortString") != null
					&& request.getParameter("hidSortString").length() > 0)
			sortString = this.getStringParameter("hidSortString", request);
		
		String operationType = this.getStringParameter("hidOperationType", request);
		if (operationType.compareTo("sorting") == 0) {
			if (sortString.indexOf(fieldName) >= 0) {
				if (sortString.indexOf(" ASC ") >= 0) 
					sortString = sortString.replace(" ASC ", " DESC ");
				else if (sortString.indexOf(" DESC ") >= 0)
					sortString = sortString.replace( " DESC ", " ASC ");
				else
					sortString = fieldName + " ASC ";
			}
			else
				sortString = fieldName + " ASC ";
		}
		
		//查询条件/////////////////////////////////
		MessageSearcher searcher = new MessageSearcher();
		searcher.setTitle(this.getStringParameter("txtSearchTitle", request));
		searcher.setContent(this.getStringParameter("txtsearchContent", request));
		searcher.setPublishIP(this.getStringParameter("txtSearchPublishIp", request));
	
		searcher.setDepartmentId(this.getLogin(request).getDepartmentId());
		
		try {
			List<MessageBean> messages = manager.search(searcher, sortString);
			
			//分页/////////////////////////////////
			int pageIndex = 1;//当前页页码
			int pageSize = 20;//页尺寸，每页的数据条数
			int begin = 1;
			int end = 1;
			int pageCount = 1;//页总数
			int rowCount = messages.size();//记录总数
			
			if (request.getParameter("hidPageSize") != null
					&& request.getParameter("hidPageSize").length() > 0)
				pageSize = this.getIntegerParameter("hidPageSize", request);
			
			//计算页总数
			pageCount = rowCount / pageSize + (rowCount % pageSize == 0 ? 0 : 1);
			//修正页总数
			if (pageCount < 1)
				pageCount = 1;
			
			if (request.getParameter("hidPageIndex") != null
					&& request.getParameter("hidFieldName").length() > 0)
				pageIndex = this.getIntegerParameter("hidPageIndex", request);
			//修正当前页
			if (pageIndex < 1)
				pageIndex = 1;
			if (pageIndex > pageCount)
				pageIndex = pageCount;
			
			begin = (pageIndex - 1) * pageSize + 1;//计算当前页第一条数据的序号
			end = pageIndex * pageSize;//计算当前页最后一条数据的序号
			
			request.setAttribute("searcher", searcher);
			
			request.setAttribute("pageSize", pageSize);//保存页尺寸
			request.setAttribute("pageIndex", pageIndex);//保存页码
			request.setAttribute("begin", begin);//保存起始记录序号
			request.setAttribute("end", end);//保存结尾记录编号
			request.setAttribute("pageCount", pageCount);//保存页总数
			request.setAttribute("rowCount", rowCount);//保存记录总数
			
			request.setAttribute("fieldName", fieldName);//保存排序字段
			request.setAttribute("sortString", sortString);//保存排序字符串
			request.setAttribute("messages", messages);
		} catch (Exception ex) {
			request.setAttribute("Message", "alert('" + ex.getMessage() + "');");
		}	
		//请求转发
		request.getRequestDispatcher("showDepartmentMessageList.jsp").forward(request, response);
	}
	

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//增 删 改在doPost实现
		String operationType = this.getStringParameter("hidOperationType", request);
		
		if (operationType.compareTo("remove") == 0) {					
			int messageId = this.getIntegerParameter("hidMessageId", request);
			
			try {
				if (manager.remove(messageId))
					request.setAttribute("Message", "alert('删除成功');");
				else
					request.setAttribute("Message", "alert('删除失败');");
				} catch (Exception ex) {
					request.setAttribute("Message", "alert('" + ex.getMessage() + "');");
				}			
			}
		doGet(request, response);
	}

}
