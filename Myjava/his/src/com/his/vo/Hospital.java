package com.his.vo;

public class Hospital {
	private String medicalNo;//病历号
	private String nurse;//护理
	private String bedNo;//病床号
	private double payCase;//缴费押金
	private String pcondition;//病情
	private String hosTime;//住院时间
	public String getHosTime() {
		return hosTime;
	}
	public void setHosTime(String hosTime) {
		this.hosTime = hosTime;
	}
	public String getMedicalNo() {
		return medicalNo;
	}
	public void setMedicalNo(String medicalNo) {
		this.medicalNo = medicalNo;
	}
	public String getNurse() {
		return nurse;
	}
	public void setNurse(String nurse) {
		this.nurse = nurse;
	}
	public String getBedNo() {
		return bedNo;
	}
	public void setBedNo(String bedNo) {
		this.bedNo = bedNo;
	}
	public double getPayCase() {
		return payCase;
	}
	public void setPayCase(double payCase) {
		this.payCase = payCase;
	}
	public String getPcondition() {
		return pcondition;
	}
	public void setPcondition(String pcondition) {
		this.pcondition = pcondition;
	}
	
}
