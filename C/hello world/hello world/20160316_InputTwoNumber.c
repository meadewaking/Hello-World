#include<stdio.h>
void main()
{
	int i = 0, j = 0, sum = 0;		//声明第一个数，第二个数，和的变量

	printf("求解任意两整数和\n");	//输入提示
	printf("请输入第一个数：");		//输入提示
	scanf_s("%d", &i);				//输入第一个数
	printf("请输入第二个数：");		//输入提示
	scanf_s("%d", &j);				//输入第二个数

	sum = i + j;					//求和

	printf("%d+%d=%d\n",i,j, sum);	//输出结果
}