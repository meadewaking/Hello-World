package net.sycu.meade.view;

import java.util.ArrayList;

import net.sycu.meade.common.Inputer;
import net.sycu.meade.entity.StudentStateBean;
import net.sycu.meade.service.StudentStateService;

public class StudentStateUI {
	
	StudentStateBean studentstate = new StudentStateBean();
	Inputer inputer = new Inputer();
	
	public void add() {
		System.out.println("请输入姓名");
		studentstate.setName(inputer.input());
		System.out.println("请输入在校状态");
		studentstate.setInschool(inputer.inputBoolean());
		System.out.println("请输入相关描述");
		studentstate.setDescription(inputer.input());
		if (new StudentStateService().add(studentstate) > 0) {
			System.out.println("添加成功！");
		} else {
			System.out.println("添加失败！");
		}
	}
	
	public void remove() {
		System.out.println("请输入编号");
		int StudentStateId = 0;
		StudentStateId = inputer.inputInteger();
		if (new StudentStateService().remove(StudentStateId) > 0) {
			System.out.println("删除成功！");
		} else {
			System.out.println("删除失败！");
		}
	}
	
	public void updata() {
		System.out.println("请输入要修改的人的编号");
		studentstate.setStudentStateId(inputer.inputInteger());
		System.out.println("请输入姓名");
		studentstate.setName(inputer.input());
		System.out.println("请输入在校状态");
		studentstate.setInschool(inputer.inputBoolean());
		System.out.println("请输入相关描述");
		studentstate.setDescription(inputer.input());
		for (StudentStateBean state : new StudentStateService().display()) {
			System.out.println(state+"\t");
		}
		System.out.println("请输入要修改的学生状态编号");
		studentstate.setStudentStateId(inputer.inputInteger());
		if (new StudentStateService().updata(studentstate) > 0) {
			System.out.println("修改成功！");
		} else {
			System.out.println("修改失败！");
		}
	}
	
	public void search() {
		System.out.println("请输入编号");
		int StudentStateId = 0;
		StudentStateId = inputer.inputInteger();
		studentstate = new StudentStateService().search(StudentStateId);
		if (studentstate == null) {
			System.out.println("查无该数据");
			return ;
		}
		System.out.println("学生状态编号\t姓名\t是否在校\t描述");
		System.out.println(studentstate.toString());
	}
	
	public void display() {
		ArrayList<StudentStateBean> studentstates = new ArrayList<StudentStateBean>();
		studentstates = new StudentStateService().display();
		if (studentstates == null) {
			System.out.println("无数据");
			return;
		}
		System.out.println("学生状态编号\t姓名\t是否在校\t描述");
		for (int i = 0; i < studentstates.size(); i++) {
			System.out.println(studentstates.get(i).toString());
		}
	}
}
