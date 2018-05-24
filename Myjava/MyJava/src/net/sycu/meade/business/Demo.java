package net.sycu.meade.business;

import java.util.Scanner;

import net.sycu.meade.entity.Student;
import net.sycu.meade.entity.Teacher;
import net.sycu.meade.exception.SexException;

public class Demo {

	/**
	 * @param args
	 */
	public void main(String[] args)  {//主函数是程序运行的入口；包含主函数的类被称为主类；主类可以作为启动类。
		Student student = new Student("meade", "男", "123"); // 声明一个学生类的引用，并引出一个学生类
		//student.input();
		//student.learn();
		//student.play();

		Teacher teacher = new Teacher("guomy", "男", "001");
		//Teacher teacher2 = new Teacher("002", "jjj", "女", 8000);//不能直接调用，必须同new关键字调用，new完成了申请内存并返回对象；
		//teacher.input();
		//teacher.work();
		//teacher.rest();
		//teacher.teach();

		/*Scanner scanner = new Scanner(System.in);
		Person person = null;
		int i;
		System.out.println("请输入选项：");
		i = scanner.nextInt();
		switch (i) {

		case 1:
			person = new Student("meade", "nan");
			break;
		case 2:
			person = new Student("tom", "nv");
			break;
		case 3:
			person = new Teacher("guomy", "nan");
			break;
		}
		person.sayhello();*/
		
		String str1 = "lzjsjfn";
		System.out.println(str1.hashCode()+":"+str1);
	}

}
