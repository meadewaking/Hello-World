package net.sycu.meade.entity;

import java.io.Serializable;

public class TeacherStateBean implements Serializable {

	private int TeacherStateId = 0;
	private String Name = "";
	private boolean Inschool = false;
	private String Description = "";

	public int getTeacherStateId() {
		return TeacherStateId;
	}

	public void setTeacherStateId(int teacherStateId) {
		TeacherStateId = teacherStateId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public boolean isInschool() {
		return Inschool;
	}

	public void setInschool(boolean inschool) {
		Inschool = inschool;
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
		return String.format("%d\t\t%s\t%s\t%s\n", this.TeacherStateId,this.Name,this.Inschool ?"在校":"不在校",this.Description);
	}
}
