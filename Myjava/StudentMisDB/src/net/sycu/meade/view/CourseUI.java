package net.sycu.meade.view;

import java.util.ArrayList;

import net.sycu.meade.common.Inputer;
import net.sycu.meade.entity.CourseBean;
import net.sycu.meade.entity.CourseTypeBean;
import net.sycu.meade.service.CourseService;
import net.sycu.meade.service.CourseTypeService;

public class CourseUI {
	CourseBean Course = new CourseBean();
	Inputer inputer = new Inputer();
	
	public void add(){
		System.out.println("������γ�����");
		Course.setName(inputer.input());
		System.out.println("������γ̴���");
		Course.setNumber(inputer.input());
		System.out.println("������ѧ��");
		Course.setTerm(inputer.inputInteger());
		System.out.println("������ѧ��");
		Course.setCredit(inputer.inputFloat());
		System.out.println("�������ʱ");
		Course.setHours(inputer.inputFloat());
		System.out.println("����������");
		Course.setDescription(inputer.input());
		for (CourseTypeBean type : new CourseTypeService().display()) {
			System.out.println(type+"\t");
		}
		System.out.println("������γ����ͱ��");
		Course.setCourseTypeId(inputer.inputInteger());
		if (new CourseService().add(Course) > 0) {
			System.out.println("��ӳɹ���");
		} else {
			System.out.println("���ʧ�ܣ�");
		}
	}
	
	public void remove(){
		System.out.println("��������");
		int CourseId = 0;
		CourseId = inputer.inputInteger();
		if (new CourseService().remove(CourseId) > 0) {
			System.out.println("ɾ���ɹ���");
		} else {
			System.out.println("ɾ��ʧ�ܣ�");
		}
	}
	
	public void updata(){
		System.out.println("������γ�����");
		Course.setName(inputer.input());
		System.out.println("������γ̴���");
		Course.setNumber(inputer.input());
		System.out.println("������ѧ��");
		Course.setTerm(inputer.inputInteger());
		System.out.println("������ѧ��");
		Course.setCredit(inputer.inputFloat());
		System.out.println("�������ʱ");
		Course.setHours(inputer.inputFloat());
		System.out.println("����������");
		Course.setDescription(inputer.input());
		for (CourseTypeBean type : new CourseTypeService().display()) {
			System.out.println(type+"\t");
		}
		System.out.println("������γ����ͱ��");
		Course.setCourseTypeId(inputer.inputInteger());
		for (CourseBean Course : new CourseService().display()) {
			System.out.println(Course+"\t");
		}
		System.out.println("������Ҫ�޸ĵĿγ̱��");
		Course.setCourseId(inputer.inputInteger());
		if (new CourseService().updata(Course) > 0) {
			System.out.println("�޸ĳɹ���");
		} else {
			System.out.println("�޸�ʧ�ܣ�");
		}
	}
	
	public void search(){
		System.out.println("��������");
		int CourseId = 0;
		CourseId = inputer.inputInteger();
		Course = new CourseService().search(CourseId);
		if (Course == null) {
			System.out.println("���޸�����");
			return;
		}
		System.out.println("���\t�γ�����\t�γ̴���\tѧ��\tѧ��\t��ʱ\t����\t�γ�����");
		System.out.println(Course.toString());
	}
	
	public void display(){
		ArrayList<CourseBean> Courses = new ArrayList<CourseBean>();
		Courses = new CourseService().display();
		if (Courses == null) {
			System.out.println("������");
			return;
		}
		System.out.println("���\t�γ�����\t�γ̴���\tѧ��\tѧ��\t��ʱ\t����\t�γ�����");
		for (int i = 0; i < Courses.size(); i++) {
			System.out.println(Courses.get(i).toString());
		}
	}
}
