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
		//��ѯ ��ҳ ������doGet������ʵ��
		//����///////////////////////
		String fieldName = "MessageId";//Ĭ�ϰ����ű������
		String sortString = " MessageId ASC ";
		
		//��ȡ�����ֶ�
		if (request.getParameter("hidFieldName") != null
				&& request.getParameter("hidFieldName").length() > 0)
			fieldName = this.getStringParameter("hidFieldName", request);
		//��ȡ�����ַ���	
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
		
		//��ѯ����/////////////////////////////////
		MessageSearcher searcher = new MessageSearcher();
		searcher.setTitle(this.getStringParameter("txtSearchTitle", request));
		searcher.setContent(this.getStringParameter("txtsearchContent", request));
		searcher.setPublishIP(this.getStringParameter("txtSearchPublishIp", request));
	
		searcher.setDepartmentId(this.getLogin(request).getDepartmentId());
		
		try {
			List<MessageBean> messages = manager.search(searcher, sortString);
			
			//��ҳ/////////////////////////////////
			int pageIndex = 1;//��ǰҳҳ��
			int pageSize = 20;//ҳ�ߴ磬ÿҳ����������
			int begin = 1;
			int end = 1;
			int pageCount = 1;//ҳ����
			int rowCount = messages.size();//��¼����
			
			if (request.getParameter("hidPageSize") != null
					&& request.getParameter("hidPageSize").length() > 0)
				pageSize = this.getIntegerParameter("hidPageSize", request);
			
			//����ҳ����
			pageCount = rowCount / pageSize + (rowCount % pageSize == 0 ? 0 : 1);
			//����ҳ����
			if (pageCount < 1)
				pageCount = 1;
			
			if (request.getParameter("hidPageIndex") != null
					&& request.getParameter("hidFieldName").length() > 0)
				pageIndex = this.getIntegerParameter("hidPageIndex", request);
			//������ǰҳ
			if (pageIndex < 1)
				pageIndex = 1;
			if (pageIndex > pageCount)
				pageIndex = pageCount;
			
			begin = (pageIndex - 1) * pageSize + 1;//���㵱ǰҳ��һ�����ݵ����
			end = pageIndex * pageSize;//���㵱ǰҳ���һ�����ݵ����
			
			request.setAttribute("searcher", searcher);
			
			request.setAttribute("pageSize", pageSize);//����ҳ�ߴ�
			request.setAttribute("pageIndex", pageIndex);//����ҳ��
			request.setAttribute("begin", begin);//������ʼ��¼���
			request.setAttribute("end", end);//�����β��¼���
			request.setAttribute("pageCount", pageCount);//����ҳ����
			request.setAttribute("rowCount", rowCount);//�����¼����
			
			request.setAttribute("fieldName", fieldName);//���������ֶ�
			request.setAttribute("sortString", sortString);//���������ַ���
			request.setAttribute("messages", messages);
		} catch (Exception ex) {
			request.setAttribute("Message", "alert('" + ex.getMessage() + "');");
		}	
		//����ת��
		request.getRequestDispatcher("showDepartmentMessageList.jsp").forward(request, response);
	}
	

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//�� ɾ ����doPostʵ��
		String operationType = this.getStringParameter("hidOperationType", request);
		
		if (operationType.compareTo("remove") == 0) {					
			int messageId = this.getIntegerParameter("hidMessageId", request);
			
			try {
				if (manager.remove(messageId))
					request.setAttribute("Message", "alert('ɾ���ɹ�');");
				else
					request.setAttribute("Message", "alert('ɾ��ʧ��');");
				} catch (Exception ex) {
					request.setAttribute("Message", "alert('" + ex.getMessage() + "');");
				}			
			}
		doGet(request, response);
	}

}
