package net.sycu.meade.entity;

import java.util.InputMismatchException;
import java.util.Scanner;

import net.sycu.meade.exception.SalaryException;

public class HourlyWorker extends Employee {

	private float cost = 0;
	private String kind = "Сʱ��";
	
	public String getKind() {
		return kind;
	}
	
	public HourlyWorker(String name, String sex, String number, float basesalary, float income, float hours,
			float hours2, float cost) {
		super(name, sex, number, basesalary, income, hours);
		hours = hours2;
		this.cost = cost;
	}
	
	public HourlyWorker() {
		super();
	}
	public void input(){
		super.input();
		Scanner scanner = new Scanner(System.in);
		System.out.println("�����빤ʱ��");
		setHours(scanner.nextInt());
		System.out.print("������ʱн��");
		try {
			cost = scanner.nextFloat();
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
		return getHours()*cost;
	}
	
	public String toString() {
		return String.format("%s\t%s\t%s%.2f\t", super.getNumber(),getKind(), super.toString(), calculateSalary());
	}
}
