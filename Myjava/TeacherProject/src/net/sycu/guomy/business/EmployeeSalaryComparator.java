package net.sycu.guomy.business;

import java.util.Comparator;

import net.sycu.guomy.entity.Employee;

public class EmployeeSalaryComparator implements Comparator<Employee> {

	@Override
	public int compare(Employee o1, Employee o2) {
		if (o1.getSalary() > o2.getSalary())
			return 1;
		else if (o1.getSalary() < o2.getSalary())
			return -1;
		else
			return 0;
	}

}
