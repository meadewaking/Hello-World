package net.sycu.meade.compare;

import java.util.Comparator;

import net.sycu.meade.entity.Employee;


public class EmployeeSalaryComparator implements Comparator<Employee>{

	@Override
	public int compare(Employee o1, Employee o2) {
		if (o1.calculateSalary() > o2.calculateSalary())
			return 1;
		else if (o1.calculateSalary() < o2.calculateSalary())		//����ԭ��ͬ�����Ƚ���
			return -1;
		else
			return 0;
	}

}
