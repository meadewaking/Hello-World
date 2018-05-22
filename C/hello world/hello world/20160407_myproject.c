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
	printf("输出正方形形\n");
	printf("请输入边长: ");
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
	printf("输出矩形\n");
	printf("1.请输入长\n");
	scanf("%d", &a);
	printf("2.请输入宽\n");
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
	printf("1.右斜平行四边形 2.左斜平行四边形 0.返回上级菜单\n");
	printf("请选择(0-2)");
	scanf(" %c", &choice2);
	switch (choice2)
	{
	case '1':
		printf("输出平行四边形，右斜\n");
		printf("请输入高：\n");
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
		printf("输出平行四边形，左斜\n");
		printf("请输入高：\n");
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
		printf("暂无该功能！\n");
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
	printf("输出直角三角形\n");
	printf("1.左下直角三角形 2.左上直角三角形 ");
	printf("3.右下直角三角形 4.右上直角三角形 0.返回\n");
	printf("请选择(0-4)：");
	scanf(" %c", &choice3);
	switch (choice3)
	{
	case'1':
		printf("输出左下直角三角形：\n");
		printf("请输入高：\n");
		scanf("%d", &h);
		for (j = 1; j <= h; j++)
		{
			for (i = 1; i <= j; i++)
				printf(" *");
			printf("\n");
		}
		break;

	case'2':
		printf("输出左上直角三角形：\n");
		printf("请输入高：\n");
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
		printf("输出右下直角三角形：\n");
		printf("请输入高：\n");
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
		printf("输出右下直角三角形：\n");
		printf("请输入高：\n");
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
		printf("暂无该功能！\n");
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
	printf("输出等腰三角形\n");
	printf("1.正置等腰三角形 2.倒置等腰三角形 0.返回\n");
	printf("请选择(0-2)");
	scanf(" %c", &choice4);
	switch (choice4)
	{
	case'1':
		printf("输出正置等腰三角形：\n");
		printf("请输入高：\n");
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
		printf("输出倒置等腰三角形：\n");
		printf("请输入高：\n");
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
		printf("暂无该功能！\n");
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
	printf("输出梯形\n");
	printf("1.向上直角梯形 2.向下直角梯形 3.反向上直角梯形");
	printf("4.反向下直角梯形 5.正置等腰梯形 6.倒置等腰梯形");
	printf("7.空心等腰梯形 0.返回\n");
	printf("请选择(0-7)");
	scanf(" %c", &choice5);
	switch (choice5)
	{
	case'1':
		printf("输出向上直角梯形\n");
		printf("请输入高：\n");
		scanf(" %d", &h);
		printf("请输入上底：\n");
		scanf(" %d", &a);
		for (j = 1; j <= h; j++)
		{
			for (i = 1; i <= j + a - 1; i++)
				printf(" *");
			printf(" \n");
		}
		break;

	case'2':
		printf("输出向下直角梯形\n");
		printf("请输入高：\n");
		scanf(" %d", &h);
		printf("请输入下底：\n");
		scanf(" %d", &a);
		for (j = 1; j <= h; j++)
		{
			for (i = 1; i <= h - j + a; i++)
				printf(" *");
			printf("\n");
		}
		break;

	case'3':
		printf("输出反向上直角梯形\n");
		printf("请输入高：\n");
		scanf("%d", &h);
		printf("请输入上底：\n");
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
		printf("输出反向下直角梯形\n");
		printf("请输入高：\n");
		scanf(" %d", &h);
		printf("请输入下底：\n");
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
		printf("输出正置等腰梯形\n");
		printf("请输入高：\n");
		scanf(" %d", &h);
		printf("请输入上底：\n");
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
		printf("输出倒置等腰梯形\n");
		printf("请输入高：\n");
		scanf(" %d", &h);
		printf("请输入下底：\n");
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
		printf("输出空心等腰梯形\n");
		printf("请输入梯形行数: ");
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
		printf("暂无该功能！\n");
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
	printf("1.实心菱形 2.空心菱形 0.返回\n");
	printf("请选择(0-2)：");
	scanf(" %c", &choice6);
	switch (choice6)
	{
	case'1':
		printf("请输入高的一半: ");
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
		printf("请输入高的一半: ");
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
	printf("输出风车\n");
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
	printf("输出圆形\n");
	printf("请输入半径（半径取值1-10）");
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
	
	printf("请以最大化窗口运行该程序！\n");

	do {
		printf("1.正方形 2.矩形 3.平行四边形 4.直角三角形 ");
		printf("5.等腰三角形 6.梯形 7.菱形 8.风车 9.圆形 -.正弦 0.退出\n");
		printf("请选择(0-9):");

		choice = choise('0', '9');

		switch (choice) {
		case '1':
			square();
			break;

		case '2':     //邵奎鑫
			juxing();
			break;

		case '3':     //邵奎鑫，王建
			pingxing();
			break;

		case'4':
			zhijiao();
			break;

		case'5':     //李念君
			dengyao();
			break;

		case'6':     //姚禹竹，李念君，董妍，高聪
			tixing();
			break;

		case'7':		//王子铭
			lingxing();
			break;

		case'8':
			fengche();
			break;

		case'9':     //王子铭
			yuan();
			break;

		case'-':     //姚禹竹
			siny();
			break;

		case'0':
			break;
		default:
			printf("暂无该功能！\n");
			break;
		}
	} while (choice != '0');
	printf("谢谢使用!\n");
}
char choise(char min, char max)
{
	char choice;	
	do
	{
		scanf_s(" %c", &choice);
		if (choice<min||choice>max)
		{
			printf("请重新输入\n");
		}
		else
		{
			break;
		}
	} while (1);
	return choice;
}

