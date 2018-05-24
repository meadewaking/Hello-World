package cn.sycu.meade.entity;

import java.sql.Timestamp;

public class MessageBean {
	private int messageId = 0;
	private String title = "";
	private String content = "";
	private Timestamp publishDateTime = new Timestamp(System.currentTimeMillis());
	private String publishIP = "";
	private int publisherId = 0;
	private int departmentId = 0;

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
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

	public Timestamp getPublishDateTime() {
		return publishDateTime;
	}

	public void setPublishDateTime(Timestamp publishDateTime) {
		this.publishDateTime = publishDateTime;
	}

	public String getPublishIP() {
		return publishIP;
	}

	public void setPublishIP(String publishIP) {
		this.publishIP = publishIP;
	}

	public int getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	private DepartmentBean department = null;

	public DepartmentBean getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentBean department) {
		this.department = department;
	}

	private LoginBean publisher = null;

	public LoginBean getPublisher() {
		return publisher;
	}

	public void setPublisher(LoginBean publisher) {
		this.publisher = publisher;
	}
}
