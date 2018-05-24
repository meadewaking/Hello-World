package net.sycu.guomy.ui;

import java.util.ArrayList;

import net.sycu.guomy.business.Clazz;
import net.sycu.guomy.common.Inputer;
import net.sycu.guomy.entity.Student;

public class StudentSubMenu {

	public void printMenu() {
		System.out.println("1.���� 2.��� 3.���� 4.ɾ�� 5.�޸� 6.��ѯ 7.���� 8.���� 9.���� 0.�˳�");
	}
	public void printFindSubMenu() {
		System.out.println("1.��ѧ�� 2.������");
	}
	public void printSortSubMenu() {
		System.out.println("1.��ѧ�� 2.������ 3.���ɼ�");
	}

	public void run() {
		int choice = 0;
		Clazz clazz = new Clazz();
		System.out.print("������༶���ƣ�");
		clazz.setName(Inputer.inputString());
		
		System.out.println("ѧ����Ϣ������ϵͳ");
		do {
			printMenu();
			System.out.print("��ѡ��");
			choice = Inputer.inputInteger(0, 9);
			
			switch (choice) {
			case 1: 
				clazz.input();
				break;
			case 2:
				clazz.output();
				break;
			case 3: 
				clazz.add();
				break;
			case 4:
				clazz.remove();
				break;
			case 5:
				clazz.modify();
				break;
			case 6:
				runFind(clazz);
				break;
			case 7:
				runSort(clazz);
				break;
			case 8:
				clazz.save();
				break;
			case 9:
				clazz.load();
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

	public void runFind(Clazz clazz) {
		int choice = 0; 
		
		printFindSubMenu();
		System.out.print("��ѡ��");
		choice = Inputer.inputInteger(1, 2);
		
		switch (choice) {
		case 1: 
			clazz.findByNumber();
			break;
		case 2:
			clazz.findByName();
			break;
		default:
			System.out.println("���޴˹��ܡ�");
			break;
		}
	}
	public void runSort(Clazz clazz) {
		int choice = 0; 
		
		printFindSubMenu();
		System.out.print("��ѡ��");
		choice = Inputer.inputInteger(1, 3);
		
		switch (choice) {
		case 1: 
			clazz.sortByNumber();
			break;
		case 2: 
			clazz.sortByName();
			break;
		case 3:
			clazz.sortByScore();
			break;
		default:
			System.out.println("���޴˹��ܡ�");
			break;
		}
	}

}