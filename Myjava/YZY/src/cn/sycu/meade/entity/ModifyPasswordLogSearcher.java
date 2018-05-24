package cn.sycu.meade.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class ModifyPasswordLogSearcher implements Serializable {
	private Timestamp modifyDateTimeBegin = Timestamp.valueOf("1970-01-01 0:0:0.0");
	private Timestamp modifyDateTimeEnd = Timestamp.valueOf("2050-01-01 0:0:0.0");
	private String oldPassword = ""; 
	private String modifyIp = "";
	public Timestamp getModifyDateTimeBegin() {
		return modifyDateTimeBegin;
	}
	public void setModifyDateTimeBegin(Timestamp modifyDateTimeBegin) {
		this.modifyDateTimeBegin = modifyDateTimeBegin;
	}
	public Timestamp getModifyDateTimeEnd() {
		return modifyDateTimeEnd;
	}
	public void setModifyDateTimeEnd(Timestamp modifyDateTimeEnd) {
		this.modifyDateTimeEnd = modifyDateTimeEnd;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getModifyIp() {
		return modifyIp;
	}
	public void setModifyIp(String modifyIp) {
		this.modifyIp = modifyIp;
	}
	
	private LoginSearcher login = null;//外键处理，包含对应主表对象
	public LoginSearcher getLogin() {
		return login;
	}
	public void setLogin(LoginSearcher login) {
		this.login = login;
	}
}
