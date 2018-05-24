package cn.sycu.meade.entity;

import java.sql.Timestamp;

public class LoginSearcher {

	private int LoginId = 0;
	private String LoginName = "";
	private String Password = "";
	private String Email = "";
	private String Nickname = "";
	private int LoginTime = 0;
	private int Grade = 0;
	private Timestamp RegisterDateTime;
	private String RegisterIp = "";
	private Timestamp LastLoginDateTime;
	private String LastLoginIp = "";
	private String Remark = "";
	private int StateId = 0;
	private int TypeId = 0;
	private int DepartmentId = 0;
	
	public int getLoginId() {
		return LoginId;
	}
	public void setLoginId(int loginId) {
		LoginId = loginId;
	}
	public String getLoginName() {
		return LoginName;
	}
	public void setLoginName(String loginName) {
		LoginName = loginName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getNickname() {
		return Nickname;
	}
	public void setNickname(String nickname) {
		Nickname = nickname;
	}
	public int getLoginTime() {
		return LoginTime;
	}
	public void setLoginTime(int loginTime) {
		LoginTime = loginTime;
	}
	public int getGrade() {
		return Grade;
	}
	public void setGrade(int grade) {
		Grade = grade;
	}
	public Timestamp getRegisterDateTime() {
		return RegisterDateTime;
	}
	public void setRegisterDateTime(Timestamp registerDateTime) {
		RegisterDateTime = registerDateTime;
	}
	public String getRegisterIp() {
		return RegisterIp;
	}
	public void setRegisterIp(String registerIp) {
		RegisterIp = registerIp;
	}
	public Timestamp getLastLoginDateTime() {
		return LastLoginDateTime;
	}
	public void setLastLoginDateTime(Timestamp lastLoginDateTime) {
		LastLoginDateTime = lastLoginDateTime;
	}
	public String getLastLoginIp() {
		return LastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		LastLoginIp = lastLoginIp;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public int getStateId() {
		return StateId;
	}
	public void setStateId(int stateId) {
		StateId = stateId;
	}
	public int getTypeId() {
		return TypeId;
	}
	public void setTypeId(int typeId) {
		TypeId = typeId;
	}
	public int getDepartmentId() {
		return DepartmentId;
	}
	public void setDepartmentId(int departmentId) {
		DepartmentId = departmentId;
	}
	
	
}
