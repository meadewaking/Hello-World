package net.sycu.meade.view;

import java.util.ArrayList;

import net.sycu.meade.common.Inputer;
import net.sycu.meade.entity.CourseTypeBean;
import net.sycu.meade.service.CourseTypeService;

public class CourseTypeUI {
	
	CourseTypeBean CourseType = new CourseTypeBean();
	Inputer inputer = new Inputer();
	
	public void add(){
		System.out.println("����������");
		CourseType.setName(inputer.input());
		System.out.println("�������������");
		CourseType.setDescription(inputer.input());
		if (new CourseTypeService().add(CourseType) > 0) {
			System.out.println("��ӳɹ���");
		} else {
			System.out.println("���ʧ�ܣ�");
		}
	}
	
	public void remove(){
		System.out.println("��������");
		int CourseTypeId = 0;
		CourseTypeId = inputer.inputInteger();
		if (new CourseTypeService().remove(CourseTypeId) > 0) {
			System.out.println("ɾ���ɹ���");
		} else {
			System.out.println("ɾ��ʧ�ܣ�");
		}
	}
	
	public void updata(){
		System.out.println("������Ҫ�޸ĵ��˵ı��");
		CourseType.setCourseTypeId(inputer.inputInteger());
		System.out.println("����������");
		CourseType.setName(inputer.input());
		System.out.println("�������������");
		CourseType.setDescription(inputer.input());
		
		for (CourseTypeBean CourseType : new CourseTypeService().display()) {
			System.out.println(CourseType+"\t");
		}
		System.out.println("������Ҫ�޸ĵĿγ����ͱ��");
		CourseType.setCourseTypeId(inputer.inputInteger());
		if (new CourseTypeService().updata(CourseType) > 0) {
			System.out.println("�޸ĳɹ���");
		} else {
			System.out.println("�޸�ʧ�ܣ�");
		}
	}
	
	public void search(){
		System.out.println("��������");
		int CourseTypeId = 0;
		CourseTypeId = inputer.inputInteger();
		CourseType = new CourseTypeService().search(CourseTypeId);
		if (CourseType == null) {
			System.out.println("���޸�����");
			return;
		}
		System.out.println("�γ����ͱ��\t����\t����");
		System.out.println(CourseType.toString());
	}
	
	public void display(){
		ArrayList<CourseTypeBean> CourseTypes = new ArrayList<CourseTypeBean>();
		CourseTypes = new CourseTypeService().display();
		if (CourseTypes == null) {
			System.out.println("������");
			return;
		}
		System.out.println("�γ����ͱ��\t����\t����");
		for (int i = 0; i < CourseTypes.size(); i++) {
			System.out.println(CourseTypes.get(i).toString());		}
	}
}
