package net.sycu.meade.common;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.InputMismatchException;
import java.util.Scanner;
import net.sycu.meade.exception.SexException;


public class Inputer {

	public float inputFloat() {			//输入浮点数,如工资利率和成绩
		float score = '0';
		Scanner scanner = new Scanner(System.in);
		try {
			score = scanner.nextFloat();		//最早被用来输入成绩
			if (score < 0) {
				System.out.println("输入错误，请重新输入：");
				return inputFloat();		//避免一次输入错误程序就终止,所以采用迭代
			}
		} catch (InputMismatchException ex) {	//捕捉输入异常
			System.out.println("输入格式错误,请重新输入：");
			return inputFloat();	//同上处理
			// TODO: handle exception
		}
		return score;		//返回浮点数
	}

	public String input() {			//输入字符串
		String string = "";
		Scanner scanner = new Scanner(System.in);
		string = scanner.nextLine();
		return string;		//输入返回字符串,不做任何异常处理
	}

	public int inputInteger(int min, int max) {		//输入整形数
		int integer = '0';
		Scanner scanner = new Scanner(System.in);
		try {
			integer = scanner.nextInt();
			if (integer < min || integer > max) {		//判断输入是否越界
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

	public int inputChoice(int min, int max) {		//输入整形数
		System.out.println("请选择：");
		int integer = '0';
		Scanner scanner = new Scanner(System.in);
		try {
			integer = scanner.nextInt();
			if (integer < min || integer > max) {
				System.out.println("输入错误，请重新输入：");		//功能完全可以由inputinteger代替,为oop遗留
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
			return string;		//输入性别,默认为男,暂未在jdbc中使用
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
				System.out.println("非法成绩，请重新输入：");	//设定上下限的inputfloat暂未在jdbc中使用
				return inputScore(min, max);
			}
		} catch (InputMismatchException ex) {
			System.out.println("输入格式错误,请重新输入：");
			return inputScore(min, max);
			// TODO: handle exception
		}
		return Score;
	}
	
	public int inputInteger(){
		return inputInteger(Integer.MIN_VALUE,Integer.MAX_VALUE);	//不确定边界的整形数输入,传入int型的最大值和最小值
	}
	
	public boolean inputBoolean(){		//输入布尔值
		System.out.println("请输入“是”或“否”：");
		boolean state = false;
		String buffer = "";
		Scanner scanner = new Scanner(System.in);
		try {
			buffer = scanner.nextLine();
			if (buffer.equals("是") == true) {		//布尔型值转换成用户可以理解的自然语言
				state = true;
			}
			else if (buffer.equals("否") == true) {
				state = false;
			}
			else {
				return inputBoolean();
			}
			//state = Boolean.valueOf(scanner.nextLine());
		} catch (InputMismatchException e) {
			// TODO Auto-generated catch block
			System.out.println("输入格式错误,请重新输入：");
			e.printStackTrace();
			return inputBoolean();
		}
		return state;
	}
	
	public Date inputDate(){
		Date date;
		Scanner scanner = new Scanner(System.in);
		date = Date.valueOf(scanner.nextLine());		//输入日期型数据
		return date;
	}
	
	public Timestamp inputTimestamp(){
		Timestamp timestamp;
		Scanner scanner = new Scanner(System.in);
		timestamp = Timestamp.valueOf(scanner.nextLine());		//输入时间戳
		return timestamp;
	}
}
