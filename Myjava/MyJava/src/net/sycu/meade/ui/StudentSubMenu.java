package net.sycu.meade.ui;

import net.sycu.meade.business.Clazz;
import net.sycu.meade.common.Inputer;

public class StudentSubMenu {
	
	Inputer inputer = new Inputer();
	Clazz clazz = new Clazz();
	
	public void run() {			//学生管理子系统的主菜单

		System.out.println("学生管理子系统");
		System.out.println("请输入班级名称：");
		clazz.setName(inputer.input());
		int choice = '0';
		do {
			printmenu();
			System.out.println("请选择：");
			choice = inputer.inputInteger(0, 9);
			switch (choice) {
			case 1:
				clazz.input();
				break;
			case 2:
				clazz.output();
				break;
			case 3:
				clazz.insert();
				break;
			case 4:
				clazz.delete();
				break;
			case 5:
				clazz.modify();
				break;
			case 6:
				clazz.search();
				break;
			case 7:
				printsortmenu();
				break;
			case 8:
				clazz.save();
				break;
			case 9:
				clazz.load();
				System.out.println("加载成功！");
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

	public void printmenu() {		//选项菜单
		System.out.println("1.输入 2.输出 3.增加 4.删除 5.修改 6.查询 7.排序 8.保存 9.加载   0.退出");
	}
	
	public void printsortmenu(){		//查找功能子菜单
		System.out.println("请选择排序方式：");
		System.out.println("1.按姓名排序\t2.按学号排序\t3.按总成绩排序");
		int i = '0';
		i = inputer.inputInteger(1, 3);
		switch (i) {
		case 1:
			clazz.sortByName();
			break;
		case 2:
			clazz.sortByNum();
			break;
		case 3:
			clazz.sortBySum();
			break;
		default:
			System.out.println("暂无此功能");
			break;
		}
	}
}
