package net.sycu.meade.ui;

import net.sycu.meade.business.Company;
import net.sycu.meade.common.Inputer;

public class EmployeeSubMenu {
	Inputer inputer = new Inputer();
	Company company = new Company();

	public void run() {

		System.out.println("员工管理子系统");
		int choice = '0';
		do {
			printmenu();
			System.out.println("请选择：");
			choice = inputer.inputInteger(0, 9);
			switch (choice) {
			case 1:
				company.input();
				break;
			case 2:
				company.output();
				break;
			case 3:
				company.insert();
				break;
			case 4:
				company.delete();
				break;
			case 5:
				company.modify();
				break;
			case 6:
				printsearchmenu();
				break;
			case 7:
				printsortmenu();
				break;
			case 8:
				company.save();
				break;
			case 9:
				company.load();
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

	public void printmenu() {
		System.out.println("1.输入 2.输出 3.增加 4.删除 5.修改 6.查询 7.排序 8.保存 9.加载   0.退出");
	}

	public void printsortmenu() {
		System.out.println("请选择排序方式：");
		System.out.println("1.按姓名排序\t2.按工号排序\t3.按薪水排序");
		int k = '0';
		k = inputer.inputInteger(1, 3);
		switch (k) {
		case 1:
			company.sortByName();
			break;
		case 2:
			company.sortByNum();
			break;
		case 3:
			company.sortBySalary();
			break;
		default:
			System.out.println("暂无此功能");
			break;
		}
	}
	
	public void printsearchmenu(){
		System.out.println("请选择查询方式：");
		System.out.println("1.按姓名查找\t2.按工号查找");
		int i = '0';
		i = inputer.inputInteger(1, 2);
		switch (i) {
		case 1:
			company.findByName();
			break;
		case 2:
			company.findByNumber();
			break;
		default:
			System.out.println("暂无此功能");
			break;
		}
	}
}
