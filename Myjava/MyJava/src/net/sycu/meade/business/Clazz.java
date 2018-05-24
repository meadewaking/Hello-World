package net.sycu.meade.business;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import net.sycu.meade.common.Inputer;
import net.sycu.meade.compare.StudentNumberComparator;
import net.sycu.meade.compare.StudentScoreComparator;
import net.sycu.meade.entity.Student;

public class Clazz {
	
	private String name = "";			//班级名称字段
	
	public void setName(String name) {	//班级名称set访问器
		this.name = name;
	}

	static ArrayList<Student> students = new ArrayList<Student>();		//实例化student类泛型
	// static Student student = new Student(null, null);
	Inputer inputer = new Inputer();		//实例化Inputer类

	public void input() {
		System.out.println("请输入学生信息:");
		System.out.println("请输入要输入的学生个数:");
		int k;
		Scanner scanner = new Scanner(System.in);
		k = scanner.nextInt();
		for (int i = 0; i < k; i++) {
			Student student = new Student();
			student.input();
			students.add(student); // add引用相当于指针调佣如每次不重新new对象只会记录最后一次记录
		}

	}

	public void output() {
		System.out.println("学号\t姓名\t性别\t数学\t英语\tJava\t总分\t平均分");
		for (int i = 0; i < students.size(); i++) {				//遍历泛型中所有元素
			System.out.println(students.get(i).toString());		//输出已被重写的tostring方法
		}
	}

//	public void outputSingle(int i) {
//		System.out.printf(students.get(i).toString());
//		for (int j = 0; j < students.get(i).getScore().length; j++) {
//			System.out.print(students.get(i).getScore()[j] + "\t");
//		}
//		System.out.print(students.get(i).sum() + "\t");
//		System.out.printf("%.2f\n", students.get(i).sum() / students.get(i).getScore().length);
//	}

	public void insert() {
		int index = 0;
		System.out.println("请输入要插入的位置");
		index = inputer.inputInteger(1, students.size() + 1);
		Student student = new Student(null, null);			//新实例化student类为要插入的元素申请空间
		student.input();
		students.add(index - 1, student);
		System.out.println("已插入一条数据！");
	}

	public void delete() {
		int index = 0;
		if (students.size() > 0) {
			System.out.println("请输入要删除的位置");
			index = inputer.inputInteger(1, students.size());
			students.remove(index - 1);
			System.out.println("已删除一条数据！");
		} else {
			System.out.println("暂无数据");
		}
	}

	public void modify() {
		int index = 0;
		System.out.println("请输入要修改的位置");
		index = inputer.inputInteger(1, students.size());
		System.out.println(students.get(index - 1).toString());
		System.out.println("请修改：");
		Student student = new Student();
		student.input();
		students.set(index - 1, student);
		System.out.println("已修改一条数据！");
	}

	public void search() {
		int i = 0;
		i = searchByNum();			//调用按学号查找方法,并返回位置
		if (i >= 0) {
			System.out.println(students.get(i).toString());
		} else {
			System.out.println("无所要查找的信息！");
		}

	}

	public int searchByNum() {
		System.out.println("请输入要查询的学号 ");
		String number = "";
		number = inputer.input();
		for (int i = 0; i < students.size(); i++) {
			if (number.compareTo(students.get(i).getNumber()) == 0) {
				return i;
			}
		}
		return -1;
	}

	public void sortBySum() {
		/*
		  ArrayList<Integer> numbers = new ArrayList<Integer>(); Random rand =
		  new Random(); for (int i = 0; i < 100; i++) {
		  numbers.add(rand.nextInt()%1000); } for (int temp : numbers) {
		  System.out.printf("%d\t",temp); } System.out.println();
		  Collections.sort(numbers); for (int temp : numbers) {
		  System.out.printf("%d\t",temp); } System.out.println();
		 */
		Collections.sort(students, new StudentScoreComparator());		//调用collection接口的排序方法,并为了实现对特定字段排序新建了一个类对compareto方法重写
		Collections.reverse(students);			//调用倒置方法
	}

	public void sortByName() {
		Collections.sort(students);		//在person类中已对姓名比较的compareto方法重写
	}

	public void sortByNum() {
		Collections.sort(students, new StudentNumberComparator());		//同分数排序
	}

	public void save() {
		// FileOutputStream fos = null;
		// OutputStreamWriter osw = null;
		// try {
		// fos = new FileOutputStream("D:\\students.mis");
		// osw = new OutputStreamWriter(fos,"UTF-8");
		//
		// for (int i = 0; i < students.size(); i++) {
		// osw.write(students.get(i).toString());
		// for (int j = 0; j < students.get(i).score.length; j++) {
		// osw.write(students.get(i).score[j] + "\t");
		// }
		// osw.write("\r\n");
		// }
		// } catch (IOException e) {
		// // TODO: handle exception
		// e.printStackTrace();
		// } finally {
		// try {
		// if (osw != null) {
		// osw.close();
		// }
		// } catch (IOException e) {
		// // TODO: handle exception
		// e.printStackTrace();
		// }
		// try {
		// if (fos != null) {
		// fos.close();
		// }
		// } catch (IOException e) {
		// // TODO: handle exception
		// e.printStackTrace();
		// }
		// }
		FileOutputStream fos = null;			//实例文件输出流
		ObjectOutputStream oos = null;			//实例对象输出流
		try {
			fos = new FileOutputStream("D:\\"+name+".mis");		//打开保存文件
			oos = new ObjectOutputStream(fos);				//打开文件输出流
			oos.writeObject(students);			//输出对象
		} catch (IOException e) {				//处理输入输出异常
			// TODO: handle exception
			e.printStackTrace();			//打印异常
		} finally {							//最终处理(finally关键字一定会被执行)
			try {
				if (oos != null) {
					oos.close();
				}
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			try {							//关闭两个流
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	public void load() {
		// FileInputStream fis = null;
		// BufferedReader br = null;
		// String str;
		// int k = 0;
		// try {
		// fis = new FileInputStream("D:\\student.mis");
		// br = new BufferedReader(br);
		// while (br != null) {
		// str = br.readLine();
		// String[] array = str.split("\t"); //分裂字符串
		// Student student = new Student(null,null);
		// student.setName(array[0]);
		// student.setNumber(array[1]);
		// for (int j = 0; j < student.getScore().length; j++) {
		// student.score[j] = Float.parseFloat(array[j+2]); //字符串强转浮点数
		// }
		// students.add(student);
		// }
		// } catch (IOException e) {
		// // TODO: handle exception
		// e.printStackTrace();
		// }
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream("D:\\"+name+".mis");
			ois = new ObjectInputStream(fis);
			students = (ArrayList<Student>) ois.readObject();		//写入泛型对象,其余同保存
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (ois != null)
					ois.close();
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			try {
				if (fis != null)
					fis.close();
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}
