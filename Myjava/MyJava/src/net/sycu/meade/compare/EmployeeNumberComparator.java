package net.sycu.meade.compare;

import java.util.Comparator;

import net.sycu.meade.entity.Employee;

public class EmployeeNumberComparator implements Comparator<Employee> {

	@Override
	public int compare(Employee o1, Employee o2) {
		return o1.getNumber().compareTo(o2.getNumber());		//��дcompare����,Ϊsort����������Ҫ�Ƚϵ��ض��ֶ�ֵ
	}

}
