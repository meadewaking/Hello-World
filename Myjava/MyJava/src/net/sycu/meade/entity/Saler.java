package net.sycu.meade.entity;

import java.util.InputMismatchException;
import java.util.Scanner;

import net.sycu.meade.exception.SalaryException;

public class Saler extends Employee {
	
	private int count = 0;
	private float cost = 0;
	private String kind = "咨询师";
	
	public String getKind() {
		return kind;
	}
	
	public Saler(String name, String sex, String number, float basesalary, float income, float hours, int count,
			float cost) {
		super(name, sex, number, basesalary, income, hours);
		this.count = count;
		this.cost = cost;
	}
	
	public Saler() {
		super();
	}
	public void input(){
		super.input();
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入销售数：");
		count = scanner.nextInt();
		System.out.print("请输入招生费：");
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
		// TODO Auto-generated method stub
		return count*cost + getBasesalary();
	}
	
	public String toString() {
		return String.format("%s\t%s\t%s%.2f\t", super.getNumber(),getKind(), super.toString(), calculateSalary());
	}
}
