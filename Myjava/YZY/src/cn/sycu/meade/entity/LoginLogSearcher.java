package cn.sycu.meade.entity;

import java.sql.Timestamp;

public class LoginLogSearcher {

	private Timestamp LoginDateTimeBegin = Timestamp.valueOf("1970-01-01 00:00:00");
	private Timestamp LoginDateTimeEnd = Timestamp.valueOf("2050-01-01 00:00:00");

	private String  loginIp = "";
	
	public Timestamp getLoginDateTimeBegin() {
		return LoginDateTimeBegin;
	}

	public void setLoginDateTimeBegin(Timestamp loginDateTimeBegin) {
		LoginDateTimeBegin = loginDateTimeBegin;
	}

	public Timestamp getLoginDateTimeEnd() {
		return LoginDateTimeEnd;
	}

	public void setLoginDateTimeEnd(Timestamp loginDateTimeEnd) {
		LoginDateTimeEnd = loginDateTimeEnd;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	private LoginSearcher login = null;

	public LoginSearcher getLogin() {
		return login;
	}

	public void setLogin(LoginSearcher login) {
		this.login = login;
	}
	
}
