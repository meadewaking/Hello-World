package com.his.vo;

public class Disp {
	private String medicalNo;//病历号
	private String drugId;//药品表主键
	private int dispCount;//发药数量
	private int aldispCount;//已发数量
	private int nodispCount;//未发数量
	private String dispTime;//发药时间
	
	public String getMedicalNo() {
		return medicalNo;
	}
	public void setMedicalNo(String medicalNo) {
		this.medicalNo = medicalNo;
	}
	public String getDrugId() {
		return drugId;
	}
	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}
	public int getDispCount() {
		return dispCount;
	}
	public void setDispCount(int dispCount) {
		this.dispCount = dispCount;
	}
	public int getAldispCount() {
		return aldispCount;
	}
	public void setAldispCount(int aldispCount) {
		this.aldispCount = aldispCount;
	}
	public int getNodispCount() {
		return nodispCount;
	}
	public void setNodispCount(int nodispCount) {
		this.nodispCount = nodispCount;
	}
	public String getDispTime() {
		return dispTime;
	}
	public void setDispTime(String dispTime) {
		this.dispTime = dispTime;
	}
	
}
