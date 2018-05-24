package net.sycu.meade.view;

import java.util.ArrayList;

import net.sycu.meade.common.Inputer;
import net.sycu.meade.entity.CourseBean;
import net.sycu.meade.entity.CourseTypeBean;
import net.sycu.meade.service.CourseService;
import net.sycu.meade.service.CourseTypeService;

public class CourseUI {
	CourseBean Course = new CourseBean();
	Inputer inputer = new Inputer();
	
	public void add(){
		System.out.println("请输入课程名称");
		Course.setName(inputer.input());
		System.out.println("请输入课程代码");
		Course.setNumber(inputer.input());
		System.out.println("请输入学期");
		Course.setTerm(inputer.inputInteger());
		System.out.println("请输入学分");
		Course.setCredit(inputer.inputFloat());
		System.out.println("请输入课时");
		Course.setHours(inputer.inputFloat());
		System.out.println("请输入描述");
		Course.setDescription(inputer.input());
		for (CourseTypeBean type : new CourseTypeService().display()) {
			System.out.println(type+"\t");
		}
		System.out.println("请输入课程类型编号");
		Course.setCourseTypeId(inputer.inputInteger());
		if (new CourseService().add(Course) > 0) {
			System.out.println("添加成功！");
		} else {
			System.out.println("添加失败！");
		}
	}
	
	public void remove(){
		System.out.println("请输入编号");
		int CourseId = 0;
		CourseId = inputer.inputInteger();
		if (new CourseService().remove(CourseId) > 0) {
			System.out.println("删除成功！");
		} else {
			System.out.println("删除失败！");
		}
	}
	
	public void updata(){
		System.out.println("请输入课程名称");
		Course.setName(inputer.input());
		System.out.println("请输入课程代码");
		Course.setNumber(inputer.input());
		System.out.println("请输入学期");
		Course.setTerm(inputer.inputInteger());
		System.out.println("请输入学分");
		Course.setCredit(inputer.inputFloat());
		System.out.println("请输入课时");
		Course.setHours(inputer.inputFloat());
		System.out.println("请输入描述");
		Course.setDescription(inputer.input());
		for (CourseTypeBean type : new CourseTypeService().display()) {
			System.out.println(type+"\t");
		}
		System.out.println("请输入课程类型编号");
		Course.setCourseTypeId(inputer.inputInteger());
		for (CourseBean Course : new CourseService().display()) {
			System.out.println(Course+"\t");
		}
		System.out.println("请输入要修改的课程编号");
		Course.setCourseId(inputer.inputInteger());
		if (new CourseService().updata(Course) > 0) {
			System.out.println("修改成功！");
		} else {
			System.out.println("修改失败！");
		}
	}
	
	public void search(){
		System.out.println("请输入编号");
		int CourseId = 0;
		CourseId = inputer.inputInteger();
		Course = new CourseService().search(CourseId);
		if (Course == null) {
			System.out.println("查无该数据");
			return;
		}
		System.out.println("编号\t课程名称\t课程代码\t学期\t学分\t课时\t描述\t课程类型");
		System.out.println(Course.toString());
	}
	
	public void display(){
		ArrayList<CourseBean> Courses = new ArrayList<CourseBean>();
		Courses = new CourseService().display();
		if (Courses == null) {
			System.out.println("无数据");
			return;
		}
		System.out.println("编号\t课程名称\t课程代码\t学期\t学分\t课时\t描述\t课程类型");
		for (int i = 0; i < Courses.size(); i++) {
			System.out.println(Courses.get(i).toString());
		}
	}
}
