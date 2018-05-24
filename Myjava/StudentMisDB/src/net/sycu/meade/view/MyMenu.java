package net.sycu.meade.view;

import net.sycu.meade.common.Inputer;

public class MyMenu {

	Inputer inputer = new Inputer();

	public void printMainMenu() {
		System.out.println("1.班级信息管理  2.教师信息管理  3.学生信息管理  4.课程信息管理  5.授课管理  6.选课管理  7.系统管理  0.退出");
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
			System.out.println("返回上级菜单");
			break;
		default:
			System.out.println("暂无该功能");
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
			System.out.println("返回上级菜单");
			break;
		default:
			System.out.println("暂无该功能");
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
			System.out.println("返回上级菜单");
			break;
		default:
			System.out.println("暂无该功能");
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
			System.out.println("返回上级菜单");
			break;
		default:
			System.out.println("暂无该功能");
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
			System.out.println("返回上级菜单");
			break;
		default:
			System.out.println("暂无该功能");
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
			System.out.println("返回上级菜单");
			break;
		default:
			System.out.println("暂无该功能");
			break;
		}
	}
	
	public void printSystemMenu() {
		System.out.println("1.学生状态信息管理  2.教师类型信息管理  3.教师状态信息管理  4.课程类型信息管理  0.返回上级菜单");
	}

	public void printDMLMenu() {
		System.out.println("1.添加  2.删除  3.修改  4.查询  5.显示  0.退出");
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
				System.out.println("谢谢使用");
				break;
			default:
				System.out.println("暂无该功能");
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
			System.out.println("返回上级菜单");
			break;
		default:
			System.out.println("暂无该功能");
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
			System.out.println("返回上级菜单");
			break;
		default:
			System.out.println("暂无该功能");
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
			System.out.println("返回上级菜单");
			break;
		default:
			System.out.println("暂无该功能");
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
			System.out.println("返回上级菜单");
			break;
		default:
			System.out.println("暂无该功能");
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
			System.out.println("返回上级菜单");
			break;
		default:
			System.out.println("暂无该功能");
			break;
		}
	}
}