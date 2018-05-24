package net.sycu.meade.entity;

import java.io.Serializable;
import java.util.Scanner;	//使用不在同一包中的类，必须先导入(import)该类，再使用

import net.sycu.meade.common.Inputer;
import net.sycu.meade.exception.SexException;

public class Person implements Serializable,Comparable<Person>{	//按姓名进行比较应该是Person类的功能，Person类应该实现Comparable<Person>接口
	private String name = "";
	private String sex = "男";

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

	public void setName(String name) {	//可以通过get和set方法，访问私有的字段；
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		if ((sex.equals("男") || sex.equals("女")) == true) {
			this.sex = sex;
		}
		else
			throw new SexException();
	}

	public void input() {
		Inputer inputer = new Inputer();
		System.out.print("请输入这个人" + "的名字：");
		this.name = inputer.input();
		System.out.print("请输入"+getName()+"的性别：" );
		try {
			setSex( inputer.input());
		} catch (SexException e) {
			System.out.println(e.getMessage());		//处理性别运行时异常避免程序崩溃
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name+"\t"+this.sex+"\t";
	}

	public void sayhello() {
		System.out.println("你好，我是" + getName());
	}
	
//	public void inputsex(){
//		Scanner scanner = new Scanner(System.in);
//		//System.out.print("请输入这个人" + "的性别：");
//		sex = scanner.nextLine();		
//
//	}
	@Override
	public int compareTo(Person o) {
		// TODO Auto-generated method stub
		return this.getName().compareTo(o.getName());		//同其他比较类
	}

}
