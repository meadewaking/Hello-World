package net.sycu.meade.business;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import net.sycu.meade.common.Inputer;
import net.sycu.meade.compare.EmployeeNumberComparator;
import net.sycu.meade.compare.EmployeeSalaryComparator;
import net.sycu.meade.entity.Employee;
import net.sycu.meade.entity.HourlyWorker;
import net.sycu.meade.entity.Manager;
import net.sycu.meade.entity.Saler;
import net.sycu.meade.entity.Teacher;

public class Company {

	static ArrayList<Employee> employee = new ArrayList<Employee>();
	Inputer inputer = new Inputer();

	public void input() {
		Employee employee = choiseEmployee();
		employee.input();
		
		this.employee.add(employee);	//因元素名与泛型名相同所以用this区分,其余基本同Clazz类
	}
	
	public Employee choiseEmployee() {
		int choice = 0;
		System.out.println("1.管理者 2.教师 3.咨询师 4.小时工");
		System.out.print("请选择：");
		choice = inputer.inputInteger(1, 4);
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
		System.out.println("工号\t工种\t姓名\t性别\t薪水\t");
		for (int i = 0; i < employee.size(); i++) {
			System.out.println(employee.get(i).toString());
		}
	}
	
	public void insert() {
		System.out.print("请输入要插入的位置：");
		int index = inputer.inputInteger(1, employee.size() + 1);	// 
		
		Employee employee = choiseEmployee();
		employee.input();
		
		this.employee.add(index - 1, employee);	// 
	}

	public void delete() {
		if (employee.size() > 0) {
			System.out.print("请输入要删除的位置：");
			int index = inputer.inputInteger(1, employee.size());	// 
			employee.remove(index - 1);
			System.out.println("删除了1条数据。");
		}
		else {
			System.out.println("暂无数据，不能删除。");
		}
	}
	
	public void modify() {
		Employee employee = null;
		
		System.out.print("请输入要修改的工号：");
		String number = inputer.input();

		employee = findByNumber(number);
		
		if (employee != null) {
			System.out.println(employee.toString());
			System.out.println("请修改：");
			employee.input();
			System.out.println("修改完成。");
		}
		else {
			System.out.println("查无此人，不能修改。");
		}
	}
	public void search() {
		System.out.println("请选择查找方式");
	}
	public void findByNumber() {
		Employee employee = null;
		
		System.out.print("请输入要查询的工号：");
		String number = inputer.input();
		
		employee = findByNumber(number);
		if (employee != null) {
			System.out.println(employee.toString());
		}
		else {
			System.out.println("查无此人。");
		}
	}
	
	public void findByName() {
		Employee employee = null;
		
		System.out.print("请输入要查询的姓名：");
		String name = inputer.input();
		
		employee = findByName(name);
		if (employee != null) {
			System.out.println(employee.toString());
		}
		else {
			System.out.println("查无此人。");
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
	
	public void sortByNum() {
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
			fos = new FileOutputStream("D:\\Company.mis");
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
			fis = new FileInputStream("D:\\Company.mis");
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
