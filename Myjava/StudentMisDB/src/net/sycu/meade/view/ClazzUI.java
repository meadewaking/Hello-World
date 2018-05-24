package net.sycu.meade.view;

import java.util.ArrayList;

import net.sycu.meade.common.Inputer;
import net.sycu.meade.entity.ClazzBean;
import net.sycu.meade.entity.TeacherBean;
import net.sycu.meade.service.ClazzService;
import net.sycu.meade.service.TeacherService;

public class ClazzUI {
	ClazzBean Clazz = new ClazzBean();
	Inputer inputer = new Inputer();
	
	public void add(){
		System.out.println("请输入班级名称");
		Clazz.setName(inputer.input());
		System.out.println("请输入开课时间");
		Clazz.setBeginDateTime(inputer.inputDate());
		System.out.println("请输入结课时间");
		Clazz.setEndDateTime(inputer.inputDate());
		System.out.println("请输入描述");
		Clazz.setDescription(inputer.input());	//输入所有字段的值
		for (TeacherBean teacher : new TeacherService().display()) {
			System.out.println(teacher+"\t");
		}		//将外键引用全部输出
		System.out.println("请输入教师编号");
		Clazz.setTeacherId(inputer.inputInteger());	//用户根据输出输入外键
		if (new ClazzService().add(Clazz) > 0) {
			System.out.println("添加成功！");
		} else {
			System.out.println("添加失败！");
		}
	}
	
	public void remove(){
		System.out.println("请输入编号");
		int ClazzId = 0;
		ClazzId = inputer.inputInteger();
		if (new ClazzService().remove(ClazzId) > 0) {
			System.out.println("删除成功！");
		} else {
			System.out.println("删除失败！");
		}
	}
	
	public void updata(){
		System.out.println("请输入班级名称");
		Clazz.setName(inputer.input());
		System.out.println("请输入开课时间");
		Clazz.setBeginDateTime(inputer.inputDate());
		System.out.println("请输入结课时间");
		Clazz.setEndDateTime(inputer.inputDate());
		System.out.println("请输入描述");
		Clazz.setDescription(inputer.input());		//输入修改信息
		for (TeacherBean teacher : new TeacherService().display()) {
			System.out.println(teacher+"\t");
		}
		System.out.println();		//外键部分的输入同上
		System.out.println("请输入教师编号");
		Clazz.setTeacherId(inputer.inputInteger());
		for (ClazzBean Clazz : new ClazzService().display()) {
			System.out.println(Clazz+"\t");
		}
		System.out.println("请输入要修改的班级编号");
		Clazz.setClazzId(inputer.inputInteger());
		if (new ClazzService().updata(Clazz) > 0) {
			System.out.println("修改成功！");
		} else {
			System.out.println("修改失败！");
		}
	}
	
	public void search(){
		System.out.println("请输入编号");
		int ClazzId = 0;
		ClazzId = inputer.inputInteger();
		Clazz = new ClazzService().search(ClazzId);
		if (Clazz == null) {		//验证表中数据是否为空
			System.out.println("查无该数据");
			return;
		}
		System.out.println("编号\t名称\t开课时间\t\t\t结课时间\t\t\t描述\t班主任");
		System.out.println(Clazz.toString());		//输出信息
	}
	
	public void display(){
		ArrayList<ClazzBean> Clazzs = new ArrayList<ClazzBean>();
		Clazzs = new ClazzService().display();
		if (Clazzs == null) {		//验证表中数据是否为空
			System.out.println("无数据");
			return;
		}
		System.out.println("编号\t名称\t开课时间\t\t\t结课时间\t\t\t描述\t班主任");
		for (int i = 0; i < Clazzs.size(); i++) {
			System.out.println(Clazzs.get(i).toString());
		}
	}
}
