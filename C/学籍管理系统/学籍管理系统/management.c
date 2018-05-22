#include<stdio.h> 
#include<stdlib.h> 
#include<conio.h> 
#include<string.h> 
struct student {
	int id;
	char name[10];
	int age;
	char sex[10];
	char birthady[20];
	int tel;
	char nativeplace[50];
}st[50];
int i = 0;
void shuru() {
	char a;
	do {
		printf("\n请输入学号:");
		scanf_s("%d", &st[i].id);
		fflush(stdin);
		printf("\n请输入姓名:");
		gets(st[i].name);
		printf("\n");
		printf("请输入年龄:");
		scanf_s("%d", &st[i].age);
		fflush(stdin);
		printf("\n请输入姓别:");
		gets(st[i].sex);
		fflush(stdin);
		printf("\n请输入生日:");
		gets(st[i].birthady);
		printf("\n请输入电话:");
		scanf_s("%d", &st[i].tel);
		fflush(stdin);
		printf("\n请输入籍贯:");
		gets(st[i].nativeplace);
		printf("\n是否继续输入另外一个学生信息?(y/n)");
		fflush(stdin);
		a = getchar();
		i++;
	} while (a == 'y' && i <= 50);

}

void xianshi()
{
	int j;
	printf("\t学号\t姓名\t年龄\t性别\t生日\t\t电话\t籍贯\n");
	for (j = 0; j<i; j++)
		printf("\t%d\t%s\t%d\t%s\t%s\t%d\t%s\n",
			st[j].id, st[j].name, st[j].age, st[j].sex, st[j].birthady, st[j].tel, st[j].nativeplace);
}

void paixu() //按年龄从大到小排序函数 
{
	int j, k;
	int temp;
	for (j = 0; j<i; j++)
	{
		for (k = 0; k<i - 1 - j; k++)
		{
			if (st[k].age<st[k + 1].age)
			{
				temp = st[k].age;
				st[k].age = st[k + 1].age;
				st[k + 1].age = temp;
			}
		}
	}
	xianshi(); //排序后输出 
}

void chazhao()
{
	int m;
	char name[20], b;
	do
	{
		printf("\n请输入想查找的学生姓名:");
		fflush(stdin);
		gets(name);
		for (m = 0; m<i; m++)
		{
			if (strcmp(name, st[m].name) == 0)
			{
				printf("\n\t您查找的学生在第%d个位置找到了!!!\n", m + 1);
				break;
			}
		}
		if (m >= 20)
			printf("\n\t没有找到这个学生!!!\n");
		else
		{
			printf("\t学号\t姓名\t年龄\t性别\t生日\t\t电话\t籍贯\n");
			printf("\t%d\t%s\t%d\t%s\t%s\t%d\t%s\n",
				st[m].id, st[m].name, st[m].age, st[m].sex, st[m].birthady, st[m].tel, st[m].nativeplace);
		}
		printf("\n是否查找另一个学生的信息?(y/n)");
		fflush(stdin);
		b = getchar();

	} while (b == 'y');

}

void shanchu()
{
	char name[20], c;
	int a, b;
	do
	{
		printf("\n请输入要删除的学生姓名:\n");
		fflush(stdin);
		gets(name);
		for (a = 0; a<i; a++)
		{
			if (strcmp(name, st[a].name) == 0)
				break;
		}

		for (b = a; b<i; b++)
			st[b] = st[b + 1];
		if (a>i)
			printf("\t没有找到这个学生!!!\n");

		else
		{
			i--;
			xianshi();
		}

		printf("\n是否继续删除另一个学生信息?(y/n) ");
		fflush(stdin);
		c = getchar();
	} while (c == 'y');

}

void charu()
{
	shuru();
	paixu();

}

void main() //主函数 
{
	int change;
	do {
		system("cls");
		printf("============================学生信息管理系统===================================\n");
		printf("\t\t一： 输入学生信息\n");
		printf("\t\t二： 显示学生信息\n");
		printf("\t\t三： 查找学生信息\n");
		printf("\t\t四： 删除学生信息\n");
		printf("\t\t五： 插入学生信息\n");
		printf("\t\t六： 退出程序\n");
		fflush(stdin);
		printf("\t\t请输入功能选项：");
		scanf_s("%d", &change);
		switch (change)
		{
		case 1:
			shuru(); break;
		case 2:
			xianshi(); break;
		case 3:
			chazhao(); break;
		case 4:
			shanchu(); break;
		case 5:
			charu(); break;
		case 6:
			break;
		}
		_getch();
	} while (change != 6);
}