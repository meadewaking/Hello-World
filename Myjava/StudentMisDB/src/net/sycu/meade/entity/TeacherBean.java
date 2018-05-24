package net.sycu.meade.entity;

import java.io.Serializable;
import java.sql.Date;

public class TeacherBean implements Serializable {
	
	private int TeacherId = 0;
	private String Name = "";
	private String Number = "";
	private String Gender = "";
	private Date Birthday ;
	private String PhoneNumber = "";
	private String Address = "";
	private float Salary = 0;
	private String Remark = "";
	private int StateId = 0;
	private int TypeId = 0;
	
	public int getTeacherId() {
		return TeacherId;
	}
	public void setTeacherId(int teacherId) {
		TeacherId = teacherId;
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
	public float getSalary() {
		return Salary;
	}
	public void setSalary(float salary) {
		Salary = salary;
	}
	public int getTypeId() {
		return TypeId;
	}
	public void setTypeId(int typeId) {
		TypeId = typeId;
	}

	//外键处理，bean类中应该包含外键所对应外键对象的引用
	private TeacherStateBean teacherState = null;
	public TeacherStateBean getTeacherState(){
		return teacherState;
	}
	public void setTeacherState(TeacherStateBean teacherState){
		this.teacherState = teacherState;
	}
	
	private TeacherTypeBean TeacherType = null;
	public TeacherTypeBean getTeacherType(){
		return TeacherType;
	}
	public void setTeacherType(TeacherTypeBean TeacherType){
		this.TeacherType = TeacherType;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%d\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n", TeacherId,Name,
				Number,Gender,Birthday.toLocaleString(),PhoneNumber,Address,Salary,Remark,
				teacherState == null?"":teacherState.getName(),TeacherType == null?"":TeacherType.getName());
	}
	
}
