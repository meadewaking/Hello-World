package net.sycu.guomy.entity;

import java.util.InputMismatchException;
import java.util.Scanner;

import net.sycu.guomy.common.SalaryException;

public class Saler extends Employee {
	private float count = 0;
	private float cost = 0;
	
	public float getCount() {
		return count;
	}
	public void setCount(float count) {
		this.count = count;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}

	public void input() {
		super.input();
		
		Scanner scanner = new Scanner(System.in);

		System.out.print("请输入招生量：");
		try {
			setCount(scanner.nextFloat());
		} catch (InputMismatchException ex) {
			System.out.println("输入招生量格式错误。");
		}
		System.out.print("请输入费用：");
		try {
			setCost(scanner.nextFloat());
		} catch (InputMismatchException ex) {
			System.out.println("输入费用格式错误。");
		}
	}
	
	public float getSalary() {
		return getBaseSalary() + count * cost;
	}

	public void display() {
		super.display();
		System.out.printf("%.2f\t%.2f\t%.2f\t", count, cost, getSalary());
	}
	
	@Override
	public String toString() {
		return String.format("%s咨询师\t%.2f\t%.2f\t%.2f\t", super.toString(), count, cost, getSalary());
	}
}