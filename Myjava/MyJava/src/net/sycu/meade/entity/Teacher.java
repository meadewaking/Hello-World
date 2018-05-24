package net.sycu.meade.entity;

import java.util.InputMismatchException;
import java.util.Scanner;

import net.sycu.meade.exception.SalaryException;
import net.sycu.meade.exception.SexException;

public class Teacher extends Employee {
	private String number = "";
	private float cost = 0;
	private String kind = "��ʦ";
	
	public String getKind() {
		return kind;
	}

	public Teacher(String name, String sex) {// ��������������ͬ������ֵ���޷���ֵ�����Ƿ���void���в���������������
		super(name, sex);
		// TODO Auto-generated constructor stub
	}

	public Teacher(String name) {
		super(name); // �������У���super�����࣬���������Ĺ��췽������super()����������Ĺ��췽����
		// TODO Auto-generated constructor stub
	}

	public Teacher() {
		super();
	}

	public Teacher(String name, String sex, String number) {
		super(name, sex);
		this.number = number; // this�ؼ��֣��ڳ�Ա�����д�����ø÷����Ķ���ͨ������ʡ��
	}

	public void work() {
		while (true) {
			Scanner scanner = new Scanner(System.in);
			System.out.print("������" + getName() + "�Ļ������ʣ�");
			try {
				setBasesalary(scanner.nextFloat());
			} catch (InputMismatchException ex) {
				System.out.println("�����ʽ����");
			}

			if (getBasesalary() < 1000)
				System.out.println("�������ʵ�����ͱ�׼��");
			else if (getBasesalary() > 100000)
				throw new SalaryException();
			else {
				System.out.println("�������ʱ����");
				try {
					setHours(scanner.nextFloat());
				} catch (InputMismatchException ex) {
					System.out.println("�����ʽ����");
					// TODO Auto-generated catch block
				}
				calculateSalary();
				System.out.println(getName() + "���ڹ�����" + "����������Ϊ" + getIncome());
				break;
			}
		}
	}

	public void teach() {
		System.out.println("����Ϊ" + number + "����ʦ�����Ͽ�");
	}

	public void rest() {
		String sex = "δ֪";
		System.out.println("һ��" + sex + "��ʦ������Ϣ");
		System.out.println("һ��" + getSex() + "��ʦ������Ϣ");
	}

	public void sayhello() {
		System.out.println("��Һã�����" + getName());
	}

	public void input() {
		super.input();
		Scanner scanner = new Scanner(System.in);
		System.out.println("�������ʱ��");
		setHours(scanner.nextInt());
		System.out.print("�������ʱ�ѣ�");
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
		return getHours() * cost + getBasesalary();
		// TODO Auto-generated method stub
	}
	
	public String toString() {
		return String.format("%s\t%s\t%s%.2f\t", super.getNumber(),getKind(), super.toString(), calculateSalary());
	}
}
