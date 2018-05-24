package net.sycu.meade.common;

import java.util.InputMismatchException;
import java.util.Scanner;

import net.sycu.meade.exception.SexException;

public class Inputer {

	public float inputFloat() {			//���븡����,�繤�����ʺͳɼ�
		float score = '0';
		Scanner scanner = new Scanner(System.in);
		try {
			score = scanner.nextFloat();
			if (score < 0) {
				System.out.println("����������������룺");
				return inputFloat();
			}
		} catch (InputMismatchException ex) {
			System.out.println("�����ʽ����,���������룺");
			return inputFloat();
			// TODO: handle exception
		}
		return score;
	}

	public String input() {			//�����ַ���
		String string = "";
		Scanner scanner = new Scanner(System.in);
		string = scanner.nextLine();
		return string;
	}

	public int inputInteger(int min, int max) {		//����������
		int integer = '0';
		Scanner scanner = new Scanner(System.in);
		try {
			integer = scanner.nextInt();
			if (integer < min || integer > max) {
				System.out.println("����������������룺");
				return inputInteger(min, max);
			}
		} catch (InputMismatchException ex) {
			System.out.println("�����ʽ����,���������룺");
			return inputInteger(min, max);
			// TODO: handle exception
		}
		return integer;
	}

	public String inputSex() {			//�����Ա�,Ĭ����
		String string = "��";
		Scanner scanner = new Scanner(System.in);
		string = scanner.nextLine();
		if ((string.equals("��") || string.equals("Ů")) == true) {
			return string;
		}
		else
		{
			throw new SexException();
		}
	}

	public float inputScore(float min, float max) {		//����ɼ����Զ���ɼ���Χ
		float Score = '0';
		Scanner scanner = new Scanner(System.in);
		try {
			Score = scanner.nextInt();
			if (Score < min || Score > max) {
				System.out.println("�Ƿ��ɼ������������룺");
				return inputScore(min, max);
			}
		} catch (InputMismatchException ex) {
			System.out.println("�����ʽ����,���������룺");
			return inputScore(min, max);
			// TODO: handle exception
		}
		return Score;
	}
}
