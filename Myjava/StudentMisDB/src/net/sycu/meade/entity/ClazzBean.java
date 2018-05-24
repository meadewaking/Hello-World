package net.sycu.meade.entity;

import java.io.Serializable;
import java.sql.Date;

public class ClazzBean implements Serializable { //����bean�඼���Ա����л�
	
	private int ClazzId = 0;		//�������б����ֶ�
	private String Name = "";
	private Date BeginDateTime;
	private Date EndDateTime;
	private String Description = "";
	private int TeacherId = 0;
	//���������ֶε�get��set����
	public int getClazzId() {
		return ClazzId;
	}
	public void setClazzId(int clazzId) {
		ClazzId = clazzId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Date getBeginDateTime() {
		return BeginDateTime;
	}
	public void setBeginDateTime(Date beginDateTime) {
		BeginDateTime = beginDateTime;
	}
	public Date getEndDateTime() {
		return EndDateTime;
	}
	public void setEndDateTime(Date endDateTime) {
		EndDateTime = endDateTime;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public int getTeacherId() {
		return TeacherId;
	}
	public void setTeacherId(int teacherId) {
		TeacherId = teacherId;
	}
	//����bean���а����ӱ�bean
	private TeacherBean teacher = null;
	//�����������ö����get��set����
	public TeacherBean getTeacher() {
		return teacher;
	}
	
	public void setTeacher(TeacherBean teacher) {
		this.teacher = teacher;
	}
	@Override//��дtostring���������ַ���
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%d\t%s\t%s\t%s\t%s\t%s", ClazzId,Name,
				BeginDateTime.toLocaleString(),EndDateTime.toLocaleString(),Description,
				teacher == null?"":teacher.getName());
	}
	
}
