#include<stdio.h>
void main()
{
	int age = 0;
	printf("请输入年龄：");
	scanf_s("%d", &age);
	if (age>65||age<18)
	{
		printf("不符合招工条件\n");
	}
	else
	{
		printf("符合招工条件\n");
	}
}