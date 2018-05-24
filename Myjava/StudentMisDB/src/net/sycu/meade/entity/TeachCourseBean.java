package net.sycu.meade.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class TeachCourseBean implements Serializable {
	
	private int TeachCourseId = 0;
	private int TeacherId = 0;
	private int CourseId = 0;
	private Timestamp BeginDateTime = new Timestamp(System.currentTimeMillis());
	private Timestamp EndDateTime = new Timestamp(System.currentTimeMillis());
	private String Description = "";
	
	public int getTeachCourseId() {
		return TeachCourseId;
	}
	public void setTeachCourseId(int teachCourseId) {
		TeachCourseId = teachCourseId;
	}
	public int getTeacherId() {
		return TeacherId;
	}
	public void setTeacherId(int teacherId) {
		TeacherId = teacherId;
	}
	public int getCourseId() {
		return CourseId;
	}
	public void setCourseId(int courseId) {
		CourseId = courseId;
	}
	public Timestamp getBeginDateTime() {
		return BeginDateTime;
	}
	public void setBeginDateTime(Timestamp beginDateTime) {
		BeginDateTime = beginDateTime;
	}
	public Timestamp getEndDateTime() {
		return EndDateTime;
	}
	public void setEndDateTime(Timestamp endDateTime) {
		EndDateTime = endDateTime;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	private CourseBean course = null;
	public CourseBean getCourse() {
		return course;
	}
	public void setCourse(CourseBean course) {
		this.course = course;
	}
	
	private TeacherBean teacher = null;
	public TeacherBean getTeacher() {
		return teacher;
	}
	public void setTeacher(TeacherBean teacher) {
		this.teacher = teacher;
	}
	@Override
	public String toString() {
		return String.format("%d\t%s\t%s\t%s\t%s\t%s\n", TeachCourseId,
				teacher == null?"":teacher.getName(),
				course == null?"":course.getName(),BeginDateTime.toLocaleString(),
				EndDateTime.toLocaleString(),Description
				);
	}
}
