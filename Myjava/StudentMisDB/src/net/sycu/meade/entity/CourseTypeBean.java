package net.sycu.meade.entity;

import java.io.Serializable;

public class CourseTypeBean implements Serializable {
	
	private int CourseTypeId = 0;
	private String Name = "";
	private String Description = "";
	
	public int getCourseTypeId() {
		return CourseTypeId;
	}
	public void setCourseTypeId(int courseTypeId) {
		CourseTypeId = courseTypeId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.CourseTypeId+"\t\t"+this.Name+"\t"+this.Description;
	}
}
