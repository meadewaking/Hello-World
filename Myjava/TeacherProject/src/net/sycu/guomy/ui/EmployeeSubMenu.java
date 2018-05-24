package net.sycu.guomy.ui;

import net.sycu.guomy.business.School;
import net.sycu.guomy.common.Inputer;

public class EmployeeSubMenu {

	public void printMenu() {
		System.out.println("1.输入 2.输出 3.新增 4.删除 5.修改 6.查询 7.排序 8.保存 9.加载 0.退出");
	}
	public void printFindSubMenu() {
		System.out.println("1.按工号 2.按姓名");
	}
	public void printSortSubMenu() {
		System.out.println("1.按工号 2.按姓名 3.按工资");
	}

	public void run() {
		int choice = 0;
		School school = new School();
		
		System.out.println("员工信息管理子系统");
		do {
			printMenu();
			System.out.print("请选择：");
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
				System.out.println("返回主菜单。");
				break;
			default:
				System.out.println("暂无此功能。");
				break;
			}
		} while (choice != 0);
	}

	public void runFind(School school) {
		int choice = 0; 
		
		printFindSubMenu();
		System.out.print("请选择：");
		choice = Inputer.inputInteger(1, 2);
		
		switch (choice) {
		case 1: 
			school.findByNumber();
			break;
		case 2:
			school.findByName();
			break;
		default:
			System.out.println("暂无此功能。");
			break;
		}
	}
	public void runSort(School school) {
		int choice = 0; 
		
		printFindSubMenu();
		System.out.print("请选择：");
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
			System.out.println("暂无此功能。");
			break;
		}
	}

}
