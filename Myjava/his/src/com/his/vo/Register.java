package com.his.vo;

public class Register {
	private String medicalNo;		//病历号
	private String name;		//姓名
	private int identifierType;		//证件类型
	private String identifier;		//证件号
	private String insuranceNumber;		//社保号
	private String phoneNumber;		//联系电话
	private int expenseFlag;		//是否自费
	private int gender;		//性别
	private int age;		//年龄
	private String profession;		//职业
	private int czflag;		//初复诊
	private int depid;      //科室表id
	private int docid;  //医生表id
	private int flag;  //状态
	private String remarks;  //备注
	private String regtime;//挂号时间
	private double regfee;//挂号费
	
	private String depName;//科室名(添加住院信心自动带出时使用)
	private String docName;//医生姓名(添加住院信心自动带出时使用)
	
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public double getRegfee() {
		return regfee;
	}
	public void setRegfee(double regfee) {
		this.regfee = regfee;
	}
	public int getDepid() {
		return depid;
	}
	public void setDepid(int depid) {
		this.depid = depid;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	public String getMedicalNo() {
		return medicalNo;
	}
	public void setMedicalNo(String medicalNo) {
		this.medicalNo = medicalNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIdentifierType() {
		return identifierType;
	}
	public void setIdentifierType(int identifierType) {
		this.identifierType = identifierType;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getInsuranceNumber() {
		return insuranceNumber;
	}
	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getExpenseFlag() {
		return expenseFlag;
	}
	public void setExpenseFlag(int expenseFlag) {
		this.expenseFlag = expenseFlag;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public int getCzflag() {
		return czflag;
	}
	public void setCzflag(int czflag) {
		this.czflag = czflag;
	}
	public int getDocid() {
		return docid;
	}
	public void setDocid(int docid) {
		this.docid = docid;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
