package net.sycu.meade.view;

import java.util.ArrayList;

import net.sycu.meade.common.Inputer;
import net.sycu.meade.entity.TeacherStateBean;
import net.sycu.meade.service.TeacherStateService;

public class TeacherStateUI {
	
	TeacherStateBean Teacherstate = new TeacherStateBean();
	Inputer inputer = new Inputer();
	
	public void add(){
		System.out.println("请输入姓名");
		Teacherstate.setName(inputer.input());
		System.out.println("请输入在校状态");
		Teacherstate.setInschool(inputer.inputBoolean());
		System.out.println("请输入相关描述");
		Teacherstate.setDescription(inputer.input());
		if (new TeacherStateService().add(Teacherstate) > 0) {
			System.out.println("添加成功！");
		} else {
			System.out.println("添加失败！");
		}
	}
	
	public void remove(){
		System.out.println("请输入编号");
		int TeacherStateId = 0;
		TeacherStateId = inputer.inputInteger();
		if (new TeacherStateService().remove(TeacherStateId) > 0) {
			System.out.println("删除成功！");
		} else {
			System.out.println("删除失败！");
		}
	}
	
	public void updata(){
		System.out.println("请输入要修改的人的编号");
		Teacherstate.setTeacherStateId(inputer.inputInteger());
		System.out.println("请输入姓名");
		Teacherstate.setName(inputer.input());
		System.out.println("请输入在校状态");
		Teacherstate.setInschool(inputer.inputBoolean());
		System.out.println("请输入相关描述");
		Teacherstate.setDescription(inputer.input());
		for (TeacherStateBean TeacherState : new TeacherStateService().display()) {
			System.out.println(TeacherState+"\t");
		}
		System.out.println("请输入要修改的教师状态编号");
		Teacherstate.setTeacherStateId(inputer.inputInteger());
		if (new TeacherStateService().updata(Teacherstate) > 0) {
			System.out.println("修改成功！");
		} else {
			System.out.println("修改失败！");
		}
	}
	
	public void search(){
		System.out.println("请输入编号");
		int TeacherStateId = 0;
		TeacherStateId = inputer.inputInteger();
		Teacherstate = new TeacherStateService().search(TeacherStateId);
		if (Teacherstate == null) {
			System.out.println("查无该数据");
			return;
		}
		System.out.println("教师状态编号\t姓名\t是否在校\t描述");
		System.out.println(Teacherstate.toString());
	}
	
	public void display(){
		ArrayList<TeacherStateBean> Teacherstates = new ArrayList<TeacherStateBean>();
		Teacherstates = new TeacherStateService().display();
		if (Teacherstates == null) {
			System.out.println("无数据");
			return;
		}
		System.out.println("教师状态编号\t姓名\t是否在校\t描述");
		for (int i = 0; i < Teacherstates.size(); i++) {
			System.out.println(Teacherstates.get(i).toString());
		}
	}
}
