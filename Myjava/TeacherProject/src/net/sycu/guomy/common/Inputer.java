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
			// ȡֵ��Χ��֤
			do {
				i = scanner.nextInt();
				if (i < min || i > max) {
					System.out.printf("������%d-%d֮���������", min, max);
				}
			} while (i < min || i > max);
			return i;
		} catch (InputMismatchException ex) {
			// ��ʽ�쳣��֤
			System.out.println("�����ʽ�������������룺");
			return inputInteger(min, max);	// ��������ʹ�õݹ���ã������ݹ����ȥ�������
		}
	}

	public static float inputFloat() {
		return inputFloat(Float.MIN_VALUE, Float.MAX_VALUE);
	}

	public static float inputFloat(float min, float max) {
		float f;
		Scanner scanner = new Scanner(System.in);

		try {
			// ȡֵ��Χ��֤
			do {
				f = scanner.nextInt();
				if (f < min || f > max) {
					System.out.printf("������%.2f-%.2f֮���ʵ����", min, max);
				}
			} while (f < min || f > max);
			return f;
		} catch (InputMismatchException ex) {
			// ��ʽ�쳣��֤
			System.out.println("�����ʽ�������������룺");
			return inputFloat(min, max);	// ��������ʹ�õݹ���ã������ݹ����ȥ�������
		}
	}

	public static String inputString() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}
}










