package net.sycu.meade.common;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.InputMismatchException;
import java.util.Scanner;
import net.sycu.meade.exception.SexException;


public class Inputer {

	public float inputFloat() {			//���븡����,�繤�����ʺͳɼ�
		float score = '0';
		Scanner scanner = new Scanner(System.in);
		try {
			score = scanner.nextFloat();		//���类��������ɼ�
			if (score < 0) {
				System.out.println("����������������룺");
				return inputFloat();		//����һ���������������ֹ,���Բ��õ���
			}
		} catch (InputMismatchException ex) {	//��׽�����쳣
			System.out.println("�����ʽ����,���������룺");
			return inputFloat();	//ͬ�ϴ���
			// TODO: handle exception
		}
		return score;		//���ظ�����
	}

	public String input() {			//�����ַ���
		String string = "";
		Scanner scanner = new Scanner(System.in);
		string = scanner.nextLine();
		return string;		//���뷵���ַ���,�����κ��쳣����
	}

	public int inputInteger(int min, int max) {		//����������
		int integer = '0';
		Scanner scanner = new Scanner(System.in);
		try {
			integer = scanner.nextInt();
			if (integer < min || integer > max) {		//�ж������Ƿ�Խ��
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

	public int inputChoice(int min, int max) {		//����������
		System.out.println("��ѡ��");
		int integer = '0';
		Scanner scanner = new Scanner(System.in);
		try {
			integer = scanner.nextInt();
			if (integer < min || integer > max) {
				System.out.println("����������������룺");		//������ȫ������inputinteger����,Ϊoop����
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
			return string;		//�����Ա�,Ĭ��Ϊ��,��δ��jdbc��ʹ��
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
				System.out.println("�Ƿ��ɼ������������룺");	//�趨�����޵�inputfloat��δ��jdbc��ʹ��
				return inputScore(min, max);
			}
		} catch (InputMismatchException ex) {
			System.out.println("�����ʽ����,���������룺");
			return inputScore(min, max);
			// TODO: handle exception
		}
		return Score;
	}
	
	public int inputInteger(){
		return inputInteger(Integer.MIN_VALUE,Integer.MAX_VALUE);	//��ȷ���߽������������,����int�͵����ֵ����Сֵ
	}
	
	public boolean inputBoolean(){		//���벼��ֵ
		System.out.println("�����롰�ǡ��򡰷񡱣�");
		boolean state = false;
		String buffer = "";
		Scanner scanner = new Scanner(System.in);
		try {
			buffer = scanner.nextLine();
			if (buffer.equals("��") == true) {		//������ֵת�����û�����������Ȼ����
				state = true;
			}
			else if (buffer.equals("��") == true) {
				state = false;
			}
			else {
				return inputBoolean();
			}
			//state = Boolean.valueOf(scanner.nextLine());
		} catch (InputMismatchException e) {
			// TODO Auto-generated catch block
			System.out.println("�����ʽ����,���������룺");
			e.printStackTrace();
			return inputBoolean();
		}
		return state;
	}
	
	public Date inputDate(){
		Date date;
		Scanner scanner = new Scanner(System.in);
		date = Date.valueOf(scanner.nextLine());		//��������������
		return date;
	}
	
	public Timestamp inputTimestamp(){
		Timestamp timestamp;
		Scanner scanner = new Scanner(System.in);
		timestamp = Timestamp.valueOf(scanner.nextLine());		//����ʱ���
		return timestamp;
	}
}
