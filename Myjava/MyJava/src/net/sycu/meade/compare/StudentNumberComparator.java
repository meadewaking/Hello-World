package net.sycu.meade.compare;

import java.util.Comparator;

import net.sycu.meade.entity.Student;

public class StudentNumberComparator implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {
		return o1.getNumber().compareTo(o2.getNumber());		//基本原理同其他比较类
	}

}
