package net.sycu.meade.view;

import java.util.ArrayList;

import net.sycu.meade.common.Inputer;
import net.sycu.meade.entity.CourseBean;
import net.sycu.meade.entity.StudentBean;
import net.sycu.meade.entity.TeachCourseBean;
import net.sycu.meade.entity.TeacherBean;
import net.sycu.meade.service.CourseService;
import net.sycu.meade.service.StudentService;
import net.sycu.meade.service.TeachCourseService;
import net.sycu.meade.service.TeacherService;

public class TeachCourseUI {
	TeachCourseBean TeachCourse = new TeachCourseBean();
	Inputer inputer = new Inputer();
	
	public void add(){
		for (TeacherBean Teacher : new TeacherService().display()) {
			System.out.println(Teacher+"\t");
		}
		System.out.println("�������ʦ���");
		TeachCourse.setTeacherId(inputer.inputInteger());
		for (CourseBean course : new CourseService().display()) {
			System.out.println(course+"\t");
		}
		System.out.println("������γ̱��");
		TeachCourse.setCourseId(inputer.inputInteger());
		System.out.println("�����뿪��ʱ��");
		TeachCourse.setBeginDateTime(inputer.inputTimestamp());
		System.out.println("��������ʱ��");
		TeachCourse.setEndDateTime(inputer.inputTimestamp());
		System.out.println("����������");
		TeachCourse.setDescription(inputer.input());
		if (new TeachCourseService().add(TeachCourse) > 0) {
			System.out.println("��ӳɹ���");
		} else {
			System.out.println("���ʧ�ܣ�");
		}
	}
	
	public void remove(){
		System.out.println("��������");
		int TeachCourseId = 0;
		TeachCourseId = inputer.inputInteger();
		if (new TeachCourseService().remove(TeachCourseId) > 0) {
			System.out.println("ɾ���ɹ���");
		} else {
			System.out.println("ɾ��ʧ�ܣ�");
		}
	}
	
	public void updata(){
		for (TeacherBean Teacher : new TeacherService().display()) {
			System.out.println(Teacher+"\t");
		}
		System.out.println("�������ʦ���");
		TeachCourse.setTeacherId(inputer.inputInteger());
		for (CourseBean course : new CourseService().display()) {
			System.out.println(course+"\t");
		}
		System.out.println("������γ̱��");
		TeachCourse.setCourseId(inputer.inputInteger());
		System.out.println("�����뿪��ʱ��");
		TeachCourse.setBeginDateTime(inputer.inputTimestamp());
		System.out.println("��������ʱ��");
		TeachCourse.setEndDateTime(inputer.inputTimestamp());
		System.out.println("����������");
		TeachCourse.setDescription(inputer.input());
		for (TeachCourseBean TeachCourse : new TeachCourseService().display()) {
			System.out.println(TeachCourse+"\t");
		}
		System.out.println("������Ҫ�޸ĵ��ڿα��");
		TeachCourse.setTeachCourseId(inputer.inputInteger());
		if (new TeachCourseService().updata(TeachCourse) > 0) {
			System.out.println("�޸ĳɹ���");
		} else {
			System.out.println("�޸�ʧ�ܣ�");
		}
	}
	
	public void search(){
		System.out.println("��������");
		int TeachCourseId = 0;
		TeachCourseId = inputer.inputInteger();
		TeachCourse = new TeachCourseService().search(TeachCourseId);
		if (TeachCourse == null) {
			System.out.println("���޸�����");
			return;
		}
		System.out.println("���\t��ʦ\t�γ�\t����ʱ��\t\t\t���ʱ��\t\t\t����");
		System.out.println(TeachCourse.toString());
	}
	
	public void display(){
		ArrayList<TeachCourseBean> TeachCourses = new ArrayList<TeachCourseBean>();
		TeachCourses = new TeachCourseService().display();
		if (TeachCourses == null) {
			System.out.println("������");
			return;
		}
		System.out.println("���\t��ʦ\t�γ�\t����ʱ��\t\t\t���ʱ��\t\t\t����");
		for (int i = 0; i < TeachCourses.size(); i++) {
			System.out.println(TeachCourses.get(i).toString());
		}
	}
}
