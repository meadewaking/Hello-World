package net.sycu.meade.entity;

import java.io.Serializable;
import java.sql.Date;

public class StudentBean implements Serializable {
	
	private int StudentId = 0;
	private String Name = "";
	private String Number = "";
	private String Gender = "";
	private Date Birthday ;
	private String PhoneNumber = "";
	private String Address = "";
	private String Remark = "";
	private int StateId = 0;
	private int ClazzId = 0;
	
	public int getStudentId() {
		return StudentId;
	}
	public void setStudentId(int studentId) {
		StudentId = studentId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getNumber() {
		return Number;
	}
	public void setNumber(String number) {
		Number = number;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public Date getBirthday() {
		return Birthday;
	}
	public void setBirthday(Date birthday) {
		Birthday = birthday;
	}
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
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
	public int getClazzId() {
		return ClazzId;
	}
	public void setClazzId(int clazzId) {
		ClazzId = clazzId;
	}
	//外键处理，bean类中应该包含外键所对应外键对象的引用
	private StudentStateBean studentState = null;
	public StudentStateBean getStudentState(){
		return studentState;
	}
	public void setStudentState(StudentStateBean studentState){
		this.studentState = studentState;
	}
	
	private ClazzBean clazz = null;
	public ClazzBean clazz(){
		return clazz;
	}
	public void setclazz(ClazzBean clazz){
		this.clazz = clazz;
	}
	@Override
	public String toString() {
		return String.format("%d\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n", StudentId,Name,
				Number,Gender,Birthday.toLocaleString(),PhoneNumber,Address,Remark,
				studentState == null?"":studentState.getName(),clazz == null?"":clazz.getName());
	}
	
}
