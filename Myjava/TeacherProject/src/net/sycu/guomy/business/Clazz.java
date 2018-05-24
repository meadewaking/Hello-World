package net.sycu.guomy.business;

import java.io.*;
import java.util.*;

import net.sycu.guomy.common.Inputer;
import net.sycu.guomy.entity.Student;

public class Clazz {
	
	private String name = "";
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	ArrayList<Student> students = new ArrayList<Student>();

	public void input() {
		Student student = new Student();
		student.input();
		
		students.add(student);
	}
	
	public void output() {
		System.out.println("ѧ��\t����\t�Ա�\t����\t��ѧ\tӢ��\tJava\tDB\t�ܷ�\tƽ����\t");
		for (int i = 0; i < students.size(); i++) {
			System.out.println(students.get(i).toString());
		}
	}
	
	public void add() {
		System.out.print("������Ҫ�����λ�ã�");
		int index = Inputer.inputInteger(1, students.size() + 1);	// 
		
		Student student = new Student();
		student.input();
		
		students.add(index - 1, student);	// 
	}

	public void remove() {
		if (students.size() > 0) {
			System.out.print("������Ҫɾ����λ�ã�");
			int index = Inputer.inputInteger(1, students.size());	// 
			students.remove(index - 1);
			System.out.println("ɾ����1�����ݡ�");
		}
		else {
			System.out.println("�������ݣ�����ɾ����");
		}
	}
	
	public void modify() {
		Student student = null;
		
		System.out.print("������Ҫ�޸ĵ�ѧ�ţ�");
		String number = Inputer.inputString();

		student = findByNumber(number);
		
		if (student != null) {
			System.out.println(student.toString());
			System.out.println("���޸ģ�");
			student.input();
			System.out.println("�޸���ɡ�");
		}
		else {
			System.out.println("���޴��ˣ������޸ġ�");
		}
	}

	public void findByNumber() {
		Student student = null;
		
		System.out.print("������Ҫ��ѯ��ѧ�ţ�");
		String number = Inputer.inputString();
		
		student = findByNumber(number);
		if (student != null) {
			System.out.println(student.toString());
		}
		else {
			System.out.println("���޴��ˡ�");
		}
	}
	
	public void findByName() {
		Student student = null;
		
		System.out.print("������Ҫ��ѯ��������");
		String name = Inputer.inputString();
		
		student = findByName(name);
		if (student != null) {
			System.out.println(student.toString());
		}
		else {
			System.out.println("���޴��ˡ�");
		}
	}

	private Student findByNumber(String number) {
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getNumber().compareTo(number) == 0) {
				return students.get(i);
			}
		}
		return null;
	}
	
	private Student findByName(String name) {
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getName().compareTo(name) == 0) {
				return students.get(i);
			}
		}
		return null;
	}

	public void sortByName() {
		Collections.sort(students);
	}
	
	public void sortByNumber() {
		Collections.sort(students, new StudentNumberComparator());
	}
	
	public void sortByScore() {
		Collections.sort(students, new StudentScoreComparator());
		Collections.reverse(students);
	}
	
	public void save() {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try {
			fos = new FileOutputStream("D:\\"+name+".mis");
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject(students);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (oos != null)
					oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
//		FileOutputStream fos = null;
//		OutputStreamWriter osw = null;
//		
//		try {
//			fos = new FileOutputStream("D:\\students.mis");
//			osw = new OutputStreamWriter(fos, "UTF-8");
//			
//			for (int i = 0; i < students.size(); i++) {
//				osw.write(students.get(i).toString() + "\r\n");
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			try {
//				if (osw != null)
//					osw.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			try {
//				if (fos != null)
//					fos.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}
	
	public void load() {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try {
			fis = new FileInputStream("D:\\"+name+".mis");
			ois = new ObjectInputStream(fis);
			
			students = (ArrayList<Student>)ois.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (ois != null)
					ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (fis != null)
					fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
//		FileInputStream fis = null;
//		InputStreamReader isr = null;
//		BufferedReader br = null;
//		try {
//			fis = new FileInputStream("D:\\students.mis");
//			isr = new InputStreamReader(fis, "UTF-8");
//			br = new BufferedReader(isr);
//			students.clear();
//			int count = 0;
//			for (count = 0;  ; count++) {
//				String str = br.readLine();
//				if (str == null || str.compareTo("") == 0)
//					break;
//				String [] array = str.split("\t");
//				Student student = new Student();
//				student.setNumber(array[0]);
//				student.setName(array[1]);
//				student.setSex(array[2]);
//				for (int i = 0; i < student.getScore().length; i++) {
//					student.getScore()[i] = Float.parseFloat(array[3 + i]);
//				}
//				students.add(student);
//			}
//			System.out.printf("�ɹ�����%d�����ݡ�\n", count);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			try {
//				if (br != null)
//					br.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			try {
//				if (isr != null)
//					isr.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			try {
//				if (fis != null)
//					fis.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}

}
