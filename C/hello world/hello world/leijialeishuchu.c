#include<stdio.h>
void main()
{
	
	int num = 0, i = 0, sum = 0;
	printf("请输入一个大于5的数：");
	scanf_s("%d", &num);
	/*
	while (i <= num)
	{
		sum += i;
		i = i++;
	}
	i = 1;
	while (i<=num-1)
	{
		printf("%d+", i);
		i = i++;
	}
	printf("%d=%d\n",num, sum);
	*/
	for ( i = 0; i <= num; i++)
	{
		sum += i;
	}
	for ( i = 1; i < num; i++)
	{
		printf("%d+", i);
	}
	printf("%d=%d\n", num, sum);
}