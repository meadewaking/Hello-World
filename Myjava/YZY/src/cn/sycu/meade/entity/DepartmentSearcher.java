package cn.sycu.meade.entity;

import java.io.Serializable;

public class DepartmentSearcher implements Serializable {

	private String DepartmentName = "";
	private String Address = "";
	private String Description = "";

	public String getDepartmentName() {
		return DepartmentName;
	}

	public void setDepartmentName(String departmentName) {
		DepartmentName = departmentName;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

}
