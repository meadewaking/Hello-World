package net.sycu.meade.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class StudyCourseBean implements Serializable {
	
	private int StudyCourseId = 0;
	private int StudentId = 0;
	private int CourseId = 0;
	private Timestamp ExamDateTime = new Timestamp(System.currentTimeMillis());
	private float Score = 0;
	private String Description = "";
	
	public int getStudyCourseId() {
		return StudyCourseId;
	}
	public void setStudyCourseId(int studyCourseId) {
		StudyCourseId = studyCourseId;
	}
	public int getStudentId() {
		return StudentId;
	}
	public void setStudentId(int studentId) {
		StudentId = studentId;
	}
	public int getCourseId() {
		return CourseId;
	}
	public void setCourseId(int courseId) {
		CourseId = courseId;
	}
	public Timestamp getExamDateTime() {
		return ExamDateTime;
	}
	public void setExamDateTime(Timestamp examDateTime) {
		ExamDateTime = examDateTime;
	}
	public float getScore() {
		return Score;
	}
	public void setScore(float score) {
		Score = score;
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
	
	private StudentBean student = null;
	public StudentBean getStudent() {
		return student;
	}
	public void setStudent(StudentBean student) {
		this.student = student;
	}
	@Override
	public String toString() {
		return String.format("%d\t%s\t%s\t%s\t%s\t%s\n", StudyCourseId,
				student == null?"":student.getName(),
				course == null?"":course.getName(),ExamDateTime.toLocaleString(),
				Score,Description
				);
	}
}	
