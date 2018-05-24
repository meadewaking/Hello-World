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
		// ��ѯ����ҳ��������doGet��ʵ��
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		// ����
		String fieldName = "LoginStateId";
		String sortString = " LoginStateId ASC "; // Ĭ�Ͻ��򣬱�֤�״η���������
		
		// ��ȡ�����ֶ�
		if (request.getParameter("hidFieldName") != null
				&& request.getParameter("hidFieldName").length() > 0)
			fieldName = this.getStringParameter("hidFieldName", request);
		// ��ȡ�����ַ���
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
		
		// ��ѯ����
		LoginStateSearcher searcher = new LoginStateSearcher();
		searcher.setLoginStateName(this.getStringParameter("txtSearchLoginStateName", request));
		searcher.setDescription(this.getStringParameter("txtSearchDescription", request));
		
		try{
			List<LoginStateBean> loginStates = manager.search(searcher, sortString);
			
			// ��ҳ
			int pageIndex = 1;	// ����ǰҳҳ��
			int pageSize= 20;	// ҳ�ߴ磬ÿҳ����������
			int begin = 1;
			int end = 1;
			int pageCount = 1;// ҳ����
			int rowCount = loginStates.size();	// ��¼����
			
			if (request.getParameter("hidPageSize") != null
					&& request.getParameter("hidPageSize").length() > 0)
				pageSize = this.getIntegerParameter("hidPageSize", request);
			
			//	����ҳ����
			pageCount = rowCount / pageSize + (rowCount % pageSize  == 0 ? 0 : 1);
			// ����ҳ����
			if (pageCount < 1)
				pageCount = 1;
			
			if (request.getParameter("hidPageIndex") != null
					&& request.getParameter("hidFieldName").length() > 0)
				pageIndex = this.getIntegerParameter("hidPageIndex", request);
			// ������ǰҳ
			if (pageIndex < 1)
				pageIndex = 1;
			if (pageIndex > pageCount)
				pageIndex = pageCount;
			
			begin = (pageIndex - 1)  * pageSize + 1; // ���㵱ǰҳ��һ�������ݵ����
			end = pageIndex * pageSize;	// ���㵱ǰҳ���һ�����ݵ����

			request.setAttribute("searcher", searcher);
			

			request.setAttribute("pageSize", pageSize);	// ����ҳ�ߴ�
			request.setAttribute("pageIndex", pageIndex); // ����ҳ��
			request.setAttribute("begin", begin); // ������ʼ��¼���
			request.setAttribute("end", end); // �����β��¼���
			request.setAttribute("pageCount", pageCount); // ����ҳ����
			request.setAttribute("rowCount", rowCount);	// �����¼����
			
			request.setAttribute("fieldName", fieldName);
			request.setAttribute("sortString", sortString);
			request.setAttribute("loginStates", loginStates);
		}  catch(Exception ex) {
			request.setAttribute("Message", "alert('" + ex.getMessage() + "');");
		}
		// ����ת��
		request.getRequestDispatcher("/admin/manageLoginStates.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ����ɾ���ģ����棩��doPost��ʵ��
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
				request.setAttribute("Message", "alert('��ӳɹ�');");
			else
				request.setAttribute("Message", "alert('���ʧ��');");
			} catch(Exception ex) {
				request.setAttribute("Message", "alert('" + ex.getMessage() + "');");
			}
		}
		else if (operationType.compareTo("remove") == 0) {
			int loginStateId = this.getIntegerParameter("hidLoginStateId", request);
			
			try{
			if (manager.remove(loginStateId))
				request.setAttribute("Message", "alert('ɾ���ɹ�');");
			else
				request.setAttribute("Message", "alert('ɾ��ʧ��');");
			} catch(Exception ex) {
				request.setAttribute("Message", "alert('" + ex.getMessage() + "');");
			}
		}
		else if (operationType.compareTo("modify") == 0) {
			int loginStateId = this.getIntegerParameter("hidLoginStateId", request);
			
			try{
				LoginStateBean loginState = manager.search(loginStateId);
				if (loginState == null)
					request.setAttribute("Message", "alert('δ�ҵ������ݣ������޸ġ�');");
				else
					request.setAttribute("ModifyLoginState", loginState); // �鵽�ˣ�������������ʾ��JSP��
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
					request.setAttribute("Message", "alert('����ɹ�');");
				else
					request.setAttribute("Message", "alert('����ʧ��');");
			} catch(Exception ex) {
				request.setAttribute("Message", "alert('" + ex.getMessage() + "');");
			}
		}
		else if (operationType.compareTo("cancelSave") == 0) {
			
		}
		
		doGet(request,response);	// ��ʾ��ҳ,ʵ�ֻش�(callback)
	}

}
