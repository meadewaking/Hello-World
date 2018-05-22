#include<stdio.h>
#include<math.h>
void main()
{
	/*
	int h = 0, i = 0, j = 0;
	for (j = 0; j <= 9; j++)
	{
		for (i = 0; i <= j; i++)
		{
			printf("*");
		}
		for (h = 9; h >= j; h--)
		{
			printf(" ");
		}
		printf("\n");
	}*/		//左下	
	/*
	int d = 0, e = 0, f = 0;
	for (f = 0; f <= 9; f++)
	{
		for (d = 9; d >= f; d--)
		{
			printf("*");
		}
		for (e = 0; e <= f; e++)
		{
			printf(" ");
		}
		printf("\n");
	}
	*/		//左上
	/*
	int m = 0, n = 0, o = 0;
	for (o = 0; o <= 9; o++)
	{
			for (n = 0; n <=o; n++)
			{
				printf(" ");
			}
			for (m = 9; m >= o; m--)
			{
				printf("*");
			}
		printf("\n");
	}*/		//右上
	/*
	int p = 0, q = 0, r = 0;
	for (r = 0; r <= 9; r++)
	{
		for (p = 9; p >= r; p--)
		{
			printf(" ");
		}
		for (q = 0; q <= r; q++)
		{
			printf("*");
		}
		printf("\n");
	}
	*/		//右下
	/*
	int h = 0, i = 0, j = 0;
	int d = 0, e = 0, f = 0;
	int m = 0, n = 0, o = 0;
	int p = 0, q = 0, r = 0;
	int up = 0, down = 0;
	for (up = 0; up < 9; up++)
	{
		for (i = 0; i <= up; i++)
		{
			printf("* ");
		}
		for (h = 9-2; h >= up; h--)
		{
			printf("  ");
		}
		for (d = 9; d > up; d--)
		{
			printf("* ");
		}
		for (e = 0; e < up; e++)
		{
			printf("  ");
		}
		printf("\n");
	}
	for ( down = 0; down < 9; down++)
	{
		for (p = 9-2; p >= down; p--)
		{
			printf("  ");
		}
		for (q = 0; q <= down; q++)
		{
			printf("* ");
		}
		for (n = 0; n < down; n++)
		{
			printf("  ");
		}
		for (m = 9; m > down; m--)
		{
			printf("* ");
		}
		printf("\n");
	}
	*/		//风车
	/*
	int h = 0, i = 0, j = 0;
	int d = 0, e = 0, f = 0;
	int m = 0, n = 0, o = 0;
	int p = 0, q = 0, r = 0;
	int up = 0, down = 0;
	for (r = 0; r < 9; r++)
	{
		for (p = 9; p >= r; p--)
		{
			printf(" ");
		}
		for (q = 0; q <= r; q++)
		{
			printf("* ");
		}
		printf("\n");
	}										//右下
	for (o = 0; o <= 9; o++)
	{
		for (n = 0; n <= o; n++)
		{
			printf(" ");
		}
		for (m = 9; m >= o; m--)
		{
			printf("* ");
		}
		printf("\n");
	}										//右上
	*/		//菱形
	/*
	int p = 0, q = 0, r = 0;
	int m = 0, n = 0, o = 0;
	for (r = 0; r <= 9; r++)
	{
		for (p = 9; p >= r; p--)
		{
			printf(" ");
		}
		for (q = 0; q <= r; q++)
		{
			if (q==0||q==r)
			{
				printf(" *");
			}
			else
			{
				printf("  ");
			}
		}
		printf("\n");
	}
	for (o = 1; o <= 9; o++)
	{
		for (n = 0; n <= o; n++)
		{
			printf(" ");
		}
		for (m = 9; m >= o; m--)
		{
			if (m==9||m==o)
			{
				printf(" *");
			}
			else
			{
				printf("  ");
			}
		}
		printf("\n");
	}
	*/		//空心菱形
	
	int cir1[100], i = 0, j = 0, cir2[100];
	for ( i = 100; i > 0; i--)
	{
		for ( j = 0; j < 100; j++)
		{
			cir1[j] = sqrt(600 - ((j-50)*(j-50)))+50;
			cir2[j] = -sqrt(600 - ((j - 50)*(j - 50))) + 55;
			if (i==cir1[j])
			{
				printf("S ");
			}
			else if (i==cir2[j])
			{
				printf("S ");
			}
			else
			{
				printf("  ");
			}
		}
		printf("\n");
	}
			//圆形
}