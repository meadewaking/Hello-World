package net.sycu.meade.view;

import java.util.ArrayList;

import net.sycu.meade.common.Inputer;
import net.sycu.meade.entity.StudyCourseBean;
import net.sycu.meade.entity.CourseBean;
import net.sycu.meade.entity.StudentBean;
import net.sycu.meade.service.StudyCourseService;
import net.sycu.meade.service.CourseService;
import net.sycu.meade.service.StudentService;


public class StudyCourseUI {
	StudyCourseBean StudyCourse = new StudyCourseBean();
	Inputer inputer = new Inputer();
	
	public void add(){
		for (StudentBean student : new StudentService().display()) {
			System.out.println(student+"\t");
		}
		System.out.println("请输入学生编号");
		StudyCourse.setStudentId(inputer.inputInteger());
		for (CourseBean course : new CourseService().display()) {
			System.out.println(course+"\t");
		}
		System.out.println("请输入课程编号");
		StudyCourse.setCourseId(inputer.inputInteger());
		System.out.println("请输入考试时间");
		StudyCourse.setExamDateTime(inputer.inputTimestamp());
		System.out.println("请输入分数");
		StudyCourse.setScore(inputer.inputFloat());
		System.out.println("请输入描述");
		StudyCourse.setDescription(inputer.input());
		if (new StudyCourseService().add(StudyCourse) > 0) {
			System.out.println("添加成功！");
		} else {
			System.out.println("添加失败！");
		}
	}
	
	public void remove(){
		System.out.println("请输入编号");
		int StudyCourseId = 0;
		StudyCourseId = inputer.inputInteger();
		if (new StudyCourseService().remove(StudyCourseId) > 0) {
			System.out.println("删除成功！");
		} else {
			System.out.println("删除失败！");
		}
	}
	
	public void updata(){
		for (StudentBean student : new StudentService().display()) {
			System.out.println(student+"\t");
		}
		System.out.println("请输入学生编号");
		StudyCourse.setStudentId(inputer.inputInteger());
		for (CourseBean course : new CourseService().display()) {
			System.out.println(course+"\t");
		}
		System.out.println("请输入课程编号");
		StudyCourse.setCourseId(inputer.inputInteger());
		System.out.println("请输入考试时间");
		StudyCourse.setExamDateTime(inputer.inputTimestamp());
		System.out.println("请输入分数");
		StudyCourse.setScore(inputer.inputFloat());
		System.out.println("请输入描述");
		StudyCourse.setDescription(inputer.input());
		for (StudyCourseBean StudyCourse : new StudyCourseService().display()) {
			System.out.println(StudyCourse+"\t");
		}
		System.out.println("请输入要修改的选课编号");
		StudyCourse.setStudyCourseId(inputer.inputInteger());
		if (new StudyCourseService().updata(StudyCourse) > 0) {
			System.out.println("修改成功！");
		} else {
			System.out.println("修改失败！");
		}
	}
	
	public void search(){
		System.out.println("请输入编号");
		int StudyCourseId = 0;
		StudyCourseId = inputer.inputInteger();
		StudyCourse = new StudyCourseService().search(StudyCourseId);
		if (StudyCourse == null) {
			System.out.println("查无该数据");
			return;
		}
		System.out.println("编号\t学生\t课程\t考试时间\t\t\t分数\t描述");
		System.out.println(StudyCourse.toString());
	}
	
	public void display(){
		ArrayList<StudyCourseBean> StudyCourses = new ArrayList<StudyCourseBean>();
		StudyCourses = new StudyCourseService().display();
		if (StudyCourses == null) {
			System.out.println("无数据");
			return;
		}
		System.out.println("编号\t学生\t课程\t考试时间\t\t\t分数\t描述");
		for (int i = 0; i < StudyCourses.size(); i++) {
			System.out.println(StudyCourses.get(i).toString());
		}
	}
}
