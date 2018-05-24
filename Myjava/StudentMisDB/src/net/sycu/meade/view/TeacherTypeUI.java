package net.sycu.meade.view;

import java.util.ArrayList;

import net.sycu.meade.common.Inputer;
import net.sycu.meade.entity.TeacherTypeBean;
import net.sycu.meade.service.TeacherTypeService;

public class TeacherTypeUI {
	
	TeacherTypeBean TeacherType = new TeacherTypeBean();
	Inputer inputer = new Inputer();
	
	public void add(){
		System.out.println("����������");
		TeacherType.setName(inputer.input());
		System.out.println("�������������");
		TeacherType.setDescription(inputer.input());
		if (new TeacherTypeService().add(TeacherType) > 0) {
			System.out.println("��ӳɹ���");
		} else {
			System.out.println("���ʧ�ܣ�");
		}
	}
	
	public void remove(){
		System.out.println("��������");
		int TeacherTypeId = 0;
		TeacherTypeId = inputer.inputInteger();
		if (new TeacherTypeService().remove(TeacherTypeId) > 0) {
			System.out.println("ɾ���ɹ���");
		} else {
			System.out.println("ɾ��ʧ�ܣ�");
		}
	}
	
	public void updata(){
		System.out.println("������Ҫ�޸ĵ��˵ı��");
		TeacherType.setTeacherTypeId(inputer.inputInteger());
		System.out.println("����������");
		TeacherType.setName(inputer.input());
		System.out.println("�������������");
		TeacherType.setDescription(inputer.input());
		for (TeacherTypeBean TeacherType : new TeacherTypeService().display()) {
			System.out.println(TeacherType+"\t");
		}
		System.out.println("������Ҫ�޸ĵĽ�ʦ���ͱ��");
		TeacherType.setTeacherTypeId(inputer.inputInteger());
		if (new TeacherTypeService().updata(TeacherType) > 0) {
			System.out.println("�޸ĳɹ���");
		} else {
			System.out.println("�޸�ʧ�ܣ�");
		}
	}
	
	public void search(){
		System.out.println("��������");
		int TeacherTypeId = 0;
		TeacherTypeId = inputer.inputInteger();
		TeacherType = new TeacherTypeService().search(TeacherTypeId);
		if (TeacherType == null) {
			System.out.println("���޸�����");
			return;
		}
		System.out.println("��ʦ���ͱ��\t����\t����");
		System.out.println(TeacherType.toString());
	}
	
	public void display(){
		ArrayList<TeacherTypeBean> TeacherTypes = new ArrayList<TeacherTypeBean>();
		TeacherTypes = new TeacherTypeService().display();
		if (TeacherTypes == null) {
			System.out.println("������");
			return;
		}
		System.out.println("��ʦ���ͱ��\t����\t����");
		for (int i = 0; i < TeacherTypes.size(); i++) {
			System.out.println(TeacherTypes.get(i).toString());
		}
	}
}
