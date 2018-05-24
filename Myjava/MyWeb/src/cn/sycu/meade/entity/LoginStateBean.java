package cn.sycu.meade.entity;

public class LoginStateBean {
	private int LoginStateId = 0;
	private String LoginStateName = "";
	private Boolean CanLogin = true;
	private String Description = "";

	public int getLoginStateId() {
		return LoginStateId;
	}

	public void setLoginStateId(int loginStateId) {
		LoginStateId = loginStateId;
	}

	public String getLoginStateName() {
		return LoginStateName;
	}

	public void setLoginStateName(String loginStateName) {
		LoginStateName = loginStateName;
	}

	public Boolean getCanLogin() {
		return CanLogin;
	}

	public void setCanLogin(Boolean canLogin) {
		CanLogin = canLogin;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

}
