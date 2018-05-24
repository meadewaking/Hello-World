package net.sycu.meade.view;

import java.util.ArrayList;

import net.sycu.meade.common.Inputer;
import net.sycu.meade.entity.StudentStateBean;
import net.sycu.meade.service.StudentStateService;

public class StudentStateUI {
	
	StudentStateBean studentstate = new StudentStateBean();
	Inputer inputer = new Inputer();
	
	public void add() {
		System.out.println("����������");
		studentstate.setName(inputer.input());
		System.out.println("��������У״̬");
		studentstate.setInschool(inputer.inputBoolean());
		System.out.println("�������������");
		studentstate.setDescription(inputer.input());
		if (new StudentStateService().add(studentstate) > 0) {
			System.out.println("��ӳɹ���");
		} else {
			System.out.println("���ʧ�ܣ�");
		}
	}
	
	public void remove() {
		System.out.println("��������");
		int StudentStateId = 0;
		StudentStateId = inputer.inputInteger();
		if (new StudentStateService().remove(StudentStateId) > 0) {
			System.out.println("ɾ���ɹ���");
		} else {
			System.out.println("ɾ��ʧ�ܣ�");
		}
	}
	
	public void updata() {
		System.out.println("������Ҫ�޸ĵ��˵ı��");
		studentstate.setStudentStateId(inputer.inputInteger());
		System.out.println("����������");
		studentstate.setName(inputer.input());
		System.out.println("��������У״̬");
		studentstate.setInschool(inputer.inputBoolean());
		System.out.println("�������������");
		studentstate.setDescription(inputer.input());
		for (StudentStateBean state : new StudentStateService().display()) {
			System.out.println(state+"\t");
		}
		System.out.println("������Ҫ�޸ĵ�ѧ��״̬���");
		studentstate.setStudentStateId(inputer.inputInteger());
		if (new StudentStateService().updata(studentstate) > 0) {
			System.out.println("�޸ĳɹ���");
		} else {
			System.out.println("�޸�ʧ�ܣ�");
		}
	}
	
	public void search() {
		System.out.println("��������");
		int StudentStateId = 0;
		StudentStateId = inputer.inputInteger();
		studentstate = new StudentStateService().search(StudentStateId);
		if (studentstate == null) {
			System.out.println("���޸�����");
			return ;
		}
		System.out.println("ѧ��״̬���\t����\t�Ƿ���У\t����");
		System.out.println(studentstate.toString());
	}
	
	public void display() {
		ArrayList<StudentStateBean> studentstates = new ArrayList<StudentStateBean>();
		studentstates = new StudentStateService().display();
		if (studentstates == null) {
			System.out.println("������");
			return;
		}
		System.out.println("ѧ��״̬���\t����\t�Ƿ���У\t����");
		for (int i = 0; i < studentstates.size(); i++) {
			System.out.println(studentstates.get(i).toString());
		}
	}
}
