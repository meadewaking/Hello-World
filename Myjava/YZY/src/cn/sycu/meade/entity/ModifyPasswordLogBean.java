package cn.sycu.meade.entity;

import java.sql.Timestamp;

public class ModifyPasswordLogBean {
	private int modifyPasswordLogId = 0;
	private Timestamp modifyDateTime = new Timestamp(System.currentTimeMillis());
	private String oldPassword = "";
	private String lastloginIp = "";
	private String modifyIp = "";
	private int loginId = 0;

	public int getModifyPasswordLogId() {
		return modifyPasswordLogId;
	}

	public void setModifyPasswordLogId(int modifyPasswordLogId) {
		this.modifyPasswordLogId = modifyPasswordLogId;
	}

	public Timestamp getModifyDateTime() {
		return modifyDateTime;
	}

	public void setModifyDateTime(Timestamp modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getLastloginIp() {
		return lastloginIp;
	}

	public void setLastloginIp(String lastloginIp) {
		this.lastloginIp = lastloginIp;
	}

	public String getModifyIp() {
		return modifyIp;
	}

	public void setModifyIp(String modifyIp) {
		this.modifyIp = modifyIp;
	}

	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	private LoginBean login = null;

	public LoginBean getLogin() {
		return login;
	}

	public void setLogin(LoginBean login) {
		this.login = login;
	}
	
	
}
