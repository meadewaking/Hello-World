#define PAI 3.14159
#include <stdio.h>
#include <math.h>
#pragma warning(disable:4996)
char choise(char min, char max);
void square()
{
	int a = 0;
	int i = 0;
	int j = 0;
	printf("�����������\n");
	printf("������߳�: ");
	scanf(" %d", &a);
	for (i = 1; i <= a; i++)
	{
		for (j = 1; j <= a; j++)
			printf(" *");
		printf(" \n");
	}
}
void juxing()
{
	
	int a = 0;
	int b = 0;
	int i = 0;
	int j = 0;
	printf("�������\n");
	printf("1.�����볤\n");
	scanf("%d", &a);
	printf("2.�������\n");
	scanf("%d", &b);
	for (i = 1; i <= b; i++)
	{
		for (j = 1; j <= a; j++)
			printf(" *");
		printf("\n");
	}
}
void pingxing()
{
	char choice2 = '0';
	int a = 0;
	int i = 0;
	int j = 0;
	printf("1.��бƽ���ı��� 2.��бƽ���ı��� 0.�����ϼ��˵�\n");
	printf("��ѡ��(0-2)");
	scanf(" %c", &choice2);
	switch (choice2)
	{
	case '1':
		printf("���ƽ���ı��Σ���б\n");
		printf("������ߣ�\n");
		scanf(" %d", &a);
		for (i = 1; i <= a; i++)
		{
			for (j = 1; j <= i - 1; j++)
				printf(" ");
			for (j = 1; j <= a; j++)
				printf(" *");
			printf("\n");
		}
		break;
	case '2':
		printf("���ƽ���ı��Σ���б\n");
		printf("������ߣ�\n");
		scanf(" %d", &a);
		for (i = 1; i <= a; i++)
		{
			for (j = 1; j <= a - i; j++)
				printf(" ");
			for (j = 1; j <= a; j++)
				printf(" *");
			printf("\n");
		}
		break;
	case '0':
		break;
	default:
		printf("���޸ù��ܣ�\n");
		break;
	}
}
void zhijiao()
{		
	char choice3 = '0';
	int a = 0;
	int h = 0;
	int i = 0;
	int j = 0;
	printf("���ֱ��������\n");
	printf("1.����ֱ�������� 2.����ֱ�������� ");
	printf("3.����ֱ�������� 4.����ֱ�������� 0.����\n");
	printf("��ѡ��(0-4)��");
	scanf(" %c", &choice3);
	switch (choice3)
	{
	case'1':
		printf("�������ֱ�������Σ�\n");
		printf("������ߣ�\n");
		scanf("%d", &h);
		for (j = 1; j <= h; j++)
		{
			for (i = 1; i <= j; i++)
				printf(" *");
			printf("\n");
		}
		break;

	case'2':
		printf("�������ֱ�������Σ�\n");
		printf("������ߣ�\n");
		scanf("%d", &h);
		for (j = 1; j <= h; j++)
		{
			a = h + 1;
			for (i = 1; i <= a - j; i++)
				printf(" *");
			printf(" \n");
		}
		break;

	case'3':
		printf("�������ֱ�������Σ�\n");
		printf("������ߣ�\n");
		scanf(" %d", &h);
		for (j = 0; j <= h; j++)
		{
			for (a = h; a >= j; a--)
			{
				printf(" ");
			}
			for (i = 0; i <= j; i++)
			{
				printf("*");
			}
			printf("\n");
		}
		break;

	case'4':
		printf("�������ֱ�������Σ�\n");
		printf("������ߣ�\n");
		scanf(" %d", &h);
		for (j = 0; j <= h; j++)
		{
			for (a = 0; a <= j; a++)
			{
				printf(" ");
			}
			for (i = h; i >= j; i--)
			{
				printf("*");
			}
			printf("\n");
		}
		break;

	case '0':
		break;

	default:
		printf("���޸ù��ܣ�\n");
		break;
	}
}
void dengyao()
{
	char choice4 = '0';
	int step = 0;
	int h = 0;
	int i = 0;
	int j = 0;
	printf("�������������\n");
	printf("1.���õ��������� 2.���õ��������� 0.����\n");
	printf("��ѡ��(0-2)");
	scanf(" %c", &choice4);
	switch (choice4)
	{
	case'1':
		printf("������õ��������Σ�\n");
		printf("������ߣ�\n");
		scanf(" %d", &h);
		for (j = 1; j <= h; j++)
		{
			for (i = 1; i <= h - j; i++)
				printf(" ");
			for (i = 1; i <= j * 2 - 1; i++)
				printf("*");
			if (i == h)
				step = -step;
			printf(" \n");
		}
		break;

	case'2':
		printf("������õ��������Σ�\n");
		printf("������ߣ�\n");
		scanf(" %d", &h);

		for (j = 1; j <= h; j++)
		{
			for (i = 1; i <= j - 1; i++)
				printf(" ");
			for (i = 1; i <= (h - j + 1) * 2 - 1; i++)
				printf("*");
			if (i == h)
				step = -step;
			printf(" \n");
		}
		break;

	case '0':
		break;
	default:
		printf("���޸ù��ܣ�\n");
		break;
	}
}
void tixing()
{
	char choice5 = '0';
	int step = 0;
	int a = 0;
	int h = 0;
	int i = 0;
	int j = 0;
	int m = 0;
	int	n = 0;
	printf("�������\n");
	printf("1.����ֱ������ 2.����ֱ������ 3.������ֱ������");
	printf("4.������ֱ������ 5.���õ������� 6.���õ�������");
	printf("7.���ĵ������� 0.����\n");
	printf("��ѡ��(0-7)");
	scanf(" %c", &choice5);
	switch (choice5)
	{
	case'1':
		printf("�������ֱ������\n");
		printf("������ߣ�\n");
		scanf(" %d", &h);
		printf("�������ϵף�\n");
		scanf(" %d", &a);
		for (j = 1; j <= h; j++)
		{
			for (i = 1; i <= j + a - 1; i++)
				printf(" *");
			printf(" \n");
		}
		break;

	case'2':
		printf("�������ֱ������\n");
		printf("������ߣ�\n");
		scanf(" %d", &h);
		printf("�������µף�\n");
		scanf(" %d", &a);
		for (j = 1; j <= h; j++)
		{
			for (i = 1; i <= h - j + a; i++)
				printf(" *");
			printf("\n");
		}
		break;

	case'3':
		printf("���������ֱ������\n");
		printf("������ߣ�\n");
		scanf("%d", &h);
		printf("�������ϵף�\n");
		scanf("%d", &a);
		for (j = 1; j <= h; j++)
		{
			for (i = 1; i <= h - j; i++)
				printf(" ");
			for (i = 1; i <= j + a - 1; i++)
				printf(" *");
			printf("\n");
		}
		break;

	case'4':
		printf("���������ֱ������\n");
		printf("������ߣ�\n");
		scanf(" %d", &h);
		printf("�������µף�\n");
		scanf(" %d", &a);
		for (j = 1; j <= h; j++)
		{
			for (i = 1; i <= j - 1; i++)
				printf(" ");
			for (i = 1; i <= h - j + a; i++)
				printf(" *");
			printf(" \n");
		}
		break;

	case'5':
		printf("������õ�������\n");
		printf("������ߣ�\n");
		scanf(" %d", &h);
		printf("�������ϵף�\n");
		scanf(" %d", &a);
		for (j = 1; j <= h; j++)
		{
			for (i = 1; i <= h - j; i++)
				printf(" ");
			for (i = 1; i <= (j - 1) * 2 + a; i++)
				printf(" *");
			if (i == h)
				step = -step;
			printf(" \n");
		}
		break;

	case'6':
		printf("������õ�������\n");
		printf("������ߣ�\n");
		scanf(" %d", &h);
		printf("�������µף�\n");
		scanf(" %d", &a);
		for (j = 1; j <= h; j++)
		{
			for (i = 1; i <= j - 1; i++)
				printf(" ");
			for (i = 1; i <= (h - j) * 2 + a; i++)
				printf("*");
			if (i == h)
				step = -step;
			printf("\n");
		}
		break;

	case'7':
		printf("������ĵ�������\n");
		printf("��������������: ");
		scanf(" %d", &n);
		for (i = 1; i <= n; i++)
		{
			for (j = 1; j <= 2 * n + i - 3; j++)
				if (j == n - i + 1 || j > n - i + 1 && (i == 1 || i == n))
					printf("*");
				else
					printf(" ");
			printf("*\n");
		}
		break;

	case '0':
		break;
	default:
		printf("���޸ù��ܣ�\n");
		break;
	}
}
void lingxing()
{
	char choice6 = '0';
	int a = 0;
	int m = 0;
	int	n = 0;
	int	o = 0;
	int p = 0;
	int	q = 0;
	int	r = 0;
	printf("1.ʵ������ 2.�������� 0.����\n");
	printf("��ѡ��(0-2)��");
	scanf(" %c", &choice6);
	switch (choice6)
	{
	case'1':
		printf("������ߵ�һ��: ");
		scanf(" %d", &a);
		for (r = 0; r < a; r++)
		{
			for (p = a; p >= r; p--)
			{
				printf(" ");
			}
			for (q = 0; q <= r; q++)
			{
				printf("* ");
			}
			printf("\n");
		}
		for (o = 0; o <= a; o++)
		{
			for (n = 0; n <= o; n++)
			{
				printf(" ");
			}
			for (m = a; m >= o; m--)
			{
				printf("* ");
			}
			printf("\n");
		}
		break;

	case'2':
		printf("������ߵ�һ��: ");
		scanf(" %d", &a);
		for (r = 0; r <= a; r++)
		{
			for (p = a; p >= r; p--)
			{
				printf("  ");
			}
			for (q = 0; q <= r; q++)
			{
				if (q == 0 || q == r)
					printf("   *");
				else
					printf("    ");
			}
			printf("\n");
		}
		for (o = 1; o <= a; o++)
		{
			for (n = 0; n <= o; n++)
			{
				printf("  ");
			}
			for (m = a; m >= o; m--)
			{
				if (m == a || m == o)
					printf("   *");
				else
					printf("    ");
			}
			printf("\n");
		}
		break;
	default:
		break;
	}
}
void fengche()
{
	int i = 0;
	int j = 0;
	printf("����糵\n");
	for (j = 1; j <= 9; j++)
	{
		for (i = 1; i <= j; i++)
			printf(" *");
		for (i = 1; i <= 9 - j; i++)
			printf("  ");
		for (i = 1; i <= 10 - j; i++)
			printf(" *");
		printf(" \n");
	}
	for (j = 1; j <= 9; j++)
	{
		for (i = 1; i <= 9 - j; i++)
			printf("  ");
		for (i = 1; i <= j; i++)
			printf(" *");
		for (i = 1; i <= j - 1; i++)
			printf("  ");
		for (i = 1; i <= 10 - j; i++)
			printf(" *");
		printf(" \n");
	}
}
void yuan()
{
	int m = 0;
	int	r = 0;
	double y;
	int x;
	printf("���Բ��\n");
	printf("������뾶���뾶ȡֵ1-10��");
	scanf(" %d", &r);
	for (y = r; y >= -r; y--)
	{
		m = 2.5 * sqrt(r*r - y * y);
		for (x = 1; x < 30 - m; x++)
			printf(" ");
		printf("*");
		for (; x < 30 + m; x++)
			printf(" ");
		printf("*\n");
	}
}
void siny()
{
	int i = 0;
	int x;
	int yy;
	double y;
	for (i = 1; i < 80; i++)
		if (i == 40)
			printf("*");
		else
			printf("-");
	printf("\n");
	for (y = 10.0; y <= 360.0; y += 10.)
	{
		x = 40 + 30 * sin(y * PAI / 180.0);
		yy = 40 > x ? 40 : x;
		for (i = 1; i <= yy; i++)
		{
			if (i == x)
				printf("*");
			else if (i == 40)
				printf("|");
			else
				printf(" ");
		}
		printf("\n");
	}
}
void main()
{
	char choice = '0';
	
	printf("������󻯴������иó���\n");

	do {
		printf("1.������ 2.���� 3.ƽ���ı��� 4.ֱ�������� ");
		printf("5.���������� 6.���� 7.���� 8.�糵 9.Բ�� -.���� 0.�˳�\n");
		printf("��ѡ��(0-9):");

		choice = choise('0', '9');

		switch (choice) {
		case '1':
			square();
			break;

		case '2':     //�ۿ���
			juxing();
			break;

		case '3':     //�ۿ��Σ�����
			pingxing();
			break;

		case'4':
			zhijiao();
			break;

		case'5':     //�����
			dengyao();
			break;

		case'6':     //Ҧ������������������ߴ�
			tixing();
			break;

		case'7':		//������
			lingxing();
			break;

		case'8':
			fengche();
			break;

		case'9':     //������
			yuan();
			break;

		case'-':     //Ҧ����
			siny();
			break;

		case'0':
			break;
		default:
			printf("���޸ù��ܣ�\n");
			break;
		}
	} while (choice != '0');
	printf("ллʹ��!\n");
}
char choise(char min, char max)
{
	char choice;	
	do
	{
		scanf_s(" %c", &choice);
		if (choice<min||choice>max)
		{
			printf("����������\n");
		}
		else
		{
			break;
		}
	} while (1);
	return choice;
}

