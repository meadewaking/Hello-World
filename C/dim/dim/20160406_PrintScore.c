#include<stdio.h>
#define row 30
#define col 5
void main()
{
	char ch = '0';
	int a[row][col];
	int i = 0, j = 0, sum = 0, sumcol[col] = { 0 };
	do
	{
		printf("1.����2.���3.����4.ɾ��5.�޸�6.����7.����8.����9.����0.�˳�\n");
		printf("������0~9��");
		scanf_s(" %c", &ch);
		switch (ch)
		{
		case'1':
			
			srand((unsigned)time(NULL));
			for ( i = 0; i < row; i++)
			{
				for ( j = 0; j < col; j++)
				{				
					a[i][j] = rand() % 101;
				}
			}
			
			break;
		case'2':
			for ( i = 0; i < row; i++)
			{
				sum = 0;
				for ( j = 0; j < col; j++)
				{
					printf("       %d\t", a[i][j]);
					sum += a[i][j];
					sumcol[j] += a[i][j];
				}
				printf("��ͳ��%d", sum);
				printf("\n");
			}
			for ( j = 0; j < col; j++)
			{
				printf("��ͳ��%d\t", sumcol[j]);
			}
		default:
			printf("����������");
			break;
		}
	} while (ch!='0');
}