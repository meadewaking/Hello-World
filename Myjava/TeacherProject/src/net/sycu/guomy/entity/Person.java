package net.sycu.guomy.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Scanner;

import net.sycu.guomy.common.SexException;

public class Person extends Object implements Serializable, Comparable<Person> {
	private String name = "";
	private String sex = "Ů";
	
	public Person() {
		super();
	}
	public Person(String name) {
		super();
		this.name = name;
	}
	public Person(String name, String sex) {
		super();
		this.name = name;
		this.sex = sex;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		if (sex.compareTo("��") == 0 || sex.compareTo("Ů") == 0)
			this.sex = sex;
		else 
			throw new SexException();
	}

	public void relax() {
		System.out.println(name + "������Ϣ...");
	}
	
	public void input() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("������������");
		name = scanner.nextLine();
		System.out.print("�������Ա�");
		try {
			setSex(scanner.nextLine());
		} catch (SexException e) {
			System.out.println(e.getMessage());
		}
	}
	public void display() {
		System.out.printf("%s\t%s\t", name, sex);
	}
	@Override
	public String toString() {
		return name + "\t" + sex + "\t";
	}
	
	@Override
	protected void finalize() throws Throwable {
		System.out.printf("%s��������", name);
		super.finalize();
	}
	
	public void sayHello() {
		System.out.printf("��ã�����%s��", name);
	}
	
	@Override
	public int compareTo(Person o) {
		return this.getName().compareTo(o.getName());
	}
}
