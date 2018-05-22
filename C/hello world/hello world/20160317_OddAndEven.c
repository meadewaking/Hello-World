#include<stdio.h>
void main()
{
	int num = 0;	//声明初始变量num
	printf("请输入一个数：");	//输入提示
	scanf_s("%d", &num);		//输入值
	if (num%2==0)			//判断是否整除2
	{
		printf("%d是偶数\n", num);		//输出结果
	}
	else
	{
		printf("%d是奇数\n", num);		//输出结果
	}
}