package net.sycu.guomy.ui;

import net.sycu.guomy.common.Inputer;

public class MainMenu {
	
	public void printMenu() {
		System.out.println("1.学生信息管理 2.员工信息管理 0.退出");
	}
	
	public void run() {
		int choice = 0;

		System.out.println("欢迎使用学生信息管理系统(Java版)");
		do {
			printMenu();
			System.out.print("请选择：");
			choice = Inputer.inputInteger(0, 2);
			
			switch (choice) {
			case 1:
				new StudentSubMenu().run();
				break;
			case 2:
				new EmployeeSubMenu().run();
				break;
			case 0:
				System.out.println("谢谢使用。");
				break;
			default:
				System.out.println("暂无此功能。");
				break;
			}
		} while (choice != 0);
	}
	
}