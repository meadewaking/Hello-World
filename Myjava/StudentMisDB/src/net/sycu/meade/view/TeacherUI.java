package net.sycu.meade.view;

import java.util.ArrayList;

import net.sycu.meade.common.Inputer;
import net.sycu.meade.entity.TeacherBean;
import net.sycu.meade.entity.TeacherStateBean;
import net.sycu.meade.entity.TeacherTypeBean;
import net.sycu.meade.service.TeacherService;
import net.sycu.meade.service.TeacherStateService;
import net.sycu.meade.service.TeacherTypeService;

public class TeacherUI {
	TeacherBean Teacher = new TeacherBean();
	Inputer inputer = new Inputer();
	
	public void add(){
		System.out.println("请输入姓名");
		Teacher.setName(inputer.input());
		System.out.println("请输入工号");
		Teacher.setNumber(inputer.input());
		System.out.println("请输入性别");
		Teacher.setGender(inputer.input());
		System.out.println("请输入生日");
		Teacher.setBirthday(inputer.inputDate());
		System.out.println("请输入电话");
		Teacher.setPhoneNumber(inputer.input());
		System.out.println("请输入地址");
		Teacher.setAddress(inputer.input());
		System.out.println("请输入工资");
		Teacher.setSalary(inputer.inputFloat());
		System.out.println("请输入备注");
		Teacher.setRemark(inputer.input());
		for (TeacherStateBean state : new TeacherStateService().display()) {
			System.out.println(state+"\t");
		}
		System.out.println("请输入状态编号");
		Teacher.setStateId(inputer.inputInteger());
		for (TeacherTypeBean TeacherType : new TeacherTypeService().display()) {
			System.out.println(TeacherType+"\t");
		}
		System.out.println("请输入类型编号");
		Teacher.setTypeId(inputer.inputInteger());
		if (new TeacherService().add(Teacher) > 0) {
			System.out.println("添加成功！");
		} else {
			System.out.println("添加失败！");
		}
	}
	
	public void remove(){
		System.out.println("请输入编号");
		int TeacherId = 0;
		TeacherId = inputer.inputInteger();
		if (new TeacherService().remove(TeacherId) > 0) {
			System.out.println("删除成功！");
		} else {
			System.out.println("删除失败！");
		}
	}
	
	public void updata(){
		System.out.println("请输入姓名");
		Teacher.setName(inputer.input());
		System.out.println("请输入工号");
		Teacher.setNumber(inputer.input());
		System.out.println("请输入性别");
		Teacher.setGender(inputer.input());
		System.out.println("请输入生日");
		Teacher.setBirthday(inputer.inputDate());
		System.out.println("请输入电话");
		Teacher.setPhoneNumber(inputer.input());
		System.out.println("请输入地址");
		Teacher.setAddress(inputer.input());
		System.out.println("请输入工资");
		Teacher.setSalary(inputer.inputFloat());
		System.out.println("请输入备注");
		Teacher.setRemark(inputer.input());
		for (TeacherStateBean state : new TeacherStateService().display()) {
			System.out.println(state+"\t");
		}
		System.out.println("请输入状态编号");
		Teacher.setStateId(inputer.inputInteger());
		for (TeacherTypeBean TeacherType : new TeacherTypeService().display()) {
			System.out.println(TeacherType+"\t");
		}
		System.out.println("请输入类型编号");
		Teacher.setTypeId(inputer.inputInteger());
		for (TeacherBean teacher : new TeacherService().display()) {
			System.out.println(teacher+"\t");
		}
		System.out.println("请输入要修改的教师编号");
		Teacher.setTeacherId(inputer.inputInteger());
		if (new TeacherService().updata(Teacher) > 0) {
			System.out.println("修改成功！");
		} else {
			System.out.println("修改失败！");
		}
	}
	
	public void search(){
		System.out.println("请输入编号");
		int TeacherId = 0;
		TeacherId = inputer.inputInteger();
		Teacher = new TeacherService().search(TeacherId);
		if (Teacher == null) {
			System.out.println("查无该数据");
			return;
		}
		System.out.println("编号\t工号\t姓名\t性别\t生日\t\t\t电话\t地址\t工资\t备注\t状态\t类型");
		System.out.println(Teacher.toString());
	}
	
	public void display(){
		ArrayList<TeacherBean> Teachers = new ArrayList<TeacherBean>();
		Teachers = new TeacherService().display();
		if (Teachers == null) {
			System.out.println("无数据");
			return;
		}
		System.out.println("编号\t工号\t姓名\t性别\t生日\t\t\t电话\t地址\t工资\t备注\t状态\t类型");
		for (int i = 0; i < Teachers.size(); i++) {
			System.out.println(Teachers.get(i).toString());
		}
	}
}
