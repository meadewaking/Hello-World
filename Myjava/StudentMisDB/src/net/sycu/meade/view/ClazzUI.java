package net.sycu.meade.view;

import java.util.ArrayList;

import net.sycu.meade.common.Inputer;
import net.sycu.meade.entity.ClazzBean;
import net.sycu.meade.entity.TeacherBean;
import net.sycu.meade.service.ClazzService;
import net.sycu.meade.service.TeacherService;

public class ClazzUI {
	ClazzBean Clazz = new ClazzBean();
	Inputer inputer = new Inputer();
	
	public void add(){
		System.out.println("������༶����");
		Clazz.setName(inputer.input());
		System.out.println("�����뿪��ʱ��");
		Clazz.setBeginDateTime(inputer.inputDate());
		System.out.println("��������ʱ��");
		Clazz.setEndDateTime(inputer.inputDate());
		System.out.println("����������");
		Clazz.setDescription(inputer.input());	//���������ֶε�ֵ
		for (TeacherBean teacher : new TeacherService().display()) {
			System.out.println(teacher+"\t");
		}		//���������ȫ�����
		System.out.println("�������ʦ���");
		Clazz.setTeacherId(inputer.inputInteger());	//�û���������������
		if (new ClazzService().add(Clazz) > 0) {
			System.out.println("��ӳɹ���");
		} else {
			System.out.println("���ʧ�ܣ�");
		}
	}
	
	public void remove(){
		System.out.println("��������");
		int ClazzId = 0;
		ClazzId = inputer.inputInteger();
		if (new ClazzService().remove(ClazzId) > 0) {
			System.out.println("ɾ���ɹ���");
		} else {
			System.out.println("ɾ��ʧ�ܣ�");
		}
	}
	
	public void updata(){
		System.out.println("������༶����");
		Clazz.setName(inputer.input());
		System.out.println("�����뿪��ʱ��");
		Clazz.setBeginDateTime(inputer.inputDate());
		System.out.println("��������ʱ��");
		Clazz.setEndDateTime(inputer.inputDate());
		System.out.println("����������");
		Clazz.setDescription(inputer.input());		//�����޸���Ϣ
		for (TeacherBean teacher : new TeacherService().display()) {
			System.out.println(teacher+"\t");
		}
		System.out.println();		//������ֵ�����ͬ��
		System.out.println("�������ʦ���");
		Clazz.setTeacherId(inputer.inputInteger());
		for (ClazzBean Clazz : new ClazzService().display()) {
			System.out.println(Clazz+"\t");
		}
		System.out.println("������Ҫ�޸ĵİ༶���");
		Clazz.setClazzId(inputer.inputInteger());
		if (new ClazzService().updata(Clazz) > 0) {
			System.out.println("�޸ĳɹ���");
		} else {
			System.out.println("�޸�ʧ�ܣ�");
		}
	}
	
	public void search(){
		System.out.println("��������");
		int ClazzId = 0;
		ClazzId = inputer.inputInteger();
		Clazz = new ClazzService().search(ClazzId);
		if (Clazz == null) {		//��֤���������Ƿ�Ϊ��
			System.out.println("���޸�����");
			return;
		}
		System.out.println("���\t����\t����ʱ��\t\t\t���ʱ��\t\t\t����\t������");
		System.out.println(Clazz.toString());		//�����Ϣ
	}
	
	public void display(){
		ArrayList<ClazzBean> Clazzs = new ArrayList<ClazzBean>();
		Clazzs = new ClazzService().display();
		if (Clazzs == null) {		//��֤���������Ƿ�Ϊ��
			System.out.println("������");
			return;
		}
		System.out.println("���\t����\t����ʱ��\t\t\t���ʱ��\t\t\t����\t������");
		for (int i = 0; i < Clazzs.size(); i++) {
			System.out.println(Clazzs.get(i).toString());
		}
	}
}
