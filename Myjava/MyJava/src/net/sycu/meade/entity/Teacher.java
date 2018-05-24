package net.sycu.meade.entity;

import java.util.InputMismatchException;
import java.util.Scanner;

import net.sycu.meade.exception.SalaryException;
import net.sycu.meade.exception.SexException;

public class Teacher extends Employee {
	private String number = "";
	private float cost = 0;
	private String kind = "教师";
	
	public String getKind() {
		return kind;
	}

	public Teacher(String name, String sex) {// 命名：与类名相同：返回值：无返回值，不是返回void；有参数，经常被重载
		super(name, sex);
		// TODO Auto-generated constructor stub
	}

	public Teacher(String name) {
		super(name); // 在子类中，用super代表父类，因此在子类的构造方法中用super()方法代表父类的构造方法；
		// TODO Auto-generated constructor stub
	}

	public Teacher() {
		super();
	}

	public Teacher(String name, String sex, String number) {
		super(name, sex);
		this.number = number; // this关键字，在成员方法中代表调用该方法的对象，通常可以省略
	}

	public void work() {
		while (true) {
			Scanner scanner = new Scanner(System.in);
			System.out.print("请输入" + getName() + "的基本工资：");
			try {
				setBasesalary(scanner.nextFloat());
			} catch (InputMismatchException ex) {
				System.out.println("输入格式错误");
			}

			if (getBasesalary() < 1000)
				System.out.println("基本工资低于最低标准！");
			else if (getBasesalary() > 100000)
				throw new SalaryException();
			else {
				System.out.println("请输入课时数：");
				try {
					setHours(scanner.nextFloat());
				} catch (InputMismatchException ex) {
					System.out.println("输入格式错误");
					// TODO Auto-generated catch block
				}
				calculateSalary();
				System.out.println(getName() + "正在工作，" + "他的总收入为" + getIncome());
				break;
			}
		}
	}

	public void teach() {
		System.out.println("工号为" + number + "的老师正在上课");
	}

	public void rest() {
		String sex = "未知";
		System.out.println("一名" + sex + "老师正在休息");
		System.out.println("一名" + getSex() + "老师正在休息");
	}

	public void sayhello() {
		System.out.println("大家好，我是" + getName());
	}

	public void input() {
		super.input();
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入课时：");
		setHours(scanner.nextInt());
		System.out.print("请输入课时费：");
		try {
			cost = scanner.nextFloat();
		} catch (InputMismatchException ex) {
			System.out.println("输入工资格式错误。");
		} catch (SalaryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public float calculateSalary() {
		return getHours() * cost + getBasesalary();
		// TODO Auto-generated method stub
	}
	
	public String toString() {
		return String.format("%s\t%s\t%s%.2f\t", super.getNumber(),getKind(), super.toString(), calculateSalary());
	}
}
