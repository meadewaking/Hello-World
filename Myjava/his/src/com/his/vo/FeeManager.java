package com.his.vo;

public class FeeManager {
	private int gid;//收费管理表id
	private String medicalNo;//病历号
	private int feeid;//收费表id
	private double charge_sum;//总费
	private String feeDate;//收费日期
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public String getMedicalNo() {
		return medicalNo;
	}
	public void setMedicalNo(String medicalNo) {
		this.medicalNo = medicalNo;
	}
	public int getFeeid() {
		return feeid;
	}
	public void setFeeid(int feeid) {
		this.feeid = feeid;
	}
	public double getCharge_sum() {
		return charge_sum;
	}
	public void setCharge_sum(double charge_sum) {
		this.charge_sum = charge_sum;
	}
	public String getFeeDate() {
		return feeDate;
	}
	public void setFeeDate(String feeDate) {
		this.feeDate = feeDate;
	}
	
	
}
