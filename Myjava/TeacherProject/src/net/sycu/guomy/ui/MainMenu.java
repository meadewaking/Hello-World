package net.sycu.guomy.ui;

import net.sycu.guomy.common.Inputer;

public class MainMenu {
	
	public void printMenu() {
		System.out.println("1.ѧ����Ϣ���� 2.Ա����Ϣ���� 0.�˳�");
	}
	
	public void run() {
		int choice = 0;

		System.out.println("��ӭʹ��ѧ����Ϣ����ϵͳ(Java��)");
		do {
			printMenu();
			System.out.print("��ѡ��");
			choice = Inputer.inputInteger(0, 2);
			
			switch (choice) {
			case 1:
				new StudentSubMenu().run();
				break;
			case 2:
				new EmployeeSubMenu().run();
				break;
			case 0:
				System.out.println("ллʹ�á�");
				break;
			default:
				System.out.println("���޴˹��ܡ�");
				break;
			}
		} while (choice != 0);
	}
	
}