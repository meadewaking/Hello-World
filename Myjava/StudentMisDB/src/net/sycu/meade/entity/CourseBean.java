package net.sycu.meade.entity;

import java.io.Serializable;
import java.sql.Date;

public class CourseBean implements Serializable {
	
	private int CourseId = 0;
	private String Name = "";
	private String Number = "";
	private int Term = 0;
	private float Credit = 0;
	private float Hours = 0;
	private String Description = "";
	private int CourseTypeId = 0;
	
	public int getCourseId() {
		return CourseId;
	}
	public void setCourseId(int courseId) {
		CourseId = courseId;
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
	public int getTerm() {
		return Term;
	}
	public void setTerm(int term) {
		Term = term;
	}
	public float getCredit() {
		return Credit;
	}
	public void setCredit(float credit) {
		Credit = credit;
	}
	public float getHours() {
		return Hours;
	}
	public void setHours(float hours) {
		Hours = hours;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public int getCourseTypeId() {
		return CourseTypeId;
	}
	public void setCourseTypeId(int courseTypeId) {
		CourseTypeId = courseTypeId;
	}
	
	private CourseTypeBean courseType = null;
	
	public CourseTypeBean getCourseType() {
		return courseType;
	}
	public void setCourseType(CourseTypeBean courseType) {
		this.courseType = courseType;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%d\t%s\t%s\t%s\t%.2f\t%.2f\t%s\t%s", CourseId,Name,
				Number,Term,Credit,Hours,Description,
				courseType == null?"":courseType.getName());
	}
	
}
