package cn.sycu.meade.entity;

import java.sql.Timestamp;

public class NoteSearcher {
	private int noteId = 0;
	private String title = "";
	private String content = "";
	private Timestamp sendDateTimeBegin = Timestamp.valueOf("1970-01-01 00:00:00");
	private Timestamp sendDateTimeEnd = Timestamp.valueOf("2050-01-01 00:00:00");
	private String sendIp = "";
	private int senderId = 0;
	private int sendeeId = 0;
	private boolean isRead = false;

	public Timestamp getSendDateTimeBegin() {
		return sendDateTimeBegin;
	}

	public void setSendDateTimeBegin(Timestamp sendDateTimeBegin) {
		this.sendDateTimeBegin = sendDateTimeBegin;
	}

	public Timestamp getSendDateTimeEnd() {
		return sendDateTimeEnd;
	}

	public void setSendDateTimeEnd(Timestamp sendDateTimeEnd) {
		this.sendDateTimeEnd = sendDateTimeEnd;
	}

	private LoginSearcher sender = null;
	private LoginSearcher sendee = null;

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

	public LoginSearcher getSender() {
		return sender;
	}

	public void setSender(LoginSearcher sender) {
		this.sender = sender;
	}

	public LoginSearcher getSendee() {
		return sendee;
	}

	public void setSendee(LoginSearcher sendee) {
		this.sendee = sendee;
	}
}
