package cn.sycu.meade.entity;

import java.sql.Date;

public class NoteBean {
	private int noteId = 0;
	private String title = "";
	private String content = "";
	private Date sendDateTime = new Date(System.currentTimeMillis());
	private String sendIp = "";
	private int senderId = 0;
	private int sendeeId = 0;
	private boolean isRead = false;

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
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

	public Date getSendDateTime() {
		return sendDateTime;
	}

	public void setSendDateTime(Date sendDateTime) {
		this.sendDateTime = sendDateTime;
	}

	public String getSendIp() {
		return sendIp;
	}

	public void setSendIp(String sendIp) {
		this.sendIp = sendIp;
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public int getSendeeId() {
		return sendeeId;
	}

	public void setSendeeId(int sendeeId) {
		this.sendeeId = sendeeId;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

}
