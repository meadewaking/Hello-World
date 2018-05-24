package net.sycu.guomy.entity;

import java.util.InputMismatchException;
import java.util.Scanner;

import net.sycu.guomy.common.SalaryException;

abstract public class Employee extends Person {

	private String number = "";
	private float baseSalary = 0;
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public float getBaseSalary() {
		return baseSalary;
	}
	public void setBaseSalary(float baseSalary) {
		if (baseSalary < 0)
			throw new SalaryException();
		else
			this.baseSalary = baseSalary;
	}

	public void input() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("请输入工号：");
		number = scanner.nextLine();

		super.input();

		System.out.print("请输入工资：");
		try {
			setBaseSalary(scanner.nextFloat());
		} catch (InputMismatchException ex) {
			System.out.println("输入工资格式错误。");
		} catch (SalaryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	abstract public float getSalary();
	
	public void display() {
		System.out.printf("%s\t", number);
		super.display();
		System.out.printf("%.2f\t", baseSalary);
	}
	
	@Override
	public String toString() {
//		String str = "";
//		str = String.format("%s\t", number);
//		str += super.toString();
//		str += String.format("%.2f\t", baseSalary);
//		return str;
		return String.format("%s\t%s%.2f\t", number, super.toString(), baseSalary);
	}
}
