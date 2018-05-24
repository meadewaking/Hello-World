package net.sycu.guomy.business;

import java.util.Comparator;

import net.sycu.guomy.entity.Student;

public class StudentNumberComparator implements Comparator<Student> {
	@Override
	public int compare(Student o1, Student o2) {
		return o1.getNumber().compareTo(o2.getNumber());
	}
}
