package net.sycu.meade.entity;

import java.io.Serializable;

//ʵ������Ϊ���е�һ����¼����
//����ֶ������ֶ�һһ��Ӧ
public class StudentStateBean implements Serializable {
	
	private int StudentStateId = 0;
	private String Name = "";
	private boolean Inschool = false;
	private String Description = "";

	public int getStudentStateId() {
		return StudentStateId;
	}

	public void setStudentStateId(int studentStateId) {
		StudentStateId = studentStateId;
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
		return String.format("%d\t\t%s\t%s\t%s\n", this.StudentStateId,this.Name,this.Inschool ?"��У":"����У",this.Description);
	}
	
}
