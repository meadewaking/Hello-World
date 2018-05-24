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

		System.out.print("请输入利润：");
		try {
			setProfit(scanner.nextFloat());
		} catch (InputMismatchException ex) {
			System.out.println("输入利润格式错误。");
		}
		System.out.print("请输入提成比例：");
		try {
			setRate(scanner.nextFloat());
		} catch (InputMismatchException ex) {
			System.out.println("输入提成比例费格式错误。");
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
		return String.format("%s管理者\t%.2f\t%.2f\t%.2f\t", super.toString(), profit, rate, getSalary());
	}
}