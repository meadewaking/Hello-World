#include<stdio.h>
#define size 50
void main()
{
	int m = 0, n = 0, i = 0;
	int array[size] = { 0 };
	int num = 0;
	
	printf("请输入人数：");
	scanf_s(" %d", &m);
	printf("请输入数到几：");
	scanf_s(" %d", &n);
	//让m个人入座
	for ( i = 0; i < m; i++)
	{
		array[i] = 1;	//对应为1有人0没人
	}

	num = 0;
	for ( i = 0; m > 0; i++)
	{
		if (i==size)
		{
			i = 0;			//成圈
		}
		if (1==array[i])
		{
			num++;
			if (num==n)
			{
				printf("%d\t", i + 1);	//输出座位号
				array[i] = 0;
				num = 0;
				m--;
			}		
		}
		
	}

}