package net.sycu.guomy.ui;

import java.util.ArrayList;

import net.sycu.guomy.business.Clazz;
import net.sycu.guomy.common.Inputer;
import net.sycu.guomy.entity.Student;

public class StudentSubMenu {

	public void printMenu() {
		System.out.println("1.输入 2.输出 3.新增 4.删除 5.修改 6.查询 7.排序 8.保存 9.加载 0.退出");
	}
	public void printFindSubMenu() {
		System.out.println("1.按学号 2.按姓名");
	}
	public void printSortSubMenu() {
		System.out.println("1.按学号 2.按姓名 3.按成绩");
	}

	public void run() {
		int choice = 0;
		Clazz clazz = new Clazz();
		System.out.print("请输入班级名称：");
		clazz.setName(Inputer.inputString());
		
		System.out.println("学生信息管理子系统");
		do {
			printMenu();
			System.out.print("请选择：");
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
				System.out.println("返回主菜单。");
				break;
			default:
				System.out.println("暂无此功能。");
				break;
			}
		} while (choice != 0);
	}

	public void runFind(Clazz clazz) {
		int choice = 0; 
		
		printFindSubMenu();
		System.out.print("请选择：");
		choice = Inputer.inputInteger(1, 2);
		
		switch (choice) {
		case 1: 
			clazz.findByNumber();
			break;
		case 2:
			clazz.findByName();
			break;
		default:
			System.out.println("暂无此功能。");
			break;
		}
	}
	public void runSort(Clazz clazz) {
		int choice = 0; 
		
		printFindSubMenu();
		System.out.print("请选择：");
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
			System.out.println("暂无此功能。");
			break;
		}
	}

}