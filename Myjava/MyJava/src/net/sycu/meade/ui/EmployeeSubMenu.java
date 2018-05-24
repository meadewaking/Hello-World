package net.sycu.meade.ui;

import net.sycu.meade.business.Company;
import net.sycu.meade.common.Inputer;

public class EmployeeSubMenu {
	Inputer inputer = new Inputer();
	Company company = new Company();

	public void run() {

		System.out.println("Ա��������ϵͳ");
		int choice = '0';
		do {
			printmenu();
			System.out.println("��ѡ��");
			choice = inputer.inputInteger(0, 9);
			switch (choice) {
			case 1:
				company.input();
				break;
			case 2:
				company.output();
				break;
			case 3:
				company.insert();
				break;
			case 4:
				company.delete();
				break;
			case 5:
				company.modify();
				break;
			case 6:
				printsearchmenu();
				break;
			case 7:
				printsortmenu();
				break;
			case 8:
				company.save();
				break;
			case 9:
				company.load();
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

	public void printmenu() {
		System.out.println("1.���� 2.��� 3.���� 4.ɾ�� 5.�޸� 6.��ѯ 7.���� 8.���� 9.����   0.�˳�");
	}

	public void printsortmenu() {
		System.out.println("��ѡ������ʽ��");
		System.out.println("1.����������\t2.����������\t3.��нˮ����");
		int k = '0';
		k = inputer.inputInteger(1, 3);
		switch (k) {
		case 1:
			company.sortByName();
			break;
		case 2:
			company.sortByNum();
			break;
		case 3:
			company.sortBySalary();
			break;
		default:
			System.out.println("���޴˹���");
			break;
		}
	}
	
	public void printsearchmenu(){
		System.out.println("��ѡ���ѯ��ʽ��");
		System.out.println("1.����������\t2.�����Ų���");
		int i = '0';
		i = inputer.inputInteger(1, 2);
		switch (i) {
		case 1:
			company.findByName();
			break;
		case 2:
			company.findByNumber();
			break;
		default:
			System.out.println("���޴˹���");
			break;
		}
	}
}
