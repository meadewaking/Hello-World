package net.sycu.guomy.entity;

import java.util.InputMismatchException;
import java.util.Scanner;

import net.sycu.guomy.common.SalaryException;

public class Teacher extends Employee {
	private float hours = 0;
	private float income = 0;

	public float getHours() {
		return hours;
	}
	public void setHours(float hours) {
		this.hours = hours;
	}
	public float getIncome() {
		return income;
	}
	public void setIncome(float income) {
		this.income = income;
	}
	
	public void input() {
		super.input();
		
		Scanner scanner = new Scanner(System.in);

		System.out.print("�������ʱ��");
		try {
			setHours(scanner.nextFloat());
		} catch (InputMismatchException ex) {
			System.out.println("�����ʱ��ʽ����");
		}
		System.out.print("�������ʱ�ѣ�");
		try {
			setIncome(scanner.nextFloat());
		} catch (InputMismatchException ex) {
			System.out.println("�����ʱ�Ѹ�ʽ����");
		}
	}
	
	public float getSalary() {
		return getBaseSalary() + hours * income;
	}

	public void display() {
		super.display();
		System.out.printf("%.2f\t%.2f\t%.2f\t", hours, income, getSalary());
	}
	
	@Override
	public String toString() {
		return String.format("%s��ʦ\t%.2f\t%.2f\t%.2f\t", super.toString(), hours, income, getSalary());
	}
}