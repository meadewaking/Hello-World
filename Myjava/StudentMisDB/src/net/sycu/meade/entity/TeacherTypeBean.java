package net.sycu.meade.entity;

import java.io.Serializable;

public class TeacherTypeBean implements Serializable {
	
	private int TeacherTypeId = 0;
	private String Name = "";
	private String Description = "";
	
	public int getTeacherTypeId() {
		return TeacherTypeId;
	}
	public void setTeacherTypeId(int teacherTypeId) {
		TeacherTypeId = teacherTypeId;
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
		return this.TeacherTypeId+"\t\t"+this.Name+"\t"+this.Description;
	}
	
}
