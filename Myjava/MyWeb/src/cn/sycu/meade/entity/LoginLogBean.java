package cn.sycu.meade.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class LoginLogBean {
	
	private int LoginLogId = 0;
	private Timestamp LoginDateTime;
	private String LoginIp = "";
	private int LoginId = 0;

	public int getLoginLogId() {
		return LoginLogId;
	}

	public void setLoginLogId(int loginLogId) {
		LoginLogId = loginLogId;
	}

	public Timestamp getLoginDateTime() {
		return LoginDateTime;
	}

	public void setLoginDateTime(Timestamp loginDateTime) {
		LoginDateTime = loginDateTime;
	}

	public String getLoginIp() {
		return LoginIp;
	}

	public void setLoginIp(String loginIp) {
		LoginIp = loginIp;
	}

	public int getLoginId() {
		return LoginId;
	}

	public void setLoginId(int loginId) {
		LoginId = loginId;
	}

	private LoginBean login = null;

	public LoginBean getLogin() {
		return login;
	}

	public void setLogin(LoginBean login) {
		this.login = login;
	}
	
}
