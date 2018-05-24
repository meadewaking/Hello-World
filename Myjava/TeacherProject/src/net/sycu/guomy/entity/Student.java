package net.sycu.guomy.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import net.sycu.guomy.common.Inputer;
import net.sycu.guomy.common.ScoreException;


// 继承是由现有类创建新类的过程，这个现有被称为父类、基类、超类，这个新类被称为子类、派生类。子类具有父类的全部特性。
// 继承的语法：定义新类时加“extends 父类名”
public class Student extends Person implements Serializable { // implements Comparable<Student> {
	private String number = "";
	private float[] score = new float[5];

	// 继承和构造方法：
	// （1）子类的构造方法接受参数；
	// （2）本着“谁的成员谁负责”的原则，将父类成员传递给父类的构造方法；
	// （3）在子类中，用super代表父类，因此在子类的构造方法中用super()方法代表父类的构造方法；
	// （4）调用父类构造方法的语句必须是第一句；
	public Student() {
		// 
		super();
	}
	public Student(String name) {
		super(name);
	}
	public Student(String number, String name) {
		super(name);
		this.number = number;
	}
	public Student(String number, String name, String sex) {
		super(name, sex);
		this.number = number;
	}
	public Student(String number, String name, String sex, float[] score) {
		// 
		super(name, sex);
		this.number = number;
		this.score = score;
	}
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public float[] getScore() {
		return score;
	}
	public void setScore(float[] score) {
		this.score = score;
	}
	
	public void input() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("请输入学号：");
		number = scanner.nextLine();

		super.input();
		
		for (int i = 0; i < score.length; i++) {
			System.out.printf("请输入第%d科成绩：", i + 1);
			score[i] = Inputer.inputFloat(0, 100);
		}
		
		//  输出总分和平均分
	}
	
	public void display() {
		System.out.printf("%s\t", number);
		super.display();
//		for (int i = 0; i < score.length; i++)
//			System.out.printf("%.2f\t", score[i]);
		for (float f : score)
			System.out.printf("%.2f\t", f);
//		for (int i = 0; i < score.length; i++) {
//			float f = score[i];
//			System.out.printf("%.2f\t", f);
//		}
	}
	@Override
	public String toString() {
		String str = number + "\t";
		float sum = sum();
		
		str += super.toString();
		for (float f : score) {
			str += f + "\t";
		}
		str += sum + "\t";
		str += sum / score.length + "\t";
		return str;
	}
	
	public float sum() {
		float sum = 0;
		for (float f : score) {
			sum += f;
		}
		return sum;
	}
	
	public void study() {
		System.out.println(getName() + "正在学习...");
	}
	public void exam() {
		System.out.println(getName() + "考试成绩为" + score);
	}
	public void play() {}

	public void sayHello() {
		System.out.printf("老师好，我是%s。", getName());
	}
	
//	@Override
//	public int compareTo(Student o) {
//		return this.getNumber().compareTo(o.getNumber());
//		if (this.sum() > o.sum())
//			return 1;
//		else if (this.sum() < o.sum())
//			return -1;
//		else
//			return 0;
//	}
	
//	@Override
//	public int compareTo(Object o) {
//		if (this.sum() > ((Student)o).sum())
//			return 1;
//		else if (this.sum() < ((Student)o).sum())
//			return -1;
//		else
//			return 0;
//	}
}