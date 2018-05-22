#include<stdio.h>
#include<math.h>
void main()
{
	int num = 0, n = 2, flag = 1, i = 2,count=0;
	/*while (1)
	{
		do
		{
			printf("请输入任意一个大于3的数：");
			scanf_s("%d", &num);
			if (num>3)
			{
				break;
			}
			printf("数据违法，请重新输入。");
		} while (num<=3);
		while (n < num)
		{
			if (num%n == 0)
			{
				printf("这是一个合数\n");
				break;
			}
			n = n + 1;
		}
		if (n==num)
		{
			printf("这是一个素数\n");
		}
	}*/
	/*printf("打印小于此数的所有质数：");
	scanf_s("%d", &num);
	while (i < num)
	{
		for (n = 2; n < i;n++)
		{
			if (i%n == 0)
			{
				flag = 0;
				break;
			}
			else
			{
				flag = 1;
			}
		}
		if (flag)
		{
			printf("%d\t", i);
			count++;
		}
		i++;
	}*/
	while (count < 300000)
	{
		for (n = 2; n < sqrt(i); n++)	//brilliant！！！
		{
			if (i%n == 0)
			{
				flag = 0;
				break;		//使得合数条件下减少循环次数
			}
			else
			{
				flag = 1;
			}
		}
		if (flag)
		{
			printf("%d\t", i);
			count++;
		}
		i++;
	}
}
	