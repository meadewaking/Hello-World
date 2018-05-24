package net.sycu.meade.view;

import net.sycu.meade.common.Inputer;

public class MyMenu {

	Inputer inputer = new Inputer();

	public void printMainMenu() {
		System.out.println("1.�༶��Ϣ����  2.��ʦ��Ϣ����  3.ѧ����Ϣ����  4.�γ���Ϣ����  5.�ڿι���  6.ѡ�ι���  7.ϵͳ����  0.�˳�");
	}
	
	public void RunStudentMenu(){
		StudentUI ui = new StudentUI();
		printDMLMenu();
		int choice = 0;
		choice = inputer.inputChoice(0, 5);
		switch (choice) {
		case 1:
			ui.add();
			break;
		case 2:
			ui.remove();
			break;
		case 3:
			ui.updata();
			break;
		case 4:
			ui.search();
			break;
		case 5:
			ui.display();
			break;
		case 0:
			System.out.println("�����ϼ��˵�");
			break;
		default:
			System.out.println("���޸ù���");
			break;
		}
	}
	
	public void RunClazzMenu(){
		ClazzUI ui = new ClazzUI();
		printDMLMenu();
		int choice = 0;
		choice = inputer.inputChoice(0, 5);
		switch (choice) {
		case 1:
			ui.add();
			break;
		case 2:
			ui.remove();
			break;
		case 3:
			ui.updata();
			break;
		case 4:
			ui.search();
			break;
		case 5:
			ui.display();
			break;
		case 0:
			System.out.println("�����ϼ��˵�");
			break;
		default:
			System.out.println("���޸ù���");
			break;
		}
	}

	public void RunCourseMenu(){
		CourseUI ui = new CourseUI();
		printDMLMenu();
		int choice = 0;
		choice = inputer.inputChoice(0, 5);
		switch (choice) {
		case 1:
			ui.add();
			break;
		case 2:
			ui.remove();
			break;
		case 3:
			ui.updata();
			break;
		case 4:
			ui.search();
			break;
		case 5:
			ui.display();
			break;
		case 0:
			System.out.println("�����ϼ��˵�");
			break;
		default:
			System.out.println("���޸ù���");
			break;
		}
	}
	
	public void RunTeacherMenu(){
		TeacherUI ui = new TeacherUI();
		printDMLMenu();
		int choice = 0;
		choice = inputer.inputChoice(0, 5);
		switch (choice) {
		case 1:
			ui.add();
			break;
		case 2:
			ui.remove();
			break;
		case 3:
			ui.updata();
			break;
		case 4:
			ui.search();
			break;
		case 5:
			ui.display();
			break;
		case 0:
			System.out.println("�����ϼ��˵�");
			break;
		default:
			System.out.println("���޸ù���");
			break;
		}
	}

	public void RunTeachCourseMenu(){
		TeachCourseUI ui = new TeachCourseUI();
		printDMLMenu();
		int choice = 0;
		choice = inputer.inputChoice(0, 5);
		switch (choice) {
		case 1:
			ui.add();
			break;
		case 2:
			ui.remove();
			break;
		case 3:
			ui.updata();
			break;
		case 4:
			ui.search();
			break;
		case 5:
			ui.display();
			break;
		case 0:
			System.out.println("�����ϼ��˵�");
			break;
		default:
			System.out.println("���޸ù���");
			break;
		}
	}
	
	public void RunStudyCourseMenu(){
		StudyCourseUI ui = new StudyCourseUI();
		printDMLMenu();
		int choice = 0;
		choice = inputer.inputChoice(0, 5);
		switch (choice) {
		case 1:
			ui.add();
			break;
		case 2:
			ui.remove();
			break;
		case 3:
			ui.updata();
			break;
		case 4:
			ui.search();
			break;
		case 5:
			ui.display();
			break;
		case 0:
			System.out.println("�����ϼ��˵�");
			break;
		default:
			System.out.println("���޸ù���");
			break;
		}
	}
	
	public void printSystemMenu() {
		System.out.println("1.ѧ��״̬��Ϣ����  2.��ʦ������Ϣ����  3.��ʦ״̬��Ϣ����  4.�γ�������Ϣ����  0.�����ϼ��˵�");
	}

	public void printDMLMenu() {
		System.out.println("1.���  2.ɾ��  3.�޸�  4.��ѯ  5.��ʾ  0.�˳�");
	}

	public void Run() {
		int choice = 0;
		do {
			printMainMenu();
			choice = inputer.inputChoice(0, 7);
			switch (choice) {
			case 1:
				RunClazzMenu();
				break;
			case 2:
				RunTeacherMenu();
				break;
			case 3:
				RunStudentMenu();
				break;
			case 4:
				RunCourseMenu();
				break;
			case 5:
				RunTeachCourseMenu();
				break;
			case 6:
				RunStudyCourseMenu();
				break;
			case 7:
				runSystemMenu();
				break;
			case 0:
				System.out.println("ллʹ��");
				break;
			default:
				System.out.println("���޸ù���");
				break;
			}
		} while (choice != 0);
	}

	public void runSystemMenu() {
		int choice = 0;
		printSystemMenu();
		choice = inputer.inputChoice(0, 4);
		switch (choice) {
		case 1:
			runStudentStateMenu();
			break;
		case 2:
			runTeacherTypeMenu();
			break;
		case 3:
			runTeacherStateMenu();
			break;
		case 4:
			runCourseTypeMenu();
			break;
		case 0:
			System.out.println("�����ϼ��˵�");
			break;
		default:
			System.out.println("���޸ù���");
			break;
		}

	}

	public void runStudentStateMenu() {
		StudentStateUI ui = new StudentStateUI();
		printDMLMenu();
		int choice = 0;
		choice = inputer.inputChoice(0, 5);
		switch (choice) {
		case 1:
			ui.add();
			break;
		case 2:
			ui.remove();
			break;
		case 3:
			ui.updata();
			break;
		case 4:
			ui.search();
			break;
		case 5:
			ui.display();
			break;
		case 0:
			System.out.println("�����ϼ��˵�");
			break;
		default:
			System.out.println("���޸ù���");
			break;
		}
	}

	public void runTeacherStateMenu() {
		TeacherStateUI ui = new TeacherStateUI();
		printDMLMenu();
		int choice = 0;
		choice = inputer.inputChoice(0, 5);
		switch (choice) {
		case 1:
			ui.add();
			break;
		case 2:
			ui.remove();
			break;
		case 3:
			ui.updata();
			break;
		case 4:
			ui.search();
			break;
		case 5:
			ui.display();
			break;
		case 0:
			System.out.println("�����ϼ��˵�");
			break;
		default:
			System.out.println("���޸ù���");
			break;
		}
	}

	public void runTeacherTypeMenu() {
		TeacherTypeUI ui = new TeacherTypeUI();
		printDMLMenu();
		int choice = 0;
		choice = inputer.inputChoice(0, 5);
		switch (choice) {
		case 1:
			ui.add();
			break;
		case 2:
			ui.remove();
			break;
		case 3:
			ui.updata();
			break;
		case 4:
			ui.search();
			break;
		case 5:
			ui.display();
			break;
		case 0:
			System.out.println("�����ϼ��˵�");
			break;
		default:
			System.out.println("���޸ù���");
			break;
		}
	}

	public void runCourseTypeMenu() {
		CourseTypeUI ui = new CourseTypeUI();
		printDMLMenu();
		int choice = 0;
		choice = inputer.inputChoice(0, 5);
		switch (choice) {
		case 1:
			ui.add();
			break;
		case 2:
			ui.remove();
			break;
		case 3:
			ui.updata();
			break;
		case 4:
			ui.search();
			break;
		case 5:
			ui.display();
			break;
		case 0:
			System.out.println("�����ϼ��˵�");
			break;
		default:
			System.out.println("���޸ù���");
			break;
		}
	}
}