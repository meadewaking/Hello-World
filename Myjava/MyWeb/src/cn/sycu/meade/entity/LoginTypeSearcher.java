package cn.sycu.meade.entity;

import java.io.Serializable;

public class LoginTypeSearcher implements Serializable {

	private String LoginTypeName = "";
	private String DefaultPage = "";
	private String Description = "";
	
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
