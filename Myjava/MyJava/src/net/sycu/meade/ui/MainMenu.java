package net.sycu.meade.ui;

import net.sycu.meade.common.Inputer;

public class MainMenu {
	public static void main(String[] args) {		//����ϵͳ�����ĵ�Ҳ����������
		printmenu();
		int choice = '0';
		Inputer inputer = new Inputer();
		StudentSubMenu studentSubMenu = new StudentSubMenu();
		EmployeeSubMenu employeeSubMenu = new EmployeeSubMenu();
		System.out.println("��ѡ��");
		do {
			choice = inputer.inputInteger(0, 3);
			switch (choice) {
			case 1:
				System.out.println("ѧ����Ϣ����");
				studentSubMenu.run();
				break;
			case 2:
				System.out.println("Ա����Ϣ����");
				employeeSubMenu.run();
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

	public static void printmenu() {
		System.out.println("\t��ӭʹ��Java��ѧУ����ϵͳ");
		System.out.println("1.ѧ����Ϣ����	2.Ա����Ϣ����   0.�˳�");
	}
}
