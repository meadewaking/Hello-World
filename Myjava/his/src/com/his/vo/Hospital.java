package com.his.vo;

public class Hospital {
	private String medicalNo;//������
	private String nurse;//����
	private String bedNo;//������
	private double payCase;//�ɷ�Ѻ��
	private String pcondition;//����
	private String hosTime;//סԺʱ��
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
