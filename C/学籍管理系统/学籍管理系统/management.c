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
		printf("\n������ѧ��:");
		scanf_s("%d", &st[i].id);
		fflush(stdin);
		printf("\n����������:");
		gets(st[i].name);
		printf("\n");
		printf("����������:");
		scanf_s("%d", &st[i].age);
		fflush(stdin);
		printf("\n�������ձ�:");
		gets(st[i].sex);
		fflush(stdin);
		printf("\n����������:");
		gets(st[i].birthady);
		printf("\n������绰:");
		scanf_s("%d", &st[i].tel);
		fflush(stdin);
		printf("\n�����뼮��:");
		gets(st[i].nativeplace);
		printf("\n�Ƿ������������һ��ѧ����Ϣ?(y/n)");
		fflush(stdin);
		a = getchar();
		i++;
	} while (a == 'y' && i <= 50);

}

void xianshi()
{
	int j;
	printf("\tѧ��\t����\t����\t�Ա�\t����\t\t�绰\t����\n");
	for (j = 0; j<i; j++)
		printf("\t%d\t%s\t%d\t%s\t%s\t%d\t%s\n",
			st[j].id, st[j].name, st[j].age, st[j].sex, st[j].birthady, st[j].tel, st[j].nativeplace);
}

void paixu() //������Ӵ�С������ 
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
	xianshi(); //�������� 
}

void chazhao()
{
	int m;
	char name[20], b;
	do
	{
		printf("\n����������ҵ�ѧ������:");
		fflush(stdin);
		gets(name);
		for (m = 0; m<i; m++)
		{
			if (strcmp(name, st[m].name) == 0)
			{
				printf("\n\t�����ҵ�ѧ���ڵ�%d��λ���ҵ���!!!\n", m + 1);
				break;
			}
		}
		if (m >= 20)
			printf("\n\tû���ҵ����ѧ��!!!\n");
		else
		{
			printf("\tѧ��\t����\t����\t�Ա�\t����\t\t�绰\t����\n");
			printf("\t%d\t%s\t%d\t%s\t%s\t%d\t%s\n",
				st[m].id, st[m].name, st[m].age, st[m].sex, st[m].birthady, st[m].tel, st[m].nativeplace);
		}
		printf("\n�Ƿ������һ��ѧ������Ϣ?(y/n)");
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
		printf("\n������Ҫɾ����ѧ������:\n");
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
			printf("\tû���ҵ����ѧ��!!!\n");

		else
		{
			i--;
			xianshi();
		}

		printf("\n�Ƿ����ɾ����һ��ѧ����Ϣ?(y/n) ");
		fflush(stdin);
		c = getchar();
	} while (c == 'y');

}

void charu()
{
	shuru();
	paixu();

}

void main() //������ 
{
	int change;
	do {
		system("cls");
		printf("============================ѧ����Ϣ����ϵͳ===================================\n");
		printf("\t\tһ�� ����ѧ����Ϣ\n");
		printf("\t\t���� ��ʾѧ����Ϣ\n");
		printf("\t\t���� ����ѧ����Ϣ\n");
		printf("\t\t�ģ� ɾ��ѧ����Ϣ\n");
		printf("\t\t�壺 ����ѧ����Ϣ\n");
		printf("\t\t���� �˳�����\n");
		fflush(stdin);
		printf("\t\t�����빦��ѡ�");
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