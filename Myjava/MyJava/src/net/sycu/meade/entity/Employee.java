package net.sycu.meade.entity;

import java.util.InputMismatchException;
import java.util.Scanner;

import net.sycu.meade.exception.SalaryException;

abstract public class Employee extends Person {

	private String number = "";
	private float basesalary = 0;
	private float income = 0;
	private float hours = 0;

	public Employee(String name, String sex, String number, float basesalary, float income, float hours) {
		super(name, sex);
		this.number = number;
		this.basesalary = basesalary;
		this.income = income;
		this.hours = hours;
	}
	
	public Employee() {
		super();
	}

	public Employee(String name, String sex) {
		super(name, sex);
	}

	public Employee(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public float getBasesalary() {
		return basesalary;
	}

	public void setBasesalary(float basesalary) {
		this.basesalary = basesalary;
	}
	
	public float getIncome() {
		return income;
	}

	public void setIncome(float income) {
		this.income = income;
	}

	public float getHours() {
		return hours;
	}

	public void setHours(float hours) {
		this.hours = hours;
	}
	
	public void input() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("请输入工号：");
		number = scanner.nextLine();

		super.input();

		System.out.print("请输入基本工资：");
		try {
			setBasesalary(scanner.nextFloat());
		} catch (InputMismatchException ex) {
			System.out.println("输入工资格式错误。");
		} catch (SalaryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public abstract float calculateSalary();		//抽象方法,由派生类具体实现
	
	@Override
	public String toString() {
		return super.toString();
	}

}
