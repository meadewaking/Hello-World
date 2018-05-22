#include<stdio.h>
void main()
/*
if (year % 4 == 0)
{
	if (year % 100 == 0)
	{
		if (year % 400 == 0)
		{
			printf("闰年");
		}
		else
		{
			printf("平年");
		}
	}
	else
	{
		printf("闰年");
	}
}
else
{
	printf("平年");
}
*/
	
{
	while (1)		//便于测试
	{
		int y;		//声明年份变量
		printf("请输入年份：");		//输入提示
		scanf_s("%d", &y);			//输入年份

		if (y % 4 != 0)				//判定是否整除4
		{
			printf("平年\n");		//输出结果
		}
		else if (y % 100 != 0)		//判定是否整除100
		{
			printf("闰年\n");		//输出结果
		}
		else if (y % 400 != 0)		//判定是否整除400
		{
			printf("平年\n");		//输出结果
		}
		else					
		{
			printf("闰年\n");		//输出结果
		}
	}

}