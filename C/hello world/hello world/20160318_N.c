#include<stdio.h>
void main()
{
	while (1)
	{
		long int num, i, sum;
		printf("请输入你想求阶乘累加的数：");
		scanf_s("%d", &num);
		i = 1;
		sum = 1;
		while (i <= num)
		{
			sum *= i;
			i = i++;
		}
		printf("阶乘是%d\n", sum);
		i = 0;
		sum = 0;
		while (i <= num)
		{
			sum += i;
			i = i++;
		}
		printf("累加是%d\n", sum);
	}
}