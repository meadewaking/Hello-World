package com.his.vo;

public class Register {
	private String medicalNo;		//������
	private String name;		//����
	private int identifierType;		//֤������
	private String identifier;		//֤����
	private String insuranceNumber;		//�籣��
	private String phoneNumber;		//��ϵ�绰
	private int expenseFlag;		//�Ƿ��Է�
	private int gender;		//�Ա�
	private int age;		//����
	private String profession;		//ְҵ
	private int czflag;		//������
	private int depid;      //���ұ�id
	private int docid;  //ҽ����id
	private int flag;  //״̬
	private String remarks;  //��ע
	private String regtime;//�Һ�ʱ��
	private double regfee;//�Һŷ�
	
	private String depName;//������(���סԺ�����Զ�����ʱʹ��)
	private String docName;//ҽ������(���סԺ�����Զ�����ʱʹ��)
	
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
