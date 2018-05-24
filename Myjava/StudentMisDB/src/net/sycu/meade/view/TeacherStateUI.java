package net.sycu.meade.view;

import java.util.ArrayList;

import net.sycu.meade.common.Inputer;
import net.sycu.meade.entity.TeacherStateBean;
import net.sycu.meade.service.TeacherStateService;

public class TeacherStateUI {
	
	TeacherStateBean Teacherstate = new TeacherStateBean();
	Inputer inputer = new Inputer();
	
	public void add(){
		System.out.println("����������");
		Teacherstate.setName(inputer.input());
		System.out.println("��������У״̬");
		Teacherstate.setInschool(inputer.inputBoolean());
		System.out.println("�������������");
		Teacherstate.setDescription(inputer.input());
		if (new TeacherStateService().add(Teacherstate) > 0) {
			System.out.println("��ӳɹ���");
		} else {
			System.out.println("���ʧ�ܣ�");
		}
	}
	
	public void remove(){
		System.out.println("��������");
		int TeacherStateId = 0;
		TeacherStateId = inputer.inputInteger();
		if (new TeacherStateService().remove(TeacherStateId) > 0) {
			System.out.println("ɾ���ɹ���");
		} else {
			System.out.println("ɾ��ʧ�ܣ�");
		}
	}
	
	public void updata(){
		System.out.println("������Ҫ�޸ĵ��˵ı��");
		Teacherstate.setTeacherStateId(inputer.inputInteger());
		System.out.println("����������");
		Teacherstate.setName(inputer.input());
		System.out.println("��������У״̬");
		Teacherstate.setInschool(inputer.inputBoolean());
		System.out.println("�������������");
		Teacherstate.setDescription(inputer.input());
		for (TeacherStateBean TeacherState : new TeacherStateService().display()) {
			System.out.println(TeacherState+"\t");
		}
		System.out.println("������Ҫ�޸ĵĽ�ʦ״̬���");
		Teacherstate.setTeacherStateId(inputer.inputInteger());
		if (new TeacherStateService().updata(Teacherstate) > 0) {
			System.out.println("�޸ĳɹ���");
		} else {
			System.out.println("�޸�ʧ�ܣ�");
		}
	}
	
	public void search(){
		System.out.println("��������");
		int TeacherStateId = 0;
		TeacherStateId = inputer.inputInteger();
		Teacherstate = new TeacherStateService().search(TeacherStateId);
		if (Teacherstate == null) {
			System.out.println("���޸�����");
			return;
		}
		System.out.println("��ʦ״̬���\t����\t�Ƿ���У\t����");
		System.out.println(Teacherstate.toString());
	}
	
	public void display(){
		ArrayList<TeacherStateBean> Teacherstates = new ArrayList<TeacherStateBean>();
		Teacherstates = new TeacherStateService().display();
		if (Teacherstates == null) {
			System.out.println("������");
			return;
		}
		System.out.println("��ʦ״̬���\t����\t�Ƿ���У\t����");
		for (int i = 0; i < Teacherstates.size(); i++) {
			System.out.println(Teacherstates.get(i).toString());
		}
	}
}
