package net.sycu.guomy.business;

import java.util.Comparator;

import net.sycu.guomy.entity.Student;

public class StudentScoreComparator implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {
		if (o1.sum() > o2.sum())
			return 1;
		else if (o1.sum() < o2.sum())
			return -1;
		else
			return 0;
	}

}
