package cn.sycu.meade.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.sycu.meade.business.LoginTypeManager;
import cn.sycu.meade.entity.LoginTypeBean;
import cn.sycu.meade.entity.LoginTypeSearcher;

public class ManageLoginTypesServlet extends BaseServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		LoginTypeManager manager = new LoginTypeManager();
		List<LoginTypeBean> loginTypes = new LoginTypeManager().search();

		String fieldName = "LoginTypeId";// 默认排序字段
		String sortString = " LoginTypeId ASC "; // 首次升序
		// 获取排序字段
		if (request.getParameter("hidFieldName") != null && request.getParameter("hidFieldName").length() > 0)
			fieldName = this.getStringParameter("hidFieldName", request);
		// 获取排序字符串
		if (request.getParameter("hidSortString") != null && request.getParameter("hidSortString").length() > 0)
			sortString = this.getStringParameter("hidSortString", request);
		String operationType = this.getStringParameter("hidOperationType", request);

		if (operationType.compareTo("sorting") == 0) {
			if (sortString.indexOf(fieldName) >= 0) {
				if (sortString.indexOf(" ASC ") >= 0) {
					sortString = sortString.replace(" ASC ", " DESC ");// 缺点字段名不能用ASC,DESC
				} else if (sortString.indexOf(" DESC ") >= 0) {
					sortString = sortString.replace(" DESC ", " ASC ");
				} else {
					sortString = fieldName + " ASC ";
				}
			} else {
				sortString = fieldName + " ASC ";
			}
		}

		String searchLoginTypeName = this.getStringParameter("txtSearchLoginTypeName", request);
		String searchDefaultPage = this.getStringParameter("txtSearchDefaultPage", request);
		String searchDescription = this.getStringParameter("txtSearchDescription", request);

		LoginTypeSearcher searcher = new LoginTypeSearcher();
		
		searcher.setLoginTypeName(searchLoginTypeName);
		searcher.setDefaultPage(searchDefaultPage);
		searcher.setDescription(searchDescription);

		try {
			loginTypes = manager.search(searcher, sortString);

			// LoginTypes = manager.search(sortString);

			int pageIndex = 1;// 当前页页码
			int pageSize = 10;// 默认页尺寸，指每一页数据的条数
			int begin = 1;
			int end = 1;
			int pageCount = 1;// 页总数
			int rowCount = loginTypes.size();// 记录总数

			if (request.getParameter("hidPageSize") != null && request.getParameter("hidPageSize").length() > 0) {
				pageSize = this.getIntegerParameter("hidPageSize", request);
			} // 获取用户选择的页尺寸

			pageCount = rowCount / pageSize + (rowCount % pageSize == 0 ? 0 : 1);// 计算页总数
			// 修正页总数
			if (pageCount < 1)
				pageCount = 1;

			if (request.getParameter("hidPageIndex") != null && request.getParameter("hidPageIndex").length() > 0)
				pageIndex = this.getIntegerParameter("hidPageIndex", request);
			// 修正当前页
			if (pageIndex < 1)
				pageIndex = 1;
			if (pageIndex > pageCount)
				pageIndex = pageCount;

			begin = (pageIndex - 1) * pageSize + 1;// 计算当前页第一条数据的序号
			end = pageIndex * pageSize;// 计算当前页最后一条数据的序号

			request.setAttribute("searcher", searcher);

			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageIndex", pageIndex);
			request.setAttribute("begin", begin);
			request.setAttribute("end", end);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("rowCount", rowCount);

			request.setAttribute("fieldName", fieldName);// 保存排序字段
			request.setAttribute("sortString", sortString);// 保存排序字符串
			request.setAttribute("loginTypes", loginTypes);
		} catch (Exception ex) {
			request.setAttribute("Message", "alert('" + ex.getMessage() + "');");

		}

		// 请求转发
		request.getRequestDispatcher("/admin/manageLoginTypes.jsp").forward(request, response);// 获取请求分发器
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 声明承接字段
		String operationType = this.getStringParameter("hidOperationType", request);
		String LoginTypeName = this.getStringParameter("txtLoginTypeName", request);
		String DefaultPage = this.getStringParameter("txtDefaultPage", request);
		String description = this.getStringParameter("txtDescription", request);

		LoginTypeManager manager = new LoginTypeManager();
		// 整合Bean
		if (operationType.compareTo("add") == 0) {
			LoginTypeBean LoginType = new LoginTypeBean();
			LoginType.setLoginTypeName(LoginTypeName);
			LoginType.setDefaultPage(DefaultPage);
			LoginType.setDescription(description);

			try {
				if (manager.add(LoginType)) // 添加信息
					request.setAttribute("Mseeage", "alert('添加成功');");
				else
					request.setAttribute("Mseeage", "alert('添加失败');");
			} catch (Exception ex) {
				request.setAttribute("Message", "alert('" + ex.getMessage() + "');");

			}
		} else if (operationType.compareTo("remove") == 0) {
			int LoginTypeId = this.getIntegerParameter("hidLoginTypeId", request);

			try {
				if (manager.remove(LoginTypeId))
					request.setAttribute("Message", "alert('删除成功');");
				else
					request.setAttribute("Message", "alert('删除失败');");
			} catch (Exception ex) {
				request.setAttribute("Message", "alert('" + ex.getMessage() + "');");

			}
		} else if (operationType.compareTo("modify") == 0) {
			int LoginTypeId = this.getIntegerParameter("hidLoginTypeId", request);

			try {
				LoginTypeBean LoginType = manager.search(LoginTypeId);
				if (LoginType == null)
					request.setAttribute("Message", "alert('无要查找内容');");
				else
					request.setAttribute("ModifyLoginType", LoginType);
			} catch (Exception ex) {
				request.setAttribute("Message", "alert('" + ex.getMessage() + "');");

			}
		} else if (operationType.compareTo("save") == 0) {
			int LoginTypeId = this.getIntegerParameter("hidLoginTypeId", request);

			try {
				LoginTypeBean LoginType = manager.search(LoginTypeId);
				LoginType.setLoginTypeName(this.getStringParameter("txtLoginTypeName", request));
				LoginType.setDefaultPage(this.getStringParameter("txtDefaultPage", request));
				LoginType.setDescription(this.getStringParameter("txtDescription", request));
				if (manager.modify(LoginType))
					request.setAttribute("Message", "alert('修改成功');");
				else
					request.setAttribute("Message", "alert('修改失败');");
			} catch (Exception ex) {
				request.setAttribute("Message", "alert('" + ex.getMessage() + "');");

			}
		} else if (operationType.compareTo("cancelSave") == 0) {

		} else if (operationType.compareTo("sort") == 0) {

		}

		doGet(request, response);
	}
}
