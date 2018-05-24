package net.sycu.meade.view;

import java.util.ArrayList;

import net.sycu.meade.common.Inputer;
import net.sycu.meade.entity.ClazzBean;
import net.sycu.meade.entity.StudentBean;
import net.sycu.meade.entity.StudentStateBean;
import net.sycu.meade.service.ClazzService;
import net.sycu.meade.service.StudentService;
import net.sycu.meade.service.StudentStateService;

public class StudentUI {
	StudentBean Student = new StudentBean();
	Inputer inputer = new Inputer();
	
	public void add(){
		System.out.println("请输入姓名");
		Student.setName(inputer.input());
		System.out.println("请输入学号");
		Student.setNumber(inputer.input());
		System.out.println("请输入性别");
		Student.setGender(inputer.input());
		System.out.println("请输入生日");
		Student.setBirthday(inputer.inputDate());
		System.out.println("请输入电话");
		Student.setPhoneNumber(inputer.input());
		System.out.println("请输入地址");
		Student.setAddress(inputer.input());
		System.out.println("请输入备注");
		Student.setRemark(inputer.input());
		for (StudentStateBean state : new StudentStateService().display()) {
			System.out.println(state+"\t");
		}
		System.out.println();
		System.out.println("请输入状态编号");
		Student.setStateId(inputer.inputInteger());
		for (ClazzBean clazz : new ClazzService().display()) {
			System.out.println(clazz+"\t");
		}
		System.out.println();
		System.out.println("请输入班级编号");
		Student.setClazzId(inputer.inputInteger());
		if (new StudentService().add(Student) > 0) {
			System.out.println("添加成功！");
		} else {
			System.out.println("添加失败！");
		}
	}
	
	public void remove(){
		System.out.println("请输入编号");
		int StudentId = 0;
		StudentId = inputer.inputInteger();
		if (new StudentService().remove(StudentId) > 0) {
			System.out.println("删除成功！");
		} else {
			System.out.println("删除失败！");
		}
	}
	
	public void updata(){
		System.out.println("请输入姓名");
		Student.setName(inputer.input());
		System.out.println("请输入学号");
		Student.setNumber(inputer.input());
		System.out.println("请输入性别");
		Student.setGender(inputer.input());
		System.out.println("请输入生日");
		Student.setBirthday(inputer.inputDate());
		System.out.println("请输入电话");
		Student.setPhoneNumber(inputer.input());
		System.out.println("请输入地址");
		Student.setAddress(inputer.input());
		System.out.println("请输入备注");
		Student.setRemark(inputer.input());
		for (StudentStateBean state : new StudentStateService().display()) {
			System.out.println(state+"\t");
		}
		System.out.println("请输入状态编号");
		Student.setStateId(inputer.inputInteger());
		for (ClazzBean clazz : new ClazzService().display()) {
			System.out.println(clazz+"\t");
		}
		System.out.println("请输入班级编号");
		Student.setClazzId(inputer.inputInteger());
		for (StudentBean student : new StudentService().display()) {
			System.out.println(student+"\t");
		}
		System.out.println("请输入要修改的学生编号");
		Student.setStudentId(inputer.inputInteger());
		if (new StudentService().updata(Student) > 0) {
			System.out.println("修改成功！");
		} else {
			System.out.println("修改失败！");
		}
	}
	
	public void search(){
		System.out.println("请输入编号");
		int StudentId = 0;
		StudentId = inputer.inputInteger();
		Student = new StudentService().search(StudentId);
		if (Student == null) {
			System.out.println("查无该数据");
			return;
		}
		System.out.println("编号\t姓名\t学号\t性别\t生日\t\t\t电话\t地址\t备注\t状态\t班级");
		System.out.println(Student.toString());
	}
	
	public void display(){
		ArrayList<StudentBean> Students = new ArrayList<StudentBean>();
		Students = new StudentService().display();
		if (Students == null) {
			System.out.println("无数据");
			return;
		}
		System.out.println("编号\t姓名\t学号\t性别\t生日\t\t\t电话\t地址\t备注\t状态\t班级");
		for (int i = 0; i < Students.size(); i++) {
			System.out.println(Students.get(i).toString());
		}
	}
}
