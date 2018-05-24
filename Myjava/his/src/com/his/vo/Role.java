package com.his.vo;

public class Role {
	private int roleId;//角色ID
	private int roleStatus;//角色状态(启用/禁用)
	private String roleName;//角色名称
	
	public int getRoleStatus() {
		return roleStatus;
	}
	public void setRoleStatus(int roleStatus) {
		this.roleStatus = roleStatus;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
