package cn.sycu.meade.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.sycu.meade.business.DepartmentManager;
import cn.sycu.meade.entity.DepartmentBean;
import cn.sycu.meade.entity.DepartmentSearcher;

public class ManageDepartmentsServlet extends BaseServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		DepartmentManager manager = new DepartmentManager();
		List<DepartmentBean> departments = new DepartmentManager().search();

		String fieldName = "DepartmentId";// Ĭ�������ֶ�
		String sortString = " DepartmentId ASC "; // �״�����
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

		String searchDepartmentName = this.getStringParameter("txtSearchDepartmentName", request);
		String searchAddress = this.getStringParameter("txtSearchAddress", request);
		String searchDescription = this.getStringParameter("txtSearchDescription", request);

		DepartmentSearcher searcher = new DepartmentSearcher();
		
		searcher.setDepartmentName(searchDepartmentName);
		searcher.setAddress(searchAddress);
		searcher.setDescription(searchDescription);

		try {
			departments = manager.search(searcher, sortString);

			// departments = manager.search(sortString);

			int pageIndex = 1;// ��ǰҳҳ��
			int pageSize = 10;// Ĭ��ҳ�ߴ磬ָÿһҳ���ݵ�����
			int begin = 1;
			int end = 1;
			int pageCount = 1;// ҳ����
			int rowCount = departments.size();// ��¼����

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
			request.setAttribute("departments", departments);
			
		} catch (Exception ex) {
			request.setAttribute("Message", "alert('" + ex.getMessage() + "');");

		}

		// ����ת��
		request.getRequestDispatcher("manageDepartments.jsp").forward(request, response);// ��ȡ����ַ���
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// �����н��ֶ�
		String operationType = this.getStringParameter("hidOperationType", request);
		String departmentName = this.getStringParameter("txtDepartmentName", request);
		String address = this.getStringParameter("txtAddress", request);
		String description = this.getStringParameter("txtDescription", request);

		DepartmentManager manager = new DepartmentManager();
		// ����Bean
		if (operationType.compareTo("add") == 0) {
			DepartmentBean department = new DepartmentBean();
			department.setDepartmentName(departmentName);
			department.setAddress(address);
			department.setDescription(description);

			try {
				if (manager.add(department)) // �����Ϣ
					request.setAttribute("Mseeage", "alert('��ӳɹ�');");
				else
					request.setAttribute("Mseeage", "alert('���ʧ��');");
			} catch (Exception ex) {
				request.setAttribute("Message", "alert('" + ex.getMessage() + "');");

			}
		} else if (operationType.compareTo("remove") == 0) {
			int departmentId = this.getIntegerParameter("hidDepartmentId", request);

			try {
				if (manager.remove(departmentId))
					request.setAttribute("Message", "alert('ɾ���ɹ�');");
				else
					request.setAttribute("Message", "alert('ɾ��ʧ��');");
			} catch (Exception ex) {
				request.setAttribute("Message", "alert('" + ex.getMessage() + "');");

			}
		} else if (operationType.compareTo("modify") == 0) {
			int departmentId = this.getIntegerParameter("hidDepartmentId", request);

			try {
				DepartmentBean department = manager.search(departmentId);
				if (department == null)
					request.setAttribute("Message", "alert('��Ҫ��������');");
				else
					request.setAttribute("ModifyDepartment", department);
			} catch (Exception ex) {
				request.setAttribute("Message", "alert('" + ex.getMessage() + "');");

			}
		} else if (operationType.compareTo("save") == 0) {
			int departmentId = this.getIntegerParameter("hidDepartmentId", request);

			try {
				DepartmentBean department = manager.search(departmentId);
				department.setDepartmentName(this.getStringParameter("txtDepartmentName", request));
				department.setAddress(this.getStringParameter("txtAddress", request));
				department.setDescription(this.getStringParameter("txtDescription", request));
				if (manager.modify(department))
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
