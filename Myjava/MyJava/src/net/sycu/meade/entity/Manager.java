package net.sycu.meade.entity;

import java.util.InputMismatchException;
import java.util.Scanner;

import net.sycu.meade.exception.SalaryException;

public class Manager extends Employee {

	private float profit = 0;
	private float rate = 0;
	private String kind = "������";
	
	public String getKind() {
		return kind;
	}
	
	public Manager() {
		super();
	}

	public Manager(String name, String sex, String number, float basesalary, float income, float hours, float profit,
			float rate) {
		super(name, sex, number, basesalary, income, hours);
		this.profit = profit;
		this.rate = rate;
	}
	public void input(){
		super.input();
		Scanner scanner = new Scanner(System.in);
		System.out.println("���������棺");
		try {
			profit = scanner.nextFloat();
		} catch (InputMismatchException ex) {
			System.out.println("���빤�ʸ�ʽ����");
		} catch (SalaryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("���������ʣ�");
		try {
			rate = scanner.nextFloat();
		} catch (InputMismatchException ex) {
			System.out.println("���빤�ʸ�ʽ����");
		} catch (SalaryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public float calculateSalary() {
		// TODO Auto-generated method stub
		return profit*rate + getBasesalary();
	}
	public String toString() {
		return String.format("%s\t%s\t%s%.2f\t", super.getNumber(),getKind(), super.toString(), calculateSalary());
	}
}
