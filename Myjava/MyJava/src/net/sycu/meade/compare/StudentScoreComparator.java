package net.sycu.meade.compare;

import java.util.Comparator;

import net.sycu.meade.entity.Student;

public class StudentScoreComparator implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {
		if (o1.sum()>o2.sum()) {
			return 1;
		} else if(o1.sum()<o2.sum()){
			return -1;					//基本原理同其他比较类
		}
		else
			return 0;
	}

}
