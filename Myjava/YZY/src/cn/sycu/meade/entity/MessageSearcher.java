package cn.sycu.meade.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class MessageSearcher implements Serializable {
	private String  title = "";
	private String  content = "";
	private Timestamp publishDateTimeBegin = Timestamp.valueOf("1970-01-01 0:0:0.0");
	private Timestamp publishDateTimeEnd = Timestamp.valueOf("2050-01-01 0:0:0.0");
	private String  publishIP = "";
	private int  departmentId = 0;
	
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getPublishDateTimeBegin() {
		return publishDateTimeBegin;
	}
	public void setPublishDateTimeBegin(Timestamp publishDateTimeBegin) {
		this.publishDateTimeBegin = publishDateTimeBegin;
	}
	public Timestamp getPublishDateTimeEnd() {
		return publishDateTimeEnd;
	}
	public void setPublishDateTimeEnd(Timestamp publishDateTimeEnd) {
		this.publishDateTimeEnd = publishDateTimeEnd;
	}
	public String getPublishIP() {
		return publishIP;
	}
	public void setPublishIP(String publishIP) {
		this.publishIP = publishIP;
	}
                                                                                 	//Íâ¼ü´¦Àí
	LoginSearcher publisher = null;
	DepartmentSearcher department = null;

	public LoginSearcher getPublisher() {
		return publisher;
	}
	public void setPublisher(LoginSearcher publisher) {
		this.publisher = publisher;
	}
	public DepartmentSearcher getDepartment() {
		return department;
	}
	public void setDepartment(DepartmentSearcher department) {
		this.department = department;
	}
	
}

