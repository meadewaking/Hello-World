#include "student.h"
#include<iostream>
				//string类型在头文件中无法声明
using namespace std;


student::student(string a, float b, float c, float d)
{
	name = a;
	math = b;
	physics = c;
	average = d;
}

void student::Display()
{
	cout << "姓名：" << name << endl;
	cout << "数学：" << math << endl;
	cout << "物理：" << physics << endl;
	cout << "平均：" << average << endl;
}

void main() {
	student stu1;
	student stu2;
	stu2 = stu1;
	stu2.Display();
}
