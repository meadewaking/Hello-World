package net.sycu.guomy.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import net.sycu.guomy.common.Inputer;
import net.sycu.guomy.common.ScoreException;


// �̳����������ഴ������Ĺ��̣�������б���Ϊ���ࡢ���ࡢ���࣬������౻��Ϊ���ࡢ�����ࡣ������и����ȫ�����ԡ�
// �̳е��﷨����������ʱ�ӡ�extends ��������
public class Student extends Person implements Serializable { // implements Comparable<Student> {
	private String number = "";
	private float[] score = new float[5];

	// �̳к͹��췽����
	// ��1������Ĺ��췽�����ܲ�����
	// ��2�����š�˭�ĳ�Ա˭���𡱵�ԭ�򣬽������Ա���ݸ�����Ĺ��췽����
	// ��3���������У���super�����࣬���������Ĺ��췽������super()����������Ĺ��췽����
	// ��4�����ø��๹�췽�����������ǵ�һ�䣻
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
		System.out.print("������ѧ�ţ�");
		number = scanner.nextLine();

		super.input();
		
		for (int i = 0; i < score.length; i++) {
			System.out.printf("�������%d�Ƴɼ���", i + 1);
			score[i] = Inputer.inputFloat(0, 100);
		}
		
		//  ����ֺܷ�ƽ����
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
		System.out.println(getName() + "����ѧϰ...");
	}
	public void exam() {
		System.out.println(getName() + "���Գɼ�Ϊ" + score);
	}
	public void play() {}

	public void sayHello() {
		System.out.printf("��ʦ�ã�����%s��", getName());
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