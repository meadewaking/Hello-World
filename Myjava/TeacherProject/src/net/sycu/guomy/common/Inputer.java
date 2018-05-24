package net.sycu.guomy.common;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Inputer {

	static public int inputInteger() {
		return inputInteger(Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	static public int inputInteger(int min, int max) {
		int i;
		Scanner scanner = new Scanner(System.in);

		try {
			// 取值范围验证
			do {
				i = scanner.nextInt();
				if (i < min || i > max) {
					System.out.printf("请输入%d-%d之间的整数：", min, max);
				}
			} while (i < min || i > max);
			return i;
		} catch (InputMismatchException ex) {
			// 格式异常验证
			System.out.println("输入格式错误，请重新输入：");
			return inputInteger(min, max);	// 尽量避免使用递归调用，不理解递归调用去玩九连环
		}
	}

	public static float inputFloat() {
		return inputFloat(Float.MIN_VALUE, Float.MAX_VALUE);
	}

	public static float inputFloat(float min, float max) {
		float f;
		Scanner scanner = new Scanner(System.in);

		try {
			// 取值范围验证
			do {
				f = scanner.nextInt();
				if (f < min || f > max) {
					System.out.printf("请输入%.2f-%.2f之间的实数：", min, max);
				}
			} while (f < min || f > max);
			return f;
		} catch (InputMismatchException ex) {
			// 格式异常验证
			System.out.println("输入格式错误，请重新输入：");
			return inputFloat(min, max);	// 尽量避免使用递归调用，不理解递归调用去玩九连环
		}
	}

	public static String inputString() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}
}










