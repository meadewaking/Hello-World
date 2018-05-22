#include<stdio.h>
#include<math.h>
void main()
{
	int cases = 0, w = 0, h = 0, p = 0, n = 0, i = 0, j = 0, s = 0, sum = 0;
	int a[10];
	printf("请输入测试个数：");
	scanf_s("%d", &cases);
	for (j = 0; j < cases; j++)
	{
		do
		{
			printf("请输入N,P,W,H:");
			scanf_s("%d %d %d %d", &n, &p, &w, &h);
			printf("请输入每段字数：");
			for (i = 0; i < n; i++)
			{
				scanf_s("%d", &a[i]);
			}

			if (n >= 1 && n <= 1000 && w >= 1 && w <= 1000 && h >= 1 && h <= 1000 && p >= 1 && p <= 1e+6)
			{
				break;
			}
			else
			{
				printf("超过输出范围，请重新输入！");
			}
		} while (1);
		for (i = 0; i < n; i++)
		{
			sum += a[i];
		}
		s = sqrt((p*h*w) / sum);
		printf("可设置的最大字体为%d\n", s);
	}
}