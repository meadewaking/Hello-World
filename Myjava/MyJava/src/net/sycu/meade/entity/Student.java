package net.sycu.meade.entity;

import java.io.Serializable;
import java.util.Scanner;

import net.sycu.meade.common.Inputer;
import net.sycu.meade.exception.ScoreException;


public class Student extends Person implements Serializable{
	private String number = "";
	private float[] score = new float[3];
	
	public Student(String name, String sex) {
		super(name, sex);
		// TODO Auto-generated constructor stub
	}

	public Student() {
		super();
	}

	public Student(String name, String sex, String number) {
		super(name, sex);
		this.number = number;
		// TODO Auto-generated constructor stub
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

	public void play() {
		System.out.print("Can Play!\n");
		System.out.println(getName() + "������");
	}

	public void learn() {
		//inputscore();
		System.out.printf("ѧ��Ϊ%s��ѧ������ѧϰ\n", this.number);
		System.out.println(getName() + "�����ճɼ�Ϊ:" + this.score);
	}

	public void input() {
		super.input();
		Inputer inputer = new Inputer();
		System.out.print("������" + getName() + "��ѧ�ţ�");
		number = inputer.input();
		for (int i = 0; i < score.length; i++) {
			System.out.println("������" + getName() + "�ĵ�"+(i+1)+"�Ƴɼ���");
			score[i] = inputer.inputScore(0,100);
		}

	}

//	public float inputscore(int i) {
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("������" + getName() + "�ĵ�"+(i+1)+"�Ƴɼ���");
//		score[i] = scanner.nextFloat();
//		if (score[i] < 0 || score[i] > 100) {
//			throw new ScoreException();
//		}
//		return score[i];
//	}

	public void sayhello() {
		System.out.println("���ã�������" + getName());
	}

	public void programming() {
		System.out.println("Can programming !");
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
	public float sum(){
		int add = 0;
		for (int i = 0; i < score.length; i++) {
			add += score[i];
		}
		return add;
	}

	/*@Override
	public int compareTo(Student o) {
		// TODO Auto-generated method stub
		if (this.sum()>((Student)o).sum()) {
			return 1;
		} else if (this.sum()<((Student)o).sum()){
			return -1;
		}
		else {
			return 0;
		}
	}*/
	
	
	/*@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		if (this.sum()>((Student)o).sum()) {
			return 1;
		} else if (this.sum()<((Student)o).sum()){
			return 0;
		}
		else {
			return 0;
		}
		
	}*/
	
}
