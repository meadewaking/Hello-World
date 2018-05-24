package net.sycu.guomy.business;

import java.util.Comparator;

import net.sycu.guomy.entity.Employee;

public class EmployeeNumberComparator implements Comparator<Employee> {
	@Override
	public int compare(Employee o1, Employee o2) {
		return o1.getNumber().compareTo(o2.getNumber());
	}
}
