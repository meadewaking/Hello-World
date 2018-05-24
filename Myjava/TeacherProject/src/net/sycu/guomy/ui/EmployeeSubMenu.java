package net.sycu.guomy.ui;

import net.sycu.guomy.business.School;
import net.sycu.guomy.common.Inputer;

public class EmployeeSubMenu {

	public void printMenu() {
		System.out.println("1.���� 2.��� 3.���� 4.ɾ�� 5.�޸� 6.��ѯ 7.���� 8.���� 9.���� 0.�˳�");
	}
	public void printFindSubMenu() {
		System.out.println("1.������ 2.������");
	}
	public void printSortSubMenu() {
		System.out.println("1.������ 2.������ 3.������");
	}

	public void run() {
		int choice = 0;
		School school = new School();
		
		System.out.println("Ա����Ϣ������ϵͳ");
		do {
			printMenu();
			System.out.print("��ѡ��");
			choice = Inputer.inputInteger(0, 9);
			
			switch (choice) {
			case 1: 
				school.input();
				break;
			case 2:
				school.output();
				break;
			case 3: 
				school.add();
				break;
			case 4:
				school.remove();
				break;
			case 5:
				school.modify();
				break;
			case 6:
				runFind(school);
				break;
			case 7:
				runSort(school);
				break;
			case 8:
				school.save();
				break;
			case 9:
				school.load();
				break;
			case 0:
				System.out.println("�������˵���");
				break;
			default:
				System.out.println("���޴˹��ܡ�");
				break;
			}
		} while (choice != 0);
	}

	public void runFind(School school) {
		int choice = 0; 
		
		printFindSubMenu();
		System.out.print("��ѡ��");
		choice = Inputer.inputInteger(1, 2);
		
		switch (choice) {
		case 1: 
			school.findByNumber();
			break;
		case 2:
			school.findByName();
			break;
		default:
			System.out.println("���޴˹��ܡ�");
			break;
		}
	}
	public void runSort(School school) {
		int choice = 0; 
		
		printFindSubMenu();
		System.out.print("��ѡ��");
		choice = Inputer.inputInteger(1, 3);
		
		switch (choice) {
		case 1: 
			school.sortByNumber();
			break;
		case 2: 
			school.sortByName();
			break;
		case 3:
			school.sortBySalary();
			break;
		default:
			System.out.println("���޴˹��ܡ�");
			break;
		}
	}

}
