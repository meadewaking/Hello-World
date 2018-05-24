package net.sycu.guomy.entity;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Manager extends Employee {
	private float profit = 0;
	private float rate = 0;
	
	public float getProfit() {
		return profit;
	}
	public void setProfit(float profit) {
		this.profit = profit;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}

	@Override
	public void input() {
		super.input();
		
		Scanner scanner = new Scanner(System.in);

		System.out.print("����������");
		try {
			setProfit(scanner.nextFloat());
		} catch (InputMismatchException ex) {
			System.out.println("���������ʽ����");
		}
		System.out.print("��������ɱ�����");
		try {
			setRate(scanner.nextFloat());
		} catch (InputMismatchException ex) {
			System.out.println("������ɱ����Ѹ�ʽ����");
		}
	}
	@Override
	public void display() {
		super.display();
		System.out.printf("%.2f\t%.2f\t%.2f\t", profit, rate, getSalary());
	}
	@Override
	public float getSalary() {
		return getBaseSalary() + profit * rate;
	}

	@Override
	public String toString() {
		return String.format("%s������\t%.2f\t%.2f\t%.2f\t", super.toString(), profit, rate, getSalary());
	}
}