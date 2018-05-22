#include<stdio.h>
void main()
{
	while (1)
	{
		int y;
		printf("请输入年份：");
		scanf_s("%d", &y);

		if (y % 4 != 0)
		{
			printf("平年\n");
		}
		else if (y % 100 != 0)
		{
			printf("闰年\n");
		}
		else if (y % 400 != 0)
		{
			printf("平年\n");
		}
		else
		{
			printf("闰年\n");
		}
	}

}