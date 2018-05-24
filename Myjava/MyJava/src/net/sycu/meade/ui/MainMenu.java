package net.sycu.meade.ui;

import net.sycu.meade.common.Inputer;

public class MainMenu {
	public static void main(String[] args) {		//管理系统的主材单也是其启动项
		printmenu();
		int choice = '0';
		Inputer inputer = new Inputer();
		StudentSubMenu studentSubMenu = new StudentSubMenu();
		EmployeeSubMenu employeeSubMenu = new EmployeeSubMenu();
		System.out.println("请选择：");
		do {
			choice = inputer.inputInteger(0, 3);
			switch (choice) {
			case 1:
				System.out.println("学生信息管理");
				studentSubMenu.run();
				break;
			case 2:
				System.out.println("员工信息管理");
				employeeSubMenu.run();
				break;
			case 0:
				System.out.println("谢谢使用");
				break;
			default:
				System.out.println("暂无此功能");
				break;
			}
		} while (choice != 0);
	}

	public static void printmenu() {
		System.out.println("\t欢迎使用Java版学校管理系统");
		System.out.println("1.学生信息管理	2.员工信息管理   0.退出");
	}
}
