package cn.sycu.meade.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class UserBean implements Serializable {
	private int userId = 0;
	private int loginId = 0;
	private String userName = "";
	private String  gender = "";
	private Date birthday = new Date(System.currentTimeMillis());
	private String cardId = "";
	private String remark = "";
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getLoginId() {
		return loginId;
	}
	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	LoginBean login = null;

	public LoginBean getLogin() {
		return login;
	}
	public void setLogin(LoginBean login) {
		this.login = login;
	}
}
