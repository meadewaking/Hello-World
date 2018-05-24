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
	
	private String name = "";			//�༶�����ֶ�
	
	public void setName(String name) {	//�༶����set������
		this.name = name;
	}

	static ArrayList<Student> students = new ArrayList<Student>();		//ʵ����student�෺��
	// static Student student = new Student(null, null);
	Inputer inputer = new Inputer();		//ʵ����Inputer��

	public void input() {
		System.out.println("������ѧ����Ϣ:");
		System.out.println("������Ҫ�����ѧ������:");
		int k;
		Scanner scanner = new Scanner(System.in);
		k = scanner.nextInt();
		for (int i = 0; i < k; i++) {
			Student student = new Student();
			student.input();
			students.add(student); // add�����൱��ָ���Ӷ��ÿ�β�����new����ֻ���¼���һ�μ�¼
		}

	}

	public void output() {
		System.out.println("ѧ��\t����\t�Ա�\t��ѧ\tӢ��\tJava\t�ܷ�\tƽ����");
		for (int i = 0; i < students.size(); i++) {				//��������������Ԫ��
			System.out.println(students.get(i).toString());		//����ѱ���д��tostring����
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
		System.out.println("������Ҫ�����λ��");
		index = inputer.inputInteger(1, students.size() + 1);
		Student student = new Student(null, null);			//��ʵ����student��ΪҪ�����Ԫ������ռ�
		student.input();
		students.add(index - 1, student);
		System.out.println("�Ѳ���һ�����ݣ�");
	}

	public void delete() {
		int index = 0;
		if (students.size() > 0) {
			System.out.println("������Ҫɾ����λ��");
			index = inputer.inputInteger(1, students.size());
			students.remove(index - 1);
			System.out.println("��ɾ��һ�����ݣ�");
		} else {
			System.out.println("��������");
		}
	}

	public void modify() {
		int index = 0;
		System.out.println("������Ҫ�޸ĵ�λ��");
		index = inputer.inputInteger(1, students.size());
		System.out.println(students.get(index - 1).toString());
		System.out.println("���޸ģ�");
		Student student = new Student();
		student.input();
		students.set(index - 1, student);
		System.out.println("���޸�һ�����ݣ�");
	}

	public void search() {
		int i = 0;
		i = searchByNum();			//���ð�ѧ�Ų��ҷ���,������λ��
		if (i >= 0) {
			System.out.println(students.get(i).toString());
		} else {
			System.out.println("����Ҫ���ҵ���Ϣ��");
		}

	}

	public int searchByNum() {
		System.out.println("������Ҫ��ѯ��ѧ�� ");
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
		Collections.sort(students, new StudentScoreComparator());		//����collection�ӿڵ����򷽷�,��Ϊ��ʵ�ֶ��ض��ֶ������½���һ�����compareto������д
		Collections.reverse(students);			//���õ��÷���
	}

	public void sortByName() {
		Collections.sort(students);		//��person�����Ѷ������Ƚϵ�compareto������д
	}

	public void sortByNum() {
		Collections.sort(students, new StudentNumberComparator());		//ͬ��������
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
		FileOutputStream fos = null;			//ʵ���ļ������
		ObjectOutputStream oos = null;			//ʵ�����������
		try {
			fos = new FileOutputStream("D:\\"+name+".mis");		//�򿪱����ļ�
			oos = new ObjectOutputStream(fos);				//���ļ������
			oos.writeObject(students);			//�������
		} catch (IOException e) {				//������������쳣
			// TODO: handle exception
			e.printStackTrace();			//��ӡ�쳣
		} finally {							//���մ���(finally�ؼ���һ���ᱻִ��)
			try {
				if (oos != null) {
					oos.close();
				}
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			try {							//�ر�������
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
		// String[] array = str.split("\t"); //�����ַ���
		// Student student = new Student(null,null);
		// student.setName(array[0]);
		// student.setNumber(array[1]);
		// for (int j = 0; j < student.getScore().length; j++) {
		// student.score[j] = Float.parseFloat(array[j+2]); //�ַ���ǿת������
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
			students = (ArrayList<Student>) ois.readObject();		//д�뷺�Ͷ���,����ͬ����
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
