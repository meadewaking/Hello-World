package com.his.vo;
/**
 * 角色权限表
 */
public class Role_Resources {
	private int id;//角色权限表主键id
	private int roleId;//角色表id
	private int resId;//权限表id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getResId() {
		return resId;
	}
	public void setResId(int resId) {
		this.resId = resId;
	}
	
}
