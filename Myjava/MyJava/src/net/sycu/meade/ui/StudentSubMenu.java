package net.sycu.meade.ui;

import net.sycu.meade.business.Clazz;
import net.sycu.meade.common.Inputer;

public class StudentSubMenu {
	
	Inputer inputer = new Inputer();
	Clazz clazz = new Clazz();
	
	public void run() {			//ѧ��������ϵͳ�����˵�

		System.out.println("ѧ��������ϵͳ");
		System.out.println("������༶���ƣ�");
		clazz.setName(inputer.input());
		int choice = '0';
		do {
			printmenu();
			System.out.println("��ѡ��");
			choice = inputer.inputInteger(0, 9);
			switch (choice) {
			case 1:
				clazz.input();
				break;
			case 2:
				clazz.output();
				break;
			case 3:
				clazz.insert();
				break;
			case 4:
				clazz.delete();
				break;
			case 5:
				clazz.modify();
				break;
			case 6:
				clazz.search();
				break;
			case 7:
				printsortmenu();
				break;
			case 8:
				clazz.save();
				break;
			case 9:
				clazz.load();
				System.out.println("���سɹ���");
				break;
			case 0:
				System.out.println("ллʹ��");
				break;
			default:
				System.out.println("���޴˹���");
				break;
			}
		} while (choice != 0);
	}

	public void printmenu() {		//ѡ��˵�
		System.out.println("1.���� 2.��� 3.���� 4.ɾ�� 5.�޸� 6.��ѯ 7.���� 8.���� 9.����   0.�˳�");
	}
	
	public void printsortmenu(){		//���ҹ����Ӳ˵�
		System.out.println("��ѡ������ʽ��");
		System.out.println("1.����������\t2.��ѧ������\t3.���ܳɼ�����");
		int i = '0';
		i = inputer.inputInteger(1, 3);
		switch (i) {
		case 1:
			clazz.sortByName();
			break;
		case 2:
			clazz.sortByNum();
			break;
		case 3:
			clazz.sortBySum();
			break;
		default:
			System.out.println("���޴˹���");
			break;
		}
	}
}
