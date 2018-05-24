package cn.sycu.meade.entity;

public class LoginTypeBean {
	private int LoginTypeId = 0;
	private String LoginTypeName = "";
	private String DefaultPage = "";
	private String Description = "";

	public int getLoginTypeId() {
		return LoginTypeId;
	}

	public void setLoginTypeId(int loginTypeId) {
		LoginTypeId = loginTypeId;
	}

	public String getLoginTypeName() {
		return LoginTypeName;
	}

	public void setLoginTypeName(String loginTypeName) {
		LoginTypeName = loginTypeName;
	}

	public String getDefaultPage() {
		return DefaultPage;
	}

	public void setDefaultPage(String defaultPage) {
		DefaultPage = defaultPage;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

}
