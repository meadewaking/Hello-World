package net.sycu.guomy.business;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import net.sycu.guomy.common.Inputer;
import net.sycu.guomy.entity.*;

public class School {

	ArrayList<Employee> employee = new ArrayList<Employee>();

	public void input() {
		Employee employee = choiseEmployee();
		employee.input();
		
		this.employee.add(employee);
	}
	
	public Employee choiseEmployee() {
		int choice = 0;
		System.out.println("1.������ 2.��ʦ 3.��ѯʦ 4.Сʱ��");
		System.out.print("��ѡ��");
		choice = Inputer.inputInteger(1, 4);
		switch (choice) {
		case 1:
			return new Manager();
		case 2:
			return new Teacher();
		case 3:
			return new Saler();
		case 4:
			return new HourlyWorker();
		}
		return null;
	}
	
	public void output() {
		System.out.println("����\t����\t�Ա�\t����\t");
		for (int i = 0; i < employee.size(); i++) {
			System.out.println(employee.get(i).toString());
		}
	}
	
	public void add() {
		System.out.print("������Ҫ�����λ�ã�");
		int index = Inputer.inputInteger(1, employee.size() + 1);	// 
		
		Employee employee = choiseEmployee();
		employee.input();
		
		this.employee.add(index - 1, employee);	// 
	}

	public void remove() {
		if (employee.size() > 0) {
			System.out.print("������Ҫɾ����λ�ã�");
			int index = Inputer.inputInteger(1, employee.size());	// 
			employee.remove(index - 1);
			System.out.println("ɾ����1�����ݡ�");
		}
		else {
			System.out.println("�������ݣ�����ɾ����");
		}
	}
	
	public void modify() {
		Employee employee = null;
		
		System.out.print("������Ҫ�޸ĵĹ��ţ�");
		String number = Inputer.inputString();

		employee = findByNumber(number);
		
		if (employee != null) {
			System.out.println(employee.toString());
			System.out.println("���޸ģ�");
			employee.input();
			System.out.println("�޸���ɡ�");
		}
		else {
			System.out.println("���޴��ˣ������޸ġ�");
		}
	}

	public void findByNumber() {
		Employee employee = null;
		
		System.out.print("������Ҫ��ѯ�Ĺ��ţ�");
		String number = Inputer.inputString();
		
		employee = findByNumber(number);
		if (employee != null) {
			System.out.println(employee.toString());
		}
		else {
			System.out.println("���޴��ˡ�");
		}
	}
	
	public void findByName() {
		Employee employee = null;
		
		System.out.print("������Ҫ��ѯ��������");
		String name = Inputer.inputString();
		
		employee = findByName(name);
		if (employee != null) {
			System.out.println(employee.toString());
		}
		else {
			System.out.println("���޴��ˡ�");
		}
	}

	private Employee findByNumber(String number) {
		for (int i = 0; i < employee.size(); i++) {
			if (employee.get(i).getNumber().compareTo(number) == 0) {
				return employee.get(i);
			}
		}
		return null;
	}
	
	private Employee findByName(String name) {
		for (int i = 0; i < employee.size(); i++) {
			if (employee.get(i).getName().compareTo(name) == 0) {
				return employee.get(i);
			}
		}
		return null;
	}

	public void sortByName() {
		Collections.sort(employee);
	}
	
	public void sortByNumber() {
		Collections.sort(employee, new EmployeeNumberComparator());
	}
	
	public void sortBySalary() {
		Collections.sort(employee, new EmployeeSalaryComparator());
		Collections.reverse(employee);
	}
	
	public void save() {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try {
			fos = new FileOutputStream("D:\\school.mis");
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject(employee);
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
	}
	
	public void load() {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try {
			fis = new FileInputStream("D:\\school.mis");
			ois = new ObjectInputStream(fis);
			
			employee = (ArrayList<Employee>)ois.readObject();
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
	}


}
