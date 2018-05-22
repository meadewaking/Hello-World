#include<stdio.h>
#include<stdlib.h>
#include<conio.h>
#include<string.h>
void main()
{
	int ch = 0;
	printf("     学生学籍管理系统\n");
	printf("1.输入\n");
	printf("2.显示\n");
	printf("3.删除\n");
	scanf_s("%d", &ch);
	switch (ch)
	{
	case 1:
		shuru(); break;
	case 2:
		xianshi(); break;
	case 3:
		shanchu(); break;
	}
	_getch();
}
	void shuru()
	{

	}
	void xianshi()
	{

	}
	void shanchu()
	{

	}
