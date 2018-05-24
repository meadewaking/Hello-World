package net.sycu.meade.view;

import java.util.ArrayList;

import net.sycu.meade.common.Inputer;
import net.sycu.meade.entity.ClazzBean;
import net.sycu.meade.entity.StudentBean;
import net.sycu.meade.entity.StudentStateBean;
import net.sycu.meade.service.ClazzService;
import net.sycu.meade.service.StudentService;
import net.sycu.meade.service.StudentStateService;

public class StudentUI {
	StudentBean Student = new StudentBean();
	Inputer inputer = new Inputer();
	
	public void add(){
		System.out.println("����������");
		Student.setName(inputer.input());
		System.out.println("������ѧ��");
		Student.setNumber(inputer.input());
		System.out.println("�������Ա�");
		Student.setGender(inputer.input());
		System.out.println("����������");
		Student.setBirthday(inputer.inputDate());
		System.out.println("������绰");
		Student.setPhoneNumber(inputer.input());
		System.out.println("�������ַ");
		Student.setAddress(inputer.input());
		System.out.println("�����뱸ע");
		Student.setRemark(inputer.input());
		for (StudentStateBean state : new StudentStateService().display()) {
			System.out.println(state+"\t");
		}
		System.out.println();
		System.out.println("������״̬���");
		Student.setStateId(inputer.inputInteger());
		for (ClazzBean clazz : new ClazzService().display()) {
			System.out.println(clazz+"\t");
		}
		System.out.println();
		System.out.println("������༶���");
		Student.setClazzId(inputer.inputInteger());
		if (new StudentService().add(Student) > 0) {
			System.out.println("��ӳɹ���");
		} else {
			System.out.println("���ʧ�ܣ�");
		}
	}
	
	public void remove(){
		System.out.println("��������");
		int StudentId = 0;
		StudentId = inputer.inputInteger();
		if (new StudentService().remove(StudentId) > 0) {
			System.out.println("ɾ���ɹ���");
		} else {
			System.out.println("ɾ��ʧ�ܣ�");
		}
	}
	
	public void updata(){
		System.out.println("����������");
		Student.setName(inputer.input());
		System.out.println("������ѧ��");
		Student.setNumber(inputer.input());
		System.out.println("�������Ա�");
		Student.setGender(inputer.input());
		System.out.println("����������");
		Student.setBirthday(inputer.inputDate());
		System.out.println("������绰");
		Student.setPhoneNumber(inputer.input());
		System.out.println("�������ַ");
		Student.setAddress(inputer.input());
		System.out.println("�����뱸ע");
		Student.setRemark(inputer.input());
		for (StudentStateBean state : new StudentStateService().display()) {
			System.out.println(state+"\t");
		}
		System.out.println("������״̬���");
		Student.setStateId(inputer.inputInteger());
		for (ClazzBean clazz : new ClazzService().display()) {
			System.out.println(clazz+"\t");
		}
		System.out.println("������༶���");
		Student.setClazzId(inputer.inputInteger());
		for (StudentBean student : new StudentService().display()) {
			System.out.println(student+"\t");
		}
		System.out.println("������Ҫ�޸ĵ�ѧ�����");
		Student.setStudentId(inputer.inputInteger());
		if (new StudentService().updata(Student) > 0) {
			System.out.println("�޸ĳɹ���");
		} else {
			System.out.println("�޸�ʧ�ܣ�");
		}
	}
	
	public void search(){
		System.out.println("��������");
		int StudentId = 0;
		StudentId = inputer.inputInteger();
		Student = new StudentService().search(StudentId);
		if (Student == null) {
			System.out.println("���޸�����");
			return;
		}
		System.out.println("���\t����\tѧ��\t�Ա�\t����\t\t\t�绰\t��ַ\t��ע\t״̬\t�༶");
		System.out.println(Student.toString());
	}
	
	public void display(){
		ArrayList<StudentBean> Students = new ArrayList<StudentBean>();
		Students = new StudentService().display();
		if (Students == null) {
			System.out.println("������");
			return;
		}
		System.out.println("���\t����\tѧ��\t�Ա�\t����\t\t\t�绰\t��ַ\t��ע\t״̬\t�༶");
		for (int i = 0; i < Students.size(); i++) {
			System.out.println(Students.get(i).toString());
		}
	}
}
