package net.sycu.meade.view;

import java.util.ArrayList;

import net.sycu.meade.common.Inputer;
import net.sycu.meade.entity.TeacherTypeBean;
import net.sycu.meade.service.TeacherTypeService;

public class TeacherTypeUI {
	
	TeacherTypeBean TeacherType = new TeacherTypeBean();
	Inputer inputer = new Inputer();
	
	public void add(){
		System.out.println("请输入姓名");
		TeacherType.setName(inputer.input());
		System.out.println("请输入相关描述");
		TeacherType.setDescription(inputer.input());
		if (new TeacherTypeService().add(TeacherType) > 0) {
			System.out.println("添加成功！");
		} else {
			System.out.println("添加失败！");
		}
	}
	
	public void remove(){
		System.out.println("请输入编号");
		int TeacherTypeId = 0;
		TeacherTypeId = inputer.inputInteger();
		if (new TeacherTypeService().remove(TeacherTypeId) > 0) {
			System.out.println("删除成功！");
		} else {
			System.out.println("删除失败！");
		}
	}
	
	public void updata(){
		System.out.println("请输入要修改的人的编号");
		TeacherType.setTeacherTypeId(inputer.inputInteger());
		System.out.println("请输入姓名");
		TeacherType.setName(inputer.input());
		System.out.println("请输入相关描述");
		TeacherType.setDescription(inputer.input());
		for (TeacherTypeBean TeacherType : new TeacherTypeService().display()) {
			System.out.println(TeacherType+"\t");
		}
		System.out.println("请输入要修改的教师类型编号");
		TeacherType.setTeacherTypeId(inputer.inputInteger());
		if (new TeacherTypeService().updata(TeacherType) > 0) {
			System.out.println("修改成功！");
		} else {
			System.out.println("修改失败！");
		}
	}
	
	public void search(){
		System.out.println("请输入编号");
		int TeacherTypeId = 0;
		TeacherTypeId = inputer.inputInteger();
		TeacherType = new TeacherTypeService().search(TeacherTypeId);
		if (TeacherType == null) {
			System.out.println("查无该数据");
			return;
		}
		System.out.println("教师类型编号\t姓名\t描述");
		System.out.println(TeacherType.toString());
	}
	
	public void display(){
		ArrayList<TeacherTypeBean> TeacherTypes = new ArrayList<TeacherTypeBean>();
		TeacherTypes = new TeacherTypeService().display();
		if (TeacherTypes == null) {
			System.out.println("无数据");
			return;
		}
		System.out.println("教师类型编号\t姓名\t描述");
		for (int i = 0; i < TeacherTypes.size(); i++) {
			System.out.println(TeacherTypes.get(i).toString());
		}
	}
}
