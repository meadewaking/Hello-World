#include<stdio.h>
void main()
{
	int age = 0;
	printf("���������䣺");
	scanf_s("%d", &age);
	if (age>65||age<18)
	{
		printf("�������й�����\n");
	}
	else
	{
		printf("�����й�����\n");
	}
}