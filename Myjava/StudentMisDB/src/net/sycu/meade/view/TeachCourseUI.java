package net.sycu.meade.view;

import java.util.ArrayList;

import net.sycu.meade.common.Inputer;
import net.sycu.meade.entity.CourseBean;
import net.sycu.meade.entity.StudentBean;
import net.sycu.meade.entity.TeachCourseBean;
import net.sycu.meade.entity.TeacherBean;
import net.sycu.meade.service.CourseService;
import net.sycu.meade.service.StudentService;
import net.sycu.meade.service.TeachCourseService;
import net.sycu.meade.service.TeacherService;

public class TeachCourseUI {
	TeachCourseBean TeachCourse = new TeachCourseBean();
	Inputer inputer = new Inputer();
	
	public void add(){
		for (TeacherBean Teacher : new TeacherService().display()) {
			System.out.println(Teacher+"\t");
		}
		System.out.println("请输入教师编号");
		TeachCourse.setTeacherId(inputer.inputInteger());
		for (CourseBean course : new CourseService().display()) {
			System.out.println(course+"\t");
		}
		System.out.println("请输入课程编号");
		TeachCourse.setCourseId(inputer.inputInteger());
		System.out.println("请输入开课时间");
		TeachCourse.setBeginDateTime(inputer.inputTimestamp());
		System.out.println("请输入结课时间");
		TeachCourse.setEndDateTime(inputer.inputTimestamp());
		System.out.println("请输入描述");
		TeachCourse.setDescription(inputer.input());
		if (new TeachCourseService().add(TeachCourse) > 0) {
			System.out.println("添加成功！");
		} else {
			System.out.println("添加失败！");
		}
	}
	
	public void remove(){
		System.out.println("请输入编号");
		int TeachCourseId = 0;
		TeachCourseId = inputer.inputInteger();
		if (new TeachCourseService().remove(TeachCourseId) > 0) {
			System.out.println("删除成功！");
		} else {
			System.out.println("删除失败！");
		}
	}
	
	public void updata(){
		for (TeacherBean Teacher : new TeacherService().display()) {
			System.out.println(Teacher+"\t");
		}
		System.out.println("请输入教师编号");
		TeachCourse.setTeacherId(inputer.inputInteger());
		for (CourseBean course : new CourseService().display()) {
			System.out.println(course+"\t");
		}
		System.out.println("请输入课程编号");
		TeachCourse.setCourseId(inputer.inputInteger());
		System.out.println("请输入开课时间");
		TeachCourse.setBeginDateTime(inputer.inputTimestamp());
		System.out.println("请输入结课时间");
		TeachCourse.setEndDateTime(inputer.inputTimestamp());
		System.out.println("请输入描述");
		TeachCourse.setDescription(inputer.input());
		for (TeachCourseBean TeachCourse : new TeachCourseService().display()) {
			System.out.println(TeachCourse+"\t");
		}
		System.out.println("请输入要修改的授课编号");
		TeachCourse.setTeachCourseId(inputer.inputInteger());
		if (new TeachCourseService().updata(TeachCourse) > 0) {
			System.out.println("修改成功！");
		} else {
			System.out.println("修改失败！");
		}
	}
	
	public void search(){
		System.out.println("请输入编号");
		int TeachCourseId = 0;
		TeachCourseId = inputer.inputInteger();
		TeachCourse = new TeachCourseService().search(TeachCourseId);
		if (TeachCourse == null) {
			System.out.println("查无该数据");
			return;
		}
		System.out.println("编号\t教师\t课程\t开课时间\t\t\t结课时间\t\t\t描述");
		System.out.println(TeachCourse.toString());
	}
	
	public void display(){
		ArrayList<TeachCourseBean> TeachCourses = new ArrayList<TeachCourseBean>();
		TeachCourses = new TeachCourseService().display();
		if (TeachCourses == null) {
			System.out.println("无数据");
			return;
		}
		System.out.println("编号\t教师\t课程\t开课时间\t\t\t结课时间\t\t\t描述");
		for (int i = 0; i < TeachCourses.size(); i++) {
			System.out.println(TeachCourses.get(i).toString());
		}
	}
}
