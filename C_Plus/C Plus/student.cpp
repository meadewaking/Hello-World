#include "student.h"
#include<iostream>
				//string������ͷ�ļ����޷�����
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
	cout << "������" << name << endl;
	cout << "��ѧ��" << math << endl;
	cout << "����" << physics << endl;
	cout << "ƽ����" << average << endl;
}

void main() {
	student stu1;
	student stu2;
	stu2 = stu1;
	stu2.Display();
}
