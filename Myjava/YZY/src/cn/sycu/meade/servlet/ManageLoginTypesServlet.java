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

		String fieldName = "LoginTypeId";// Ĭ�������ֶ�
		String sortString = " LoginTypeId ASC "; // �״�����
		// ��ȡ�����ֶ�
		if (request.getParameter("hidFieldName") != null && request.getParameter("hidFieldName").length() > 0)
			fieldName = this.getStringParameter("hidFieldName", request);
		// ��ȡ�����ַ���
		if (request.getParameter("hidSortString") != null && request.getParameter("hidSortString").length() > 0)
			sortString = this.getStringParameter("hidSortString", request);
		String operationType = this.getStringParameter("hidOperationType", request);

		if (operationType.compareTo("sorting") == 0) {
			if (sortString.indexOf(fieldName) >= 0) {
				if (sortString.indexOf(" ASC ") >= 0) {
					sortString = sortString.replace(" ASC ", " DESC ");// ȱ���ֶ���������ASC,DESC
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

			int pageIndex = 1;// ��ǰҳҳ��
			int pageSize = 10;// Ĭ��ҳ�ߴ磬ָÿһҳ���ݵ�����
			int begin = 1;
			int end = 1;
			int pageCount = 1;// ҳ����
			int rowCount = loginTypes.size();// ��¼����

			if (request.getParameter("hidPageSize") != null && request.getParameter("hidPageSize").length() > 0) {
				pageSize = this.getIntegerParameter("hidPageSize", request);
			} // ��ȡ�û�ѡ���ҳ�ߴ�

			pageCount = rowCount / pageSize + (rowCount % pageSize == 0 ? 0 : 1);// ����ҳ����
			// ����ҳ����
			if (pageCount < 1)
				pageCount = 1;

			if (request.getParameter("hidPageIndex") != null && request.getParameter("hidPageIndex").length() > 0)
				pageIndex = this.getIntegerParameter("hidPageIndex", request);
			// ������ǰҳ
			if (pageIndex < 1)
				pageIndex = 1;
			if (pageIndex > pageCount)
				pageIndex = pageCount;

			begin = (pageIndex - 1) * pageSize + 1;// ���㵱ǰҳ��һ�����ݵ����
			end = pageIndex * pageSize;// ���㵱ǰҳ���һ�����ݵ����

			request.setAttribute("searcher", searcher);

			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageIndex", pageIndex);
			request.setAttribute("begin", begin);
			request.setAttribute("end", end);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("rowCount", rowCount);

			request.setAttribute("fieldName", fieldName);// ���������ֶ�
			request.setAttribute("sortString", sortString);// ���������ַ���
			request.setAttribute("loginTypes", loginTypes);
		} catch (Exception ex) {
			request.setAttribute("Message", "alert('" + ex.getMessage() + "');");

		}

		// ����ת��
		request.getRequestDispatcher("/admin/manageLoginTypes.jsp").forward(request, response);// ��ȡ����ַ���
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// �����н��ֶ�
		String operationType = this.getStringParameter("hidOperationType", request);
		String LoginTypeName = this.getStringParameter("txtLoginTypeName", request);
		String DefaultPage = this.getStringParameter("txtDefaultPage", request);
		String description = this.getStringParameter("txtDescription", request);

		LoginTypeManager manager = new LoginTypeManager();
		// ����Bean
		if (operationType.compareTo("add") == 0) {
			LoginTypeBean LoginType = new LoginTypeBean();
			LoginType.setLoginTypeName(LoginTypeName);
			LoginType.setDefaultPage(DefaultPage);
			LoginType.setDescription(description);

			try {
				if (manager.add(LoginType)) // �����Ϣ
					request.setAttribute("Mseeage", "alert('��ӳɹ�');");
				else
					request.setAttribute("Mseeage", "alert('���ʧ��');");
			} catch (Exception ex) {
				request.setAttribute("Message", "alert('" + ex.getMessage() + "');");

			}
		} else if (operationType.compareTo("remove") == 0) {
			int LoginTypeId = this.getIntegerParameter("hidLoginTypeId", request);

			try {
				if (manager.remove(LoginTypeId))
					request.setAttribute("Message", "alert('ɾ���ɹ�');");
				else
					request.setAttribute("Message", "alert('ɾ��ʧ��');");
			} catch (Exception ex) {
				request.setAttribute("Message", "alert('" + ex.getMessage() + "');");

			}
		} else if (operationType.compareTo("modify") == 0) {
			int LoginTypeId = this.getIntegerParameter("hidLoginTypeId", request);

			try {
				LoginTypeBean LoginType = manager.search(LoginTypeId);
				if (LoginType == null)
					request.setAttribute("Message", "alert('��Ҫ��������');");
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
					request.setAttribute("Message", "alert('�޸ĳɹ�');");
				else
					request.setAttribute("Message", "alert('�޸�ʧ��');");
			} catch (Exception ex) {
				request.setAttribute("Message", "alert('" + ex.getMessage() + "');");

			}
		} else if (operationType.compareTo("cancelSave") == 0) {

		} else if (operationType.compareTo("sort") == 0) {

		}

		doGet(request, response);
	}
}
