package net.sycu.meade.view;

import java.util.ArrayList;

import net.sycu.meade.common.Inputer;
import net.sycu.meade.entity.StudyCourseBean;
import net.sycu.meade.entity.CourseBean;
import net.sycu.meade.entity.StudentBean;
import net.sycu.meade.service.StudyCourseService;
import net.sycu.meade.service.CourseService;
import net.sycu.meade.service.StudentService;


public class StudyCourseUI {
	StudyCourseBean StudyCourse = new StudyCourseBean();
	Inputer inputer = new Inputer();
	
	public void add(){
		for (StudentBean student : new StudentService().display()) {
			System.out.println(student+"\t");
		}
		System.out.println("������ѧ�����");
		StudyCourse.setStudentId(inputer.inputInteger());
		for (CourseBean course : new CourseService().display()) {
			System.out.println(course+"\t");
		}
		System.out.println("������γ̱��");
		StudyCourse.setCourseId(inputer.inputInteger());
		System.out.println("�����뿼��ʱ��");
		StudyCourse.setExamDateTime(inputer.inputTimestamp());
		System.out.println("���������");
		StudyCourse.setScore(inputer.inputFloat());
		System.out.println("����������");
		StudyCourse.setDescription(inputer.input());
		if (new StudyCourseService().add(StudyCourse) > 0) {
			System.out.println("��ӳɹ���");
		} else {
			System.out.println("���ʧ�ܣ�");
		}
	}
	
	public void remove(){
		System.out.println("��������");
		int StudyCourseId = 0;
		StudyCourseId = inputer.inputInteger();
		if (new StudyCourseService().remove(StudyCourseId) > 0) {
			System.out.println("ɾ���ɹ���");
		} else {
			System.out.println("ɾ��ʧ�ܣ�");
		}
	}
	
	public void updata(){
		for (StudentBean student : new StudentService().display()) {
			System.out.println(student+"\t");
		}
		System.out.println("������ѧ�����");
		StudyCourse.setStudentId(inputer.inputInteger());
		for (CourseBean course : new CourseService().display()) {
			System.out.println(course+"\t");
		}
		System.out.println("������γ̱��");
		StudyCourse.setCourseId(inputer.inputInteger());
		System.out.println("�����뿼��ʱ��");
		StudyCourse.setExamDateTime(inputer.inputTimestamp());
		System.out.println("���������");
		StudyCourse.setScore(inputer.inputFloat());
		System.out.println("����������");
		StudyCourse.setDescription(inputer.input());
		for (StudyCourseBean StudyCourse : new StudyCourseService().display()) {
			System.out.println(StudyCourse+"\t");
		}
		System.out.println("������Ҫ�޸ĵ�ѡ�α��");
		StudyCourse.setStudyCourseId(inputer.inputInteger());
		if (new StudyCourseService().updata(StudyCourse) > 0) {
			System.out.println("�޸ĳɹ���");
		} else {
			System.out.println("�޸�ʧ�ܣ�");
		}
	}
	
	public void search(){
		System.out.println("��������");
		int StudyCourseId = 0;
		StudyCourseId = inputer.inputInteger();
		StudyCourse = new StudyCourseService().search(StudyCourseId);
		if (StudyCourse == null) {
			System.out.println("���޸�����");
			return;
		}
		System.out.println("���\tѧ��\t�γ�\t����ʱ��\t\t\t����\t����");
		System.out.println(StudyCourse.toString());
	}
	
	public void display(){
		ArrayList<StudyCourseBean> StudyCourses = new ArrayList<StudyCourseBean>();
		StudyCourses = new StudyCourseService().display();
		if (StudyCourses == null) {
			System.out.println("������");
			return;
		}
		System.out.println("���\tѧ��\t�γ�\t����ʱ��\t\t\t����\t����");
		for (int i = 0; i < StudyCourses.size(); i++) {
			System.out.println(StudyCourses.get(i).toString());
		}
	}
}
