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
		System.out.println("����������");
		Teacher.setName(inputer.input());
		System.out.println("�����빤��");
		Teacher.setNumber(inputer.input());
		System.out.println("�������Ա�");
		Teacher.setGender(inputer.input());
		System.out.println("����������");
		Teacher.setBirthday(inputer.inputDate());
		System.out.println("������绰");
		Teacher.setPhoneNumber(inputer.input());
		System.out.println("�������ַ");
		Teacher.setAddress(inputer.input());
		System.out.println("�����빤��");
		Teacher.setSalary(inputer.inputFloat());
		System.out.println("�����뱸ע");
		Teacher.setRemark(inputer.input());
		for (TeacherStateBean state : new TeacherStateService().display()) {
			System.out.println(state+"\t");
		}
		System.out.println("������״̬���");
		Teacher.setStateId(inputer.inputInteger());
		for (TeacherTypeBean TeacherType : new TeacherTypeService().display()) {
			System.out.println(TeacherType+"\t");
		}
		System.out.println("���������ͱ��");
		Teacher.setTypeId(inputer.inputInteger());
		if (new TeacherService().add(Teacher) > 0) {
			System.out.println("��ӳɹ���");
		} else {
			System.out.println("���ʧ�ܣ�");
		}
	}
	
	public void remove(){
		System.out.println("��������");
		int TeacherId = 0;
		TeacherId = inputer.inputInteger();
		if (new TeacherService().remove(TeacherId) > 0) {
			System.out.println("ɾ���ɹ���");
		} else {
			System.out.println("ɾ��ʧ�ܣ�");
		}
	}
	
	public void updata(){
		System.out.println("����������");
		Teacher.setName(inputer.input());
		System.out.println("�����빤��");
		Teacher.setNumber(inputer.input());
		System.out.println("�������Ա�");
		Teacher.setGender(inputer.input());
		System.out.println("����������");
		Teacher.setBirthday(inputer.inputDate());
		System.out.println("������绰");
		Teacher.setPhoneNumber(inputer.input());
		System.out.println("�������ַ");
		Teacher.setAddress(inputer.input());
		System.out.println("�����빤��");
		Teacher.setSalary(inputer.inputFloat());
		System.out.println("�����뱸ע");
		Teacher.setRemark(inputer.input());
		for (TeacherStateBean state : new TeacherStateService().display()) {
			System.out.println(state+"\t");
		}
		System.out.println("������״̬���");
		Teacher.setStateId(inputer.inputInteger());
		for (TeacherTypeBean TeacherType : new TeacherTypeService().display()) {
			System.out.println(TeacherType+"\t");
		}
		System.out.println("���������ͱ��");
		Teacher.setTypeId(inputer.inputInteger());
		for (TeacherBean teacher : new TeacherService().display()) {
			System.out.println(teacher+"\t");
		}
		System.out.println("������Ҫ�޸ĵĽ�ʦ���");
		Teacher.setTeacherId(inputer.inputInteger());
		if (new TeacherService().updata(Teacher) > 0) {
			System.out.println("�޸ĳɹ���");
		} else {
			System.out.println("�޸�ʧ�ܣ�");
		}
	}
	
	public void search(){
		System.out.println("��������");
		int TeacherId = 0;
		TeacherId = inputer.inputInteger();
		Teacher = new TeacherService().search(TeacherId);
		if (Teacher == null) {
			System.out.println("���޸�����");
			return;
		}
		System.out.println("���\t����\t����\t�Ա�\t����\t\t\t�绰\t��ַ\t����\t��ע\t״̬\t����");
		System.out.println(Teacher.toString());
	}
	
	public void display(){
		ArrayList<TeacherBean> Teachers = new ArrayList<TeacherBean>();
		Teachers = new TeacherService().display();
		if (Teachers == null) {
			System.out.println("������");
			return;
		}
		System.out.println("���\t����\t����\t�Ա�\t����\t\t\t�绰\t��ַ\t����\t��ע\t״̬\t����");
		for (int i = 0; i < Teachers.size(); i++) {
			System.out.println(Teachers.get(i).toString());
		}
	}
}
