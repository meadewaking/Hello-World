package net.sycu.meade.entity;

import java.io.Serializable;
import java.util.Scanner;	//ʹ�ò���ͬһ���е��࣬�����ȵ���(import)���࣬��ʹ��

import net.sycu.meade.common.Inputer;
import net.sycu.meade.exception.SexException;

public class Person implements Serializable,Comparable<Person>{	//���������бȽ�Ӧ����Person��Ĺ��ܣ�Person��Ӧ��ʵ��Comparable<Person>�ӿ�
	private String name = "";
	private String sex = "��";

	public Person(String name, String sex) {
		super();
		this.name = name;
		this.sex = sex;
	}
	
	public Person() {
		super();
	}

	public Person(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {	//����ͨ��get��set����������˽�е��ֶΣ�
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		if ((sex.equals("��") || sex.equals("Ů")) == true) {
			this.sex = sex;
		}
		else
			throw new SexException();
	}

	public void input() {
		Inputer inputer = new Inputer();
		System.out.print("�����������" + "�����֣�");
		this.name = inputer.input();
		System.out.print("������"+getName()+"���Ա�" );
		try {
			setSex( inputer.input());
		} catch (SexException e) {
			System.out.println(e.getMessage());		//�����Ա�����ʱ�쳣����������
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name+"\t"+this.sex+"\t";
	}

	public void sayhello() {
		System.out.println("��ã�����" + getName());
	}
	
//	public void inputsex(){
//		Scanner scanner = new Scanner(System.in);
//		//System.out.print("�����������" + "���Ա�");
//		sex = scanner.nextLine();		
//
//	}
	@Override
	public int compareTo(Person o) {
		// TODO Auto-generated method stub
		return this.getName().compareTo(o.getName());		//ͬ�����Ƚ���
	}

}
