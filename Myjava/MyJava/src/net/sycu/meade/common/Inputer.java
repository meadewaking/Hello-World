package net.sycu.meade.common;

import java.util.InputMismatchException;
import java.util.Scanner;

import net.sycu.meade.exception.SexException;

public class Inputer {

	public float inputFloat() {			//输入浮点数,如工资利率和成绩
		float score = '0';
		Scanner scanner = new Scanner(System.in);
		try {
			score = scanner.nextFloat();
			if (score < 0) {
				System.out.println("输入错误，请重新输入：");
				return inputFloat();
			}
		} catch (InputMismatchException ex) {
			System.out.println("输入格式错误,请重新输入：");
			return inputFloat();
			// TODO: handle exception
		}
		return score;
	}

	public String input() {			//输入字符串
		String string = "";
		Scanner scanner = new Scanner(System.in);
		string = scanner.nextLine();
		return string;
	}

	public int inputInteger(int min, int max) {		//输入整形数
		int integer = '0';
		Scanner scanner = new Scanner(System.in);
		try {
			integer = scanner.nextInt();
			if (integer < min || integer > max) {
				System.out.println("输入错误，请重新输入：");
				return inputInteger(min, max);
			}
		} catch (InputMismatchException ex) {
			System.out.println("输入格式错误,请重新输入：");
			return inputInteger(min, max);
			// TODO: handle exception
		}
		return integer;
	}

	public String inputSex() {			//输入性别,默认男
		String string = "男";
		Scanner scanner = new Scanner(System.in);
		string = scanner.nextLine();
		if ((string.equals("男") || string.equals("女")) == true) {
			return string;
		}
		else
		{
			throw new SexException();
		}
	}

	public float inputScore(float min, float max) {		//输入成绩可自定义成绩范围
		float Score = '0';
		Scanner scanner = new Scanner(System.in);
		try {
			Score = scanner.nextInt();
			if (Score < min || Score > max) {
				System.out.println("非法成绩，请重新输入：");
				return inputScore(min, max);
			}
		} catch (InputMismatchException ex) {
			System.out.println("输入格式错误,请重新输入：");
			return inputScore(min, max);
			// TODO: handle exception
		}
		return Score;
	}
}
