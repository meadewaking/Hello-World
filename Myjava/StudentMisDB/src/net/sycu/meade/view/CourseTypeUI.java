package net.sycu.meade.view;

import java.util.ArrayList;

import net.sycu.meade.common.Inputer;
import net.sycu.meade.entity.CourseTypeBean;
import net.sycu.meade.service.CourseTypeService;

public class CourseTypeUI {
	
	CourseTypeBean CourseType = new CourseTypeBean();
	Inputer inputer = new Inputer();
	
	public void add(){
		System.out.println("请输入名称");
		CourseType.setName(inputer.input());
		System.out.println("请输入相关描述");
		CourseType.setDescription(inputer.input());
		if (new CourseTypeService().add(CourseType) > 0) {
			System.out.println("添加成功！");
		} else {
			System.out.println("添加失败！");
		}
	}
	
	public void remove(){
		System.out.println("请输入编号");
		int CourseTypeId = 0;
		CourseTypeId = inputer.inputInteger();
		if (new CourseTypeService().remove(CourseTypeId) > 0) {
			System.out.println("删除成功！");
		} else {
			System.out.println("删除失败！");
		}
	}
	
	public void updata(){
		System.out.println("请输入要修改的人的编号");
		CourseType.setCourseTypeId(inputer.inputInteger());
		System.out.println("请输入名称");
		CourseType.setName(inputer.input());
		System.out.println("请输入相关描述");
		CourseType.setDescription(inputer.input());
		
		for (CourseTypeBean CourseType : new CourseTypeService().display()) {
			System.out.println(CourseType+"\t");
		}
		System.out.println("请输入要修改的课程类型编号");
		CourseType.setCourseTypeId(inputer.inputInteger());
		if (new CourseTypeService().updata(CourseType) > 0) {
			System.out.println("修改成功！");
		} else {
			System.out.println("修改失败！");
		}
	}
	
	public void search(){
		System.out.println("请输入编号");
		int CourseTypeId = 0;
		CourseTypeId = inputer.inputInteger();
		CourseType = new CourseTypeService().search(CourseTypeId);
		if (CourseType == null) {
			System.out.println("查无该数据");
			return;
		}
		System.out.println("课程类型编号\t名称\t描述");
		System.out.println(CourseType.toString());
	}
	
	public void display(){
		ArrayList<CourseTypeBean> CourseTypes = new ArrayList<CourseTypeBean>();
		CourseTypes = new CourseTypeService().display();
		if (CourseTypes == null) {
			System.out.println("无数据");
			return;
		}
		System.out.println("课程类型编号\t名称\t描述");
		for (int i = 0; i < CourseTypes.size(); i++) {
			System.out.println(CourseTypes.get(i).toString());		}
	}
}
