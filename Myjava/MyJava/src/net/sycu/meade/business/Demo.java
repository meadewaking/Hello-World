package net.sycu.meade.business;

import java.util.Scanner;

import net.sycu.meade.entity.Student;
import net.sycu.meade.entity.Teacher;
import net.sycu.meade.exception.SexException;

public class Demo {

	/**
	 * @param args
	 */
	public void main(String[] args)  {//�������ǳ������е���ڣ��������������౻��Ϊ���ࣻ���������Ϊ�����ࡣ
		Student student = new Student("meade", "��", "123"); // ����һ��ѧ��������ã�������һ��ѧ����
		//student.input();
		//student.learn();
		//student.play();

		Teacher teacher = new Teacher("guomy", "��", "001");
		//Teacher teacher2 = new Teacher("002", "jjj", "Ů", 8000);//����ֱ�ӵ��ã�����ͬnew�ؼ��ֵ��ã�new����������ڴ沢���ض���
		//teacher.input();
		//teacher.work();
		//teacher.rest();
		//teacher.teach();

		/*Scanner scanner = new Scanner(System.in);
		Person person = null;
		int i;
		System.out.println("������ѡ�");
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
